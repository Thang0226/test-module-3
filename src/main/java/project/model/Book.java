package project.model;

public class Book {
	private int id;
	private String name;
	private String author;
	private String description;
	private int count;

	public Book(int id, String name, String author, String description, int count) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
