package project.model;

public class Student {
	private int id;
	private String fullName;
	private String className;

	public Student(int id, String fullName, String className) {
		this.id = id;
		this.fullName = fullName;
		this.className = className;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
