package com.lukeProject.ReadCSVWFrontend.repository;

import com.lukeProject.ReadCSVWFrontend.model.DayTick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DayTickRepository extends JpaRepository<DayTick, Long> {
    DayTick findByName(String name);

    /*
    @Query("SELECT new com.fdm.model.statistics.ItemRepoOutput(si.id, si.name, SUM(ti.amount))" + 
			"  FROM TransactionItem ti" + 
			"    INNER JOIN StoreItem si" + 
			"    ON ti.item = si.id" + 
			"      GROUP BY si.id, si.name")
    List<ItemRepoOutput> findTotalSaleStoreItem();
    */
    
    @Query("SELECT SUM(a.market_cap) FROM DayTick a WHERE a.name like 'tezos'")
    Iterable<DayTick> findByName2();
    
    // @Query("SELECT a.name, sum(a.market_cap) FROM DAY_TICK a GROUP BY a.name ORDER BY sum(a.market_cap) desc")
    // Iterable<DayTick> findAndOrderByMarketcapAndGroupBy();

    @Query(value = "SELECT dt.name, SUM(dt.market_cap) FROM DayTick dt GROUP BY dt.name ORDER BY SUM(dt.market_cap) desc")
    Iterable<DayTick> findTotalMarketCapByCoinDesc();

    @Query(value = "SELECT dt.name, SUM(dt.volume) FROM DayTick dt GROUP BY dt.name ORDER BY SUM(dt.volume) desc")
    Iterable<DayTick> findTotalVolumeByCoinDesc();
    
}
