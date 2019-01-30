package in28minutes.junit.suite;

import in28minutes.junit.helper.ArraysTest;
import in28minutes.junit.helper.StringHelperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ArraysTest.class, StringHelperTest.class })
public class DummyTestSuite {

}