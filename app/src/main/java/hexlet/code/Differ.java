package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }

        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map1 = mapper.readValue(json1, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> map2 = mapper.readValue(json2, new TypeReference<Map<String,Object>>(){});

        Map<String, Object> mapAll = new TreeMap<>();
        mapAll.putAll(map1);
        mapAll.putAll(map2);

        List<String> listDiff = new ArrayList<>();

        for (Map.Entry<String, Object> entry : mapAll.entrySet()) {
            String key = entry.getKey();


        }

    }
}
