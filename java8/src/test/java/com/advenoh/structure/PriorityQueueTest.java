package com.advenoh.structure;

import com.advenoh.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PriorityQueueTest {
    @Test
    public void test_minHeap() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(10);
        minHeap.add(5);

        assertThat(minHeap.poll()).isEqualTo(1);
        assertThat(minHeap.poll()).isEqualTo(3);
    }

    @Test
    public void test_maxHeap() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(10);
        minHeap.add(5);

        assertThat(minHeap.poll()).isEqualTo(10);
        assertThat(minHeap.poll()).isEqualTo(5);
    }

    @Test
    public void test_student_age() {
        PriorityQueue<Student> studentAgeHeap = new PriorityQueue<>();

        studentAgeHeap.add(new Student("Frank", 23));
        studentAgeHeap.add(new Student("Angela", 10));
        studentAgeHeap.add(new Student("David", 30));
        studentAgeHeap.add(new Student("Joe", 15));

//        assertThat(studentAgeHeap.poll()).isEqualTo();

    }
}
