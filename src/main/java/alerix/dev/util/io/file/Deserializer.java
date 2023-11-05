package alerix.dev.util.io.file;

import alerix.dev.util.io.terminal.TerminalOutput;
import com.fasterxml.jackson.core.type.TypeReference;
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
            return deserializeFromText(path, type);
    }

    private static <G> List<G> deserializeFromText(String path, Class<G> type) {
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

    private static <G> List<G> deserializeFromJson(String path, Class<G> type) throws IOException {
        if (!path.endsWith(".json")) {
            throw new IllegalArgumentException("Path must end with .json");
        }
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = null;
        try {
            TerminalOutput.print("Deserialized data from " + path);
            inputStream = Deserializer.class.getClassLoader().getResourceAsStream(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectMapper.readValue(inputStream, new TypeReference<>() {
        });
    }
}
