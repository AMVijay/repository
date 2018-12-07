package vijay.poc.pivotal.firstExample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller Class
 * @author Vijay (AM.Vijay@gmail.com)
 *
 */
@Controller
public class HelloController {

	/**
	 * Method to sent the Hello Work Greeting.
	 * @param model as Spring UI Model
	 * @return viewName as String
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sayHello(Model model) {
		model.addAttribute("greeting","Hello, World From Spring Controller");
		return "greeting";
	}
}
