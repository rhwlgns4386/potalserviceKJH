package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    public JejuConnetcionMaker getConnectionMaker() {
        return new JejuConnetcionMaker();
    }
}
