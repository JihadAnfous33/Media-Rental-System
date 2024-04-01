package application;


public abstract class Media implements Comparable <Media>
{
	protected String title;
	protected int copiesAvailable;
	protected String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Media()
	{
		
	}
	public Media(String title, int copiesAvailable, String code)
	{
		this.title = title;
		this.copiesAvailable = copiesAvailable;
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}
	
	public abstract int compareTo(Media obj);
	
	public abstract String toString();
	
	public abstract String print();
	
	public boolean equals(Object obj)
	{
		return this==obj;
	}
	
}
