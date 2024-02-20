package lk.ijse.layardproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attendance {
    private String attendanceId;
    private LocalTime departTime;
    private String eId;
    private LocalTime entryTime;
}