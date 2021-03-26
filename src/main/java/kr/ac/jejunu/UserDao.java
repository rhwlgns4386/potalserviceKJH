package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    private final ConnetcionMaker connetcionMaker;
    public UserDao(ConnetcionMaker connetcionMaker){
        this.connetcionMaker=connetcionMaker;
    }

    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = connetcionMaker.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select *from userinfo where id=?");
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        result.next();

        User user = new User();
        user.setId(result.getInt("id"));
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));

        result.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public User insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = connetcionMaker.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name,password) value(?,?)",
                Statement.RETURN_GENERATED_KEYS);


        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        return connetcionMaker.getConnection();
    }
}