package powermock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.replace;

@PrepareForTest({MyClass3.class, Logger.class, ConsoleLogger.class})
@RunWith(PowerMockRunner.class)
public class MyClass3Test {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReplacingStaticMethod() throws Exception {
        replace(method(Logger.class, "debug")).
                with(method(ConsoleLogger.class, "print"));
        new MyClass3().hello("Frank");
    }
}