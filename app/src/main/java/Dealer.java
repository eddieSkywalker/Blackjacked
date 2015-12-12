import java.util.ArrayList;
import java.util.Scanner;

public class Dealer {
	private Deck deck;
	Scanner scan = new Scanner(System.in);
	Card playersCard1, playersCard2, dealersCard1, dealersCard2;
	ArrayList<Card> dealersHand = new ArrayList<Card>();
	ArrayList<Card> playersHand = new ArrayList<Card>();
	String playAgain = "";
	private static int wins, losses;
	private static int dHand, pHand;
	private int cashMoney = 10000;
	int amount = 0;
	int aceValue = 0;
 
	public Dealer(String path) 
	{
		deck = new Deck(path);
	}
	public void deal()
	{
		//DEALS CARDS TO DEALER AND PLAYER AND ADDS THEM TO ACCORDING ARRAY LISTS
		this.playersCard1 = deck.dealCard();
		playersHand.add(playersCard1);
		deck.deckOfCards.remove(deck.deckOfCards.size()-1);
		this.playersCard2 = deck.dealCard();
		playersHand.add(playersCard2);
		deck.deckOfCards.remove(deck.deckOfCards.size()-1);
		this.dealersCard1 = deck.dealCard();
		dealersHand.add(dealersCard1);
		deck.deckOfCards.remove(deck.deckOfCards.size()-1);
		this.dealersCard2 = deck.dealCard();
		dealersHand.add(dealersCard2);
		deck.deckOfCards.remove(deck.deckOfCards.size()-1);
	}
	public void rounds()
	{		
		while(deck.deckOfCards.size() >= 4)
		{
		//ROUND STARTS HERE.
		amount = 0;
		pHand = 0;
		dHand = 0;
		System.out.println("How much would you like to bet?(5$ min, 500$ max) "
						 + "Or press '0' to end game.");
		amount = scan.nextInt();
		bet(amount);
		deal();
		System.out.println("Your hand: " + playersCard1.toString() + ", " + playersCard2.toString());
		System.out.println("Dealer's hand: " + dealersCard1.toString() + ", Hidden");
		System.out.println(pHandRank() + " <-- player  dealer --> " + dHandRank());
		quickWin();			
		nextMove();
		}
	}
	public void play()
	{
		//THIS METHOD RUNS ONCE PER DECK
		System.out.println("Welcome to Vegas Blackjack!");
		System.out.println("Your score will be kept throughout your game.");
		System.out.println("Your current cash amount: $" + cashMoney);
		System.out.println("Goodluck!\n");
		deck.shuffle();
		System.out.println("Cards left in deck: " + deck.deckOfCards.size());
		//ROUNDS IS THE FUNCTIONING PLAY-THROUGH OF EACH 'GAME/ROUND/HAND' of BLACKJACK
		rounds();

	}
	private void bet(int amount)
	{	
		// SIMPLE BETTING SYSTEM WITH '0' TO EXIT GAME
		if(amount ==  0)
		{
			System.out.println("Final payout is: " + cashMoney);
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
		while(amount > cashMoney || amount < 5 || amount > 500)			
		{
			System.out.println("You cannot bet that much, homie!");
			System.out.println("Please place a proper bet. Or press '0' to end game.\n");
			rounds();
		}
		if(amount < cashMoney && amount > 4 || amount < 501)
		{
			cashMoney -= amount;
			System.out.println("You've placed a bet of $" + amount);
			System.out.println("You have $" + cashMoney + " left.\n");
		}
	}

	private void nextMove()
	{
		System.out.println("\nEnter 1 to Hit, 2 to Stay, or 3 to Double Down!");
		System.out.println("Cards left in deck: " + deck.deckOfCards.size());
		
		int answer = scan.nextInt();
		switch(answer)
		{
			case 1: if(deck.deckOfCards.size() == 0)
					{
						System.out.println("All cards have been played. Must Stay.");
						nextMove();
					}
					pAddToHand(hit());
					System.out.print("Player's new hand: ");
					for(int i = 0; i < playersHand.size(); i++)
					{
						System.out.print(playersHand.get(i).toString() + ", ");
					}
					System.out.println("");
					System.out.println("\nplayer: " + pHandRank() + " dealer: Hidden");
					quickWin();
					nextMove();
					break;	
					
			case 2: stay(answer);
					break;
					
			case 3: doubleDown();
					break;
		   default: System.out.println("Enter a valid choice.");
					nextMove();
		}
	}
	
	private void quickWin() 
	{
		//CHECKS FOR AN INITIAL INSTANT WIN, SEPERATE FROM COMPARE METHOD
		if(pHandRank() == 21 && dHandRank() == 21)
		{
			System.out.println("You have Blackjack! You WIN!");
			wins++;
			System.out.println("You've won $" + amount + " this round with a new total of $" + (cashMoney += amount));
			score();
			playAgain();
		}
		if(pHandRank() == 21)
		{
			System.out.println("You have Blackjack! You WIN!");
			wins++;
			System.out.println("You've won $" + amount + " this round with a new total of $" + (cashMoney += amount));
			score();
			playAgain();
		}
		if(pHandRank() > 21)
		{
			System.out.println("You have bust. You LOSE!");
			losses++;
			System.out.println("You've lost $" + amount + " this round with a remaining total of $" + cashMoney);
			score();
			playAgain();
		}
		
	}
	public Card hit() {
		Card hit = deck.dealCard();
		deck.deckOfCards.remove(deck.deckOfCards.size()-1);
		return hit;
	}
	public void stay(int answer) {
		System.out.println("Your input was: " + answer + "\nYou selected Stay\n");
		System.out.println("Dealer's hand shows: " + dealersCard1.toString() + ", " + dealersCard2.toString());
		System.out.println("player: " + pHandRank() + " dealer: " + dHandRank());
		System.out.println("");

		while(dHandRank() < 17)
		{
			if(deck.deckOfCards.size() == 0)
			{
				System.out.println("Out of cards. Dealer Stays.");
				for(int i = 0; i < playersHand.size(); i++)
				{
					System.out.print(playersHand.get(i).toString() + ", ");
				}
				
				for(int i = 0; i < dealersHand.size(); i++)
				{
					System.out.print(dealersHand.get(i).toString() + ", ");
				}
				System.out.println("\nplayer: " + pHandRank() + " dealer: " + dHandRank());
				compare();
				playAgain();
			}
			else
			{
			System.out.println("Dealer Hits!");
			dAddToHand(hit());
			System.out.print("Dealer's new hand: ");
			for(int i = 0; i < dealersHand.size(); i++)
			{
				System.out.print(dealersHand.get(i).toString() + ", ");
			}
			System.out.println("\nplayer: " + pHandRank() + " dealer: " + dHandRank() + "\n");
			}
		}

		if(dHandRank() == pHandRank())
		{
			System.out.println("Tie!(round not counted in score)");
			cashMoney += amount;
			score();
			playAgain();	
		}
		
		if(dHandRank() == 21)
		{
			System.out.println("Dealer has Blackjack! You LOSE!");
			losses++;
			score();
			playAgain();
		}	
		
		if(dHandRank() > 21)
		{
			System.out.println("Dealer has bust. You WIN!");
			wins++;
			System.out.println("You've won $" + amount + " this round with a new total of $" + (cashMoney += amount));
			score();
			playAgain();
		}
		
		if(dHandRank() < 21)
		{
	 	compare();
		}
	}
	private void dAddToHand(Card hit) {
		dealersHand.add(hit);	
	}
	private void pAddToHand(Card hit) {
		playersHand.add(hit);
	}
	private void compare() 
	{		
		//RANK METHODS RETURN INTEGERS TO COMPARE WINNER AND SET SCORE ACCORDINGLY
		if(dHandRank() > pHandRank() && dHandRank() <= 21)
		{
			System.out.println("Dealer WINS.");
			losses++;
			System.out.println("You've lost $" + amount + " this round with a remaining total of $" + cashMoney);
			score();
			playAgain();
		}
		else if(dHandRank() < pHandRank() && pHandRank() <= 21)
		{
			System.out.println("YOU WIN!");
			wins++;
			System.out.println("You've won $" + amount + " this round with a new total of $" + (cashMoney += amount*2));
			score();
			playAgain();
		}
	}
	private void score() 
	{
		System.out.println("Score: Dealer: " + losses + " You: " + wins + "\n");
	}
	private void playAgain() 
	{
		//MINIMUM OF 4 CARDS NEEDED TO PLAY A ROUND!
		if(deck.deckOfCards.size() < 4)
		{
			System.out.println("Not enough cards to deal new round. Would you like"
							+ " to shuffle the deck and continue playing?(y/n)");
			scan.nextLine();
			String replay = scan.nextLine();
			if(replay.equalsIgnoreCase("y"))
			{
				score();
				clearHands();
				//RECALLS IN THE FILE TO RECREATE THE DECK OF CARDS
				deck = new Deck("/Users/MacbookPro/ASU/Java Projects/Blackjack/Blackjack/bj.txt");
				play();
			}
			else if(replay.equalsIgnoreCase("n"))
			{
				score();
				System.out.println("Final payout is: $" + cashMoney);
				System.out.println("Thanks for playing!");
				System.exit(0);
			}
			else
			{
				System.out.println("Please enter either 'y' or 'n'");
				playAgain();
			}
		}
		System.out.println("");
		dHand = 0;
		pHand = 0;
		clearHands();
		System.out.println("Would you like to play again? (y/n)");
		scan.nextLine();
		playAgain = scan.nextLine();
		if(playAgain.equalsIgnoreCase("y"))
		{
			rounds();
		}
		else if(playAgain.equalsIgnoreCase("n"))
		{
			System.out.println("Final payout is: $" + cashMoney);
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
		else
		{
			System.out.println("Please enter either 'y' or 'n'");
			scan.next();
			playAgain();
		}
		
	}
	private void clearHands() {
		dealersHand.clear();
		playersHand.clear();
		pHand = 0;
		dHand = 0;
	}
	public void doubleDown()
	{
		//PROTECTS FROM HITTING WITHOUT A CARD TO HIT!
		if(deck.deckOfCards.size() < 1)
		{
			System.out.println("No more cards in deck. Must Stay.");
			nextMove();
		}
		else
		{
			//HIT A CARD TO PLAYERS HAND THEN COMPARE TO FIND WINNER
			pAddToHand(hit());
			System.out.println("Player takes another card.");
			System.out.print("Player's new hand: ");
			for(int i = 0; i < playersHand.size(); i++)
			{
				System.out.print(playersHand.get(i).toString() + ", ");
			}
			System.out.println("\nplayer: " + pHandRank() + " dealer: " + dHandRank());
			quickWin();
			compare();
		}
	}
	public int dHandRank()
	{
		dHand = 0;
		int count = 0;
		for(int i = 0; i < dealersHand.size(); i++)
		{
			if(dealersHand.get(i).getRank() == 1)
			{
				//Active HIGH if dealer has an ACE
				count = 1;
			}
			if(count == 1)
			{
				for(i = 0; i < dealersHand.size(); i++)
				{
					dHand = dHand + dealersHand.get(i).getValue();
				}
				if((dHand + 10) < 22)
				{
					dHand = dHand + 10;
				}
			}
			else if(count == 0)
			{
				for(i = 0; i < dealersHand.size(); i++)
				{
					dHand = dHand + dealersHand.get(i).getValue();
				}
			}
		}
		return dHand;
	}

	public int pHandRank()
	{
		pHand = 0;
		int count = 0;
		for(int i = 0; i < playersHand.size(); i++)
		{
			//IF PLAYER HAS AN ACE IN HIS HAND
			if(playersHand.get(i).getRank() == 1)
			{
				count = 1;
				if(pHand + 11 <= 21)
				{
					pHand += 11;
				}else
				{
					pHand += 1;
				}
			}else
			{
				pHand += playersHand.get(i).getValue();
			}
			
			
		//	return pHand;
			
			//IF PLAYER HAS AN ACE IN HIS HAND
//			if(playersHand.get(i).getRank() == 1)
//			{
//				//Active HIGH if player has an ACE
//				count = 1;
//
//			}
//			if(count == 1)
//			{
//				for(i = 0; i < playersHand.size(); i++)
//				{
//					pHand = pHand + playersHand.get(i).getValue();
//					System.out.println(pHand);
//				}
//				if((pHand + 10) < 22)
//				{
//					pHand = pHand + 10;
//				}
//			}
//			else if(count == 0)
//			{
//				for(i = 0; i < playersHand.size(); i++)
//				{
//					pHand = pHand + playersHand.get(i).getValue();
//				}
//			}
		}
		if(pHand > 21 && count == 1)
		{
			pHand -= 10;
		}
		return pHand;
	}


	public static void main(String[] args)
	{
		Dealer d = new Dealer("/Users/MacbookPro/AndroidStudioProjects/Blackjacked/app/src/Deck Files/bj");
		d.play();
	}

}
