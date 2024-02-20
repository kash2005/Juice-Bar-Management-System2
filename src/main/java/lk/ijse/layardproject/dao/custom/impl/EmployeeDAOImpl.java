package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.EmployeeDAO;
import lk.ijse.layardproject.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws SQLException {
        String sql = "insert into employee(eId,name,address,email,contact,jobRoll,onePerHour) values(?,?,?,?,?,?,?);";;
        return SQLUtil.execute(sql,employee.getEId(),employee.getName(),employee.getAddress(),
                employee.getEmail(),employee.getContact(),employee.getJobRoll(),employee.getOnePerHour());
    }

    @Override
    public Employee search(String searchIdText) throws SQLException {
        String sql = "select * from employee where eId = ?;";
        Employee employee = null;
        ResultSet resultSet = SQLUtil.execute(sql, searchIdText);
        if (resultSet.next()){
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String contact = resultSet.getString("contact");
            String jobRoll = resultSet.getString("jobRoll");
            String onePerHour = resultSet.getString("onePerHour");
            String eId = resultSet.getString("eId");
            employee = new Employee(eId,name,address,email,contact,jobRoll,onePerHour);
        }
        return employee;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        String  sql = "update employee set name = ?,address = ?,email = ?,contact = ?,jobRoll = ?,onePerHour = ? where eId = ?;";
        return SQLUtil.execute(sql,employee.getName(),employee.getAddress(),employee.getEmail(),
                employee.getContact(),employee.getJobRoll(),employee.getOnePerHour(),employee.getEId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from employee where eId = ?;";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(eId) as lastId from employee;";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            String lastId = resultSet.getString("lastId");
            if (lastId == null){
                return "E001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(1)) + 1;
                return "E" + String.format("%03d",nextId);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        String sql = "select * from employee;";
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        Employee employee = null;
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            employee = new Employee(
                    resultSet.getString("eId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("contact"),
                    resultSet.getString("jobRoll"),
                    resultSet.getString("onePerHour")
            );
            employeeArrayList.add(employee);
        }
        return employeeArrayList;
    }

    @Override
    public ArrayList<String> getId() throws SQLException {
        String sql = "select eId from employee;";
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            String eId = resultSet.getString("eId");
            arrayList.add(eId);
        }
        return arrayList;
    }

    @Override
    public ArrayList<String> getCmbId() throws SQLException {
        String sql = "select eId,jobRoll from employee;";
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            String eId = resultSet.getString("eId");
            String jobRoll = resultSet.getString("jobRoll");
            if (jobRoll.equals("Cashier") || jobRoll.equals("Admin")){
                arrayList.add(eId);
            }
        }
        return arrayList;
    }

    @Override
    public String searchId(String eId) throws SQLException {
        String sql = "select jobRoll from employee where eId = ?;";
        ResultSet resultSet = SQLUtil.execute(sql, eId);
        String jobRoll =null;
        if (resultSet.next()){
            jobRoll = resultSet.getString("jobRoll");
        }
        return jobRoll;
    }
}
