package alerix.dev.util.io.file;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class Serializer {

    public static <G> void serialize(List<G> collection, String path, int type) {
        switch (type) {
            case 1 -> serializeToSer(collection, path);
            case 2 -> serializeToJson(collection, path);
            case 3 -> serializeToYaml(collection, path);
            default -> throw new IllegalArgumentException("Invalid type");
        }
    }
    private static <G> void serializeToSer(List<G> collection, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(collection);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static <G> void serializeToJson(List<G> collection, String path) {
        path += ".json";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        try {
            String json = objectMapper.writeValueAsString(collection);
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
                writer.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static <G> void serializeToYaml(List<G> collection, String path) {
        if(!path.endsWith(".yaml"))
            path += ".yaml";

        ObjectMapper objectMapper = new ObjectMapper(new com.fasterxml.jackson.dataformat.yaml.YAMLFactory());
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        try {
            objectMapper.writeValue(new File(path), collection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
