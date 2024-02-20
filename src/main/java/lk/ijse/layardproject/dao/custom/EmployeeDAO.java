package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.CrudDAO;
import lk.ijse.layardproject.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee> {
    ArrayList<String> getId() throws SQLException;

    ArrayList<String> getCmbId() throws SQLException;
}
