package com.getir.rig.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.getir.rig.config.ApplicationConstant.STATS_PATH;

@RestController
@RequestMapping(STATS_PATH)
public class StatsController {

    @GetMapping
    public String getStats() {
        return null;
    }

}
