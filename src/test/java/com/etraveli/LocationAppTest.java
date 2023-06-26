package com.etraveli;

import com.etraveli.config.WebAppConfig;
import com.etraveli.config.WebAppInitializer;
import com.etraveli.controller.preference.PreferenceCreateController;
import com.etraveli.controller.preference.PreferenceDeleteController;
import com.etraveli.controller.preference.PreferenceListController;
import com.etraveli.controller.preference.PreferenceUpdateController;
import com.etraveli.controller.user.UserCreateController;
import com.etraveli.controller.user.UserDeleteController;
import com.etraveli.controller.user.UserListController;
import com.etraveli.controller.user.UserUpdateController;
import com.etraveli.dao.*;
import com.etraveli.dao.impl.*;
import com.etraveli.model.*;
import com.etraveli.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
@Transactional
@Rollback(value = true)
public class LocationAppTest {

    @Autowired
    private UserCreateController userCreateController;

    @Autowired
    private UserDeleteController userDeleteController;

    @Autowired
    private UserListController userListController;
    private MockMvc mockMvc;
    @Autowired
    private PreferenceDao preferenceDao;
    @Autowired
    private ExceededTemperatureViewDao excessTempDao;
    @Autowired
    private ProviderDao providerDao;
    @Autowired
    private TemperatureDao temperatureDao;
    @Autowired
    private WeatherSystem weatherSystem;

    @Autowired
    private UserUpdateController updateController;

    @Autowired
    private PreferenceDeleteController preferenceDeleteController;

    @Autowired
    private PreferenceUpdateController preferenceUpdateController;

    @Autowired
    private PreferenceListController preferenceListController;

    @Autowired
    private PreferenceCreateController preferenceCreateController;
    @Configuration
    static class ContextConfiguration {

        @Bean
        public PreferenceUpdateController preferenceUpdateController(){
            return new PreferenceUpdateController();
        }

        @Bean
        public PreferenceListController preferenceListController(){
            return new PreferenceListController();
        }

        @Bean
        public PreferenceCreateController preferenceCreateController(){
            return new PreferenceCreateController();
        }

        @Bean
        public PreferenceDeleteController preferenceDeleteController(){
            return new PreferenceDeleteController();
        }
        @Bean
        public UserUpdateController updateController(){
            return new UserUpdateController();
        }

        @Bean
        public UserListController userListController(){
            return new UserListController();
        }

        @Bean
        public UserDeleteController userDeleteController(){
            return new UserDeleteController();
        }

        @Bean
        public UserCreateController userCreateController(){
            return new UserCreateController();
        }
        @Bean
        public WeatherSystem weatherSystem(){
            return new WeatherSystemImpl();
        }
        @Bean
        public ExceededTemperatureViewDao excessTempDao(){
            return new JdbcExceededTemperatureViewDaoImpl();
        }

        @Bean
        public ProviderDao providerDao(){
            return new JdbcProviderDao();
        }

        @Bean
        public TemperatureDao temperatureDao(){
            return new JdbcTemperatureDao();
        }
        @Bean
        public PreferenceService preferenceService(){
            return new PreferenceServiceImpl();
        }
        @Bean
        public PreferenceDao preferenceDao(){
            return new JdbcPreferenceDao();
        }
        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }

        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
        @Bean
        public UserDao userDao(){
            return new JdbcUserDao();
        }
        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:/schema.sql", "classpath:/data.sql").build();
        }

        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
        @Bean
        public RestTemplate getRestTemplate() {
            return new RestTemplate();
        }

    }

    @Autowired
    private UserService userService;

    @Test
    public void findUserTest(){
        User byId = userService.findById(1l);
        Assert.assertNotNull(byId);
    }

    @Test
    public void findAllUserTest(){
        List<User> users = userService.findAll();
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void createUserTest(){
        User user = new User();
        user.setId(2l);
        user.setFirstName("test");
        user.setLastName("user");
        List<User> allusers = userService.findAll();
        Assert.assertEquals(1, allusers.size());
        userService.create(user);
        List<User> allusers1 = userService.findAll();
        Assert.assertEquals(2, allusers1.size());
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("user");
        userService.update(user);
        User updatedUser = userService.findById(user.getId());
        Assert.assertEquals("test", updatedUser.getFirstName());
        Assert.assertEquals("user", updatedUser.getLastName());
    }

    @Test
    public void createPreferenceTest(){
        Preference preference = getPreference();
        List<Preference> allPreferences = preferenceDao.findAll();
        Assert.assertEquals(1, allPreferences.size());
        preferenceDao.create(preference);
        allPreferences = preferenceDao.findAll();
        Assert.assertEquals(2, allPreferences.size());
    }
    @Test
    public void updatePreferenceTest(){
        Preference preference = getPreference();
        preference.setCity("San Francisco");
        preference.setState("CA");
        preference.setNotifyCycleMins(20);
        preferenceDao.update(preference);
        Preference updatedPref = preferenceDao.findById(preference);
        Assert.assertEquals(20, updatedPref.getNotifyCycleMins());
    }

    @Test
    public void findAllPreferenceTest(){
        List<Preference> preferences = preferenceDao.findAll();
        Assert.assertEquals(1, preferences.size());
    }
    @Test
    public void findAllLocationsTest(){
        List<Preference> allLocations = preferenceDao.findAllLocations();
        Assert.assertEquals(1, allLocations.size());
    }
    @Test

    public void updateLastNotifiedTest(){
        Preference preference = getPreference();
        preference.setCity("San Francisco");
        preference.setState("CA");
        Preference pref = preferenceDao.findById(preference);
        preferenceDao.updateLastNotified(preference.getCity(),
                preference.getState(), preference.getUserId());
        Preference updatedPref = preferenceDao.findById(preference);
        Assert.assertEquals(pref.getState(), updatedPref.getState());
    }

    private Preference getPreference(){
        Preference preference = new Preference();
        preference.setState("JH");
        preference.setCity("JSR");
        preference.setUserId(1l);
        preference.setThresholdInC(32l);
        preference.setIsSmsActive("Y");
        preference.setIsAppNotifyActive("Y");
        preference.setIsMailActive("Y");
        return preference;
    }

    @Test
    public void weatherGetCurrentWeatherDetailsTest(){
        WeatherResponseDTO currentWeatherDetails = weatherSystem.getCurrentWeatherDetails("PUNE", "MH", "http.co.in");
        Assert.assertNotNull(currentWeatherDetails);
    }

    @Test
    public void weatherReportSystemTest(){
        weatherSystem.weatherReportSystem();
        Preference preference = getPreference();
        preference.setCity("San Francisco");
        preference.setState("CA");
        Preference byId = preferenceDao.findById(preference);
        byId.setNotifyCycleMins(-1);
        preferenceDao.update(byId);
        weatherSystem.weatherReportSystem();
    }
    @Test
    public void getExceededTemp(){
        List<ExceededTemperatureUserView> userViewList = excessTempDao.listAll();
        Assert.assertNotNull(userViewList);
    }

    @Test
    public void deletePreferenceTest(){
        Preference preference = getPreference();
        preference.setCity("San Francisco");
        preference.setState("CA");
        preferenceDao.delete(preference);
        List<Preference> preferences = preferenceDao.findAll();
        Assert.assertEquals(0, preferences.size());
    }

    @Test
    public void findAllProviders(){
        List<Provider> providerDaoAll = providerDao.findAll();
        Assert.assertEquals(2,providerDaoAll.size());
    }

    @Test
    public void temperatureTest(){
        Temperature temperature = new Temperature();
        temperature.setWindDir("W");
        temperature.setProviderId(1l);
        temperature.setCredits("W");
        temperature.setCity("San Francisco");
        temperature.setState("CA");
        temperature.setRelativeHumidity("33");
        temperature.setVisibilityMiles("20");
        temperature.setTempC(20);
        temperature.setTempF(40);
        temperatureDao.upsert(temperature);
    }

    @Test
    public void webAppConfigInitTest(){
        WebAppConfig webAppConfig = new WebAppConfig();
        WebAppInitializer webAppInitializer = new WebAppInitializer();
        Assert.assertNotNull(webAppConfig);
        Assert.assertNotNull(webAppInitializer);
    }
    @Test
    public void userCreateControllerTest(){
        userCreateController.createUserFailed();
        userCreateController.performCreate(new User(), new RedirectAttributesModelMap(), getSessionStatus());

        userCreateController.startCreatingNewUser(getModel());
    }

    private Model getModel(){
        return new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
    }
    private SessionStatus getSessionStatus(){
        return new SessionStatus() {
            @Override
            public void setComplete() {

            }

            @Override
            public boolean isComplete() {
                return false;
            }
        };
    }

    @Test
    public void userDeleteControllerTest(){
        userDeleteController.deleteUserFailed();
        userDeleteController.selectForDelete(1l, getModel());
        userDeleteController.delete(new User(), new RedirectAttributesModelMap(), getSessionStatus());
    }

    @Test
    public void userListControllerTest(){
        userListController.findAllPersons(getModel());
    }

    @Test
    public void userUpdateControllerTest(){
        updateController.performUpdate(new User(), new RedirectAttributesModelMap(), getSessionStatus());
        updateController.updateUserFailed();
        updateController.selectForUpdate(1l, getModel());
    }

    @Test
    public void preferenceUpdateControllerTest(){
        preferenceUpdateController.performUpdate(new Preference(),
                new RedirectAttributesModelMap(), getSessionStatus());
        preferenceUpdateController.updatePreferenceFailed();//','
        preferenceUpdateController.selectForUpdate(1l, "San Francisco","CA", getModel());
    }
    @Test
    public void preferenceDeleteControllerTest(){
        preferenceDeleteController.delete(new Preference(), new RedirectAttributesModelMap(), getSessionStatus());
        preferenceDeleteController.deletePreferenceFailed();
        preferenceDeleteController.selectForDelete(1l,"San Francisco", "CA", getModel());
    }
    @Test
    public void preferenceCreateControllerTest(){
        preferenceCreateController.createPreferenceFailed();
        preferenceCreateController.performCreate(new Preference(), new RedirectAttributesModelMap(), getSessionStatus());
        preferenceCreateController.startCreatingNewPreference(getModel());
    }
    @Test
    public void preferenceListControllerTest(){
        preferenceListController.findAllPreferences(getModel());
    }
}
