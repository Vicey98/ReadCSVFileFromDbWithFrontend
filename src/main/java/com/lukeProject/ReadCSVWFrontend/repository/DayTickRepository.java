package com.lukeProject.ReadCSVWFrontend.repository;

import com.lukeProject.ReadCSVWFrontend.model.DayTick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DayTickRepository extends JpaRepository<DayTick, Long> {
    DayTick findByName(String name);
    
    // Test Query
    @Query("SELECT SUM(a.market_cap) FROM DayTick a WHERE a.name like 'tezos'")
    Iterable<DayTick> findByName2();

    // Selects coin and sum of the market cap of that coin with descending order
    @Query(value = "SELECT dt.name, SUM(dt.market_cap) FROM DayTick dt GROUP BY dt.name ORDER BY SUM(dt.market_cap) desc")
    Iterable<DayTick> findTotalMarketCapByCoinDesc();


    @Query(value = "SELECT dt.name, dt.date, dt.close, dt.volume, dt.open FROM DayTick dt WHERE dt.name LIKE ?1")
    Iterable<DayTick> getPriceChangeDataByDate(String Name);
    
}
