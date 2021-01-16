package com.lukeProject.ReadCSVWFrontend.repository;

import com.lukeProject.ReadCSVWFrontend.model.PriceChange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceChangeRepository extends JpaRepository<PriceChange, Long> {

    @Query (value = "SELECT pc.coinName, pc.price, pc.dayChange, pc.weekChange, " + 
    "pc.monthChange, pc.dayVolume, pc.marketCap FROM PriceChange pc ORDER BY pc.marketCap desc")
    Iterable<PriceChange> findPriceChangeTable();

    // @Query(value = 
    // "SELECT pc.coinName, pc.price, pc.dayChange, pc.weekChange FROM Price_Change AS pc " +
    // "INNER JOIN ( " +
    // "SELECT dt.name, SUM(dt.market_cap) FROM Day_Tick dt GROUP BY dt.name ORDER BY SUM(dt.market_cap) desc " + 
    // ") AS dt " +
    // "ON dt.name = pc.coin_name")
    // Iterable<PriceChange> test();  
}
    
