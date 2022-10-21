package code.yummypizza.FSD.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import code.yummypizza.FSD.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{

}
