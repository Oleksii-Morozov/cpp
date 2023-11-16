package alerix.dev.util.io.file;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deserializer {

    public static <G> List<G> deserialize(String path, Class<G> type, int format) {
        List<G> collection;
        switch (format) {
            case 1 -> collection = deserializeFromSer(path);
            case 2 -> collection = deserializeFromJson(path, type);
            case 3 -> collection = deserializeFromYaml(path, type);
            default -> throw new IllegalArgumentException("Invalid type");
        }
        return collection;
    }

    private static <G> List<G> deserializeFromSer(String path) {
        List<G> collection;
        path = "src/main/resources/" + path;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //noinspection unchecked
            collection = Collections.unmodifiableList((ArrayList<G>) objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return collection;
    }

    private static <G> List<G> deserializeFromJson(String path, Class<G> type) {
        if (!path.endsWith(".json")) {
            throw new IllegalArgumentException("Path must end with .json");
        }

        InputStream inputStream = Deserializer.class.getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            try {
                throw new FileNotFoundException("Resource not found: " + path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
        try {
            return objectMapper.readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <G> List<G> deserializeFromYaml(String path, Class<G> type) {
        if (!path.endsWith(".yaml")) {
            throw new IllegalArgumentException("Path must end with .yaml");
        }

        InputStream inputStream = Deserializer.class.getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            try {
                throw new FileNotFoundException("Resource not found: " + path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper(new com.fasterxml.jackson.dataformat.yaml.YAMLFactory());
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
        try {
            return objectMapper.readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
