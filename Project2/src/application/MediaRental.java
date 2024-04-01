package application;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRental implements MediaRentalInt
{
	public ArrayList <Customer> cList = new ArrayList<>();
	public ArrayList <Media> mList = new ArrayList<>();
	public static int limitedPlan = 2;
	
	
	
	public void addCustomer(String ID ,String name, String address, String MobileNum ,String plan)
	{
		cList.add(new Customer(ID ,name, address, MobileNum, plan));
	}
	
	public void addMovie(String title, int copiesAvailable, String rating, String code)
	{
		mList.add(new Movie(title,copiesAvailable,rating,code));
	}
	
	public void addGame(String title, int copiesAvailable, double weight, String code)
	{
		mList.add(new Game(title,copiesAvailable,weight,code));		
	}
	
	public void addAlbum(String title, int copiesAvailable, String artist, String songs ,String code)
	{
		
		mList.add(new Album(title,copiesAvailable,artist,songs,code));
	}
	
	public void setLimitedPlanLimit(int value)
	{
		this.limitedPlan = value;
	}
	
	public String getAllCustomersInfo()
	{
		String customerInfo="";
		Collections.sort(cList);
		for(int i=0; i<cList.size(); i++)
		{
			customerInfo += cList.get(i).toString()+"\n";
		}
		return customerInfo;
	}
	
	public String getAllMediaInfo()
	{
		String mediaInfo="";
		Collections.sort(mList);
		for(int i=0; i<mList.size(); i++)
		{
			mediaInfo += mList.get(i).toString()+"\n";
		}
		
		return mediaInfo;
	}
	
	public String printMediaInfo()
	{
		String mediaInfo ="";
		for(int i=0; i<mList.size(); i++)
		{
			mediaInfo += (i+1)+") "+mList.get(i).print()+"\n";
		}
		return mediaInfo;
	}
	
	public boolean addToCart(String CustomerID , String mediaCode)
	{
		Customer cust = null;
		for(int i=0; i<cList.size(); i++)
		{
			if(cList.get(i).getID().equals(CustomerID))
			{
				cust = cList.get(i);
			}
		}
		
		for(int i=0; i<mList.size(); i++)
		{
			if(mList.get(i).getCode().equals(mediaCode))
			{
				if(!(cust.getInterestedIn().contains(mList.get(i).getTitle())))
				{
					cust.getInterestedIn().add(mList.get(i).getTitle());

				}
				return true;
			}
		}
		return false;
	}
	
	public boolean removeFromCart(String CustID, String mediaCode)
	{
		Customer cust = null;
		String mediaTitle = "";
		for(int i=0; i<cList.size(); i++)
		{
			if(cList.get(i).getID().equals(CustID))
			{
				cust = cList.get(i);
			}
		}
		for(int i=0; i<mList.size(); i++)
		{
			if(mList.get(i).getCode().equals(mediaCode))
			{
				mediaTitle = mList.get(i).getTitle();
			}
		}
		for(int i=0; i<cust.getInterestedIn().size(); i++)
		{
			if(cust.getInterestedIn().get(i).equals(mediaTitle))
			{
				cust.getInterestedIn().remove(mediaTitle);
				//System.out.println(mediaTitle+" has been removed from "+customerName+"'s interested in cart");
				return true;
			}
		}
		//System.out.println(mediaTitle+" is not in "+customerName+"'s interested in cart");
		return false;
	}
	
	public String processReqeusts(String CustID)
	{
		//Collections.sort(cList);
		String r = "";
		for(int i=0; i<cList.size(); i++)
		{
			if(cList.get(i).getID().equals(CustID))
			{
				int f=0;
				int InterestedInSize = cList.get(i).getInterestedIn().size();
				if(cList.get(i).getPlan().equals("UNLIMITED"))
				{
					for(int j=0; j<InterestedInSize; j++)
					{
						for(int e=0; e<mList.size(); e++)
						{
							if(mList.get(e).getTitle().equals(cList.get(i).getInterestedIn().get(f)))
							{
								if(mList.get(e).getCopiesAvailable()>0)
								{
									cList.get(i).getRented().add(cList.get(i).getInterestedIn().get(f));
									cList.get(i).getInterestedIn().remove(f);
									mList.get(e).setCopiesAvailable(mList.get(e).getCopiesAvailable()-1);
									r += cList.get(i).getName()+" Purchased "+mList.get(e).getTitle();
									break;
								}else 
									f++;
							}
						}
						
					}
				}else if(cList.get(i).getPlan().equals("LIMITED"))
				{
					for(int j=0; j<InterestedInSize; j++)
					{
						for(int e=0; e<mList.size(); e++)
						{
							if(mList.get(e).getTitle().equals(cList.get(i).getInterestedIn().get(f)))
							{
								if(mList.get(e).getCopiesAvailable()>0)
								{
									if(cList.get(i).getRented().size()<limitedPlan)
									{
										cList.get(i).getRented().add(cList.get(i).getInterestedIn().get(f));
										cList.get(i).getInterestedIn().remove(f);
										mList.get(e).setCopiesAvailable(mList.get(e).getCopiesAvailable()-1);
										r += cList.get(i).getName()+" Purchased "+mList.get(e).getTitle();
										break;
									}
								}else 
									f++;
							}
						}
					}
				}
			}
		}
		return r;	
	}
	
	public boolean returnMedia(String CustID, String mediaCode)
	{
		Customer cust = new Customer();
		String mediaTitle = "";
		for(int i=0; i<cList.size(); i++)
		{
			if(cList.get(i).getID().equals(CustID))
			{
				cust = cList.get(i);
			}
		}
		for(int i=0; i<mList.size(); i++)
		{
			if(mList.get(i).getCode().equals(mediaCode))
			{
				mediaTitle = mList.get(i).getTitle();
			}
		}
		for(int i=0; i<cust.getRented().size(); i++)
		{
			if(cust.getRented().get(i).equals(mediaTitle))
			{
				cust.getRented().remove(mediaTitle);
				//System.out.println(mediaTitle+" has been removed from "+customerName+"'s rented cart.");
			}
		}
		for(int i=0; i<mList.size(); i++)
		{
			if(mList.get(i).getTitle().equals(mediaTitle))
			{
				mList.get(i).setCopiesAvailable(((mList.get(i).getCopiesAvailable())+1));
				//System.out.println("Now there is "+mList.get(i).getCopiesAvailable()+" copy of "+mList.get(i).getTitle()+" in your store.");
				return true;
			}
		}
		return false;
	
	}
	
	
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs)
	{
		ArrayList <String> mediaTitles = new ArrayList<>();
		
		for(int i=0; i<mList.size(); i++)
		{
			if((title!=null) && (rating==null) && (artist==null) && (songs==null))
			{
				if(mList.get(i).getTitle().equalsIgnoreCase(title))
				{
					mediaTitles.add(mList.get(i).getTitle());
					
				}
				else
					System.out.println("The media you want is not in the database");
			}
			else if((title!=null) && (rating!=null) && (artist==null) && (songs==null))
			{
				if(mList.get(i).getTitle().equalsIgnoreCase(title) || ((Movie)mList.get(i)).getRating().equalsIgnoreCase(rating))
				{
					mediaTitles.add(mList.get(i).getTitle());
					
				}
				else
					System.out.println("The media you want is not in the database");
			}
			else if((title==null) && (rating!=null) && (artist==null) && (songs==null))
			{
				if(((Movie)mList.get(i)).getRating().equalsIgnoreCase(rating))
				{
					mediaTitles.add((mList.get(i)).getTitle());
					
				}
				else
					System.out.println("The media you want is not in the database");
			}
			else if((title==null) && (rating==null) && (artist!=null) && (songs==null))
			{
				if(((Album)mList.get(i)).getArtist().equalsIgnoreCase(title))
				{
					mediaTitles.add(((Album)mList.get(i)).getTitle());
				}
				else
					System.out.println("The media you want is not in the database");
			}
			else if((title==null) && (rating==null) && (artist==null) && (songs!=null))
			{
				String [] tokens = ((Album)mList.get(i)).getSongs().split(",");
				for(int j=0; j<tokens.length; j++)
				{
					if(songs.equalsIgnoreCase(tokens[j]))
					{
						mediaTitles.add(((Album)mList.get(i)).getTitle());
					}
				}
			}
			else if((title!=null) && (rating==null) && (artist!=null) && (songs!=null))
			{
				String [] tokens = ((Album)mList.get(i)).getSongs().split(",");
				for(int j=0; j<tokens.length; j++)
				{
					if((songs.equalsIgnoreCase(tokens[j])) || (mList.get(i).getTitle().equalsIgnoreCase(title)) || 
							((Album)mList.get(i)).getArtist().equalsIgnoreCase(artist))
					{
						mediaTitles.add(mList.get(i).getTitle());
					}
				}
			}
			else if((title!=null) && (rating!=null) && (artist!=null) && (songs!=null))
			{
				String [] tokens = ((Album)mList.get(i)).getSongs().split(",");
				for(int j=0; j<tokens.length; j++)
				{
					if(((mList.get(i).getTitle().equalsIgnoreCase(title)) || ((Movie)mList.get(i)).getRating().equals(rating)) 
							|| (((Album)mList.get(i)).getArtist().equalsIgnoreCase(artist)) || tokens[j].equalsIgnoreCase(songs))
					{
						mediaTitles.add(mList.get(i).getTitle());
					}		
				}
			}
			else 
			{
				mediaTitles.add(mList.get(i).getTitle());
			}
		}
		Collections.sort(mediaTitles);
		return mediaTitles;
	}
}
