import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryCatalogManager {

    public static void main(String[] args) {
        System.out.println("=== Inventory Catalog Manager Started ===");
        benchmarkSynchronizedMap();
        benchmarkConcurrentMap();  
        System.out.println("=== Inventory Catalog Manager Finished ===");
    }

    public static void benchmarkSynchronizedMap() {
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>()); 
        syncMap.put("PROD-001", 1000);
        int stock = syncMap.get("PROD-001");  
        System.out.println("[1] Synchronized Map Stock: " + stock);
    }

    public static void benchmarkConcurrentMap() { 
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();  
        concurrentMap.put("PROD-001", 1000);
        int stock = concurrentMap.get("PROD-001"); 
        System.out.println("[2] ConcurrentHashMap Stock: " + stock);
    }
}