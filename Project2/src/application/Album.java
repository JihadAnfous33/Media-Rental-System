package application;

public class Album extends Media
{
	private String artist;
	private String songs;

	public Album()
	{
		super();
	}
	public Album(String title, int copiesAvailable, String artist, String songs, String code)
	{
		super(title, copiesAvailable, code);
		this.artist = artist;
		this.songs = songs;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
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
		return "Album"+"$"+code+"$"+title+"$"+copiesAvailable+"$"+artist+"$"+songs;
	}
	
	public String print() {
		return "Album[Code: "+code+", Title: "+title+", Copies: "+copiesAvailable+
				", Artist: "+artist+", Song/s: "+songs+"]";
	}

}
