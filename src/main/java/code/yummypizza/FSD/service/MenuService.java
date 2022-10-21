package code.yummypizza.FSD.service;

import java.util.List;

import org.springframework.data.domain.Page;

import code.yummypizza.FSD.model.Menu;

public interface MenuService {
	List<Menu> getAllMenu();
    void saveMenu(Menu menu);
    Menu getMenuById(long id);
    void deleteMenuById(long id);
    Page<Menu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
