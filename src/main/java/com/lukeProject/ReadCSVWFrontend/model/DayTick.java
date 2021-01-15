package com.lukeProject.ReadCSVWFrontend.model;

import java.util.Date;  

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DayTick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Could use a composite key of name & date but not required in this case

    private String name;

    private String date;

    private double open;

    private double high;

    private double low;

    private double close;

    private Long volume;

    private Long market_cap;
    
}
