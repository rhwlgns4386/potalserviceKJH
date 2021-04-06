package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;
    public UserDao(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public User findById(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select *from userinfo where id=?");
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            if(result.next()){
                user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
            }


        } finally {
            try {
                result.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }
            return user;
    }
    public User insert(User user) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("insert into userinfo(name,password) value(?,?)",
                    Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            } finally {
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }
        return user;
    }

    public void update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("update userinfo set name=?, password=? where id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3,user.getId());

            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("delete from userinfo where id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } finally {

            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }
    }

//    public Connection getConnection() throws ClassNotFoundException, SQLException{
//        return connetcionMaker.getConnection();
//    }
}