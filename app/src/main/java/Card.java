
public class Card 
{
	private int rank;
	private int suit;
	private int value;
	private String suitStr, faceStr;
	
	
	public Card(int rank, int suit)
	{
		this.rank = rank;
		this.suit = suit;
	}	
	public int getRank()
	{
		return rank;
	}
	//Create SUITS
	private String getSuit() {
		if(suit == 1){
			suitStr = "Hearts";
		}
		if(suit == 2){
			suitStr = "Spades";
		}
		if(suit == 3){
			suitStr = "Diamonds";
		}
		if(suit == 4){
			suitStr = "Clubs";
		}
		return suitStr;
	}
	//Create RANKS
	public String getFaceValue()
	{
		if(rank == 1){
			faceStr = "Ace";
		}
		if(rank == 2){
			faceStr = "2";
		}
		if(rank == 3){
			faceStr = "3";
		}
		if(rank == 4){
			faceStr = "4";
		}
		if(rank == 5){
			faceStr = "5";
		}
		if(rank == 6){
			faceStr = "6";
		}
		if(rank == 7){
			faceStr = "7";
		}
		if(rank == 8){
			faceStr = "8";
		}
		if(rank == 9){
			faceStr = "9";
		}
		if(rank == 10){
			faceStr = "10";
		}
		if(rank == 11){
			faceStr = "Jack";
		}
		if(rank == 12){
			faceStr = "Queen";
		}
		if(rank == 13){
			faceStr = "King";
		}
		
		return faceStr;	
	}
	public String toString()
	{
		return(String.format("%s%s%s",
				   getFaceValue(), " of ", getSuit()));
	}
	
	public int getValue()
	{
		if(rank == 1){
			value = 1;
		}
		if(rank == 2){
			value = 2;
		}
		if(rank == 3){
			value = 3;
		}
		if(rank == 4){
			value = 4;
		}
		if(rank == 5){
			value = 5;
		}
		if(rank == 6){
			value = 6;
		}
		if(rank == 7){
			value = 7;
		}
		if(rank == 8){
			value = 8;
		}
		if(rank == 9){
			value = 9;
		}
		if(rank == 10){
			value = 10;
		}
		if(rank == 11){
			value = 10;
		}
		if(rank == 12){
			value = 10;
		}
		if(rank == 13){
			value = 10;
		}
		return value;
	}
	
}
