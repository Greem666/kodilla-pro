package com.kodillapro.customconverter.controller;

import com.kodillapro.customconverter.domain.HashTagBonanzaClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hash/")
public class HashTagBonanzaController {

    @PostMapping(path = "gibberish")
    public void acceptHashGibberish(@RequestBody HashTagBonanzaClass customObject) {
        for (String message: customObject.getListOfMessages()) {
            System.out.println(message);
        }
    }
}
