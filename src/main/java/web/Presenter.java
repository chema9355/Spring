package web;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

	@Autowired
	private ServletContext servletContext;

	public Presenter() {
	}

	@RequestMapping("/home")
	public String home(Model model) {
		// La vista resultante no lleva extensión (.jsp) configurado en
		// WebConfig.java
		return "/home";
	}

}