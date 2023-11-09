package util.io.file;

import model.Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Reader {
    @SuppressWarnings("unchecked")
    public static List<Project> readProjectsFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".ser"))) {
            return (List<Project>) in.readObject();
        }
    }
}
