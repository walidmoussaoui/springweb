package joe.spring.springweb.mvc.validator;

import joe.spring.springweb.mvc.model.CustomerModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomerModelValidator implements Validator {

	protected final static Logger log = LoggerFactory
			.getLogger(CustomerModelValidator.class);
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return CustomerModel.class.equals(paramClass);	}

	@Override
	public void validate(Object obj, Errors errors) {
		log.info("In CustomerModelValidator.validate().");
         
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "dob.required");
        
        CustomerModel customer = (CustomerModel) obj;
        if (customer.getTitle() == null || customer.getTitle() == 0) {
        	errors.rejectValue("title", "title.required", new Object[]{"'title'"}, "title cannot be 0 or null.");
        }
        
		log.info("CustomerModelValidator.validate() caught " + errors.getErrorCount() + " errors.");
    }

}
