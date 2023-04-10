package me.devksh930.booktoby.user.learning.factory_bean;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

@ExtendWith(SpringExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/factoryBean-applicationContext.xml")
public class FactoryBeanTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        assertThat(message, isA(Message.class));
        assertThat(((Message) message).getText(), is("Factory Bean"));
    }

    @Test
    public void getFactoryBean() {
        Object message = context.getBean("&message");
        assertThat(message, isA(MessageFactoryBean.class));
    }

}
