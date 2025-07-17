package sia.taco_cloud.repository;

import org.springframework.data.repository.CrudRepository;
import sia.taco_cloud.model.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
