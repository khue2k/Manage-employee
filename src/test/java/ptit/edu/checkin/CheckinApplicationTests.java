package ptit.edu.checkin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ptit.edu.checkin.entity.User;
import ptit.edu.checkin.repository.UserRepository;
import ptit.edu.checkin.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class CheckinApplicationTests {
    @Test
    void contextLoads() {
    }
//    private SimpleSourceDestinationMapper mapper= Mappers.getMapper(SimpleSourceDestinationMapper.class);

    @MockBean
    private UserRepository userRepository;
    @Autowired
    UserService userService;

    //    @Test
//    public void  getUserTest(){
//        Mockito.when(userRepository.findAll()).thenReturn(Stream.of(new User("user1","123"),
//                new User("user2","123")).collect(Collectors.toList()));
//        Assert.assertEquals(2,userService.getAllUser().size());
//    }
    @Test
    public void getUserByUserNameTest() {

        Mockito.when(userRepository.findAllByUserName("user1"))
                .thenReturn(Stream.of(
                        new User("user1", "123"),
                        new User("user1", "123"),
                        new User("user1", "123")
                ).collect(Collectors.toList()));
        Assert.assertEquals(3,userService.getUserByUserName("user1").size());
//        userRepository.findAllByUserName("user11").forEach(user -> System.out.println(user.getUserName()));
    }
//    @Test
//    public void saveUserTest(){
//        User user=new User("user1","123");
//        Mockito.when(userRepository.save(user)).thenReturn(user);
//        Assert.assertEquals(new User("user1","123"),userService.saveUser(user));
//    }
//    @Test
//    public void test(){
//        List<String> mockedList=Mockito.mock(List.class);
//        mockedList.size();
//        mockedList.size();
//        mockedList.add("hello");
//        mockedList.clear();
//        Mockito.verify(mockedList,Mockito.times(2)).size();
//        Mockito.verify(mockedList,Mockito.times(1)).add("hello");
//    }

}
