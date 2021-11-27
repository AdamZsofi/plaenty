package plaentyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plaentyapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByName(String name);

    boolean existsByName(String name);
}
