package com.scaler.ecomm_productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {

    @GetMapping("/xyz/{name}/{city}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("city") String city) {
        return "Hello! " +name + " from city "+ city;
    }
}
