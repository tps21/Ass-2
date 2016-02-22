//player class
public class Player 
{
	//variables
    private String playerName;
    private double bank;
    private int wins, handsPlayed;    

    //constructor
    public Player(String name) 
    {
        playerName = name;
    }

    /**
     * This set method sets the name of the player.
     *  @param Takes in a String as name.
     */
    public void setName(String name) 
    {
        playerName = name;
    }

    /**
     * This get method returns the String value of player name.
     *  @return playerName, a String value.
     */
    public String getName() 
    {
        return playerName;
    }
    
    //sets player's bank
    public void setBank(Double money)
    {
    	bank = money;
    }
    
    //gets the player's bank
    public double getBank()
    {
    	return bank;
    }
    
    //sets the player's win(s)
    public void setWins(int w)
    {
    	wins = w;
    }
    
    //gets the wins
    public int getWins()
    {
    	return wins;
    }
    
    //sets the number of handsPlayed
    public void setHandsPlayed(int h)
    {
    	handsPlayed = h;
    }
    
    //gets the number of handsPlayed
    public int getHandsPlayed()
    {
    	return handsPlayed;
    }
}
