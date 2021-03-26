package kr.ac.jejunu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;


public class UserDaoTests {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer id=1;
        String name="hulk";
        String password="1234";
        DaoFactory daoFactory=new DaoFactory();
        UserDao userDao=new UserDao(daoFactory.getConnectionMaker());
        User user=userDao.findById(id);

        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user=new User();
        String name="허윤호";
        String password="1111";
        user.setName(name);

        user.setPassword(password);
        DaoFactory daoFactory=new DaoFactory();
        UserDao userDao=new UserDao(daoFactory.getConnectionMaker());
        userDao.insert(user);

        User insertedUser=userDao.findById(user.getId());

        assertThat(insertedUser.getId(),greaterThan(0));
        assertThat(insertedUser.getId(),is(user.getId()));
        assertThat(insertedUser.getPassword(),is(user.getPassword()));
        assertThat(insertedUser.getName(),is(user.getName()));

    }

    @Test
    public void Hallaget() throws SQLException, ClassNotFoundException {
        Integer id=1;
        String name="hulk";
        String password="1234";
        UserDao userDao=new UserDao(new HallaConnetcionMaker());
        User user=userDao.findById(id);

        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void Hallainsert() throws SQLException, ClassNotFoundException {
        User user=new User();
        String name="허윤호";
        String password="1111";
        user.setName(name);

        user.setPassword(password);
        UserDao userDao=new UserDao(new HallaConnetcionMaker());
        userDao.insert(user);

        User insertedUser=userDao.findById(user.getId());

        assertThat(insertedUser.getId(),greaterThan(0));
        assertThat(insertedUser.getId(),is(user.getId()));
        assertThat(insertedUser.getPassword(),is(user.getPassword()));
        assertThat(insertedUser.getName(),is(user.getName()));

    }
}
