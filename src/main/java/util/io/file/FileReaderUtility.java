package util.io.file;

import model.Plank;
import model.Package;

import java.io.*;
import java.util.*;

public class FileReaderUtility {
    public static List<Plank> readPlanks(String filename) throws IOException {
        List<Plank> planks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            for(Integer id = 1; line != null; id++) {
                String[] parts = line.split(", ");
                for(int i = Integer.parseInt(parts[3]); i > 0; i--) {
                    planks.add(new Plank(
                            id,
                            Integer.parseInt(parts[0]),
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2])));
                }
                line = reader.readLine();
            }
        }

        return planks;
    }

    public static List<Package> readPackages(String filename) throws IOException {
        List<Package> packages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            for(Integer id = 1; line != null; id++) {
                String[] parts = line.split(", ");
                packages.add(new Package(
                        id,
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[2]),
                        new ArrayList<>()));
                line = reader.readLine();
            }
        }
        return packages;
    }

}
