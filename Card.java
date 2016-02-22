//Card class
import java.util.Random;
import java.util.Scanner;

public class Card
{
	// variables   
	private int number, value, suit;
	private static int aceCard;
	String[] names = { "Ace", "Two", "Three", "Four", "Five", "Six" ,"Seven",
						"Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
	String cardSuit = "";
	String cardName = "";
	private boolean aces = false;
	
	public Card()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	  	Constructor
	 	@param
	 	@param
	*/
	public Card(int cardValue, String[] name, String[] suit)
	{
		//deck = d;
		number = cardValue;
		names = name;
		suits = suit;
	}
	
	//sets the card number
	public void setCardNumber() 
	{
		Random randomNumber = new Random();
		int temp = (randomNumber.nextInt(13) + 1);
		number = setCardValue(temp);	
	}
	
	//returns the card number
	public int getCardNumber()
	{
		return number;
	}
	
	//sets the suit name e.g. clubs, hearts, diamonds, spades
	public void setCardSuit()
	{
		suit = new Random().nextInt(suits.length);
		cardSuit = (suits[suit]);
	}
	
	//gets the suit name e.g. clubs, hearts, diamonds, spades
	public String getCardSuit()
	{
		return cardSuit;
	}

	//sets the card's value and corrects for Jacks, Queens, & Kings
	public int setCardValue(int value)
	{
		switch(value)
		{
			case 1:
				setCardName(value-1);
				number = 1;
				break;
			case 2:
				setCardName(value-1);
				number = 2;
				break;
			case 3:
				setCardName(value-1);
				number = 3;
				break;
			case 4:
				setCardName(value-1);
				number = 4;
				break;
			case 5:
				setCardName(value-1);
				number = 5;
				break;
			case 6:
				setCardName(value-1);
				number = 6;
				break;
			case 7:
				setCardName(value-1);
				number = 7;
				break;
			case 8:
				setCardName(value-1);
				number = 8;
				break;
			case 9:
				setCardName(value-1);
				number = 9;
				break;
			case 10:
				setCardName(value-1);
				number = 10;
				break;
			case 11:
				setCardName(value-1);
				number = 10;
				break;
			case 12:
				setCardName(value-1);
				number = 10;
				break;
			case 13:
				setCardName(value-1);
				number = 10;
				break;
		}
		return number;
	}

	//converts number to names such as Ace, Two, ... Queen, King
	public void setCardName(int index)
	{
		cardName = names[index];
	}
	
	//returns the card name in s string
	public String getCardName()
	{
		return cardName;
	}
	
	//returns the card's value
	public int getCardValue()
	{
		return value;
	}
	
	//gives user the choice for Ace of 1 or 11
	public static void setAce()
	{
		System.out.print("Will your Ace be worth 1 or 11? ");
		Scanner pick = new Scanner(System.in);
		aceCard = pick.nextInt();
	}
	
	//returns the Ace choice
	public int getAce()
	{
		return aceCard;
	}
	
	public void playersAceExist()
	{
		aces = true;
	}
	
	public boolean doesAceExist()
	{
		
		return aces;
	}
	
	//toString method
	public String toString()
	{
		return null;
	}
}
