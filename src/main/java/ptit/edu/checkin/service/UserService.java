package ptit.edu.checkin.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ptit.edu.checkin.dto.response.MistakeOfUserDto;
import ptit.edu.checkin.dto.response.TimeCheckDto;
import ptit.edu.checkin.entity.TimeCheck;
import ptit.edu.checkin.entity.User;
import ptit.edu.checkin.repository.TimeCheckRepository;
import ptit.edu.checkin.repository.UserRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeCheckRepository timeCheckRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<TimeCheckDto> getTimeCheckInWeekDefault(int numberCheck) {
        LocalDate nowDate =LocalDate.now();
        LocalDate monday = nowDate.with(DayOfWeek.MONDAY);
        LocalDate sunday = nowDate.with(DayOfWeek.SUNDAY);
        return timeCheckRepository.findTimeCheckByWeek(numberCheck,monday,sunday).stream().map(timeCheck ->
                modelMapper.map(timeCheck,TimeCheckDto.class)).collect(Collectors.toList());
    }

    public List<TimeCheck>getTimeCheck(int numberCheck){
        List<TimeCheck> timeChecks=timeCheckRepository.findAllByNumberCheck(numberCheck);
        return timeChecks;
    }

    public List<MistakeOfUserDto> getAllMistakeInMonth(int numberCheck) {
        LocalDate  nowDate = LocalDate.now();
        LocalDate firstDayInMonth= nowDate.withDayOfMonth(1);
        LocalDate lastDayInMonth=nowDate.with(lastDayOfMonth());
        return timeCheckRepository.getMistakeDto(numberCheck,firstDayInMonth,lastDayInMonth);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void getMyAccount() {
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUserName(userName);
        log.info(user.getUserName());
        log.info(user.getPassword());
    }

    //test mokito
    public List<User> getUserByUserName(String userName){
        return userRepository.findAllByUserName(userName);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
