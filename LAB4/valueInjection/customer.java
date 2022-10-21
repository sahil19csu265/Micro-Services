package valueInjection;

import java.util.*;

public class customer {
	
	String name;
	String email;
	
	address Address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public address getAddress() {
		return Address;
	}

	public void setAddress(address address) {
		Address = address;
	}

	@Override
	public String toString() {
		return "customer [name=" + name + ", email=" + email + ", Address=" + Address + "]";
	}
	
	
	
}
