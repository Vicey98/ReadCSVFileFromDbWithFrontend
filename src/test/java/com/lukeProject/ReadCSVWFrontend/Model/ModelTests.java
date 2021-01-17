package com.lukeProject.ReadCSVWFrontend.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import com.lukeProject.ReadCSVWFrontend.model.DayTick;
import com.lukeProject.ReadCSVWFrontend.model.PriceChange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTests {

    @BeforeEach
	public void init() {

    }
    
    @Test
    public void testThat_no_args_DT_annotation_works() {
        DayTick dt = new DayTick();
        assertEquals(dt.getName(), null);
    }

    @Test
    public void testThat_realDt_returns_correct_vals() {
        DayTick realDt = new DayTick(1L,"test", new Date(), 1.1, 1.1, 1.1, 1.1, 1L, 2L);
        assertEquals(realDt.getName(), "test");
    }

    @Test
    public void testThat_no_args__PC_annotation_works() {
        PriceChange pc = new PriceChange();
        assertEquals(pc.getCoinName(), null);
    }
}
