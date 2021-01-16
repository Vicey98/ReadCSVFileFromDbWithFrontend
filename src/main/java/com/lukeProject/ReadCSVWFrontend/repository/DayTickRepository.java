package com.lukeProject.ReadCSVWFrontend.repository;

import com.lukeProject.ReadCSVWFrontend.model.DayTick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DayTickRepository extends JpaRepository<DayTick, Long> {
    DayTick findByName(String name);
    
    @Query("SELECT SUM(a.market_cap) FROM DayTick a WHERE a.name like 'tezos'")
    Iterable<DayTick> findByName2();

    @Query(value = "SELECT dt.name, SUM(dt.market_cap) FROM DayTick dt GROUP BY dt.name ORDER BY SUM(dt.market_cap) desc")
    Iterable<DayTick> findTotalMarketCapByCoinDesc();
}
