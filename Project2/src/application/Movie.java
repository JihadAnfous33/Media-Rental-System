package application;

public class Movie extends Media
{
	
	private String rating;
	
	public Movie()
	{
		super();
	}
	public Movie(String title, int copiesAvailable, String rating , String code)
	{
		super(title, copiesAvailable, code);
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) throws IllegalArgumentException {
		if(rating.contains("HR") || rating.contains("DR") || rating.contains("AC"))
		{
			this.rating = rating;
		}
		else
			throw new IllegalArgumentException("You have to enter the rating from the following"
					+ "(HR , DR , AC");
		
	}
	
	public int compareTo(Media m)
	{
		if(getTitle().compareTo(this.getTitle())>0)
		{
			return 1;
		}
		else if(getTitle().compareToIgnoreCase(this.getTitle())<0)
		{
			return -1;
		}
		else
			return 0;
				
	}
	@Override
	public String toString() {
		return "Movie"+"$"+code+"$"+title+"$"+copiesAvailable+"$"+rating;
	}
	
	public String print() {
		return "Movie [Code: "+code+", Title: "+title+", Copies: "+copiesAvailable+
				", Rating: "+rating+"]";
	}
	
}
