package ptit.edu.checkin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ptit.edu.checkin.dto.response.EmployeeDto;
import ptit.edu.checkin.dto.response.EmployeeListCheckinDto;
import ptit.edu.checkin.dto.response.EmployeeListMistakeDto;
import ptit.edu.checkin.dto.response.ResponseDto;
import ptit.edu.checkin.dto.request.RequestSearch;
import ptit.edu.checkin.dto.request.SignUpDto;
import ptit.edu.checkin.entity.Employee;
import ptit.edu.checkin.entity.Role;
import ptit.edu.checkin.entity.User;
import ptit.edu.checkin.repository.RoleRepository;
import ptit.edu.checkin.repository.UserRepository;
import ptit.edu.checkin.service.EmployeeService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getAllEmployeeCheck")
    public List<EmployeeListCheckinDto> getAllEmployee(Principal principal) {
        log.info("IN ADMIN CONTROLLER");
        log.info(principal.getName());
        return employeeService.getAllEmployeeCheck();
    }

    @GetMapping("/getAllEmployee/{offset}/{pageSize}")
    public Page<Employee> getAllEmployee(@PathVariable int offset, @PathVariable int pageSize) {
        return employeeService.getAllEmployee(offset, pageSize);
    }

    @GetMapping("/getAllMistakeOfEmployee")
    public List<EmployeeListMistakeDto> getAllMistakeOfEmployee() {
        return employeeService.getAllMistakeOfEmployee();
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body("add employee successfully !");
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Long id) {
        employeeService.update(employee, id);
        return ResponseEntity.status(HttpStatus.OK).body("update successfully !");
    }

    @GetMapping("/findById/{id}")
    public Optional<Employee> findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseDto deleteById(@PathVariable Long id) {
        return employeeService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public ResponseDto deleteAll() {
        return employeeService.deleteAll();
    }

    @GetMapping("/searchByFullName")
    public List<Employee> searchByFullName(@RequestBody RequestSearch requestSearch) {
        String name = requestSearch.getName();
        return employeeService.searchByEmployeeFullName(name);
    }

    @GetMapping("/sortByFullName")
//    @Secured("ADMIN")
    public List<Employee> sortByFullName() {
        return employeeService.sortByFullName();
    }

    @PostMapping("/createAccountCensor")
    public ResponseEntity<String> createAccountAdmin(@RequestBody SignUpDto signUpDto) {
        String userName = signUpDto.getUserName();
        String password = signUpDto.getPassword();
        if (userRepository.existsByUserName(userName)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("UserName is already taken !");
        } else {
            Role role = roleRepository.findByName("CENSOR").get(0);
            User user = new User();
            user.setUserName(userName);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Collections.singleton(role));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Create Censor successful !");
        }
    }

    @GetMapping("/findEmployeeById/{id}")
    public Optional<Employee> findEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/updateAgeEmployeeById/{id}/{age}")
    public Optional<Employee> updateAgeEmployeeById(@PathVariable Long id, @PathVariable int age) {
        return employeeService.updateAge(id, age);
    }

    @GetMapping("/getAll")
    public List<EmployeeDto> getAll() {
        return employeeService.getAllEmployee();
    }

}
