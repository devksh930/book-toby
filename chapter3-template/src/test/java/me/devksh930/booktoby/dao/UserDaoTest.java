package me.devksh930.booktoby.dao;


import lombok.extern.slf4j.Slf4j;
import me.devksh930.booktoby.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@Slf4j
//@DirtiesContext
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;
    private UserDao dao;

    @BeforeEach
    public void setUp() {
        this.dao = this.context.getBean("userDao", UserDao.class);
//        DataSource dataSource = new SingleConnectionDataSource(
//                "jdbc:mysql://localhost/test",
//                "root",
//                "1234",
//                true
//        );
//        dao.setDataSource(dataSource);
        System.out.println(this);
        System.out.println(this.context);
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