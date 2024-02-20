package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.UserDAO;
import lk.ijse.layardproject.db.DbConnection;
import lk.ijse.layardproject.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) throws SQLException {
        String sql = "insert into user(userId,userName,password) values(?,?,?);";
        return SQLUtil.execute(sql,user.getUserId(),user.getUserName(),user.getPassword());
    }

    @Override
    public User search(String searchIdText) throws SQLException {
        String sql = "select * from user where userId = ?;";
        ResultSet resultSet = SQLUtil.execute(sql, searchIdText);
        User user = null;
        if (resultSet.next()){
            String userId = resultSet.getString("userId");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");
            user = new User(userId, userName, password);
        }
        return user;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "update user set userName = ?,password = ? where userId = ?;";
        return SQLUtil.execute(sql,user.getUserName(),user.getPassword(),user.getUserId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from user where userId = ?;";
        return SQLUtil.execute(sql, id);
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        String sql = "select * from user;";
        ArrayList<User> userArrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            String userId = resultSet.getString("userId");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");
            User user = new User(userId, userName, password);
            userArrayList.add(user);
        }
        return userArrayList;
    }

    @Override
    public User getUser(String userName) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select * from user where userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        ResultSet resultSet = preparedStatement.executeQuery();//table ek represent karanne result set ehekin
        if (resultSet.next()){//table eke row ek check karanw data tyenw nam ilaga ekt paninw
            User user = new User(
                    resultSet.getString("userId"),
                    resultSet.getString("userName"),
                    resultSet.getString("password")
            );
            return user;
        }
        return null;
    }
}
