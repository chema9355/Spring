package web;

import java.util.Calendar;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import data.daos.UserDao;
import data.entities.User;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class UserPresenter {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private UserDao userDao;

	public UserPresenter() {
	}

	@RequestMapping("/user-list")
	public ModelAndView listUsers() {
		ModelAndView modelAndView = new ModelAndView("/userList");
		modelAndView.addObject("userList", userDao.findAll());
		return modelAndView;
	}

	@RequestMapping(value = { "/delete-user/{id}" })
	public String deleteUser(@PathVariable int id, Model model) {
		userDao.delete(userDao.findOne(id));
		model.addAttribute("userList", userDao.findAll());
		return "/userList";
	}

	@RequestMapping(value = "/create-user", method = RequestMethod.GET)
	public String createUser(Model model) {
		User u = new User("new user", "new user", "", Calendar.getInstance());
		model.addAttribute("user", u);
		return "/createUser";
	}

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public String createUserSubmit(@ModelAttribute(value = "user") User user, BindingResult bindingResult,
			Model model) {
		if (!bindingResult.hasErrors()) {
			try {
				userDao.save(user);
			} catch (Exception e) {
				model.addAttribute("error", "error" + e);
				return "/registrationFail";
			}
			model.addAttribute("name", user.getUsername());
			return "/registrationSuccess";
		} else {
			bindingResult.rejectValue("id", "error.user", "Usuario ya existente");
		}
		return "/createUser";

	}
}