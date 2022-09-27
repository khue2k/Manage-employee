package ptit.edu.checkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.edu.checkin.dto.response.MistakeOfUserDto;
import ptit.edu.checkin.dto.response.TimeCheckDto;
import ptit.edu.checkin.entity.TimeCheck;
import ptit.edu.checkin.entity.User;
import ptit.edu.checkin.service.UserService;
import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllTimeCheckInWeek/{numberCheck}")
    public List<TimeCheckDto> getTimeCheckInWeek(@PathVariable int numberCheck ){
        return userService.getTimeCheckInWeekDefault(numberCheck);
    }

    @GetMapping("/getAllTimeCheck/{numberCheck}")
    public List<TimeCheck> getAllTimeCheck(@PathVariable int numberCheck){
        return userService.getTimeCheck(numberCheck);
    }

    @GetMapping("/getAllMistakeInMonth/{numberCheck}")
    public  List<MistakeOfUserDto> getAllMistakeInMonth(@PathVariable int numberCheck){
        return userService.getAllMistakeInMonth(numberCheck);
    }

    //@Secured()
    @GetMapping("/getAllUser")
    public  List<User> getAllUser(Principal principal){
        System.out.println("IN USER CONTROLLER");
        System.out.println(principal.getName());
        return userService.getAllUser();
    }
    @GetMapping("/getMyAccount")
    public String  getMyAccount(){
       userService.getMyAccount();
       return "getMyAccount successful ! ";
    }
}
