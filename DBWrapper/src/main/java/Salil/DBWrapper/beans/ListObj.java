package Salil.DBWrapper.beans;

public class ListObj {
	String memberId;
	String listName;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	@Override
	public String toString() {
		return "ListObj [memberId=" + memberId + ", listName=" + listName + "]";
	}
	

}
