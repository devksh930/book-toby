package me.devksh930.booktoby;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class SpringContextJunitTest {
    @Autowired
    ApplicationContext context;

    static Set<SpringContextJunitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test
    public void test1() {
        assertThat(testObjects,not(hasItem(this)));
        testObjects.add(this);
        assertThat(contextObject==null|| contextObject==this.context,is(true));
        contextObject = this.context;
    }

    @Test
    public void test2() {
        assertThat(testObjects,not(hasItem(this)));
        testObjects.add(this);
        assertThat(contextObject==null|| contextObject==this.context,is(true));
        contextObject = this.context;
    }

    @Test
    public void test3() {
        assertThat(testObjects,not(hasItem(this)));
        testObjects.add(this);
        assertThat(contextObject==null|| contextObject==this.context,is(true));
        contextObject = this.context;
    }
}
