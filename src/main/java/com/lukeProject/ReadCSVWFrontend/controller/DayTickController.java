package com.lukeProject.ReadCSVWFrontend.controller;

import com.lukeProject.ReadCSVWFrontend.model.DayTick;
import com.lukeProject.ReadCSVWFrontend.model.PriceChange;

import com.lukeProject.ReadCSVWFrontend.repository.DayTickRepository;
import com.lukeProject.ReadCSVWFrontend.repository.PriceChangeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/dailytick")
public class DayTickController {

    @Autowired
    private DayTickRepository dtrepo;
    @Autowired
    private PriceChangeRepository ptrepo;
    
    @GetMapping("/marketcap")
    public Iterable<DayTick> getTotalMarketCapByCoin() {
        return dtrepo.findTotalMarketCapByCoinDesc();
    }

    @GetMapping("/pricechange")
    public Iterable<PriceChange> getPriceChangeDataByDate() {
        return ptrepo.findPriceChangeTable();
    }
}
