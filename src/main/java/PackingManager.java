import model.Package;
import model.Plank;
import util.io.file.FileReaderUtility;
import util.io.file.FileWriterUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class PackingManager {

    private final List<Plank> planks;
    private final List<Package> packages;
    private final List<Package> packedPackages;
    private List<Plank> unpackedPlanks;
    private final String userProfile = System.getenv("USERPROFILE");

    public PackingManager() throws IOException {
        planks = FileReaderUtility
                .readPlanks(userProfile + "\\Work\\PlankPackingAlgorithm\\src\\main\\resources\\planks.txt");
        packages = FileReaderUtility
                .readPackages(userProfile + "\\Work\\PlankPackingAlgorithm\\src\\main\\resources\\packages.txt");
        packedPackages = new ArrayList<>();
    }

    public void run() throws IOException {
        packPlanks();
        FileWriterUtility.write(
                userProfile + "\\Work\\PlankPackingAlgorithm\\src\\main\\resources\\result.txt",
                packedPackages,
                false);
        FileWriterUtility.write(
                userProfile + "\\Work\\PlankPackingAlgorithm\\src\\main\\resources\\result.txt",
                "Non-standard planks:",
                true);
        FileWriterUtility.write(
                userProfile + "\\Work\\PlankPackingAlgorithm\\src\\main\\resources\\result.txt",
                unpackedPlanks,
                true);
    }

    private void packPlanks() {
        List <Plank> planks = sort(this.planks, Comparator.comparing(Plank::width));
        unpackedPlanks = new ArrayList<>(planks);
        List<Package> packages = sort(this.packages, Comparator.comparing(Package::width).thenComparing(Package::length));

        for(Plank plank : planks) {
            for(Package pack : packages) {
                if(canFit(plank, pack)) {
                    pack.addPlank(plank);
                    unpackedPlanks.remove(plank);
                    break;
                }
                if(isPacked(pack)) {
                    List<Plank> tmpPlanks = new ArrayList<>(pack.planks());
                    packedPackages.add(new Package(pack.id(), pack.width(), pack.length(), pack.height(), tmpPlanks));
                    pack.planks().clear();
                }
            }
        }

        packedPackages.addAll(packages);
        packedPackages.removeIf(pack -> pack.planks().isEmpty());
    }

    private <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
        return list;
    }

    private Plank rotatePlank(Plank plank) {
        return new Plank(plank.id(), plank.width(), plank.length(), plank.height());
    }

    private boolean canFit(Plank plank, Package pack) {
        int packHeight = pack.height() - pack.planks().stream().mapToInt(Plank::height).sum();
        Plank rtPlank = rotatePlank(plank);
        return (plank.width() <= pack.width() && plank.length() <= pack.length() && plank.height() <= packHeight)
                || (rtPlank.width() <= pack.width() && rtPlank.length() <= pack.length() && rtPlank.height() <= packHeight);
    }

    private boolean isPacked(Package pack) {
        return pack.planks().stream().mapToInt(Plank::height).sum() == pack.height();
    }
}
