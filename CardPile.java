import java.util.Random;
public class CardPile{
	private Card[] deck;
	private VALUE[] valueArray;
	private SUIT[] suitArray;
	private Random randomCard;
	private int cardIndex;
	private int positionCard;
	private int remover;
	private int sum;
	private final int randomShuffle;
	
	//The constructor initializes three arrays, one random object and one int.
	public CardPile(){
		this.deck=new Card[52];
		this.valueArray=VALUE.values();
		this.suitArray=SUIT.values();
		this.randomCard=new Random();
		this.randomShuffle=300;
	}
	
	//I had to have one array of suits and one array of values so I can make 52 cards and put them into the deck using nested for loops.
	public void addCardToDeck(){
		int deckIndex=0;
		for(int indexSUIT=0;indexSUIT<this.suitArray.length;indexSUIT++){
			for(int indexVALUE=0;indexVALUE<this.valueArray.length;indexVALUE++){
				this.deck[deckIndex]=new Card(this.valueArray[indexVALUE],this.suitArray[indexSUIT]);
				deckIndex++;
			}
		}
		this.positionCard=this.deck.length;
	}
	
	// I shuffle the deck by swapping randomly two cards in the deck and I do it for 300 times to make sure that the deck is well shuffled.
	public void shuffle(){
		Card temp;
		for(int z=1;z<=this.randomShuffle;z++){
			int index1=randomCard.nextInt(this.deck.length);
			int index2=randomCard.nextInt(this.deck.length);
			temp=this.deck[index1];
			this.deck[index1]=this.deck[index2];
			this.deck[index2]=temp;
		}
	}
	
	// This method takes a random card out of the deck. This random card will be given to either the dealer or the player and will be removed from the deck.
	public Card getRandomCard(){
		this.cardIndex=randomCard.nextInt(this.deck.length-remover);
		this.remover++;
		return this.deck[cardIndex];
	}
	
	//This method removes the card which was randomly chosen.
	public void removeFromDeck(){
		for(int i=this.cardIndex;i<this.deck.length-1;i++){
			this.deck[i]=this.deck[i+1];
		}
		this.deck[this.deck.length-1]=null;
	}
	//This method is used to add the randomly selected card to either the player or the dealer.
	public void addCard(Card card){
		this.deck[this.positionCard]=card;
		this.positionCard++;
	}
	
	// This method gets the first card of the deck. It is used to show the first card of the dealer when the game has begun.
	public Card getFirstCard(){
		return this.deck[0];
	}
	
	//This method calculates the total points of a hand. It also takes care of the fact that ACE can sometimes hold 11 points and sometimes 1 point.
	public int calculatePoints(){
		this.sum=0;
		boolean ace=false;
		boolean usedAce=false;
		for(int i=0;i<this.positionCard;i++){
			if(this.deck[i].getValue()==VALUE.ACE){
				ace=true;
			}
			this.sum=((this.deck[i].getValue()).getPoints())+this.sum;
		}
		if(ace && this.sum+10<=21){
				this.sum+=10;
				usedAce=true;
		}
		else if(usedAce && this.sum>21  ){
				this.sum-=10;
				usedAce=false;
		}
		return this.sum;
	}
	
	//This method checks if there is a blackjack.
	public boolean blackJack(){
		if(this.sum==21){
			return true;
		}
		else{
			return false;
		}
	}
	
	//The toString method prints the whole deck.
	public String toString(){
		String myDeck="";
		for(int i=0;i<this.positionCard;i++){
			myDeck=myDeck+this.deck[i]+"\n";
		}
		myDeck+="\nThe sum of points is: "+this.sum;
		return myDeck;
	}
	
}

/*CardPile class is my dynamicArray class. it has all the methods which are used to manipulate a deck of cards such addCard(), removeFromDeck(), shuffle() and so on.
This class is also used to calculte the points that a deck has. Since Ace can have both 11 and 1 as values dependeing on the contexte, I had to create boolean usedAce to see
if the Ace has already been used as a 11 points card or not.
The toString() of this class prints the whole deck.*/