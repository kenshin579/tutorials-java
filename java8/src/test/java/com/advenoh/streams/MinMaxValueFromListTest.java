package com.advenoh.streams;

import com.advenoh.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MinMaxValueFromListTest {
    /**
     * https://www.baeldung.com/java-collection-min-max
     */
    @Test
    public void 숫자_list_max_값_찾기() {
        List<Integer> intList = Arrays.asList(2, 3, 6, 4, 10, 23);
        Integer maxValue = intList.stream()
                .mapToInt(x -> x)
                .max()
                .orElseThrow(NoSuchElementException::new);

        assertThat(maxValue).isEqualTo(23);
    }

    @Test
    public void 숫자_array_max_값_찾기() {
        int[] intArr = {3, 2, 6, 10, 234};
        Integer maxValue = Arrays.stream(intArr)
                .max()
                .getAsInt();
        assertThat(maxValue).isEqualTo(234);
    }

    @Test
    public void 객체_list에서_나이가_max_값_찾기() {
        int max = 5;
        List<Student> students = IntStream.range(0, max)
                .mapToObj(i -> new Student("name" + i, i + 10))
                .peek(i -> log.info("{}", i))
                .collect(Collectors.toList());

//        Comparator<Student> comparatorByAge = (x1, x2) -> Integer.compare(x1.getAge(), x2.getAge());
        Comparator<Student> comparatorByAge = Comparator.comparingInt(Student::getAge);

        Student studentWithMaxAge = students.stream()
                .max(comparatorByAge)
                .orElseThrow(NoSuchElementException::new);

        assertThat(studentWithMaxAge.getAge()).isEqualTo(14);
    }

    @Test
    public void array_str에서_가장_긴_string의_길이_찾기() {
        String[] lines = {"Hello", "My", "World11"};
        int maxWidth = Arrays.stream(lines).mapToInt(String::length).max().getAsInt();
        assertThat(maxWidth).isEqualTo(7);
    }
}