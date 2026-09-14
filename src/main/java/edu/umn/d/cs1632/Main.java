package edu.umn.d.cs1632;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MArray mArray = new MArray("src/data/simple.csv");

        ArrayList<ArrayList<Value>> matrix = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader("src/data/simple.csv"));
            List<String[]> allData = reader.readAll();
            for (int i = 1; i < allData.size(); i++) {
                ArrayList<Value> row = new ArrayList<>();
                for (String cell : allData.get(i)) {
                    row.add(sorter(cell));
                }
                matrix.add(row);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Query? ");
            String cmd = scanner.next();
            if (cmd.equals("Q") || cmd.equals("q")) {
                break;
            }

            int rStart = 0;
            int rEnd = 0;
            int cStart = 0;
            int cEnd = 0;

            if (cmd.equals("V") || cmd.equals("v")) {
                int col = scanner.nextInt();
                cStart = col;
                cEnd = col;
                rStart = scanner.nextInt();
                rEnd = scanner.nextInt();
            } else if (cmd.equals("H") || cmd.equals("h")) {
                int row = scanner.nextInt();
                rStart = row;
                rEnd = row;
                cStart = scanner.nextInt();
                cEnd = scanner.nextInt();
            } else if (cmd.equals("M") || cmd.equals("m")) {
                rStart = scanner.nextInt();
                rEnd = scanner.nextInt();
                cStart = scanner.nextInt();
                cEnd = scanner.nextInt();
            }

            String firstType = "";
            boolean isMulti = false;

            for (int r = rStart; r <= rEnd; r++) {
                for (int c = cStart; c <= cEnd; c++) {
                    Value v = matrix.get(r).get(c);
                    String currentType = "";

                    if (v instanceof IntValue) {
                        currentType = "Int";
                    } else if (v instanceof DoubleValue) {
                        currentType = "Double";
                    } else if (v instanceof StringValue) {
                        currentType = "String";
                    }

                    if (firstType.equals("")) {
                        firstType = currentType;
                    } else if (!firstType.equals(currentType)) {
                        isMulti = true;
                    }
                }
            }

            if (isMulti) {
                System.out.println("Multi");
            } else {
                System.out.println(firstType);
            }
        }
        scanner.close();
    }

    private static Value sorter(String input) {
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
