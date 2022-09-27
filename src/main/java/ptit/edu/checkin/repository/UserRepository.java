package ptit.edu.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.edu.checkin.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    List<User> findAllByUserName(String userName);

    boolean existsByUserName(String userName);
}
