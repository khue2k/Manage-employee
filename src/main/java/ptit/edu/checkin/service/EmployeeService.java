package ptit.edu.checkin.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.edu.checkin.dto.response.EmployeeDto;
import ptit.edu.checkin.dto.response.EmployeeListCheckinDto;
import ptit.edu.checkin.dto.response.EmployeeListMistakeDto;
import ptit.edu.checkin.dto.response.ResponseDto;
import ptit.edu.checkin.entity.Employee;
import ptit.edu.checkin.entity.TimeCheck;
import ptit.edu.checkin.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Join;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    public List<EmployeeListCheckinDto> getAllEmployeeCheck() {
        return employeeRepository.findAll().stream().map(employee ->
                modelMapper.map(employee, EmployeeListCheckinDto.class)
        ).collect(Collectors.toList());
    }

    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAllEmployeeDto();
    }

    public Page<Employee> getAllEmployee(int offset, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public List<EmployeeListMistakeDto> getAllMistakeOfEmployee() {
        return employeeRepository.findAll().stream().map(employee ->
                        modelMapper.map(employee, EmployeeListMistakeDto.class))
                .collect(Collectors.toList());
    }

    public ResponseDto deleteAll() {
        employeeRepository.deleteAll();
        return new ResponseDto("Delete all success !", "OK");

    }

    public ResponseDto deleteById(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return new ResponseDto("Delete by id success !", "OK");
        } else {
            return new ResponseDto("Delete by id failed !", "FAILED");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class,noRollbackFor = EntityNotFoundException.class)
    public ResponseEntity<?> addEmployee(Employee employee) {
        if (!employeeRepository.existsByFullName(employee.getFullName())) {
            Employee employee1 = new Employee(employee.getFullName(), employee.getAge(), employee.getPosition(), employee.getEmail());
            Random random = new Random();
            int ran = random.nextInt(8999) + 1000;
            employee1.setNumberCheck(ran);
            employeeRepository.save(employee1);
            mailService.sendEmail(employee1);
            return ResponseEntity.status(HttpStatus.OK).body("add employee successful !");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("add employee failed !");
    }



    public ResponseEntity<Employee> update(Employee employee, Long id) {
        employeeRepository.findById(id).map(employee1 -> {
            if (employee.getFullName() != null) {
                employee1.setFullName(employee.getFullName());
            }
            if (employee.getAge() >= 18 && employee.getAge() <= 150) {
                employee1.setAge(employee.getAge());
            }
            if (employee.getPosition() != null) {
                employee1.setPosition(employee.getPosition());
            }
            if (employee.getEmail() != null) {
                employee1.setEmail(employee.getEmail());
            }
            return employeeRepository.save(employee1);
        }).orElseGet(() -> {
                    employee.setId(id);
                    return employeeRepository.save(employee);
                }
        );
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    public List<Employee> sortByFullName() {
        return employeeRepository.findAll(Sort.by("fullName"));
    }

    public List<Employee> searchByEmployeeFullName(String name) {
        return employeeRepository.findLikeFullName(name);
    }


    @Cacheable("employee")
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @CachePut(value = "employee")
    public Optional<Employee> updateAge(Long id, int age) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.map(employee1 -> {
            employee1.setAge(age);
            return employeeRepository.save(employee1);
        });
        return employee;
    }
}
