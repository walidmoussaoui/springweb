package joe.spring.springweb.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the form page examples.
 */
@Controller
public class StatesController {
	protected final static Logger log = LoggerFactory
			.getLogger(StatesController.class);

	public StatesController() {

	}

	@RequestMapping(value = "/states")
	public String home() {
		log.info("Displaying list of states by country");

		return "states";
	}

}