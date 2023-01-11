package com.example.tpbdd.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping(value="/mongo/")
    public String getUserById() {
        return "Everything alright";
    }
}
