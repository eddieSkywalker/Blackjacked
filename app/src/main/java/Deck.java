import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Deck {
	
	List<Card> deckOfCards = new ArrayList<Card>();
	int i = 0;
	
	public Deck(String path) 
	{
		File f = new File(path);
		
			try{
				Scanner scan = new Scanner(f);//Pulls data from file into buffer
				while(scan.hasNext()) 
				{
											  //Disects buffer into individual variables for placement.
					int rank = scan.nextInt();
					int suit = scan.nextInt(); 
											  //Places variables into arrayList
					Card genericCard = new Card(rank, suit);
					deckOfCards.add(genericCard);
				}
			} 
			catch (FileNotFoundException e) {	
				e.printStackTrace();
			}
	}
	public void shuffle()
	{
		Collections.shuffle(deckOfCards);
	}
	public Card dealCard()
	{
		return deckOfCards.get(deckOfCards.size()-1);
	}
	
}
