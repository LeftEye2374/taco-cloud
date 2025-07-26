package sia.tacoCloud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sia.tacoCloud.data.taco.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>, PagingAndSortingRepository<Taco, Long> {
}
