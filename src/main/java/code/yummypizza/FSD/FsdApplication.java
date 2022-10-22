package code.yummypizza.FSD;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import code.yummypizza.FSD.model.Menu;
import code.yummypizza.FSD.repo.MenuRepository;

@SpringBootApplication
public class FsdApplication  implements InitializingBean {

	@Autowired
	private MenuRepository menuRepo;

	private void setupMenuList() {
		Menu menumodel ;

		menuRepo.deleteAll();
		
		menumodel = new Menu(1, "Chicken Supreme", "Garlic Bread");
		menuRepo.save(menumodel);

		menumodel = new Menu(2, "Farm House", "Taco wraps");
		menuRepo.save(menumodel);
		
		menumodel = new Menu(3, "	Meat Lovers", "Nuggets");
		menuRepo.save(menumodel);

		menumodel = new Menu(4, "Peri Peri", "Brownie");
		menuRepo.save(menumodel);
		
		menumodel = new Menu(5, "Supreme delight", "Friska");
		menuRepo.save(menumodel);
		
		menumodel = new Menu(6, "Veg Extravaganza", "Stuffed garlic bread");
		menuRepo.save(menumodel);
		
		menumodel = new Menu(7, "Maharaja supreme", "Double patty burger");
		menuRepo.save(menumodel);
	}
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(FsdApplication.class);
		builder.headless(false).run(args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setupMenuList();
	}

}
