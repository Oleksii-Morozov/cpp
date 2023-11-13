package alerix.dev.util.io.file;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

@SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
public class Deserializer {

    public static <G> List<G> deserialize(String path, Class<G> type, boolean json) {
        if (json) {
            try {
                return deserializeFromJson(path, type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
            return deserializeFromText(path);
    }

    private static <G> List<G> deserializeFromText(String path) {
        List<G> collection = null;
        path = "src/main/resources/" + path;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            collection = (List<G>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return collection;
    }

    private static <G> List<G> deserializeFromJson(String path, Class<G> type) throws IOException {
        if (!path.endsWith(".json")) {
            throw new IllegalArgumentException("Path must end with .json");
        }

        InputStream inputStream = Deserializer.class.getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException("Resource not found: " + path);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);

        List<G> collection = objectMapper.readValue(inputStream, javaType);

        inputStream.close();

        return collection;
    }
}
