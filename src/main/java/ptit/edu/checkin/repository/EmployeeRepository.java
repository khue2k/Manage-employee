package ptit.edu.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.edu.checkin.dto.response.EmployeeDto;
import ptit.edu.checkin.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    Employee findByFullName(String name);

    Employee findByNumberCheck(int numberCheck);

    Employee findByNumberCheckAndFullName(int numberCheck, String fullName);

    @Query(value = "SELECT e FROM Employee e ")
    List<EmployeeDto> findAllEmployeeDto();

    @Query(value = "SELECT * FROM `employee` WHERE number_check = :numberCheck", nativeQuery = true)
    Employee find(int numberCheck);

    @Query(value = "SELECT e FROM Employee e WHERE e.id= ?1")
    List<Employee> findEmployee(Long id);

    @Query(value = "SELECT e FROM Employee e WHERE e.fullName like ?1")
    List<Employee> findLikeFullName(String fullName);

    boolean existsByFullName(String fullName);

    boolean existsByNumberCheck(int i);
}
