package joe.spring.springweb.mvc.data;

public class FormFieldError {
	private String fieldName;
	private String errorMsg;
	
	public FormFieldError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormFieldError(String fieldName, String errorMsg) {
		super();
		this.fieldName = fieldName;
		this.errorMsg = errorMsg;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "FieldError [fieldName=" + fieldName + ", errorMsg=" + errorMsg
				+ "]";
	}
	
	
}
