package com.demo.app.api;

import com.demo.app.Weather;
import com.demo.app.annotation.ExternalCommunication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://ad4cddc7-444c-4be3-8bba-e5ba78bf445f.mock.pstmn.io",name = "weather-api")
public interface WeatherClient {

    @ExternalCommunication
    @GetMapping("/weather")
    public String getWeather(@RequestParam String city);


    @ExternalCommunication
    @PostMapping("/todo")
    public void toDo(@RequestParam String todo);
}
