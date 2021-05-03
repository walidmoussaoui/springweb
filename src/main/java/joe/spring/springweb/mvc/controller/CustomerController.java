package joe.spring.springweb.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.reference.Title;
import joe.spring.springapp.services.CustomerService;
import joe.spring.springapp.services.ReferenceService;
import joe.spring.springweb.mvc.data.DropDownData;
import joe.spring.springweb.mvc.data.FormFieldError;
import joe.spring.springweb.mvc.data.ValidationResponse;
import joe.spring.springweb.mvc.model.AnnotatedAccountModel;
import joe.spring.springweb.mvc.model.CustomerModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the form page examples.
 */
@Controller
public class CustomerController {

	@Autowired
	protected ReferenceService refService;

	@Autowired
    @Qualifier("customerModelValidator")
    private Validator validator;

	@Autowired
	protected MessageSource messageSource;
	
	@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	protected final static Logger log = LoggerFactory
			.getLogger(CustomerController.class);

	public CustomerController() {

	}

	// Displays a list of all customers.

	@RequestMapping(value = "/customers")
	public String home() {
		log.info("Displaying the customers page.");

		return "customers";
	}

	// ******************************************************************

	// Customer search example. Takes a single search term and searches
	// by customer first, last and user name for matches.

	@RequestMapping(value = "/customerSearch", method = RequestMethod.GET)
	public String displayCustomerSearch() {
		log.info("Displaying customer search page");

		return "customerSearch";
	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
	public String displayCreateCustomer(Model model) {

		model.addAttribute("titleList", getTitleList());
		model.addAttribute("customerModel", new CustomerModel());
		log.info("Displaying the create customer form");

		return "createCustomer";
	}
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST, produces = "application/json")
	public String createCustomer(@Validated CustomerModel customerModel,
			BindingResult result, Model model) {
		log.info("In createCustomer...");
		String dest = "createCustomerThanks";
		log.info("CustomerModel:" + customerModel);
		if (result.hasErrors()) {
			log.info("Form validation errors: " + result.getErrorCount());
			for (ObjectError oe : result.getAllErrors()) {
				log.info(oe.toString());
			}
			model.addAttribute("titleList", getTitleList());
			dest = "createCustomer";
		} else {
			log.info("NO form validation errors found. Creating a new customer!");
			//customerService.createCustomer(customerModel.getFirstName(), customerModel.getLastName(), customerModel.getUserName(), customerModel.getDob());
			model.addAttribute("firstName", customerModel.getFirstName());
		}
		return dest;
	}
	
	private List<DropDownData> getTitleList() {
		List<Title> titleList = new ArrayList<Title>();
		List<DropDownData> titleDropDownList = new ArrayList<DropDownData>();
		titleList = refService.getAllTitles();
		for (Title t : titleList) {
			titleDropDownList.add(new DropDownData(t.id(), t.name()));
		}
		return titleDropDownList;
	}

}