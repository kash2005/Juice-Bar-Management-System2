package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.bo.custom.UserBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.UserDAO;
import lk.ijse.layardproject.dto.UserDTO;
import lk.ijse.layardproject.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean delete(String id) throws SQLException {
        return userDAO.delete(id);
    }

    @Override
    public boolean save(UserDTO userDTO) throws SQLException {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword()));
    }

    @Override
    public boolean update(UserDTO userDTO) throws SQLException {
        return userDAO.update(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword()));
    }

    @Override
    public UserDTO searchId(String id) throws SQLException {
        User user = userDAO.search(id);
        return new UserDTO(user.getUserId(), user.getUserName(), user.getPassword());
    }

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException {
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        ArrayList<User> userArrayList = userDAO.getAll();
        for (User user: userArrayList){
            userDTOS.add(new UserDTO(user.getUserId(), user.getUserName(), user.getPassword()));
        }
        return userDTOS;
    }
}
