package api.utils.config;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMapHelper {
    private static ObjectToMapHelper instance;

    private ObjectToMapHelper() {

    }

    public static ObjectToMapHelper getInstance() {

        if (instance == null) {
            instance = new ObjectToMapHelper();
        }

        return instance;
    }

    public Map<String, String> objectToQueryParams(Object obj) {
        Map<String, String> queryParams = new HashMap<>();
        if (obj == null) return queryParams;

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null) {
                    queryParams.put(field.getName(), value.toString());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to extract query parameters from object", e);
            }
        }
        return queryParams;
    }
}
