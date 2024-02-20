package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.AttendanceDAO;
import lk.ijse.layardproject.dto.AttendanceDTO;
import lk.ijse.layardproject.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public boolean save(Attendance attendance) throws SQLException {
        String sql = "insert into attendance(attendanceId,departTime,eId,entryTime) values(?,?,?,?);";
        return SQLUtil.execute(sql,attendance.getAttendanceId(),attendance.getDepartTime(),
                attendance.getEId(),attendance.getEntryTime());
    }

    @Override
    public Attendance search(String searchIdText) throws SQLException {
        String sql = "select * from attendance where attendanceId = ?;";
        ResultSet resultSet = SQLUtil.execute(sql, searchIdText);
        Attendance attendance = null;
        if (resultSet.next()){
            String attendanceId = resultSet.getString("attendanceId");
            LocalTime departTime = LocalTime.parse(resultSet.getString("departTime"));
            LocalTime entryTime = LocalTime.parse(resultSet.getString("entryTime"));
            String eId = resultSet.getString("eId");
            attendance = new Attendance(attendanceId,departTime,eId,entryTime);
        }
        return attendance;
    }

    @Override
    public boolean update(Attendance attendance) throws SQLException {
        String sql = "update attendance set departTime =?, eId = ?, entryTime = ? where attendanceId = ?;";
        return SQLUtil.execute(sql,attendance.getDepartTime(),attendance.getEId(),attendance.getEntryTime(),
                attendance.getAttendanceId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from attendance where attendanceId = ?;";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(attendanceId) as lastId from attendance;";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()){
            String lastId = resultSet.getString("lastId");
            if (lastId == null){
                return "A001";
            }else {
                int nextId = Integer.parseInt(lastId.substring(1)) + 1;
                return "A"+String.format("%03d",nextId);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Attendance> getAll() throws SQLException {
        String sql = "select * from attendance;";
        ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            String attendanceId = resultSet.getString("attendanceId");
            LocalTime departTime = LocalTime.parse(resultSet.getString("departTime"));
            LocalTime entryTime = resultSet.getTime("entryTime").toLocalTime();
            String eId = resultSet.getString("eId");
            Attendance attendance = new Attendance(attendanceId, departTime, eId,entryTime);
            attendanceArrayList.add(attendance);
        }
        return attendanceArrayList;
    }
}
