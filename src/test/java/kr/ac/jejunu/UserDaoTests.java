package kr.ac.jejunu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;


public class UserDaoTests {

    static UserDao userDao;

    @BeforeAll
    public  static void setup(){
        //ApplicationContext applicationContext=new ClassPathXmlApplicationContext("daoFactory.xml");
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext("kr.ac.jejunu");
        userDao=applicationContext.getBean("userDao",UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer id=1;
        String name="hulk";
        String password="1234";
//        DaoFactory daoFactory=new DaoFactory();
//        UserDao userDao=daoFactory.userDao();
        User user=userDao.findById(id);

        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name="허윤호";
        String password="1111";
        //User user=User.builder().name(name).password(password).build();
        User user=new User();
        user.setName(name);

        user.setPassword(password);
//        DaoFactory daoFactory=new DaoFactory();
//        UserDao userDao=daoFactory.userDao();
        userDao.insert(user);

        User insertedUser=userDao.findById(user.getId());

        assertThat(insertedUser.getId(),greaterThan(0));
        assertThat(insertedUser.getId(),is(user.getId()));
        assertThat(insertedUser.getPassword(),is(user.getPassword()));
        assertThat(insertedUser.getName(),is(user.getName()));

    }

    @Test
    public void update() throws SQLException {
        User user=new User();
        String name="허윤호";
        String password="1111";
        user.setName(name);
        user.setPassword(password);
//        DaoFactory daoFactory=new DaoFactory();
//        UserDao userDao=daoFactory.userDao();
        userDao.insert(user);

        user.setName("hulk");
        user.setPassword("1234");

        userDao.update(user);

        User updateUser=userDao.findById(user.getId());

        assertThat(updateUser.getId(),is(user.getId()));
        assertThat(updateUser.getName(),is(user.getName()));
        assertThat(updateUser.getPassword(),is(user.getPassword()));

    }

    @Test
    public void delete() throws SQLException {
        User user=new User();
        String name="허윤호";
        String password="1111";
        user.setName(name);

        user.setPassword(password);
//        DaoFactory daoFactory=new DaoFactory();
//        UserDao userDao=daoFactory.userDao();
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser= userDao.findById(user.getId());

        assertThat(deletedUser,nullValue());
    }

//    @Test
//    public void Hallaget() throws SQLException, ClassNotFoundException {
//        Integer id=1;
//        String name="hulk";
//        String password="1234";
//        UserDao userDao=new UserDao(new HallaConnetcionMaker());
//        User user=userDao.findById(id);
//
//        assertThat(user.getId(),is(id));
//        assertThat(user.getName(),is(name));
//        assertThat(user.getPassword(),is(password));
//    }
//
//    @Test
//    public void Hallainsert() throws SQLException, ClassNotFoundException {
//        User user=new User();
//        String name="허윤호";
//        String password="1111";
//        user.setName(name);
//
//        user.setPassword(password);
//        UserDao userDao=new UserDao(new HallaConnetcionMaker());
//        userDao.insert(user);
//
//        User insertedUser=userDao.findById(user.getId());
//
//        assertThat(insertedUser.getId(),greaterThan(0));
//        assertThat(insertedUser.getId(),is(user.getId()));
//        assertThat(insertedUser.getPassword(),is(user.getPassword()));
//        assertThat(insertedUser.getName(),is(user.getName()));
//
//    }
}
