package test;

public class News {

	private String title;
	private String body;
	private String notes;
	
	public News() {}
	
	public News(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", body=" + body + ", notes=" + notes
				+ "]";
	}
	
	
}
