package com.etraveli.service;

import com.etraveli.dao.ExceededTemperatureViewDao;
import com.etraveli.dao.PreferenceDao;
import com.etraveli.dao.ProviderDao;
import com.etraveli.dao.TemperatureDao;
import com.etraveli.dao.impl.JdbcExceededTemperatureViewDaoImpl;
import com.etraveli.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
@Lazy(false)
public class WeatherSystemImpl implements WeatherSystem{

    Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private PreferenceDao preferenceDao;

    @Autowired
    private TemperatureDao temperatureDao;

    @Autowired
    private ExceededTemperatureViewDao exceededTempV;

    static boolean  isMock = true;
    public  WeatherResponseDTO getCurrentWeatherDetails(String city, String state, String uri){
        if(!isMock) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<WeatherResponseDTO> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(uri, HttpMethod.GET, entity, WeatherResponseDTO.class).getBody();
        }
        return getMockWeatherReport(city, state);
    }

    private WeatherResponseDTO getMockWeatherReport(String city, String state){
        WeatherResponseDTO weatherDto = new WeatherResponseDTO();
        weatherDto.weather="A Few Clouds";
        weatherDto.city=city;
        weatherDto.code="Success";
        weatherDto.credits="499999978";
        weatherDto.relativeHumidity="57";
        weatherDto.state=state;
        weatherDto.visibilityMiles="10.00";
        weatherDto.tempC="22.2";
        weatherDto.tempF="72.0";
        weatherDto.windDir="West";
        weatherDto.windMPH="17.3";
        return weatherDto;
    }

    @Scheduled(fixedRate = 1000) //Every Ten seconds it fetches data from servers and checks if any violation has happened.
    public void weatherReportSystem(){
        List<Provider> providers = providerDao.findAll();
        Map<Long, Provider> providerMap = providers.stream()
                .collect(Collectors.toMap(provider -> provider.getProviderId(), Function.identity()));
        List<Preference> allPreferenceLocations = preferenceDao.findAllLocations();
        allPreferenceLocations.forEach(location -> {
            providers.forEach(provider -> {
                WeatherResponseDTO currentWeatherDetails = getCurrentWeatherDetails(location.getCity(), location.getState(), provider.getURI());
                temperatureDao.upsert(mapToTemperature(currentWeatherDetails, provider.getProviderId()));
            });
        });
        notifyUsers();
    }

    private void notifyUsers() {
        List<ExceededTemperatureUserView> userViewList = exceededTempV.listAll();
        // The above view contains logic to aggregate the temperature from different providers like IMD etc
        // It also contains logic to filter only details about users whose chosen
        // preferences are low and needs to be notified
        // Please refer schema.sql, View Name : EXCEEDED_TEMP_V
        userViewList.forEach(user -> {
            print(user);
            preferenceDao.updateLastNotified(user.getCity(), user.getState(), user.getUserId());
        });
    }

    private void print(ExceededTemperatureUserView view){
        logger.info("User with the ID will be notified [USER_ID]: "+view.getUserId());
        logger.info("Threshold set by the user : " + view.getThreshold());
        logger.info("Current temperature : " + view.getTempC());
        if(view.getIsSmsActive().equals("Y")){
            logger.info("SMS sent to the user with ID : " + view.getUserId());
        }
        if(view.getIsMailActive().equals("Y")){
            logger.info("Mail sent to the user with ID : " + view.getUserId());
        }
        if(view.getIsAppNotifyActive().equals("Y")){
            logger.info("App notification sent to the user with ID : " + view.getUserId());
        }
    }

    private Temperature mapToTemperature(WeatherResponseDTO weatherResponseDTO, Long providerId){
        Temperature temperature = new Temperature();
        temperature.setCode(weatherResponseDTO.code);
        temperature.setCity(weatherResponseDTO.city);
        temperature.setCredits(weatherResponseDTO.credits);
        temperature.setState(weatherResponseDTO.state);
        temperature.setVisibilityMiles(weatherResponseDTO.visibilityMiles);
        temperature.setWindMph(weatherResponseDTO.windMPH);
        temperature.setTempC((int)Double.parseDouble(weatherResponseDTO.tempC));
        temperature.setTempF((int)Double.parseDouble(weatherResponseDTO.tempF));
        temperature.setRelativeHumidity(weatherResponseDTO.relativeHumidity);
        temperature.setWindDir(weatherResponseDTO.windDir);
        temperature.setProviderId(providerId);
        return temperature;
    }

}
