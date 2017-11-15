package com.github.java4wro.csvparser.service.impl;


import com.github.java4wro.csvparser.model.Seat;
import com.github.java4wro.csvparser.repository.HallRepository;
import com.github.java4wro.csvparser.service.HallService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hall_Repository;

    public HallServiceImpl() throws FileNotFoundException {
    }

    @Override
    public List<Seat> findAllSeats() {
        return hall_Repository.findAll();
    }

    @Override
    public void readFile(InputStream is) throws IOException {
        try {
            File file;
            HashMap<String, Float> hall_1 = new HashMap<>();
            String line;
            final String UTF8_BOM = "\uFEFF";
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));


            int row = 1;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(UTF8_BOM)) {
                    line = line.substring(1);
                }
                String[] singleRow = line.split(",");
                for (int i = 0; i < singleRow.length; i++) {
                    if (Float.valueOf(singleRow[i]) > 0) {
                        Character r = (char) (row + 64);
                        StringBuilder sB = new StringBuilder();
                        sB.append(r);
                        sB.append(String.valueOf(i + 1));
                        hall_1.put(sB.toString(), Float.valueOf(singleRow[i]));
                        Seat newSeat = new Seat();
                        newSeat.setSeat(sB.toString());
                        newSeat.setValue(Float.valueOf(singleRow[i]));
                        hall_Repository.save(newSeat);
                    }
                }
                row++;
            }
        } catch (UnsupportedEncodingException unEE) {
            throw new IOException();
        }

//        CSVReader reader = null;
//        try {
//            reader = new CSVReader(new InputStreamReader(is, "UTF-8"));
//            String[] line;
//
//            while ((line = reader.readNext()) != null) {
//
//            }
//        }
    }
}
