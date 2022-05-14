public enum VALUE{
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);
	private final int points;
	private VALUE(final int points){
		this.points=points;
	}
	public int getPoints(){
		return this.points;
	}
}

/*This file is used to store all the values that a card can have using enums. The point of each card is indicated in front of it in the parantheses. getPoints() returns the
the point that each value has.*/