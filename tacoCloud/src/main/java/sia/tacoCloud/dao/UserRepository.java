package sia.tacoCloud.dao;

import org.springframework.data.repository.CrudRepository;
import sia.tacoCloud.security.User;

public interface UserRepository  extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
