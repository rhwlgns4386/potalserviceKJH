package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/jeju?" +
                "characterEncoding=utf-8&serverTimezone=UTC","jeju","jejupw");
        PreparedStatement preparedStatement=connection.prepareStatement("select *from userinfo where id=?");
        preparedStatement.setInt(1,id);
        ResultSet result=preparedStatement.executeQuery();
        result.next();

        User user=new User();
        user.setId(result.getInt("id"));
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));

        result.close();
        preparedStatement.close();
        connection.close();
        return user;
    }
}
