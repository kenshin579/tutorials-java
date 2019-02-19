package com.advenoh.examples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.replace;
import static org.powermock.api.support.membermodification.MemberModifier.stub;

@PrepareForTest(MyClass1.class)
@RunWith(PowerMockRunner.class)
public class MyClass1Test {

    @Test
    public void testStubbingStaticMethod() throws Exception {
        stub(method(MyClass1.class, "hello")).toReturn("Hello World");
        assertEquals("Hello World", MyClass1.hello("Frank Oh"));
    }

    @Test
    public void testStubbingPrivateMethod() throws Exception {
        stub(method(MyClass1.class, "goodbye")).toReturn("Something");
        assertEquals("Something", new MyClass1().goodByeWrapper("Frank Oh"));
    }

    @Test
    public void testReplacingStaticMethod() throws Exception {
        replace(method(MyClass1.class, "hello")).with(
                new InvocationHandler() {
                    public Object invoke(Object object, Method method,
                                         Object[] arguments) throws Throwable {
                        if (arguments[0].equals("John")) {
                            return "Hello John, you are awesome!";
                        } else {
                            return method.invoke(object, arguments);
                        }
                    }
                });

        assertEquals("Hello Someone", MyClass1.hello("Someone"));
        assertEquals("Hello John, you are awesome!", MyClass1.hello("John"));

    }

    @Test
    public void testReplacingNonStaticMethod() throws Exception {
        replace(method(MyClass1.class, "nonStaticHello")).with(
                new InvocationHandler() {
                    public Object invoke(Object object, Method method,
                                         Object[] arguments) throws Throwable {
                        if (arguments[0].equals("Frank")) {
                            return "NonStatic Hello John, you are awesome!";
                        } else {
                            return method.invoke(object, arguments);
                        }
                    }
                });

        assertEquals("NonStatic Hello Someone", new MyClass1().nonStaticHello("Someone"));
        assertEquals("NonStatic Hello John, you are awesome!", new MyClass1().nonStaticHello("Frank"));

    }

}