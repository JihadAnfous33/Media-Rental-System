package application;


public class Game extends Media
{
	private double weight;
	
	public Game()
	{
		super();
	}
	public Game(String title, int copiesAvailable, double weight , String code)
	{
		super(title, copiesAvailable, code);
		this.weight = weight;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
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
		return "Game"+"$"+code+"$"+title+"$"+copiesAvailable+"$"+weight;
	}
	
	public String print()
	{
		return "Game[Code: "+code+", Title: "+title+", Copies: "+copiesAvailable+
				", Weight: "+weight+"]";
	}
	
	
	
}
