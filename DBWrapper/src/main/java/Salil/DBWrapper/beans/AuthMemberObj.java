package Salil.DBWrapper.beans;

public class AuthMemberObj {
	boolean authenticated;
	String memberId;
	
	@Override
	public String toString() {
		return "AuthMemberObj [authenticated=" + authenticated + ", memberId=" + memberId + "]";
	}
	
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
