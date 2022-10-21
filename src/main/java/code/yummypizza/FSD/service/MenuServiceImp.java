package code.yummypizza.FSD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import code.yummypizza.FSD.model.Menu;
import code.yummypizza.FSD.repo.MenuRepository;
import org.springframework.data.domain.Sort;

@Service
public class MenuServiceImp implements MenuService{

	@Autowired
	private MenuRepository menurepo;

	@Override
	public List<Menu> getAllMenu() {
		
		return (List<Menu>) menurepo.findAll();
	}

	@Override
	public void saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.menurepo.save(menu);
	}

	@Override
	public Menu getMenuById(long id) {
		// TODO Auto-generated method stub
		Optional<Menu> option = menurepo.findById(id);
		Menu menu = null;
		
		if(option.isPresent()) {
			menu = option.get();
		}else {
			throw new RuntimeException("Menu not found for id : "+id);
		}
		
		return menu;
	}

	@Override
	public void deleteMenuById(long id) {
		// TODO Auto-generated method stub
		this.menurepo.deleteById(id);
		
	}
	
	@Override
	public Page<Menu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.menurepo.findAll(pageable);
	}
	
}
