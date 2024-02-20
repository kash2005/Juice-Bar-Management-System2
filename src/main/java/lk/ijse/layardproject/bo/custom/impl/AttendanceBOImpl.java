package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.bo.custom.AttendanceBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.AttendanceDAO;
import lk.ijse.layardproject.dto.AttendanceDTO;
import lk.ijse.layardproject.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);

    @Override
    public boolean delete(String id) throws SQLException {
        return attendanceDAO.delete(id);
    }

    @Override
    public boolean save(AttendanceDTO attendanceDTO) throws SQLException {
        return attendanceDAO.save(new Attendance(attendanceDTO.getAttendanceId(), attendanceDTO.getDepartTime(),
                attendanceDTO.getEId(), attendanceDTO.getEntryTime()));
    }

    @Override
    public boolean update(AttendanceDTO attendanceDTO) throws SQLException {
        return attendanceDAO.update(new Attendance(attendanceDTO.getAttendanceId(), attendanceDTO.getDepartTime(),
                attendanceDTO.getEId(), attendanceDTO.getEntryTime()));
    }

    @Override
    public AttendanceDTO searchId(String id) throws SQLException {
        Attendance attendance = attendanceDAO.search(id);
        return new AttendanceDTO(attendance.getAttendanceId(), attendance.getDepartTime(), attendance.getEId(),
                attendance.getEntryTime());
    }

    @Override
    public String generateId() throws SQLException {
        return attendanceDAO.generateId();
    }

    @Override
    public ArrayList<AttendanceDTO> getAll() throws SQLException {
        ArrayList<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        ArrayList<Attendance> attendanceArrayList = attendanceDAO.getAll();
        for (Attendance attendance : attendanceArrayList){
            attendanceDTOS.add(new AttendanceDTO(attendance.getAttendanceId(), attendance.getDepartTime(), attendance.getEId(),
                    attendance.getEntryTime()));
        }
        return attendanceDTOS;
    }
}
