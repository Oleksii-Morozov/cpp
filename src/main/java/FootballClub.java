public record FootballClub(String name, int foundedYear, String city) {
    @Override
    public String toString() {
        return String.format("%-20s | %-4d | %-10s", name, foundedYear, city);
    }
}
