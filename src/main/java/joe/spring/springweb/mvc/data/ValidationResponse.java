package joe.spring.springweb.mvc.data;

import java.util.List;

public class ValidationResponse {
	private String status;
	private List<FormFieldError> fieldErrorList;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<FormFieldError> getFieldErrorList() {
		return this.fieldErrorList;
	}

	public void setErrorMessageList(List<FormFieldError> fieldErrorList) {
		this.fieldErrorList = fieldErrorList;
	}
}
