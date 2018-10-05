package Salil.DBWrapper.beans;

public class MemberValObject {
	String email;
	String password;
	
	@Override
	public String toString() {
		return "MemberValObject [email=" + email + ", password=" + password + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
