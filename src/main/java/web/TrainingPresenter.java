package web;

import java.util.Calendar;
import java.util.List;
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

import business.controllers.TrainingController;
import business.wrapper.TrainingAvailableTime;
import data.daos.TrainingDao;
import data.entities.Reserve;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class TrainingPresenter {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private TrainingController trainingController;

	@Autowired
	private TrainingDao trainingDao;

	public TrainingPresenter() {
	}

	@RequestMapping("/training-list")
	public ModelAndView listTrainings() {
		ModelAndView modelAndView = new ModelAndView("/trainingList");
		modelAndView.addObject("trainingList", trainingDao.findAll());
		return modelAndView;
	}

	@RequestMapping(value = { "/delete-training/{id}" })
	public String deleteTraining(@PathVariable int id, Model model) {
		trainingDao.delete(trainingDao.findOne(id));
		model.addAttribute("trainingList", trainingDao.findAll());
		return "/userList";
	}

	@RequestMapping(value = { "/show-reserves/{id}" })
	public String showTrainingReserves(@PathVariable int id, Model model) {
		List<Reserve> reserves = trainingDao.findOne(id).getReserves();
		model.addAttribute("reserveList", reserves);
		return "/reserveList";
	}

	@RequestMapping(value = "/create-training", method = RequestMethod.GET)
	public String createTraining(Model model) {
		TrainingForm t = new TrainingForm(0, 1, 100, "admin");
		model.addAttribute("trainingForm", t);
		return "/createTraining";
	}

	@RequestMapping(value = "/create-training", method = RequestMethod.POST)
	public String createTrainingSubmit(@ModelAttribute(value = "training") TrainingForm trainingForm,
			BindingResult bindingResult, Model model) {
		try {
			if (!bindingResult.hasErrors()) {

				Calendar startDate = Calendar.getInstance();
				startDate.set(Calendar.DAY_OF_YEAR, trainingForm.getStartDayOfYear());
				Calendar endDate = Calendar.getInstance();
				endDate.set(Calendar.DAY_OF_YEAR, trainingForm.getEndDayOfYear());
				String result = trainingController.createTraining(
						new TrainingAvailableTime(trainingForm.getCourtId(), startDate, endDate),
						trainingForm.getTrainer());
				model.addAttribute("result", result);
				return "/registrationTraining";
			} else {
				bindingResult.rejectValue("id", "error.user", "Usuario ya existente");
			}
			return "/createTraining";
		} catch (Exception e) {
			model.addAttribute("result", "error" + e);
			return "/registrationTraining";
		}
	}

}