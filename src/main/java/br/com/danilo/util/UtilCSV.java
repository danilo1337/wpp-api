package br.com.danilo.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class UtilCSV {
	
    public String lerPrimeiraLinha(MultipartFile file) throws IOException{
        try (CSVParser csvParser = CSVFormat.DEFAULT.parse(new InputStreamReader(file.getInputStream()))) {
            for (CSVRecord record : csvParser) {
                return record.get(0);
            }
        }
        
        return null;
    }
    
    public List<String> lerDuasLinhas(MultipartFile file) throws IOException{
        List<String> twoLines = new ArrayList<>();
        try (CSVParser csvParser = CSVFormat.DEFAULT.parse(new InputStreamReader(file.getInputStream()))) {
            int linesRead = 0;
            for (CSVRecord record : csvParser) {
                twoLines.add(record.get(0));
                linesRead++;
                if (linesRead >= 2) {
                    break; 
                }
            }
        }
        return twoLines;
    }
    
    public List<String> lerTodasLinhas(MultipartFile file) throws IOException{
        List<String> allLines = new ArrayList<>();
        try (CSVParser csvParser = CSVFormat.DEFAULT.parse(new InputStreamReader(file.getInputStream()))) {
            for (CSVRecord record : csvParser) {
                allLines.add(record.get(0));
            }
        }
        return allLines;
    }
}
