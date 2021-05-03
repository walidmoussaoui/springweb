package joe.spring.springweb.mvc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import joe.spring.springweb.mvc.model.LoginModel;

public class LoginModelValidator implements Validator {

	protected final static Logger log = LoggerFactory
			.getLogger(LoginModelValidator.class);
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return LoginModel.class.equals(paramClass);	}

	@Override
	public void validate(Object obj, Errors errors) {
		log.info("In LoginModelValidator.validate().");
         
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        
        LoginModel login = (LoginModel) obj;
        if (login.getUserName() == null || login.getUserName().isEmpty()) {
        	errors.rejectValue("userName", "userName.required", new Object[]{"'userName'"}, "userName cannot be null or empty.");
        }
        if (login.getPassword() == null || login.getPassword().isEmpty()) {
        	errors.rejectValue("password", "password.required", new Object[]{"'password'"}, "password cannot be null or empty.");
        }
        
		log.info("LoginModelValidator.validate() caught " + errors.getErrorCount() + " errors.");
    }

}
