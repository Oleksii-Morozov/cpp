import java.util.List;
import static java.lang.String.format;

public class FootballClubPrinter {
    public static void printFootballClubList(List<FootballClub> footballClubs) {
        String header = format("%-20s | %-4s | %-10s", "Name", "Year", "City") + "\n" + "-".repeat(37);
        System.out.println(header);
        for (FootballClub footballClub : footballClubs) {
            System.out.println(footballClub);
        }
    }
}
