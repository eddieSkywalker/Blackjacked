package electrifyingstudios.blackjacked.com;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Deck {
	private static final String DEBUG_BUILD    = "DEBUG_TAG Deck()";
	private static final String DEBUG_SCANNING = "DEBUG_TAG Scanning()";
	
	static List<Card> deckOfCards = new ArrayList<Card>();
	int i = 0;
	
//	public Deck(String path)
//	{
//		Log.d(DEBUG_BUILD, "Deck Class: Buidling Deck.");
//		File f = new File(path);
//
//			try{
//				Scanner scan = new Scanner(f);//Pulls data from file into buffer
//				while(scan.hasNext())
//				{
//											  //Disects buffer into individual variables for placement.
//					int rank = scan.nextInt();
//					int suit = scan.nextInt();
//					Log.d(DEBUG_SCANNING, "Reading in values. rank = " + rank + " and suit = " + suit);
//											  //Places variables into arrayList
//					Card genericCard = new Card(rank, suit);
//					deckOfCards.add(genericCard);
//				}
//			}
//			catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//	}

	public Deck(){
		Log.d(DEBUG_BUILD, "Deck Class: Buidling Deck.");

		Card h1 = new Card(1, 1);
		Card h2 = new Card(2, 1);
		Card h3 = new Card(3, 1);
		Card h4 = new Card(4, 1);
		Card h5 = new Card(5, 1);
		Card h6 = new Card(6, 1);
		Card h7 = new Card(7, 1);
		Card h8 = new Card(8, 1);
		Card h9 = new Card(9, 1);
		Card h10 = new Card(10, 1);
		Card hjack = new Card(11, 1);
		Card hqueen = new Card(12, 1);
		Card hking = new Card(13, 1);

		Card s1 = new Card(1, 2);
		Card s2 = new Card(2, 2);
		Card s3 = new Card(3, 2);
		Card s4 = new Card(4, 2);
		Card s5 = new Card(5, 2);
		Card s6 = new Card(6, 2);
		Card s7 = new Card(7, 2);
		Card s8 = new Card(8, 2);
		Card s9 = new Card(9, 2);
		Card s10 = new Card(10, 2);
		Card sjack = new Card(11, 2);
		Card squeen = new Card(12, 2);
		Card sking = new Card(13, 2);

		Card di1 = new Card(1, 3);
		Card di2 = new Card(2, 3);
		Card di3 = new Card(3, 3);
		Card di4 = new Card(4, 3);
		Card di5 = new Card(5, 3);
		Card di6 = new Card(6, 3);
		Card di7 = new Card(7, 3);
		Card di8 = new Card(8, 3);
		Card di9 = new Card(9, 3);
		Card di10 = new Card(10, 3);
		Card dijack = new Card(11, 3);
		Card diqueen = new Card(12, 3);
		Card diking = new Card(13, 3);

		Card cl1 = new Card(1, 4);
		Card cl2 = new Card(2, 4);
		Card cl3 = new Card(3, 4);
		Card cl4 = new Card(4, 4);
		Card cl5 = new Card(5, 4);
		Card cl6 = new Card(6, 4);
		Card cl7 = new Card(7, 4);
		Card cl8 = new Card(8, 4);
		Card cl9 = new Card(9, 4);
		Card cl10 = new Card(10, 4);
		Card cljack = new Card(11, 4);
		Card clqueen = new Card(12, 4);
		Card clking = new Card(13, 4);

		deckOfCards.add(h1);
		deckOfCards.add(h2);
		deckOfCards.add(h3);
		deckOfCards.add(h4);
		deckOfCards.add(h5);
		deckOfCards.add(h6);
		deckOfCards.add(h7);
		deckOfCards.add(h8);
		deckOfCards.add(h9);
		deckOfCards.add(h10);
		deckOfCards.add(hjack);
		deckOfCards.add(hqueen);
		deckOfCards.add(hking);

		deckOfCards.add(s1);
		deckOfCards.add(s2);
		deckOfCards.add(s3);
		deckOfCards.add(s4);
		deckOfCards.add(s5);
		deckOfCards.add(s6);
		deckOfCards.add(s7);
		deckOfCards.add(s8);
		deckOfCards.add(s9);
		deckOfCards.add(s10);
		deckOfCards.add(sjack);
		deckOfCards.add(squeen);
		deckOfCards.add(sking);

		deckOfCards.add(di1);
		deckOfCards.add(di2);
		deckOfCards.add(di3);
		deckOfCards.add(di4);
		deckOfCards.add(di5);
		deckOfCards.add(di6);
		deckOfCards.add(di7);
		deckOfCards.add(di8);
		deckOfCards.add(di9);
		deckOfCards.add(di10);
		deckOfCards.add(dijack);
		deckOfCards.add(diqueen);
		deckOfCards.add(diking);

		deckOfCards.add(cl1);
		deckOfCards.add(cl2);
		deckOfCards.add(cl3);
		deckOfCards.add(cl4);
		deckOfCards.add(cl5);
		deckOfCards.add(cl6);
		deckOfCards.add(cl7);
		deckOfCards.add(cl8);
		deckOfCards.add(cl9);
		deckOfCards.add(cl10);
		deckOfCards.add(cljack);
		deckOfCards.add(clqueen);
		deckOfCards.add(clking);


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
