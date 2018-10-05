package ie.tcd.ajgaonks.DBWrapper.beans;

public class TaskDoc {
	String id = null;
	String task = null;
	
	@Override
	public String toString() {
		return "TaskDoc [id=" + id + ", task=" + task + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}

}
