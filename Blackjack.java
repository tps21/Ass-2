import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Blackjack 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		//variables
		int answer;
		int hands = 0;
		int win = 0;
		int count = 0;
		double money, bank;
		String filename = null, username;
		
		/** Section will ask for user's name
			check to see if user exists, 
			create a new user with game data, 
			or load a saved user with game dat
		*/
		System.out.println("****************************************");
		System.out.println("Welcome to Infinite Blackjack");
		System.out.println(" ");
		System.out.print("Please Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		username = scanner.nextLine();
		
		//establishes a player
		Player player = new Player(username);
			
		//check for saved game
		File yourFile = new File(username + ".txt");
		if(!yourFile.exists()) 
		{
	    	PrintWriter outputFile = new PrintWriter(username + ".txt");
	    	System.out.print("Enter Initial Bank Deposit: $");
	   		money = scanner.nextDouble();
	   		player.setBank(money);
	   		outputFile.close();
		} 
		
		//Greetings to start the game
		System.out.println("Welcome: " + player.getName());
		
		//loads saved game data
		Scanner readFile = new Scanner(yourFile);
		while (readFile.hasNext()) 
		{
	      	if (count==0 && readFile.hasNextInt()) 
	      	{
		     	player.setWins(readFile.nextInt());
		       	count++;
	      	}
	      	else if (count==1 && readFile.hasNextInt())
	      	{
	      		player.setHandsPlayed(readFile.nextInt());
	      	}
	      	else if (readFile.hasNextDouble()) 
	      	{
	      		player.setBank(readFile.nextDouble());
	      	} 
	      	else 
	      	{
	      		String str = readFile.next();
		    }
		}
			
		//writes data to user's file
		PrintWriter outputFile = new PrintWriter(username + ".txt");

		//do while for the game, everybody plays at least once
		do
		{
			//creates a player and a dealer
			Card card = new Card();
			Card dealerCard = new Card();
			
			//asks for the bet
			bank = player.getBank();
			System.out.println("Your Bank is:  " + bank);
			System.out.print("How much do you wish to bet? ");
			double bet = scanner.nextDouble();
			
			//sets up the first card for both players
			card.setCardNumber();
			card.setCardSuit();
			dealerCard.setCardNumber();
			dealerCard.setCardSuit();
			
			//sets up the first card totals for both players
			int total = card.getCardNumber();
			int dealerTotal = dealerCard.getCardNumber();
			
			//sets up card counter
			int playerCardDrawn = 1;
			int dealerCardDrawn = 1;
	
			//first card drawn
			int firstCardTemp = total;
			System.out.println("Card "+ playerCardDrawn +" is: " + card.getCardName() + " of " + card.getCardSuit());
			
			//second card for first player
			playerCardDrawn++;
			card.setCardNumber();
			card.setCardSuit();
			
			//card.setCardValue(card.getCardNumber());
			System.out.println("Card "+ playerCardDrawn +" is: " + card.getCardName() + " of " + card.getCardSuit());
			int secondCardTemp = card.getCardNumber();
			
			//player Ace check
			if (firstCardTemp == 1)
			{
				Card.setAce();
				firstCardTemp = card.getAce();
			}
			else if (secondCardTemp == 1)
			{
				Card.setAce();
				secondCardTemp = card.getAce();
			}
		
			//first dealer card for the dealer
			System.out.println("Dealer Card "+ dealerCardDrawn +" is: " + dealerCard.getCardName() + " of " + dealerCard.getCardSuit());
			int firstDealerCardTemp = dealerTotal;
			
			//second card for dealer
			dealerCardDrawn++;
			dealerCard.setCardNumber();
			dealerCard.setCardSuit();
			dealerCard.setCardValue(dealerCard.getCardNumber());
			System.out.println("Dealer Card "+ dealerCardDrawn +" is: " + dealerCard.getCardName() + " of " + dealerCard.getCardSuit());
			int secondDealerCardTemp = dealerCard.getCardNumber();
			
			//dealer Ace check
			if (firstDealerCardTemp == 1)
			{
				card.setAce();
				firstDealerCardTemp = card.getAce();
			}
			else if (secondDealerCardTemp == 1)
			{
				card.setAce();
				secondDealerCardTemp = card.getAce();
			}
	
			//shows total for the face off
			total = firstCardTemp + secondCardTemp;
			dealerTotal = firstDealerCardTemp + secondDealerCardTemp;
			System.out.println("Your total is: " + total);
			System.out.println("Dealer total is: " + dealerTotal);
			
			//asks if player would like to continue
			System.out.print("Would you like another card?(NO=1 YES=any other number)");
			Scanner scanner1 = new Scanner(System.in);
			
			//makes sure a integer is entered
			while (!scanner1.hasNextInt())
			{
	   			System.out.println("Pick a number please!");
	   			scanner1.nextLine();
			}
			
			int choice = scanner1.nextInt();
			playerCardDrawn++;
			
			//loop to continue asking for hits
			while (choice != 1 && total < 21)
			{
				System.out.println("Card "+ playerCardDrawn +" is: " + card.getCardName() + " of " + card.getCardSuit());
				total = total + card.getCardNumber();
				
				if (total >= 21)
				{
					choice = 1;
					break;
				}
				
				//shows total and asks for another card
				card.setCardSuit();
				System.out.println("Your total is: " + total);
				card.setCardNumber();
				playerCardDrawn++;
				System.out.print("Would you like another card?(NO=1 YES=any other number)");
				
				//makes sure a integer is entered
				while (!scanner1.hasNextInt())
				{
		   			System.out.println("Pick a number please!");
		   			scanner1.nextLine();
				}
				choice = scanner1.nextInt();		
			}
	
			//while loop for dealer
			while ((dealerTotal < 17  && dealerCardDrawn <= 5) && total <= 21)
			{
				//shows dealer card and dealerTotal
				dealerCardDrawn++;
				System.out.println("Dealer Card "+ dealerCardDrawn +" is: " + dealerCard.getCardName() + " of " + dealerCard.getCardSuit());
				dealerTotal = dealerTotal + dealerCard.getCardNumber();
				System.out.println("Dealer total is: " + dealerTotal);
				dealerCard.setCardNumber();
			}
			
			//who is the winner of this game
			if (dealerTotal > total && dealerTotal <= 21)
			{
				System.out.println("dealer wins");
				bank = bank - bet;
				player.setBank(bank);
				hands = player.getHandsPlayed() + 1;
				player.setHandsPlayed(hands);
			}
			else if (dealerTotal > total && dealerTotal > 21)
			{
				System.out.println("player wins");
				win = player.getWins() + 1;
				player.setWins(win);
				bank = bank + (bet);
				player.setBank(bank);
				hands = player.getHandsPlayed() + 1;
				player.setHandsPlayed(hands);
			}
			else if (total == 21 && playerCardDrawn < 3)
			{
				System.out.println("BLACKJACK wins");
				win = player.getWins() + 1;
				player.setWins(win);
				bank = bank + (1.5 * bet);
				player.setBank(bank);
				hands = player.getHandsPlayed() + 1;
				player.setHandsPlayed(hands);
			}
			else if (dealerTotal < total && total > 21)
			{
				System.out.println("dealer wins");
				bank = bank - bet;
				player.setBank(bank);
				hands = player.getHandsPlayed() + 1;
				player.setHandsPlayed(hands);
			}
			else if (dealerTotal < total)
			{
				System.out.println("player wins");
				win = player.getWins() + 1;
				player.setWins(win);
				bank = bank + (bet);
				player.setBank(bank);
				hands = player.getHandsPlayed() + 1;
				player.setHandsPlayed(hands);
			}
			else
			{
				System.out.println("push!");
				hands = player.getHandsPlayed() + 1;
				player.setHandsPlayed(hands);
			}
			
			//shows the current stats
			System.out.println("Your bank is now $ " + player.getBank());			
			System.out.println("Your win total is " + player.getWins());			
			System.out.println("Total hands played: " + player.getHandsPlayed());			
			System.out.print("Do you wish to play again?(NO=1 YES=any other number)");
			
			
			//makes sure a integer is entered
			while (!scanner1.hasNextInt())
			{
	   			System.out.println("Pick a number please!");
	   			scanner1.nextLine();
			}
			answer = scanner1.nextInt();
			
		}while(answer != 1);
		
		//shows end of game stats
		outputFile.println("Your bank is now $ " + player.getBank());
		outputFile.println("Your win total is " + player.getWins());
		outputFile.println("Total hands played: " + player.getHandsPlayed());
		outputFile.close();
		
		
		//goodbye message to know if program ended
		System.out.println("\ngoodbye");
		System.out.println("****************************************");
	}
}
