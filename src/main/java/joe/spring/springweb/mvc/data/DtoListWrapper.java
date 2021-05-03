package joe.spring.springweb.mvc.data;

import java.util.List;

import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonProperty;

import joe.spring.springdomain.CustomerDto;

@XmlRootElement(name="data")
@XmlSeeAlso({CustomerDto.class})
public class DtoListWrapper<T> {

	@JsonProperty("data")
	private List<T> data; 

	public DtoListWrapper() {}

	public DtoListWrapper(List<T> list) {
		this.data = list;
	}

	@XmlMixed
	public List<T> getData() {
		return data;
	}
	
}
