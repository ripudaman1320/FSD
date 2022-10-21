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

@Controller
public class MenuController {

	@Autowired
	private MenuService menuservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		return "LoginPage";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String viewHomePage(Model model) {
		Login login = new Login();
		model.addAttribute("login", login);
		model.addAttribute("userName", login.getUserName());
		model.addAttribute("password", login.getPassword());
		return findPaginated(1, "name", "asc", model);	
	}
	
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String getHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);	
	}
	
	@GetMapping("/showNewMenuForm")
    public String showNewMenuForm(Model model) {

        Menu menu= new Menu();
        model.addAttribute("menu", menu);
        return "AddMenu";
    }
	
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	public String saveMenu(@ModelAttribute("menu") Menu menu) {

        menuservice.saveMenu(menu);
        return "redirect:/home2";
    }
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        Menu menu = menuservice.getMenuById(id);

        model.addAttribute("menu", menu);
        return "UpdateMenu";
    }
	
	 @GetMapping("/deleteMenu/{id}")
	    public String deleteMenu(@PathVariable(value = "id") long id) {

	        this.menuservice.deleteMenuById(id);
	        return "redirect:/home2";
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
