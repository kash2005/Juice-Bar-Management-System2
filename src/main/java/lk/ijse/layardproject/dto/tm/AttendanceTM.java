package lk.ijse.layardproject.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceTM {
    private String attendanceId;
    private LocalTime departTime;
    private LocalTime entryTime;
    private String eId;
}
