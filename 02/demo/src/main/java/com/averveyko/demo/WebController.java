package com.averveyko.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping
    public SomePojo index() {
        return new SomePojo("alexander", Integer.valueOf(35));
    }
}
