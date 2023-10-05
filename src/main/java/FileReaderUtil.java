import java.io.*;
import java.util.*;

public class FileReaderUtil {
    public static List<FootballClub> readFootballClubsFromFile(String fileName) throws IOException {
        List<FootballClub> footballClubList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                String name = tokens[0];
                int foundedYear = Integer.parseInt(tokens[1]);
                String city = tokens[2];

                FootballClub footballClub = new FootballClub(name, foundedYear, city);
                footballClubList.add(footballClub);

                line = reader.readLine();
            }
        }

        return footballClubList;
    }
}
