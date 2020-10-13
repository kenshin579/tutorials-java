package com.advenoh.streams;

import com.advenoh.Student;
import com.advenoh.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ConvertListToMapTest {

	@Test
	public void collectorsToMap() {
		List<Student> students = TestUtil.getStudentSample(3);
		assertThat(students.size()).isEqualTo(3);
	}
}
