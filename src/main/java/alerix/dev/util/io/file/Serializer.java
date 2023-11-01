package alerix.dev.util.io.file;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@SuppressWarnings({"CallToPrintStackTrace"})
public class Serializer {
    public static <G> void serializeCollection(List<G> collection, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(collection);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <G> void serializeCollectionToJson(List<G> collection, String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(collection);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(json);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
