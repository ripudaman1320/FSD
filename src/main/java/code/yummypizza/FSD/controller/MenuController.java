package code.yummypizza.FSD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import code.yummypizza.FSD.model.Login;
import code.yummypizza.FSD.model.Menu;
import code.yummypizza.FSD.service.MenuService;
//import code.yummypizza.FSD.service.MenuServiceImp;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuservice;
	
//	@RequestMapping("/")
//	public String getMenu() {
//		return "Ripu";
//	}
//	
//	@RequestMapping("/menu")
//	public List<Menu> getAllMenu(){
//		
//		return (List<Menu>) repo.findAll();
//	}
	
	
	@GetMapping("/")
	public String getLoginPage(Model model) {
		return "LoginPage";
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	@GetMapping("/home")
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String viewHomePage(Model model) {
//	        model.addAttribute("listMenu", menuservice.getAllMenu());
//	        return "index";
		Login login = new Login();
		model.addAttribute("login", login);
		model.addAttribute("userName", login.getUserName());
		model.addAttribute("password", login.getPassword());
		return findPaginated(1, "name", "asc", model);	
	}
	
	@GetMapping("/showNewMenuForm")
    public String showNewMenuForm(Model model) {
        // create model attribute to bind form data
        Menu menu= new Menu();
        model.addAttribute("menu", menu);
        return "AddMenu";
    }
	
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	public String saveMenu(@ModelAttribute("menu") Menu menu) {
        // save employee to database
        menuservice.saveMenu(menu);
        return "redirect:/";
    }
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Menu menu = menuservice.getMenuById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("menu", menu);
        return "UpdateMenu";
    }
	
	 @GetMapping("/deleteMenu/{id}")
	    public String deleteMenu(@PathVariable(value = "id") long id) {

	        // call delete employee method 
	        this.menuservice.deleteMenuById(id);
	        return "redirect:/";
	    }
	 
	 @GetMapping("/page/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model) {
		 
			int pageSize = 5;
			
			Page<Menu> page = menuservice.findPaginated(pageNo, pageSize, sortField, sortDir);
			List<Menu> listMenu = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			
			model.addAttribute("listMenu", listMenu);
			return "index";
		}
}
