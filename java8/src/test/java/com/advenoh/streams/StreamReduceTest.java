package com.advenoh.streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StreamReduceTest {

    @Test
    public void test_sum하기() {
        int sum = Stream.of(3, 2, 7, 23, 34, 8)
                .reduce(0,
                        (subtotal, element) -> subtotal + element);
        assertThat(sum).isEqualTo(77);
    }

    @Test
    public void test_method_reference_sum하기() {
        int sum = Stream.of(3, 2, 7, 23, 34, 8)
                .reduce(0, Integer::sum);
        assertThat(sum).isEqualTo(77);
    }

    /**
     * stream을 병렬로 실행하는 경우에는 stream을 여러 stream으로 분리해서 처리하게 되어
     * 병령 stream 사용시에는 여러 stream을 다시 합칠 수 있는 메서드(combiner)를 추가해줘야 한다
     */
    @Test
    public void test_parallel_sum하기() {
        int sum = Arrays.asList(3, 2, 7, 23, 34, 8)
                .parallelStream()
                .reduce(0,
                        (subtotal, element) -> subtotal + element,
                        (x1, x2) -> x1 + x2); //split된 스트림을 다시 합침
        assertThat(sum).isEqualTo(77);
    }

    @Test
    public void test_max값_찾기() {
        int sum = Stream.of(3, 2, 7, 23, 34, 8)
                .reduce(0, Integer::max);
        assertThat(sum).isEqualTo(34);
    }

    /**
     * reduce 메서드는 초기값을 주지 않으면 null 같은 결과가 나올 수 있기 때문에 optional로 결과 값을 받아와서 처리해야 함
     */
    @Test
    public void test_max값_찾기_초기값이_없는경우() {
        Optional<Integer> optSum = Stream.of(3, 2, 7, 23, 34, 8)
                .reduce(Integer::max);
        assertThat(optSum.get()).isEqualTo(34);
    }

    @Test
    public void test_parallel_words_길이() {
        List<String> wordList = Arrays.asList("a", "bc", "def", "ghjk");
        int totalLength = wordList.parallelStream().reduce(0,
                (Integer total, String word) -> total + word.length(),
                (Integer total1, Integer total2) -> {
                    log.info("total1:{} total2:{}", total1, total2);
                    return total1 + total2;
                });
        assertThat(totalLength).isEqualTo(10);
    }

    @Test
    public void test_긴_string값_찾기() {
        final List<String> names = Arrays.asList("Sehoon", "Songwoo", "Chan", "Youngsuk", "Dajung");

        //java 7
        String longerElement = "";
        for (String name : names) {
            if ("hoone".length() <= name.length()
                    && longerElement.length() <= name.length()) {
                longerElement = name;
            }
        }
        assertThat(longerElement).isEqualTo("Youngsuk");

        //java 8 Lambda
        longerElement = names.stream()
                .reduce("hoone", (name1, name2) ->
                        name1.length() >= name2.length() ? name1 : name2);
        assertThat(longerElement).isEqualTo("Youngsuk");
    }


}
