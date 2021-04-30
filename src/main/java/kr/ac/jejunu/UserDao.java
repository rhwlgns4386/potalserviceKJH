package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.*;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate ;

//    @Autowired
//    public UserDao(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate=jdbcTemplate;
//    }


    public User findById(Integer id)  {
        String test="테스트";
        System.out.println(test);
        String sql="select *from userinfo where id=?";
//        Object[] param=new Object[]{id};

        return jdbcTemplate.query(sql,rs -> {
            User user = null;
            if(rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));}
            return user;
        },id);
    }



    public void insert(User user) throws SQLException{
        String sql="insert into userinfo(name,password) value(?,?)";
        Object[] param=new Object[]{user.getName(),user.getPassword()};

        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < param.length; i++) {
                preparedStatement.setObject(i + 1, param[i]);
            }
            return preparedStatement;
        },keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }



    public void update(User user) throws SQLException {
        String sql="update userinfo set name=?, password=? where id=?";
        Object[] param=new Object[]{user.getName(),user.getPassword(),user.getId()};
        jdbcTemplate.update(sql, param);
    }



    public void delete(Integer id) throws SQLException {
        String sql="delete from userinfo where id=?";
        Object[] param=new Object[]{id};

        jdbcTemplate.update(sql,param);
    }


}