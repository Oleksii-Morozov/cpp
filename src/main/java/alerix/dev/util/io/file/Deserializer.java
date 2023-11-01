package alerix.dev.util.io.file;

import alerix.dev.util.io.terminal.TerminalOutput;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

@SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
public class Deserializer {
    public static <G> List<G> deserializeCollection(String path) {
        List<G> collection = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            collection = (List<G>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            TerminalOutput.print("Deserialized data from " + path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return collection;
    }

    public static <G> List<G> deserializeCollectionFromJson(String path) {
        if(!path.endsWith(".json")) {
            throw new IllegalArgumentException("Path must end with .json");
        }
        if(!new File(path).exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<G> collection = null;
        try {
            collection = (List<G>) objectMapper.readValue(new File(path), Object.class);
            TerminalOutput.print("Deserialized data from " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collection;
    }
}
