public class Card{
	private VALUE value;
	private SUIT suit;
	public Card(VALUE value, SUIT suit){
		this.value=value;
		this.suit=suit;
	}
	public VALUE getValue(){
		return this.value;
	}
	public String toString(){
		return ""+this.value+" of "+this.suit;
	}
}

/*The Card class is used to create a card(VALUE+SUIT). Its constructor is reponsible of doing so. getValue() returns the value that a card has. E.g. Two of Diamonds will have 2 as value.
This method is used in order to calculate the total points that a deck has.
toString() simply prints the card with its value and suit.*/