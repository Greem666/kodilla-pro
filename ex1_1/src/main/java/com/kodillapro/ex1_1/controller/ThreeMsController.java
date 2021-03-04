package com.kodillapro.ex1_1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/methods")
public class ThreeMsController {

    @GetMapping(path = "/m1")
    public String m1() {
        return "m1";
    }

    @GetMapping(path = "/m2")
    public String m2() {
        return "m2";
    }

    @GetMapping(path = "/m3")
    public String m3() {
        return "m3";
    }
}
