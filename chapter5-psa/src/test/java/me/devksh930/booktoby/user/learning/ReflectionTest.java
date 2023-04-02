package me.devksh930.booktoby.user.learning;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReflectionTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        assertThat(name.length(), is(6));

        Method lengthMethod = String.class.getMethod("length");
        assertThat(lengthMethod.invoke(name), is(6));

        assertThat(name.charAt(0), is('S'));

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat(charAtMethod.invoke(name, 0), is('S'));
    }

    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("devksh930"), is("Hello devksh930"));
        assertThat(hello.sayHi("devksh930"), is("Hi devksh930"));
        assertThat(hello.sayThankYou("devksh930"), is("Thank You devksh930"));

        Hello proxiedHello = new HelloUppercase(new HelloTarget());
        assertThat(proxiedHello.sayHello("devksh930"), is("HELLO DEVKSH930"));
        assertThat(proxiedHello.sayHi("devksh930"), is("HI DEVKSH930"));
        assertThat(proxiedHello.sayThankYou("devksh930"), is("THANK YOU DEVKSH930"));
    }

    @Test
    public void invocationHandler() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UpperCaseHandler(new HelloTarget())
        );
        assertThat(proxiedHello.sayHello("devksh930"), is("HELLO DEVKSH930"));
        assertThat(proxiedHello.sayHi("devksh930"), is("HI DEVKSH930"));
        assertThat(proxiedHello.sayThankYou("devksh930"), is("THANK YOU DEVKSH930"));
    }
}
