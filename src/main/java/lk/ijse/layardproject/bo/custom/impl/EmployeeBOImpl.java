package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.custom.EmployeeBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.EmployeeDAO;
import lk.ijse.layardproject.dto.EmployeeDTO;
import lk.ijse.layardproject.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean delete(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean save(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.save(new Employee(employeeDTO.getEId(),employeeDTO.getName(), employeeDTO.getAddress(),
                employeeDTO.getEmail(),employeeDTO.getContact(), employeeDTO.getJobRoll(), employeeDTO.getOnePerHour()));
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.update(new Employee(employeeDTO.getEId(),employeeDTO.getName(), employeeDTO.getAddress(),
                employeeDTO.getEmail(),employeeDTO.getContact(), employeeDTO.getJobRoll(), employeeDTO.getOnePerHour()));
    }

    @Override
    public EmployeeDTO searchEmployeeId(String id) throws SQLException {
        Employee employee = employeeDAO.search(id);
        return new EmployeeDTO(employee.getEId(), employee.getName(), employee.getAddress(), employee.getEmail(),
                employee.getContact(), employee.getJobRoll(), employee.getOnePerHour());
    }

    @Override
    public String generateId() throws SQLException {
        return employeeDAO.generateId();
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ArrayList<Employee> employeeArrayList = employeeDAO.getAll();
        for (Employee employee : employeeArrayList){
            employeeDTOS.add(new EmployeeDTO(employee.getEId(), employee.getName(), employee.getAddress(), employee.getEmail(),
                    employee.getContact(), employee.getJobRoll(), employee.getOnePerHour()));
        }
        return employeeDTOS;
    }

    @Override
    public ArrayList<String> getEmployeeId() throws SQLException {
        ArrayList<String> arrayList = employeeDAO.getId();
        return arrayList;
    }

    @Override
    public ArrayList<String> getCmbEmployeeId() throws SQLException {
        ArrayList<String> arrayList = employeeDAO.getCmbId();
        return arrayList;
    }
}
