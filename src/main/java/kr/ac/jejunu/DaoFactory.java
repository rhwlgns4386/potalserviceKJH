package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(ConnectionMaker());
    }

    @Bean
    public JejuConnetcionMaker ConnectionMaker() {
        return new JejuConnetcionMaker();
    }
}
