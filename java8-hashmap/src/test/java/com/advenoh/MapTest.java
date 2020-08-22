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
    public void no_computeIfAbsent_method() {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        map1.put("John1", 5);

        Integer value1 = map1.computeIfAbsent("John1", String::length);
        assertThat(value1).isEqualTo(5); //존재하면 value값을 반환함

        if (!map2.containsKey("John2")) {
            map2.put("John2", 15);
        }
        Integer value2 = map2.get("John2");
        assertThat(value2).isEqualTo(15);
    }

    @Test
    public void computeIfAbsent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 5);

        assertThat(map.computeIfAbsent("John", key -> key.length())).isEqualTo(5); //존재하면 value값을 반환함
        assertThat(map.size()).isEqualTo(1);

        //없으면 2번째 인자 함수를 실행한 결과를 반환하고 map에도 추가가 된다
        assertThat(map.computeIfAbsent("John2", key -> key.length())).isEqualTo("John2".length());
        assertThat(map.get("John2")).isNotNull();
        assertThat(map.size()).isEqualTo(2);

        assertThat(map.computeIfAbsent("John3", key -> null)).isNull();
        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    public void putIfAbsent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 5);

        assertThat(map.putIfAbsent("John", 10)).isEqualTo(5); //존재하는 경우, value값을 반환한다
        assertThat(map.size()).isEqualTo(1);

        assertThat(map.putIfAbsent("John2", 10)).isNull(); //없는 경우, null로 반환하고 map에 저장함
        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    public void computeIfPresent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);

        map.computeIfPresent("kelly", (k, v) -> v + 10);
        assertThat(map.get("kelly")).isNull();

        map.computeIfPresent("peter", (k, v) -> v + 10);
        assertThat(map.get("peter")).isEqualTo(40 + 10);
    }

    @Test
    public void compute() {
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);

        map.compute("peter", (k, v) -> v + 50);
        assertThat(map.get("peter")).isEqualTo(40 + 50);
    }

    @Test
    public void merge() {
        Map<String, Integer> map = new HashMap<>();
        map.put("john", 20);
        map.put("paul", 30);
        map.put("peter", 40);

        //key값이 존재를 하면, 해당 key의 값을 remapping 함수의 결과 값으로 바꾼다
        map.merge("peter", 50, (k, v) -> map.get("john") + 10);
        assertThat(map.get("peter")).isEqualTo(30);

        //key가 존재하고 remapping 함수의 결과가 null이면 map에서 해당 key를 삭제한다
        map.merge("peter", 30, (k, v) -> map.get("nancy"));
        assertThat(map.get("peter")).isNull();
        assertThat(map.size()).isEqualTo(3);

        //key가 존재하지 않으면 key, value값을 추가함
        map.merge("kelly", 50, (k, v) -> map.get("john") + 10);
        assertThat(map.get("kelly")).isEqualTo(50);
        assertThat(map.size()).isEqualTo(4);

    }

    @Test
    public void getOrDefault() {
        String str = "aagbssdf";
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        //getOrDefault 사용하지 않는 경우
        for (char c : str.toCharArray()) {
            if (map2.containsKey(c)) {
                map2.put(c, map2.get(c) + 1);
            } else {
                map2.put(c, 1);
            }
        }

        //getOrDefault 사용하는 경우
        for (char c : str.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        assertThat(map1).isEqualTo(map2);
    }
}
