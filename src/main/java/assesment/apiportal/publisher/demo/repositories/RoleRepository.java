package assesment.apiportal.publisher.demo.repositories;

import assesment.apiportal.publisher.demo.models.Role;
import assesment.apiportal.publisher.demo.utils.enums.RoleTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleTypes name);
}
