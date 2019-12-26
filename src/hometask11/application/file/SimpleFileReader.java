package hometask11.application.file;

import hometask11.cargo.file.CargoFromFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import hometask11.cargo.file.CargoParser;

public class SimpleFileReader {

    public static final String whereCargosBegin = "CargosBegin";
    public static final String whereCargosEnd = "CargosEnd";

    public File inputFile;

    public SimpleFileReader(File file) {
        inputFile = file;
    }

    public List<CargoFromFile> readCargos() {
        List<CargoFromFile> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null && !(line.equals(whereCargosBegin))) {
                line = reader.readLine();
            }
            while (line != null && !(line.equals(whereCargosEnd))) {
                line = reader.readLine();
                if (!(line.equals(whereCargosEnd))) {
                    CargoFromFile cargo = CargoParser.parse(line);
                    list.add(cargo);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
