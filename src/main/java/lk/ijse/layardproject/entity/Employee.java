package lk.ijse.layardproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String eId;
    private String name;
    private String address;
    private String email;
    private String contact;
    private String jobRoll;
    private String onePerHour;
}