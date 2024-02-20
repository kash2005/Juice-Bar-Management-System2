package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    boolean delete(String id) throws SQLException;

    boolean save(AttendanceDTO attendanceDTO) throws SQLException;

    boolean update(AttendanceDTO attendanceDTO) throws SQLException;

    AttendanceDTO searchId(String id) throws SQLException;

    String generateId() throws SQLException;

    ArrayList<AttendanceDTO> getAll() throws SQLException;
}
