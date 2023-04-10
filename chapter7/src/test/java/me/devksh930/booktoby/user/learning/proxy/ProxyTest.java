package me.devksh930.booktoby.user.learning.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProxyTest {
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

    @Test
    public void proxyFactoryBean() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());


        Hello proxiedHello = (Hello) pfBean.getObject();
        assertThat(proxiedHello.sayHello("devksh930"), is("HELLO DEVKSH930"));
        assertThat(proxiedHello.sayHi("devksh930"), is("HI DEVKSH930"));
        assertThat(proxiedHello.sayThankYou("devksh930"), is("THANK YOU DEVKSH930"));
    }

    @Test
    public void pointcutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) pfBean.getObject();
        assertThat(proxiedHello.sayHello("devksh930"), is("HELLO DEVKSH930"));
        assertThat(proxiedHello.sayHi("devksh930"), is("HI DEVKSH930"));
        assertThat(proxiedHello.sayThankYou("devksh930"), is("Thank You devksh930"));
    }

    @Test
    public void classNamePointcutAdvisor() {
        NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
            public ClassFilter getClassFilter() {
                return clazz -> clazz.getSimpleName().startsWith("HelloT");
            }
        };
        classMethodPointcut.setMappedName("sayH*");
        checkAdviced(new HelloTarget(), classMethodPointcut, true);

        class HelloWorld extends HelloTarget {
        }
        ;
        checkAdviced(new HelloWorld(), classMethodPointcut, false);

        class HelloToby extends HelloTarget {
        }
        ;
        checkAdviced(new HelloToby(), classMethodPointcut, true);
    }

    private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();

        if (adviced) {
            assertThat(proxiedHello.sayHello("devksh930"), is("HELLO DEVKSH930"));
            assertThat(proxiedHello.sayHi("devksh930"), is("HI DEVKSH930"));
            assertThat(proxiedHello.sayThankYou("devksh930"), is("Thank You devksh930"));
        } else {
            assertThat(proxiedHello.sayHello("devksh930"), is("Hello devksh930"));
            assertThat(proxiedHello.sayHi("devksh930"), is("Hi devksh930"));
            assertThat(proxiedHello.sayThankYou("devksh930"), is("Thank You devksh930"));
        }
    }

}
