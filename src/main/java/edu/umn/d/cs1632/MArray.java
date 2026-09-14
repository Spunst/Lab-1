package edu.umn.d.cs1632;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.List;

class MArray {
    private MArray() {}
    public MArray(String fileName) {
        try {
            FileReader filereader = new FileReader(fileName);
            CSVReader csvReader = new CSVReader(filereader);
            List<String[]> allData = csvReader.readAll();

            boolean isHeader = true;

            for (String[] row : allData) {
                for (String cell : row) {
                    Value displayValue;

                    if (isHeader) {
                        displayValue = new StringValue(cell.trim());
                    } else {
                        displayValue = sorter(cell);
                    }

                    System.out.print(displayValue + "\t");
                }
                System.out.println();
                isHeader = false;
            }
            csvReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Value sorter(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new StringValue("");
        }
        String trimmed = input.trim();

        try {
            return new IntValue(Integer.parseInt(trimmed));
        } catch (NumberFormatException e1) {
            try {
                return new DoubleValue(Double.parseDouble(trimmed));
            } catch (NumberFormatException e2) {
                return new StringValue(trimmed);
            }
        }
    }
}