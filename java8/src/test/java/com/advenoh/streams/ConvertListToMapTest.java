package com.advenoh.streams;

import com.advenoh.Student;
import com.advenoh.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ConvertListToMapTest {

	@Test
	public void collectorsToMap() {
		int max = 3;
		List<Student> students = TestUtil.getStudentSample(max);

		Map<String, Integer> nameVsAgeMap1 = students
				.stream()
				.collect(Collectors.toMap(
						i1 -> i1.getName(),
						i2 -> i2.getAge())
				);

		assertThat(nameVsAgeMap1.size()).isEqualTo(max);
		log.info("nameVsAgeMap1 : {}", nameVsAgeMap1);

		//method reference
		Map<String, Integer> nameVsAgeMap2 = students
				.stream()
				.collect(Collectors.toMap(
						Student::getName,
						Student::getAge)
				);

		assertThat(nameVsAgeMap2.size()).isEqualTo(max);

	}

	@Test
	public void 중복키가_존재하는_경우_IllegalStateException_발생() {
		int max = 3;
		List<Student> students = TestUtil.getStudentSample(max);
		students.add(new Student("name1", 30)); //중복 이름 추가

		//throw 발생함 - java.lang.IllegalStateException: Duplicate key
		assertThatThrownBy(() -> students.stream()
				.collect(Collectors.toMap(
						Student::getName,
						Student::getAge)
				)).hasMessage("Duplicate key name1 (attempted merging values 11 and 30)");
	}

	@Test
	public void 중복키가_존재하는_경우_3rd_인자에_merge함수로_해결() {
		int max = 3;
		List<Student> students = TestUtil.getStudentSample(max);
		students.add(new Student("name1", 30)); //중복 이름 추가

		Map<String, Integer> nameVsAgeMap = students
				.stream()
				.collect(Collectors.toMap(
						Student::getName,
						Student::getAge,
						(oldValue, newValue) -> {
							log.info("oldValue : {} newValue : {}", oldValue, newValue);
							return oldValue;
						})
				);

		assertThat(nameVsAgeMap.size()).isEqualTo(max);

	}

}
