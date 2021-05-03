package joe.spring.springweb.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.dto.DtoConverter;
import joe.spring.springapp.data.reference.Title;
import joe.spring.springapp.services.CustomerService;
import joe.spring.springapp.services.ReferenceService;
import joe.spring.springdomain.CustomerDto;
import joe.spring.springdomain.CustomerDtoList;
import joe.spring.springdomain.CustomerSearchRequest;
import joe.spring.springdomain.CustomerSearchResponse;
import joe.spring.springdomain.RequestStatus;
import joe.spring.springweb.mvc.data.DropDownData;
import joe.spring.springweb.mvc.data.FormFieldError;
import joe.spring.springweb.mvc.data.ValidationResponse;
import joe.spring.springweb.mvc.model.CustomerModel;

/**
 * Handles requests for the form page examples.
 */
@RestController
@RequestMapping("api/customer")
public class CustomerServiceController {

	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected ReferenceService refService;

//	@Autowired
//    @Qualifier("customerModelValidator")
//    private Validator validator;

	@Autowired
	protected MessageSource messageSource;
	
//	@InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }
	protected final static Logger log = LoggerFactory
			.getLogger(CustomerServiceController.class);

	public CustomerServiceController() {

	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.POST, produces = "application/json")
	public List<CustomerDto> getAllCustomers() {
		log.debug("Fetching a list of all customers");
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerService
				.getAllCustomers();
		log.debug("CustomerService.getCustomers() returned "
				+ customerList.size() + " customers.");
		
		return DtoConverter.toCustomerDtoList(customerList);
	}

	@RequestMapping(value = "/customerSearch", method = RequestMethod.POST, produces = "application/json")
	public List<CustomerDto> searchCustomers(
			@RequestParam(value = "searchTerm", required = false) String searchTerm) {
		log.debug("Searching for customers with searchTerm = " + searchTerm);
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerService
				.searchCustomers(searchTerm);
		log.debug("CustomerService.searchCustomers() returned "
				+ customerList.size() + " customers.");
		return DtoConverter.toCustomerDtoList(customerList);
	}
	
	@RequestMapping(value = "/v2/customerSearch", method = RequestMethod.POST, produces = "application/json")
	public CustomerSearchResponse searchCustomersVersion2(
			@RequestBody CustomerSearchRequest request) {
		CustomerSearchResponse response = new CustomerSearchResponse();
		
		log.debug("Searching for customers with searchTerm = " + request.getSearchTerm());
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerService
				.searchCustomers(request.getSearchTerm());
		log.debug("CustomerService.searchCustomers() returned "
				+ customerList.size() + " customers.");
		response.setStatus(RequestStatus.OK);
		CustomerDtoList customers = new CustomerDtoList();
		customers.getCustomers().addAll(DtoConverter.toCustomerDtoList(customerList));
		response.setCustomers(customers);
		return response;
	}
	
	
	@RequestMapping(value = "/createCustomerJson", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ValidationResponse createCustomerJson(
			@Validated CustomerModel customerModel, BindingResult result,
			Model model) {

		log.info("In createCustomerJson...");
		ValidationResponse response = new ValidationResponse();
		log.info("CustomerModel:" + customerModel);
		if (result.hasErrors()) {
			response.setStatus("ERROR");
			List<FormFieldError> errorList = new ArrayList<FormFieldError>();
			// Validation - get the current locale to use to look up error messages
			Locale currentLocale = LocaleContextHolder.getLocale();
			log.info("Form validation errors: " + result.getErrorCount());
			for (ObjectError oe : result.getAllErrors()) {
				log.info(((FieldError) oe).toString());
				// Look up the localized error message and create a FormFieldError with it.
		        String localizedErrorMessage = messageSource.getMessage((FieldError) oe, currentLocale);
				errorList.add(new FormFieldError(((FieldError) oe).getField(),
						localizedErrorMessage));
			}
			response.setErrorMessageList(errorList);
		} else {
			response.setStatus("OK");
			log.info("NO form validation errors found.");
			// TODO: With no validation errors, time to create a new customer.
		}
		return response;
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