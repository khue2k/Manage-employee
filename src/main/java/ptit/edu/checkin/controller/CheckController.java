package ptit.edu.checkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ptit.edu.checkin.dto.request.CheckRequestDto;
import ptit.edu.checkin.dto.response.ResponseDto;
import ptit.edu.checkin.service.CheckService;
@RestController
@RequestMapping("/api/check")
public class CheckController {
    @Autowired
    CheckService checkService;
    @GetMapping("/check-in")
    public ResponseDto checkIn(@RequestBody CheckRequestDto checkRequestDto){
    return  checkService.checkIn(checkRequestDto.getNumberCheck());
    }
    @GetMapping("/check-out")
    public ResponseDto checkOut(@RequestBody CheckRequestDto checkRequestDto){
        return  checkService.checkOut(checkRequestDto.getNumberCheck());
    }
}
