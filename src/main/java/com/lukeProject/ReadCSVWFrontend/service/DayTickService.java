package com.lukeProject.ReadCSVWFrontend.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.lukeProject.ReadCSVWFrontend.repository.DayTickRepository;
import com.lukeProject.ReadCSVWFrontend.repository.PriceChangeRepository;
import com.lukeProject.ReadCSVWFrontend.model.DayTick;
import com.lukeProject.ReadCSVWFrontend.model.PriceChange;
import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayTickService {

    @Autowired
    private DayTickRepository dtrepo;

    @Autowired
    private PriceChangeRepository pcrepo;

    String line = "";

    public void loadDataToDb(String file) {
        Map<String, List<DayTick>> allTickDataGivenCoinMap = new HashMap<String, List<DayTick>>();
        file = file.length() == 0 ? "src/main/resources/crypto_historical_data.csv" : file;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            // Skips header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].replaceAll("\"", "").replaceAll(",","");
                }
                DateFormat df = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH); 
                Date date = df.parse(tokens[1]);
                DayTick dt = new DayTick(null, tokens[0], date, Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), 
                Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5]), Long.parseLong((tokens[6])), Long.parseLong(tokens[7]));
                dtrepo.save(dt);
                List<DayTick> temp = allTickDataGivenCoinMap.get(tokens[0]);
                if (temp == null) {
                    temp = new ArrayList<DayTick>();
                    allTickDataGivenCoinMap.put(tokens[0], temp);
                }
                temp.add(dt);
            }
            br.close(); 

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception in loading data");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Date malformatted in csv");
            e.printStackTrace();
        }
        generatePriceChangeTable(allTickDataGivenCoinMap);
    }

    public void generatePriceChangeTable(Map<String, List<DayTick>> allDTMap) {

        for (String key : allDTMap.keySet()) {
            PriceChange pc = new PriceChange();
            pc.setCoinName(key);
            for (int i = 0; i < allDTMap.get(key).size(); i++) {
                DayTick mostRecentDT = allDTMap.get(key).get(0);
                DayTick currentDT = allDTMap.get(key).get(i);
                DecimalFormat df = new DecimalFormat("#.#");    
                if (i == 0) {
                    pc.setDate(mostRecentDT.getDate());
                    pc.setPrice(mostRecentDT.getClose());
                    pc.setDayVolume(mostRecentDT.getVolume());
                    pc.setMarketCap(mostRecentDT.getMarket_cap());
                } else if (i == 1) {
                    pc.setDayChange(Double.valueOf(df.format((mostRecentDT.getClose() - currentDT.getClose())/currentDT.getClose() * 100)));
                } else if (i == 6) {
                    pc.setWeekChange(Double.valueOf(df.format((mostRecentDT.getClose() - currentDT.getClose())/currentDT.getClose() * 100)));
                } else if (i == 27) {
                    pc.setMonthChange(Double.valueOf(df.format((mostRecentDT.getClose() - currentDT.getClose())/currentDT.getClose() * 100)));
                }
            }
            pcrepo.save(pc);
        }
    }
}
