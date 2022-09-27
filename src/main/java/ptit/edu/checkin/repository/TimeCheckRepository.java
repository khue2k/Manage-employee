package ptit.edu.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptit.edu.checkin.dto.response.MistakeOfUserDto;
import ptit.edu.checkin.entity.TimeCheck;

import java.time.LocalDate;
import java.util.List;

public interface TimeCheckRepository extends JpaRepository<TimeCheck,Long> {
    List<TimeCheck> findByNumberCheckAndDate(int numberCheck, LocalDate nowDate);

    TimeCheck findByDateAndNumberCheck(LocalDate date,int numberCheck);

    @Query(value = "SELECT t FROM TimeCheck t  WHERE t.numberCheck= :numberCheck " +
            " AND t.date BETWEEN :startDate AND :endDate")
    List<TimeCheck> findTimeCheckByWeek(@Param("numberCheck") int numberCheck,@Param("startDate") LocalDate start,@Param("endDate") LocalDate end);

    List<TimeCheck> findAllByNumberCheck(int numberCheck);

    @Query("SELECT t.mistakeMorning as mistakeMorning ,t.mistakeAfternoon as mistakeAfternoon,t.date as date from TimeCheck t " +
            " WHERE t.numberCheck= :numberCheck AND t.date BETWEEN :startDate AND :endDate ")
    List<MistakeOfUserDto> getMistakeDto(@Param("numberCheck") int numberCheck, @Param("startDate") LocalDate start, @Param("endDate") LocalDate end);

    boolean existsByDate(LocalDate nowDate);
}
