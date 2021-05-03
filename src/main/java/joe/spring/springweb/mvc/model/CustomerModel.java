package joe.spring.springweb.mvc.model;



public class CustomerModel {

	private Long title;
	
	private String firstName;

	private String lastName;
	
	private String userName;
	
	private String dob;

	public CustomerModel() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Long getTitle() {
		return title;
	}

	public void setTitle(Long title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "CustomerModel [title=" + title + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userName=" + userName
				+ ", dob=" + dob + "]";
	}
	
}
