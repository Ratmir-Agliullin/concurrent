package cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {

    private Map<String, Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getKeyByIndex(int index) {
        int count=0;
        for(String key:map.keySet()) {
            if (count==index) {
                return key;
            }
            ++count;
        }
        return "nothing";
    }

    public Object getValueByIndex(int index) {
        int count=0;
        for(Object value:map.values()) {
            if (count==index) {
                return value;
            }
            ++count;
        }
        return null;
    }

    public void clearCache() {
        map.clear();
        System.out.println("Cache has been cleared");
    }
}
