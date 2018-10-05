package Salil.DBWrapper.beans;

public class TaskObj {
	String memberId;
	String listName;
	String task;
	
	@Override
	public String toString() {
		return "TaskObj [memberId=" + memberId + ", listName=" + listName + ", task=" + task + "]";
	}
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}

}
