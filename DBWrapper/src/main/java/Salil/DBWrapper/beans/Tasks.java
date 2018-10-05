package Salil.DBWrapper.beans;

import java.util.Arrays;

public class Tasks {
	String memberId;
	String listName;
	String[] tasks;
	
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
	public String[] getTasks() {
		return tasks;
	}
	public void setTasks(String[] tasks) {
		this.tasks = tasks;
	}
	@Override
	public String toString() {
		return "Tasks [memberId=" + memberId + ", listName=" + listName + ", tasks=" + Arrays.toString(tasks) + "]";
	}
	
}
