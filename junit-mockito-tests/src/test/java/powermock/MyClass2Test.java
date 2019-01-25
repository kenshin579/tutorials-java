package powermock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNull;
import static org.powermock.api.support.membermodification.MemberMatcher.*;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;


@PrepareForTest(MyClass2.class)
@RunWith(PowerMockRunner.class)
public class MyClass2Test {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSupressingConstructorMethod() throws Exception {
        suppress(constructor(MyClass2.class));
        assertNull(new MyClass2().getStr());
    }

    @Test
    public void testSupressingAllConstructorMethod() throws Exception {
        suppress(constructorsDeclaredIn(MyClass2.class));
        assertNull(new MyClass2("test").getStr());
    }

    @Test
    public void testSupressingAllMethods() throws Exception {
        suppress(methodsDeclaredIn(MyClass2.class));
        assertNull(new MyClass2("test").getStr());
    }

    @Test
    public void testSupressingEntireClass() throws Exception {
        suppress(everythingDeclaredIn(MyClass2.class));
        assertNull(new MyClass2("test").getStr());
    }

}