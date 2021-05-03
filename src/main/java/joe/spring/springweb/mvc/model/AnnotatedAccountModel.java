package joe.spring.springweb.mvc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AnnotatedAccountModel {

	@NotNull
	private Long customerId;

	@NotNull
	private Long accountTypeId;

	@Size(min=11, max=11)
	private String accountNumber;

	public AnnotatedAccountModel() {
		super();
	}

	public AnnotatedAccountModel(Long customerId, Long accountTypeId,
			String accountNumber) {
		super();
		this.customerId = customerId;
		this.accountTypeId = accountTypeId;
		this.accountNumber = accountNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "AnnotatedAccountModel [customerId=" + customerId
				+ ", accountTypeId=" + accountTypeId + ", accountNumber="
				+ accountNumber + "]";
	}

	
}
