package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

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

        Map<String, Object> map1 = mapper.readValue(json1, new TypeReference<Map<String, Object>>() { });
        Map<String, Object> map2 = mapper.readValue(json2, new TypeReference<Map<String, Object>>() { });

        List<Map<String, Object>> resultList = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(map1.keySet());

        keysSet.addAll(map2.keySet());

        for (String key : keysSet) {
            Map<String, Object> temp = new LinkedHashMap<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                temp.put("key", key);
                temp.put("value1", map1.get(key));
                temp.put("status", "removed");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                temp.put("key", key);
                temp.put("value2", map2.get(key));
                temp.put("status", "added");
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                temp.put("key", key);
                temp.put("value1", map1.get(key));
                temp.put("value2", map2.get(key));
                temp.put("status", "updated");
            } else {
                temp.put("key", key);
                temp.put("value1", map1.get(key));
                temp.put("status", "unchanged");
            }
            resultList.add(temp);
        }
        String result = objectMapper.writeValueAsString(resultList);

        return result;
    }
}
