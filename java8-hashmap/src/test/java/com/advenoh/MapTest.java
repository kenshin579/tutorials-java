package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * http://tech.javacafe.io/2018/12/03/HashMap/
 */
@Slf4j
public class MapTest {
    private Map<Integer, BigInteger> memoizeHashMap;

    @Before
    public void setUp() throws Exception {
        memoizeHashMap = new HashMap<>() {{
            put(0, BigInteger.ZERO);
            put(1, BigInteger.ONE);
            put(2, BigInteger.ONE);
        }};
    }

    @Test
    public void fibonacci_test() {
        int max = 10;
        for (int i = 0; i < max; i++) {
            log.info("{}", fibonacci(i));
        }
    }

    private BigInteger fibonacci(int n) {
        if (memoizeHashMap.containsKey(n)) {
            return memoizeHashMap.get(n);
        } else {
            BigInteger result = fibonacci(n - 1).add(fibonacci(n - 2));
            memoizeHashMap.put(n, result);
            return result;
        }
    }

    @Test
    public void fibonacci_test2() {
        int max = 10;
        for (int i = 0; i < max; i++) {
            log.info("{}", fibonacciComputeIfAbsent(i));
        }
    }

    private BigInteger fibonacciComputeIfAbsent(int n) {
        return memoizeHashMap.computeIfAbsent(n,
                (key) -> fibonacci(n - 1).add(fibonacci(n - 2)));
    }

    @Test
    public void computeIfAbsent1() {
        Map<String, Integer> stringLengthMap1 = new HashMap<>();
        Map<String, Integer> stringLengthMap2 = new HashMap<>();
        stringLengthMap1.put("John1", 5);

        Integer value1 = stringLengthMap1.computeIfAbsent("John1", String::length);
        assertThat(value1).isEqualTo(5); //존재하면 value값을 반환함

        if (!stringLengthMap2.containsKey("John2")) {
            stringLengthMap2.put("John2", 15);
        }
        Integer value2 = stringLengthMap2.get("John2");
        assertThat(value2).isEqualTo(15);
    }

    @Test
    public void computeIfAbsent2() {
        Map<String, Integer> stringLengthMap = new HashMap<>();
        stringLengthMap.put("John", 5);
        log.info("stringLengthMap1 : {}", stringLengthMap);

        assertThat(stringLengthMap.computeIfAbsent("John", key -> key.length())).isEqualTo(5); //존재하면 value값을 반환함
        assertThat(stringLengthMap.size()).isEqualTo(1);
        log.info("stringLengthMap2 : {}", stringLengthMap);

        //없으면 2번째 인자 함수를 실행한 결과를 반환하고 map에도 추가가 된다
        assertThat(stringLengthMap.computeIfAbsent("John2", key -> key.length())).isEqualTo("John2".length());
        assertThat(stringLengthMap.get("John2")).isNotNull();
        assertThat(stringLengthMap.size()).isEqualTo(2);
        log.info("stringLengthMap3 : {}", stringLengthMap);

        assertThat(stringLengthMap.computeIfAbsent("John3", key -> null)).isNull();
        assertThat(stringLengthMap.size()).isEqualTo(2);
    }

    @Test
    public void putIfAbsent() {
        Map<String, Integer> stringLengthMap = new HashMap<>();
        stringLengthMap.put("John", 5);

        assertThat(stringLengthMap.putIfAbsent("John", 10)).isEqualTo(5); //존재하는 경우, value값을 반환한다
        assertThat(stringLengthMap.size()).isEqualTo(1);

        assertThat(stringLengthMap.putIfAbsent("John2", 10)).isNull(); //없는 경우, null로 반환하고 map에 저장함
        assertThat(stringLengthMap.size()).isEqualTo(2);
    }

    @Test
    public void computeIfPresent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);

        map.computeIfPresent("kelly", (k, v) -> v + 10); //{john=20, paul=30, peter=40} //kelly not present
        assertThat(map.get("kelly")).isNull();

        map.computeIfPresent("peter", (k, v) -> v + 10); //{john=20, paul=30, peter=50} // peter present, so increase the value
        assertThat(map.get("peter")).isEqualTo(40 + 10);
    }

    @Test
    public void compute() {
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);

        map.compute("peter", (k, v) -> v + 50); //{john=20, paul=30, peter=90} //Increase the value
        assertThat(map.get("peter")).isEqualTo(40 + 50);
    }

    @Test
    public void merge() {
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);

        //Adds the key-value pair to the map, if key is not present or value for the key is null
        map.merge("kelly", 50, (k, v) -> map.get("john") + 10); // {john=20, paul=30, peter=40, kelly=50}
        assertThat(map.get("kelly")).isEqualTo(50);
        assertThat(map.size()).isEqualTo(4);

        //Replaces the value with the newly computed value, if the key is present
        map.merge("peter", 50, (k, v) -> map.get("john") + 10); //{john=20, paul=30, peter=30, kelly=50}
        assertThat(map.get("peter")).isEqualTo(30);

        //Key is removed from the map , if new value computed is null
        map.merge("peter", 30, (k, v) -> map.get("nancy")); //{john=20, paul=30, kelly=50}
        assertThat(map.get("peter")).isNull();
        assertThat(map.size()).isEqualTo(3);
    }

    @Test
    public void getOrDefault() {
        String str = "aagbssdf";
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (char c : str.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        for (char c : str.toCharArray()) {
            if (map2.containsKey(c)) {
                map2.put(c, map2.get(c) + 1);
            } else {
                map2.put(c, 1);
            }
        }

        assertThat(map1).isEqualTo(map2);
    }
}
