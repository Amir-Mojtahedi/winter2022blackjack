import java.util.Scanner;
public class HelperBlackJack{
	private CardPile gameDeck;
	private CardPile playerHand;
	private CardPile dealerHand;
	private int numFiveHundred;
	private int numHundred;
	private int numFifty;
	private int numTen;
	private int numFive;
	private Money bet;
	private Scanner userInput;
	
	public HelperBlackJack(){
		this.bet=new Money();
		this.userInput=new Scanner(System.in);
	}
	
	public double setBet(){
		System.out.println("How many 500$ chips you want to bet?: ");
		setFivehundred(userInput.nextInt());
		this.numFiveHundred=checker(this.numFiveHundred);
		
		System.out.println("How many 100$ chips you want to bet?: ");
		setHundred(userInput.nextInt());
		this.numHundred=checker(this.numHundred);
		
		System.out.println("How many 50$ chips you want to bet?: ");
		setFifty(userInput.nextInt());
		this.numFifty=checker(this.numFifty);
		
		System.out.println("How many 10$ chips you want to bet?: ");
		setTen(userInput.nextInt());
		this.numTen=checker(this.numTen);
		
		System.out.println("How many 5$ chips you want to bet?: ");
		setFive(userInput.nextInt());
		this.numFive=checker(this.numFive);
		
		this.bet.setBet(this.numFiveHundred,this.numHundred,this.numFifty,this.numTen,this.numFive);
		amountChecker(this.bet);
	
		if(this.bet.getBet()>this.bet.getMoney() || this.bet.getBet()<=0){
			throw new IllegalArgumentException("The bet is greater than the current balance or it is less than or equal to 0 and it was accepted.");
		}
		return this.bet.getBet();
	}
	//This method makes sure that the player is not entering negative chips.
	private int checker(int chips){
		while(chips<0){
			System.out.println("You cannot have a negative number of chips!");
			System.out.println("Re-enter your value: ");
			chips=userInput.nextInt();
		}
		return chips;
	}
	//amountChecker() method makes sure if the bet is neither 0 nor greater than the current balance.
	private void amountChecker(Money playerBet){
		while(playerBet.getBet()>playerBet.getMoney() || playerBet.getBet()==0){
			System.out.println("Your bet cannot be equal to 0 or be greater than your current balance which is: "+playerBet.getMoney()+"$\n");
			System.out.println("How many 500$ chips you want to bet?: ");
			setFivehundred(userInput.nextInt());
			this.numFiveHundred=checker(this.numFiveHundred);
			
			System.out.println("How many 100$ chips you want to bet?: ");
			setHundred(userInput.nextInt());
			this.numHundred=checker(this.numHundred);
			
			System.out.println("How many 50$ chips you want to bet?: ");
			setFifty(userInput.nextInt());
			this.numFifty=checker(this.numFifty);
			
			System.out.println("How many 10$ chips you want to bet?: ");
			setTen(userInput.nextInt());
			this.numTen=checker(this.numTen);
			
			System.out.println("How many 5$ chips you want to bet?: ");
			setFive(userInput.nextInt());
			this.numFive=checker(this.numFive);
			playerBet.setBet(this.numFiveHundred,this.numHundred,this.numFifty,this.numTen,this.numFive);
			if(!(playerBet.getBet()>playerBet.getMoney() || playerBet.getBet()==0)){
				break;
			}
			continue;
		}
		
	}
	
	public void setFivehundred(int fiveh){
		this.numFiveHundred=fiveh;
	}
	public void setHundred(int hun){
		this.numHundred=hun;
	}
	public void setFifty(int fif){
		this.numFifty=fif;
	}
	public void setTen(int ten){
		this.numTen=ten;
	}
	public void setFive(int five){
		this.numFive=five;
	}
	//This method re-start the game.
	public void newGame(){
		this.gameDeck=new CardPile();
		this.playerHand=new CardPile();
		this.dealerHand=new CardPile();
		this.gameDeck.addCardToDeck();
		this.gameDeck.shuffle();
		this.dealerHand.addCard(this.gameDeck.getRandomCard());
		this.gameDeck.removeFromDeck();
		this.playerHand.addCard(this.gameDeck.getRandomCard());
		this.gameDeck.removeFromDeck();
		this.dealerHand.addCard(this.gameDeck.getRandomCard());
		this.gameDeck.removeFromDeck();
		this.playerHand.addCard(gameDeck.getRandomCard());
		this.gameDeck.removeFromDeck();
		this.playerHand.calculatePoints();
		this.dealerHand.calculatePoints();
	}
	public CardPile getPlayerHand(){
		return this.playerHand;
	}
	public CardPile getDealerHand(){
		return this.dealerHand;
	}
	public CardPile getGameDeck(){
		return this.gameDeck;
	}
	public Money getBet(){
		return this.bet;
	}
	public boolean doubleDown(String ddown){
		if(ddown.equals("YES")){
			return true;
		}
		else{
			return false;
		}
	}
}
/*This class only serves to reduce the amount of code I should write in the Application class which in my case is BlackJack.
Some codes which are used in a repetitive manner are defined here as methods so I can avoid copy pasting. It also serves as a place where the user inputs are checked. */
