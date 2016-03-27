package web;

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
import data.daos.CourtDao;
import data.entities.Court;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class CourtPresenter {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private CourtDao courtDao;

	public CourtPresenter() {
	}

	@RequestMapping("/court-list")
	public ModelAndView listCourts() {
		ModelAndView modelAndView = new ModelAndView("/courtList");
		modelAndView.addObject("courtList", courtDao.findAll());
		return modelAndView;
	}

	@RequestMapping(value = { "/delete-court/{id}" })
	public String deleteCourt(@PathVariable int id, Model model) {
		courtDao.delete(courtDao.findOne(id));
		model.addAttribute("courtList", courtDao.findAll());
		return "/courtList";
	}

	@RequestMapping(value = "/create-court", method = RequestMethod.GET)
	public String createCourt(Model model) {
		Court c = new Court();
		model.addAttribute("court", c);
		return "/createCourt";
	}

	@RequestMapping(value = "/create-court", method = RequestMethod.POST)
	public String createCourtSubmit(@ModelAttribute(value = "court") Court court, BindingResult bindingResult,
			Model model) {
		if (!bindingResult.hasErrors()) {
			try {
				courtDao.save(court);
			} catch (Exception e) {
				model.addAttribute("error", "error" + e);
				return "/registrationCourtFail";
			}
			model.addAttribute("success", court.getId());
			return "/registrationCourtSuccess";
		} else {
			bindingResult.rejectValue("error", "error");
		}
		return "/createCourt";

	}
}