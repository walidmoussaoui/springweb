package joe.spring.springweb.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the form page examples.
 */
@Controller
public class AdminController {

	@Autowired
	@Qualifier("customerModelValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	protected final static Logger log = LoggerFactory.getLogger(AdminController.class);

	public AdminController() {

	}

	// Displays a list of all customers.

	@RequestMapping(value = "/admin")
	public String home() {
		log.info("Displaying the admin page.");

		return "admin";
	}

}