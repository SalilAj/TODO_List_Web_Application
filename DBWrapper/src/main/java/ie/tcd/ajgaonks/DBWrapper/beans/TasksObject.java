package ie.tcd.ajgaonks.DBWrapper.beans;
import java.util.ArrayList;

public class TasksObject {
	
	String memberId;
	ArrayList<TaskDoc> task;
	
	public ArrayList<TaskDoc> getTask() {
		return task;
	}
	
	public void setTask(ArrayList<TaskDoc> task) {
		this.task = task;
	}

	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "TasksObject [memberId=" + memberId + ", task=" + task + "]";
	}


}
