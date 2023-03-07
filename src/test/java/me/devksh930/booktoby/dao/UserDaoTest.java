package me.devksh930.booktoby.dao;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;


import me.devksh930.booktoby.domain.User;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;


public class UserDaoTest {
    private UserDao dao;

    @BeforeEach
    public void setUp() {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);
    }

    @Test
    @DisplayName("andAndGet 테스트")
    public void andAndGet() throws SQLException {

        User user1 = new User("Devksh930", "cadence", "springno1");
        User user2 = new User("cadence", "devksh930", "springno1");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userGet2 = dao.get(user2.getId());
        assertThat(userGet2.getName(), is(user2.getName()));
        assertThat(userGet2.getPassword(), is(userGet2.getPassword()));
    }

    @Test
    @DisplayName("count 테스트")
    public void count() throws SQLException {
        User user1 = new User("Devksh930", "cadence", "springno1");
        User user2 = new User("cadence", "devksh930", "springno1");
        User user3 = new User("kim", "cadence", "springno1");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test
    @DisplayName("getUserFailure 테스트")
    public void getUserFailure() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->
                dao.get("unkown_id"));
    }
}