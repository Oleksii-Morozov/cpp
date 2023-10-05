import java.util.*;

public class FootballClubController {
    public Map<String, List<FootballClub>> createCityClubMap(List<FootballClub> clubs) {
        Map<String, List<FootballClub>> cityClubMap = new HashMap<>();
        for (FootballClub club : clubs) {
            cityClubMap
                    .computeIfAbsent(club.city(), k -> new ArrayList<>())
                    .add(club);
        }
        return cityClubMap;
    }

    public void printFirstNFromEachCity(Map<String, List<FootballClub>> cityClubMap, int n) {
        for (Map.Entry<String, List<FootballClub>> entry : cityClubMap.entrySet()) {
            System.out.println("City: " + entry.getKey());
            List<FootballClub> clubs = entry.getValue();
            for (int i = 0; i < Math.min(n, clubs.size()); i++) {
                System.out.println(clubs.get(i).name());
            }
        }
    }

    public void countCitiesWithSameNameClub(List<FootballClub> clubs) {
        Set<String> clubNames = new HashSet<>();
        Set<String> repeatedClubNames = new HashSet<>();

        for (FootballClub club : clubs) {
            if (clubNames.contains(club.name())) {
                repeatedClubNames.add(club.name());
            } else {
                clubNames.add(club.name());
            }
        }

        int count = 0;
        for (String name : repeatedClubNames) {
            Set<String> cities = new HashSet<>();
            for (FootballClub club : clubs) {
                if (club.name().equals(name)) {
                    cities.add(club.city());
                }
            }
            if (cities.size() > 1) {
                count++;
            }
        }

        System.out.println("Number of cities with identical club names: " + count);
    }

    public static List<FootballClub> mergeTwoLists(List<FootballClub> clubs1, List<FootballClub> clubs2) {
        Set<FootballClub> commonClubs = new HashSet<>(clubs1);
        commonClubs.retainAll(clubs2);
        return commonClubs.stream().toList();
    }
}
