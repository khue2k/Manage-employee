package ptit.edu.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.edu.checkin.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findByName(String role_admin);
}
