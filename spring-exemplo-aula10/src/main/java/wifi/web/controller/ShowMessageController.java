package wifi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowMessageController {

	@RequestMapping("/showMessage.html")
	public ModelAndView execute() {
		System.out.println("Executando o Controlador ShowMessageController");
		
		ModelAndView mv = new ModelAndView("showMessage");
		mv.addObject("message", "Teste Olá Mundo!");
		
		return mv;
	}
	
}
