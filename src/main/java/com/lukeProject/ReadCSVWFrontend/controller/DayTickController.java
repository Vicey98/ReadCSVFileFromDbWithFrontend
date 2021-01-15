package com.lukeProject.ReadCSVWFrontend.controller;

import com.lukeProject.ReadCSVWFrontend.model.DayTick;
import com.lukeProject.ReadCSVWFrontend.repository.DayTickRepository;
import com.lukeProject.ReadCSVWFrontend.service.DayTickService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/DailyTick")
public class DayTickController {

    private DayTickRepository dtrepo;

    /* @GetMapping("/Summary")
    public Iterable<DayTick>  getDailyTickSummary() {
        return dtrepo.findAll();
    } */
    
    @GetMapping("/Summary")
    public Iterable<DayTick> getTotalMarketCapByCoin() {
        return dtrepo.findTotalMarketCapByCoinDesc();
    }

    @GetMapping("/Test")
    public Iterable<DayTick> getVolumeByCoin() {
        return dtrepo.findTotalVolumeByCoinDesc();
    }
}