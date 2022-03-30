package com.demo.app;

import com.demo.app.annotation.ExternalCommunication;
import com.demo.app.api.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {
    @Autowired
    WeatherClient weatherClient;


    @GetMapping("/book")
    public ResponseEntity<Book> getBook(@RequestParam String author){
        Book b= Book.builder()
                .Author("SvkDey")
                .name("this is JAVA")
                .build();
        return ResponseEntity.ok(b);
    }
    @ExternalCommunication
    @GetMapping("/api/weather/{city}")
    public ResponseEntity<String> getWeather(@PathVariable String city){
        return ResponseEntity.ok(weatherClient.getWeather(city));
    }

    @ExternalCommunication
    @PostMapping("/todo")
    public ResponseEntity<Void> postTodo(@RequestBody String todo){
        weatherClient.toDo(todo);
        return ResponseEntity.status(201).build();
    }
}
