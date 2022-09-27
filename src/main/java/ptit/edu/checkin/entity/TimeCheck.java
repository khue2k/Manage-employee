package ptit.edu.checkin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "time_check")
public class TimeCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalTime timeCheckIn;
    private LocalTime timeCheckOut;
    private LocalDate date;
    private int numberCheck;
    @Column(length = 50)
    private String mistakeMorning;
    @Column(length = 50)
    private String mistakeAfternoon;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
