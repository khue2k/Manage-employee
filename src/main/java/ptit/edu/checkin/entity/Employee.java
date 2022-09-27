package ptit.edu.checkin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "fullName cannot be null")
    private String fullName;
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;
    @NotNull(message = "fullName cannot be null")
    private String position;
    @Email(message = "Email should be valid")
    private String email;
    private int numberCheck;

    public Employee(String fullName, int age, String position, String email) {
        this.fullName = fullName;
        this.age = age;
        this.position = position;
        this.email = email;
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TimeCheck> timeChecks;

}
