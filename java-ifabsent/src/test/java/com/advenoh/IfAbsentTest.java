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
public class IfAbsentTest {
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
    public void computeIfAbsent() {
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
}
