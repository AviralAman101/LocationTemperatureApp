package com.etraveli.model;

public class WeatherResponseDTO {
       // @JsonProperty("City")
        public String city;
       // @JsonProperty("State")
        public String state;
       // @JsonProperty("TempF")
        public String tempF;
        //////@JsonProperty("TempC")
        public String tempC;
        //@JsonProperty("Weather")
        public String weather;
        //@JsonProperty("WindMPH")
        public String windMPH;
       // @JsonProperty("WindDir")
        public String windDir;
        //@JsonProperty("RelativeHumidity")
        public String relativeHumidity;
       // @JsonProperty("VisibilityMiles")
        public String visibilityMiles;
       // @JsonProperty("Code")
        public String code;
        //@JsonProperty("Credits")
        public String credits;

    @Override
    public String toString() {
        return "WeatherResponseDTO{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", tempF='" + tempF + '\'' +
                ", tempC='" + tempC + '\'' +
                ", weather='" + weather + '\'' +
                ", windMPH='" + windMPH + '\'' +
                ", windDir='" + windDir + '\'' +
                ", relativeHumidity='" + relativeHumidity + '\'' +
                ", visibilityMiles='" + visibilityMiles + '\'' +
                ", code='" + code + '\'' +
                ", credits='" + credits + '\'' +
                '}';
    }
}
