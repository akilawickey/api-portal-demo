package assesment.apiportal.publisher.demo.repositories;

import assesment.apiportal.publisher.demo.models.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {

    @Query("SELECT COUNT(a)>0 FROM Api a WHERE a.name = :name")
    boolean findAllByEndpointName(@Param("name") String name);

    @Query("SELECT a FROM Api a WHERE a.isProtected = :isProtected")
    List<Api> findAllByProtected(Boolean isProtected);
}
