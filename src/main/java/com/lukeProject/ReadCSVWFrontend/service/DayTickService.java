package com.lukeProject.ReadCSVWFrontend.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.lukeProject.ReadCSVWFrontend.repository.DayTickRepository;
import com.lukeProject.ReadCSVWFrontend.model.DayTick;
import java.text.*;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayTickService {

    @Autowired
    private DayTickRepository dtrepo;

    public void loadDataToDb() {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/crypto_historical_data.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].replaceAll("\"", "").replaceAll(",","");
                }
                DayTick dt = new DayTick(null, tokens[0], tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), 
                Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5]), Long.parseLong((tokens[6])), Long.parseLong(tokens[7]));
                // Date date = new SimpleDateFormat("MMM dd, YYYY").parse((tokens[1].replaceAll("\"", "")));
                dtrepo.save(dt);
            }
            br.close();

            SimpleDateFormat sdf = new SimpleDateFormat("mm dd YYYY");
            String dateInString = "12 04 1998";
            Date date = sdf.parse(dateInString);
            System.out.println(date);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // I should write but eh
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Iterable<DayTick> getPriceChangeDataByDate() {
        Iterable<DayTick> itr = dtrepo.getPriceChangeDataByDate("tezos");

        return dtrepo.getPriceChangeDataByDate("tezos");
    }

    // Get price change of individual coins 
    // Then add them to a single json
    // Then return

    // OR

    // Create a price change table on dataLoader
    // Then create a return statement in the DayTickRepo
    // Need to do: Create Model: PriceChange, Repo, 

}
