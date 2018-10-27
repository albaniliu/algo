import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseLog {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("d:\\production.dat");
		BufferedReader bf = new BufferedReader(fr);

		String line = "";
		Map<String, Integer> hitMap = new HashMap<>();
		Map<String, Integer> totalMap = new HashMap<>();
		Map<String, List<String>> reqMap = new HashMap<>();
		Map<String, String> idToKey = new HashMap<>();
		while ((line = bf.readLine()) != null) {
//			System.out.println(line);
			String[] sp = line.split("\t");
			String id = sp[0];
			String type = sp[1];
			String opcode = sp[2];
			String statue = sp[3];
			String key = sp[4];
			if (type.equals("req")) {
				if (opcode.equals("get")) {
					reqMap.put(id, new ArrayList<>());
					reqMap.get(id).add(key);
					idToKey.put(id, key);
				} else if (opcode.equals("getkq")) {
					if (!reqMap.containsKey(id)) {
						reqMap.put(id, new ArrayList<>());
					}
					reqMap.get(id).add(key);
				}
			} else if (type.equals("resp")) {
				if (!reqMap.containsKey(id)) continue;
				if (opcode.equals("get")) {
					if (statue.equals("ok")) {
						key = idToKey.get(id);
						hitMap.put(key, hitMap.getOrDefault(key, 0) + 1);
					}
					for (String tmp: reqMap.get(id)) {
						totalMap.put(tmp, totalMap.getOrDefault(tmp, 0) + 1);
					}
					reqMap.remove(id);
					idToKey.remove(id);
				} else if (opcode.equals("getkq")) {
					if (statue.equals("ok")) {
						hitMap.put(key, hitMap.getOrDefault(key, 0) + 1);
					} 
				} else if (opcode.equals("noop")) {
					for (String tmp: reqMap.get(id)) {
						totalMap.put(tmp, totalMap.getOrDefault(tmp, 0) + 1);
					}
					reqMap.remove(id);
				}
			}
		}
		for (String key: totalMap.keySet()) {
			System.out.println(key + ", " + hitMap.getOrDefault(key, 0) * 1.0 / totalMap.get(key));
		}
	}
}
