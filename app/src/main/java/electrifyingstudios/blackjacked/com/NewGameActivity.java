package electrifyingstudios.blackjacked.com;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;

public class NewGameActivity extends AppCompatActivity
{
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

    // VARIABLES DUE TO GUI IMPLEMENTATION
//    ImageButton playerCardOneImage = (ImageButton) findViewById(R.id.hand1);
//    ImageButton playerCardTwoImage = (ImageButton) findViewById(R.id.hand2);
//    ImageButton dealerCardOneImage = (ImageButton) findViewById(R.id.enemyHand1);
//    ImageButton dealerCardTwoImage = (ImageButton) findViewById(R.id.enemyHand2);

//    ImageButton playerCardTwoImage, dealerCardOneImage, dealerCardTwoImage;
    int drawableID;

    private static final String DEBUG_STARTUP    = "DEBUG_TAG onCreate()";
    private static final String DEBUG_PLAY       = "DEBUG_TAG Play()";
    private static final String DEBUG_ROUNDS1    = "DEBUG_TAG Rounds()1";
    private static final String DEBUG_ROUNDS2    = "DEBUG_TAG Rounds()2";
    private static final String DEBUG_DEALS      = "DEBUG_TAG Deals()";
    private static final String DEBUG_QUICKWIN   = "DEBUG_TAG QuickWin()";
    private static final String DEBUG_NEXTMOVE   = "DEBUG_TAG NextMove()";
    private static final String DEBUG_PLAYAGAIN  = "DEBUG_TAG playAgain()";
    private static final String DEBUG_SCORE      = "DEBUG_TAG SCORE()";


    private static final String DEBUG_PLAYERWINS = "DEBUG_TAG PLAYERWINS";
    private static final String DEBUG_PLAYRLOSES = "DEBUG_TAG PLAYRLOSES";
    private static final String DEBUG_NOTENOUGH  = "DEBUG_TAG_NOTENOUGH";
    private static final String DEBUG_PLAYAGAINYN= "DEBUG_TAG_PLAYAGAIN?";


//    TextView yourTurn = (TextView) findViewById(R.id.yourTurn);
//    TextView stay = (TextView) findViewById(R.id.stay);
//    TextView hit = (TextView) findViewById(R.id.hit);
//    TextView doubleDown = (TextView) findViewById(R.id.doubleDown);
//    TextView AlertDialog = (TextView) findViewById(R.id.alertDialogTitle);


//    public NewGameActivity(){
//
//    }
//
//    public NewGameActivity(String path)
//    {
//        deck = new Deck(path);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        Log.d(DEBUG_STARTUP, "NewGameActivity onCreate() activated ");

//        NewGameActivity d = new NewGameActivity("/Users/MacbookPro/AndroidStudioProjects/Blackjacked/app/src/main/assets/bj.txt");
//		d.play();

//        deck = new Deck("/Users/MacbookPro/AndroidStudioProjects/Blackjacked/app/src/main/assets/bj.txt");
//        play();

        deck = new Deck();
        play();
    }

    public void play()
    {
//        TextView alertMessage = (TextView) findViewById(R.id.alertDialogTitle);
//        alertMessage.setText(R.string.welcomeMessage);
        FragmentManager fm = getFragmentManager();
        newInstance(R.id.alertDialogTitle).show(fm, "test");

//        String alertDialogMessage = AlertDialog.getText().toString();
//        FragmentManager fm = getFragmentManager();
//        newInstance(R.id.alertDialogTitle).show(fm, "test");        //THIS METHOD RUNS ONCE PER DECK
//        System.out.println("Welcome to Vegas Blackjack!");
//        System.out.println("Your score will be kept throughout your game.");
//        System.out.println("Your current cash amount: $" + cashMoney);
//        System.out.println("Goodluck!\n");

        deck.shuffle();
//        System.out.println("Cards left in deck: " + deck.deckOfCards.size());
        Log.d(DEBUG_PLAY, "Play() activated");

        //ROUNDS IS THE FUNCTIONING PLAY-THROUGH OF EACH 'GAME/ROUND/HAND' of BLACKJACK
        rounds();
    }

    public void rounds()
    {
        if(deck.deckOfCards.size() >= 4)
            Log.d(DEBUG_ROUNDS1, "Cards in deck: " + deck.deckOfCards.size());
        {
            //ROUND STARTS HERE.
            amount = 0;
            pHand = 0;
            dHand = 0;
//            System.out.println("How much would you like to bet?(5$ min, 500$ max) "
//                    + "Or press '0' to end game.");
//            amount = scan.nextInt();
//            bet(amount);
            deal();
            Log.d(DEBUG_ROUNDS2, "     Your hand: " + playersCard1.toString() + ", " + playersCard2.toString()
                                        + "\n" + "Dealer's hand: " + dealersCard1.toString() + ", Hidden"
                                        + "\n" + pHandRank() + " <-- player  dealer --> " + dHandRank());

//            System.out.println("Your hand: " + playersCard1.toString() + ", " + playersCard2.toString());
//            System.out.println("Dealer's hand: " + dealersCard1.toString() + ", Hidden");
//            System.out.println(pHandRank() + " <-- player  dealer --> " + dHandRank());
            quickWin();
            nextMove();
        }
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

    public void deal()
    {
        Log.d(DEBUG_DEALS, "Dealing");

        ImageButton playerCardOneImage = (ImageButton) findViewById(R.id.hand1);
        ImageButton playerCardTwoImage = (ImageButton) findViewById(R.id.hand2);
        ImageButton dealerCardOneImage = (ImageButton) findViewById(R.id.enemyHand1);
        ImageButton dealerCardTwoImage = (ImageButton) findViewById(R.id.enemyHand2);

        // DEALS CARDS TO DEALER AND PLAYER AND ADDS THEM TO ACCORDING ARRAY LISTS
        // NOW ALSO CHANGES GUI CARD IMAGE AS ACCORDINGLY
        this.playersCard1 = deck.dealCard();
        playersHand.add(playersCard1);
        deck.deckOfCards.remove(deck.deckOfCards.size() - 1);
        dealCardsGUI(playersCard1, playerCardOneImage);

        this.playersCard2 = deck.dealCard();
        playersHand.add(playersCard2);
        deck.deckOfCards.remove(deck.deckOfCards.size() - 1);
        dealCardsGUI(playersCard2, playerCardTwoImage);

        this.dealersCard1 = deck.dealCard();
        dealersHand.add(dealersCard1);
        deck.deckOfCards.remove(deck.deckOfCards.size() - 1);
        dealCardsGUI(dealersCard1, dealerCardOneImage);

        this.dealersCard2 = deck.dealCard();
        dealersHand.add(dealersCard2);
        deck.deckOfCards.remove(deck.deckOfCards.size() - 1);
        dealCardsGUI(dealersCard2, dealerCardTwoImage);
    }

    private void quickWin()
    {
        Log.d(DEBUG_QUICKWIN, "quickWin");

        //CHECKS FOR AN INITIAL INSTANT WIN; SEPARATE FROM COMPARE METHOD
        if(pHandRank() == 21 && dHandRank() == 21)
        {
            Log.d(DEBUG_PLAYERWINS, "You have Blackjack! You WIN! \n" +
                                    " You've won $" + amount + " this round with a new total of $" + (cashMoney += amount));
//            System.out.println("You have Blackjack! You WIN!");

//            AlertDialog.setText("Welcome to Electrifying Blackjack. Goodluck!");
//            newInstance(R.id.alertDialogTitle);

            wins++;
//            System.out.println("You've won $" + amount + " this round with a new total of $" + (cashMoney += amount));
            score();
            playAgain();
        }
        if(pHandRank() == 21)
        {
            Log.d(DEBUG_PLAYERWINS, "You have Blackjack! You WIN! \n" +
                    " You've won $" + amount + " this round with a new total of $" + (cashMoney += amount));
//            System.out.println("You have Blackjack! You WIN!");
            wins++;
//            System.out.println("You've won $" + amount + " this round with a new total of $" + (cashMoney += amount));
            score();
            playAgain();
        }
        if(pHandRank() > 21)
        {
            Log.d(DEBUG_PLAYRLOSES, "You have bust. You LOSE!" +
                                    " You've lost $" + amount + " this round with a remaining total of $" + cashMoney);
//            System.out.println("You have bust. You LOSE!");
            losses++;
//            System.out.println("You've lost $" + amount + " this round with a remaining total of $" + cashMoney);
            score();
            playAgain();
        }

    }

    private void nextMove()
    {
        Log.d(DEBUG_NEXTMOVE, "NextMove() method reached");

//        System.out.println("\nEnter 1 to Hit, 2 to Stay, or 3 to Double Down!");
//        System.out.println("Cards left in decks: " + deck.deckOfCards.size());

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

    public void dealCardsGUI(Card card, ImageButton cardImage)
    {
        switch(card.getSuit())
        {
            case "Hearts":
                if (card.getFaceValue() == "Ace"){
                    cardImage.setBackgroundResource(R.drawable.cards_a_heart);
                }
                if (card.getFaceValue().equals("2")){
                    cardImage.setBackgroundResource(R.drawable.cards_2_heart);
                }
                if (card.getFaceValue().equals("3")){
                    cardImage.setBackgroundResource(R.drawable.cards_3_heart);
                }
                if (card.getFaceValue().equals("4")){
                    cardImage.setBackgroundResource(R.drawable.cards_4_heart);
                }
                if (card.getFaceValue().equals("5")){
                    cardImage.setBackgroundResource(R.drawable.cards_5_heart);
                }
                if (card.getFaceValue().equals("6")){
                    cardImage.setBackgroundResource(R.drawable.backside_of_card_default);
                }
                if (card.getFaceValue().equals("7")){
                    cardImage.setBackgroundResource(R.drawable.cards_7_heart);
                }
                if (card.getFaceValue().equals("8")){
                    cardImage.setBackgroundResource(R.drawable.cards_8_heart);
                }
                if (card.getFaceValue().equals("9")){
                    cardImage.setBackgroundResource(R.drawable.cards_9_heart);
                }
                if (card.getFaceValue().equals("10")){
                    cardImage.setBackgroundResource(R.drawable.cards_10_heart);
                }
                if (card.getFaceValue().equals("Jack")){
                    cardImage.setBackgroundResource(R.drawable.cards_j_heart);
                }
                if (card.getFaceValue().equals("Queen")){
                    cardImage.setBackgroundResource(R.drawable.cards_q_heart);
                }
                if (card.getFaceValue().equals("King")){
                    cardImage.setBackgroundResource(R.drawable.backside_of_card_default);
                }
                break;

            case "Spades":
                if (card.getFaceValue() == "Ace"){
                    cardImage.setBackgroundResource(R.drawable.backside_of_card_default);
                }
                if (card.getFaceValue().equals("2")){
                    cardImage.setBackgroundResource(R.drawable.cards_2_spade);
                }
                if (card.getFaceValue().equals("3")){
                    cardImage.setBackgroundResource(R.drawable.cards_3_spade);
                }
                if (card.getFaceValue().equals("4")){
                    cardImage.setBackgroundResource(R.drawable.cards_4_spade);
                }
                if (card.getFaceValue().equals("5")){
                    cardImage.setBackgroundResource(R.drawable.cards_5_spade);
                }
                if (card.getFaceValue().equals("6")){
                    cardImage.setBackgroundResource(R.drawable.cards_6_spade);
                }
                if (card.getFaceValue().equals("7")){
                    cardImage.setBackgroundResource(R.drawable.cards_7_spade);
                }
                if (card.getFaceValue().equals("8")){
                    cardImage.setBackgroundResource(R.drawable.cards_8_spade);
                }
                if (card.getFaceValue().equals("9")){
                    cardImage.setBackgroundResource(R.drawable.cards_9_spade);
                }
                if (card.getFaceValue().equals("10")){
                    cardImage.setBackgroundResource(R.drawable.cards_10_spade);
                }
                if (card.getFaceValue().equals("Jack")){
                    cardImage.setBackgroundResource(R.drawable.cards_j_spade);
                }
                if (card.getFaceValue().equals("Queen")){
                    cardImage.setBackgroundResource(R.drawable.cards_q_spade);
                }
                if (card.getFaceValue().equals("King")){
                    cardImage.setBackgroundResource(R.drawable.cards_k_spade);
                }
                break;

            case "Clubs":
                if (card.getFaceValue() == "Ace"){
                    cardImage.setBackgroundResource(R.drawable.cards_a_club);
                }
                if (card.getFaceValue().equals("2")){
                    cardImage.setBackgroundResource(R.drawable.cards_2_club);
                }
                if (card.getFaceValue().equals("3")){
                    cardImage.setBackgroundResource(R.drawable.cards_3_club);
                }
                if (card.getFaceValue().equals("4")){
                    cardImage.setBackgroundResource(R.drawable.cards_4_club);
                }
                if (card.getFaceValue().equals("5")){
                    cardImage.setBackgroundResource(R.drawable.backside_of_card_default);
                }
                if (card.getFaceValue().equals("6")){
                    cardImage.setBackgroundResource(R.drawable.cards_6_club);
                }
                if (card.getFaceValue().equals("7")){
                    cardImage.setBackgroundResource(R.drawable.cards_7_club);
                }
                if (card.getFaceValue().equals("8")){
                    cardImage.setBackgroundResource(R.drawable.cards_8_club);
                }
                if (card.getFaceValue().equals("9")){
                    cardImage.setBackgroundResource(R.drawable.cards_9_club);
                }
                if (card.getFaceValue().equals("10")){
                    cardImage.setBackgroundResource(R.drawable.cards_10_club);
                }
                if (card.getFaceValue().equals("Jack")){
                    cardImage.setBackgroundResource(R.drawable.cards_j_club);
                }
                if (card.getFaceValue().equals("Queen")){
                    cardImage.setBackgroundResource(R.drawable.backside_of_card_default);
                }
                if (card.getFaceValue().equals("King")){
                    cardImage.setBackgroundResource(R.drawable.cards_k_club);
                }
                break;

            case "Diamond":
                if (card.getFaceValue() == "Ace"){
                    cardImage.setBackgroundResource(R.drawable.cards_a_diamond);
                }
                if (card.getFaceValue().equals("2")){
                    cardImage.setBackgroundResource(R.drawable.cards_2_diamond);
                }
                if (card.getFaceValue().equals("3")){
                    cardImage.setBackgroundResource(R.drawable.cards_3_diamond);
                }
                if (card.getFaceValue().equals("4")){
                    cardImage.setBackgroundResource(R.drawable.cards_4_diamond);
                }
                if (card.getFaceValue().equals("5")){
                    cardImage.setBackgroundResource(R.drawable.cards_5_diamond);
                }
                if (card.getFaceValue().equals("6")){
                    cardImage.setBackgroundResource(R.drawable.cards_6_diamond);
                }
                if (card.getFaceValue().equals("7")){
                    cardImage.setBackgroundResource(R.drawable.cards_7_diamond);
                }
                if (card.getFaceValue().equals("8")){
                    cardImage.setBackgroundResource(R.drawable.cards_8_diamond);
                }
                if (card.getFaceValue().equals("9")){
                    cardImage.setBackgroundResource(R.drawable.cards_9_diamond);
                }
                if (card.getFaceValue().equals("10")){
                    cardImage.setBackgroundResource(R.drawable.cards_10_diamond);
                }
                if (card.getFaceValue().equals("Jack")){
                    cardImage.setBackgroundResource(R.drawable.cards_j_diamond);
                }
                if (card.getFaceValue().equals("Queen")){
                    cardImage.setBackgroundResource(R.drawable.cards_q_diamond);
                }
                if (card.getFaceValue().equals("King")){
                    cardImage.setBackgroundResource(R.drawable.cards_k_diamond);
                }
                break;
        }
    }


    public Card hit() {
        Card hit = deck.dealCard();
        deck.deckOfCards.remove(deck.deckOfCards.size()-1);
        return hit;
    }
    public void stay(int answer) {
        System.out.println("Your input was: " + answer + "\nYou selected Stay\n");
        System.out.println("electrifyingstudios.blackjacked.com.Dealer's hand shows: " + dealersCard1.toString() + ", " + dealersCard2.toString());
        System.out.println("player: " + pHandRank() + " dealer: " + dHandRank());
        System.out.println("");

        while(dHandRank() < 17)
        {
            if(deck.deckOfCards.size() == 0)
            {
                System.out.println("Out of cards. electrifyingstudios.blackjacked.com.Dealer Stays.");
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
                System.out.println("electrifyingstudios.blackjacked.com.Dealer Hits!");
                dAddToHand(hit());
                System.out.print("electrifyingstudios.blackjacked.com.Dealer's new hand: ");
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
            System.out.println("electrifyingstudios.blackjacked.com.Dealer has Blackjack! You LOSE!");
            losses++;
            score();
            playAgain();
        }

        if(dHandRank() > 21)
        {
            System.out.println("electrifyingstudios.blackjacked.com.Dealer has bust. You WIN!");
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
            System.out.println("electrifyingstudios.blackjacked.com.Dealer WINS.");
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
//        System.out.println("Score: electrifyingstudios.blackjacked.com.Dealer: " + losses + " You: " + wins + "\n");
        Log.d(DEBUG_SCORE, "Score: electrifyingstudios.blackjacked.com.Dealer: " + losses + " You: " + wins + "\n");

    }
    private void playAgain()
    {
        Log.d(DEBUG_PLAYAGAIN, "playAgain() method reached");

        //MINIMUM OF 4 CARDS NEEDED TO PLAY A ROUND!
        if(deck.deckOfCards.size() < 4)
        {
            Log.d(DEBUG_NOTENOUGH, "Not enough cards to deal new round. Would you like"
                    + " to shuffle the deck and continue playing?(y/n)");

//            System.out.println("Not enough cards to deal new round. Would you like"
//                    + " to shuffle the deck and continue playing?(y/n)");
            scan.nextLine();
            String replay = scan.nextLine();
            if(replay.equalsIgnoreCase("y"))
            {
                score();
                clearHands();
                //RECALLS IN THE FILE TO RECREATE THE DECK OF CARDS
//                deck = new Deck("/Users/MacbookPro/ASU/Java Projects/Blackjack/Blackjack/bj.txt");
                deck = new Deck();
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

        Log.d(DEBUG_PLAYAGAINYN, "Would you like to play again? (y/n)");
//        System.out.println("Would you like to play again? (y/n)");

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

    public static MyAlertDialogFragment newInstance(int title)
    {
        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

}
