import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorTestDrive {

    Map<Integer, String> testMap1;
    Map<Integer, String> testMap2;

    public void removeFromKeyObj() {

        Iterator<Integer> itKey = testMap1.keySet().iterator();

        while (itKey.hasNext()) {

            itKey.next();
            itKey.remove();

        }

        System.out.println(" TestMap1 Size : " + testMap1.size());
    }

    public void removeFromKeyFromLoop() {

        Iterator<Integer> itKey = testMap2.keySet().iterator();

        while (itKey.hasNext()) {

            Integer index = itKey.next();
            testMap2.remove(index);

        }

        System.out.println(" TestMap2 Size : " + testMap2.size());
    }

    public void initTable() {
        System.out.println("initTable");
        this.testMap1 = new HashMap<Integer, String>();
        this.testMap2 = new HashMap<Integer, String>();

        for (int i = 0; i < 100; i++) {
            testMap1.put(i, i + "우헤");
            testMap2.put(i + 100, (i + 100) + "우헤");
        }

    }

    public static void main(String[] args) {
//		Map<String, Integer> map = new HashMap<String, Integer>();

//		map.put("1", 1);
//		map.put("2", 2);
//		map.put("3", 3);
//		map.put("1", 5);
//		Set<String> set = map.keySet();
//
//		for (String id : set) {
//			System.out.println("["+id+"]" + map.get(id));
//		}
//
//		boolean res1 = false, res2 = true;
//		System.out.println("result: " + (res1 && res2));


        IteratorTestDrive test = new IteratorTestDrive();
        test.initTable();
//		test.removeFromKeyObj();
//		test.removeFromKeyFromLoop();
    }

}