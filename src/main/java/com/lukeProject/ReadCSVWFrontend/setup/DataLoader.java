package com.lukeProject.ReadCSVWFrontend.setup;

import com.lukeProject.ReadCSVWFrontend.service.DayTickService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner{

    @Autowired
    private DayTickService dtService;

    @Override
    public void run(String... args) throws Exception {
        
        dtService.loadDataToDb();
    }
}
