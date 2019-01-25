package com.java.examples;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    private static final Logger LOG = LoggerFactory.getLogger(PersonTest.class);

    @Test(expected = NullPointerException.class)
    public void testOldJavStyle_throw_NPE() {
        String str = null;
        System.out.println(str.charAt(0)); //NPE 발생
    }

    @Test
    public void testOldJavStyle_checkNull() {
        String str = "test";
        if (str != null) {
            System.out.println(str.charAt(0));
        }
    }

    @Test
    public void testOptionalJavStyle_checkNull() {
        String str = "test";
        Optional<String> opText = Optional.ofNullable(str);
        opText.ifPresent(s -> System.out.println(s.charAt(0)));
    }

    @Test
    public void test_1_creating_empty_optional() {
        Optional<String> optStr = Optional.empty();
    }

    @Test(expected = NullPointerException.class)
    public void test_2_creating_optional_of() {
        String str = "test";
        Optional<String> optStr1 = Optional.of(str);

        String nullStr = null;
        Optional<String> optStr2 = Optional.of(nullStr); //NPE 발생
    }

    @Test
    public void test_3_creating_optional_ofnullable() {
        String str = "test";
        Optional<String> optStr1 = Optional.ofNullable(str);

        Optional<String> optStr2 = Optional.ofNullable(null); //empty Optional 객체를 반환함
    }

    @Test
    public void test_1_otional_usage_ifPresent() {
        String str = "test";
        Optional<String> optStr1 = Optional.ofNullable(str);
        optStr1.ifPresent(s -> System.out.println(s.charAt(0))); //t 프린트

        Optional<String> optStr2 = Optional.ofNullable(null);
        optStr2.ifPresent(s -> System.out.println(s.charAt(0))); //print 안됨
    }

    @Test
    public void test_2_otional_usage_orElse() {
        Optional<String> optStr = Optional.ofNullable(null);
        String result = optStr.orElse("test");
        System.out.println(result); //test
    }

    @Test
    public void test_2_otional_usage_orElseGet() {
        Optional<String> optStr = Optional.ofNullable(null);
        String result = optStr.orElseGet(this::getDefaultValue);
        System.out.println(result); //default
    }

    @Test
    public void test_optional_usage_diff_orElse_orElseGet() {
        String str = "test";
        String result1 = Optional.ofNullable(str).orElse(getDefaultValue()); //null이 아니여도 getDefaultValue() 함수는 실행함
        LOG.info("orElse result: {}", result1);

        String result2 = Optional.ofNullable(str).orElseGet(this::getDefaultValue);// getDefaultValue() 실행하지 않음
        LOG.info("orElseGet result: {}", result2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_3_optional_usage_orElseThrow() {
        String str = null;
        String result = Optional.ofNullable(str).orElseThrow(IllegalArgumentException::new);
        LOG.info("result {}", result);
    }

    @Test
    public void test_1_non_stream_usage_filter_with_optional() {
        Map<Person, Boolean> personVsExpectedValueMap = new HashMap<Person, Boolean>() {{
            put(new Person("Frank", "Oh"), true);
            put(new Person(null, "Oh"), false);
            put(new Person("David", "Oh"), false);
            put(new Person("John", "Oh"), false);
        }};

        boolean expectedResult;
        for (Person person : personVsExpectedValueMap.keySet()) {
            expectedResult = personVsExpectedValueMap.get(person);
            assertEquals(expectedResult, isLastNameFrank(person));
        }
    }

    @Test
    public void test_1_stream_usage_filter_with_optional() {
        Map<Person, Boolean> personVsExpectedValueMap = new HashMap<Person, Boolean>() {{
            put(new Person("Frank", "Oh"), true);
            put(new Person(null, "Oh"), false);
            put(new Person("David", "Oh"), false);
            put(new Person("John", "Oh"), false);
        }};

        boolean expectedResult;
        for (Person person : personVsExpectedValueMap.keySet()) {
            expectedResult = personVsExpectedValueMap.get(person);
            assertEquals(expectedResult, isLastNameFrankWithStream(person));
        }
    }

    @Test
    public void test_2_stream_usage_map_with_optional() {
        List<String> strArray = Arrays.asList("frank", "angela", "david");
        Optional<List<String>> optArray = Optional.of(strArray);

        int size = optArray.map(List::size).orElse(0);
        assertEquals(3, size);

        optArray = Optional.ofNullable(null);
        size = optArray.map(List::size).orElse(1);
        assertEquals(1, size);
    }

    @Test
    public void test_1_funtional_interface_predicate() {
        Predicate<String> stringCompare = (String str) -> str.compareTo("abc") == 0 ? true : false;
        System.out.println("Predicate test for abc=" + stringCompare.test("abc"));
    }

    @Test
    public void test_3_stream_usage_flatMap() {
        PersonOpt person = new PersonOpt("Oh", "Frank");
        Optional<PersonOpt> personOpt = Optional.of(person);

        Character firstCharOfFirstName = personOpt
                .flatMap(PersonOpt::getFirstName) //getFirstName의 반환값이 Optional이기 때문에 flatMap을 사용해야 함
                .map(s -> s.charAt(0)) //요소가 pritimive 타입이기 때문에 map을 사용함
                .orElse('0');
        assertEquals(new Character('F'), firstCharOfFirstName);
    }

    @Test
    public void test_filter_sample() {
        List<String> strArray = Arrays.asList("frank", "abc", "hello", "word");
        strArray.stream()
                .filter(s -> s.length() > 3)
                .forEach(System.out::println);
    }

    /**
     * https://jaepils.github.io/java/2018/06/27/java-time-Instant.html
     */
    @Test
    public void test_stream_map_vs_flatMap_1() {
        String[][] data = new String[][]{{"1", "2"}, {"3", "4"}};

        System.out.println("map");
        Stream<Stream<String>> map = Arrays.stream(data).map(x -> Arrays.stream(x));
        map.forEach(s -> s.forEach(System.out::println));

        System.out.println("flatmap");
        Stream<String> flatmap = Arrays.stream(data).flatMap(x -> Arrays.stream(x));
        flatmap.forEach(System.out::println);
    }

    @Test
    public void test_stream_map_vs_flatMap_2() {
        LOG.info("map");
        Optional<String> stringOptional = Optional.ofNullable("hi");
        Optional<Integer> integerOptional = stringOptional.map(s -> {
            LOG.info("s: {}", s);
            return s.length();
        });
        LOG.info("{}", integerOptional.get());
        Optional<Integer> integerOptional2 = stringOptional.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });
        Optional<String> stringOptional2 = stringOptional.map(s -> " java");

        LOG.info("flatMap");
        stringOptional = Optional.ofNullable("hi");
        integerOptional = stringOptional.flatMap(s -> {
            LOG.info("s: {}", s);
            return Optional.of(s.length()); //map은 요소를 반환하지만, flatMap은 Optional을 반환함
        });
        LOG.info("{}", integerOptional.get());
    }

    private boolean isLastNameFrank(Person person) {
        if (person != null && person.getLastName() != null) {
            return person.getLastName()
                    .toLowerCase().equals("frank");
        }
        return false;
    }

    private boolean isLastNameFrankWithStream(Person person) {
        return Optional.ofNullable(person)
                .map(Person::getLastName)
                .map(String::toLowerCase)
                .filter(s -> s.equals("frank")).isPresent();
    }

    private String getDefaultValue() {
        LOG.info("calling getDefaultValue");
        return "default";
    }

    @Test
    public void test_jdk9_optional_or() {
        String str = null;
        Optional<String> defaultOpt = Optional.of("default");
        Optional<String> strOpt = Optional.ofNullable(str);

        Optional<String> result = strOpt.or(() -> defaultOpt);
        assertEquals("default", result.get());
    }

    @Test
    public void test_jdk9_ifPresentOrElse() {
        String str = null;
        Optional<String> strOpt = Optional.ofNullable(str);
        strOpt.ifPresentOrElse(
                s -> System.out.println("string : " + s),
                () -> System.out.println("null value")
        );
    }

    @Test
    public void test_jdk9_stream() {
        String str = "frank";
        Optional<String> strOpt = Optional.of(str);
        strOpt.stream().map(String::toUpperCase).forEach(System.out::println);
    }
}