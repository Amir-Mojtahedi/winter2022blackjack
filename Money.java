import java.util.Scanner;
public class Money{
	private final int INITIAL_VALUE=800;
	private final double WIN_RATIO=0.5;
	private double bet;
	private double money;
	
	//I decided to make this game work with an initial money of 800$. The reason I did not take the intial money as an input from the user is to make the game more challenging.
	public Money(){
		this.money=INITIAL_VALUE;
	}
	
	//This method sets the bet. I have five types of chips which are the inputs of the method.
	public double setBet(int numFiveHundred,int numHundred,int numFifty,int numTen,int numFive){
		this.bet=numFiveHundred*500+numHundred*100+numFifty*50+numTen*10+numFive*5;
		System.out.println("Your bet is: "+this.bet+"$");
		return this.bet;
	}
	
	//In case of victory, the player's money will increase.
	public void win(){
		this.money+=this.bet*WIN_RATIO;
	}
	
	//In case of a victory which comes after a double down, the player's money will increase
	public void ddWin(){
		this.money+=this.bet;
	}
	
	//If the player surrenders, this method will decrease his money based on rules of surrending.
	public void surrender(){
		this.money=this.money-(this.bet/2);
	}
	
	//If the players loses, this method decreases his money.
	public void lose(){
		this.money-=this.bet;
	}
	//If the players loses in a double down situation, this method decreases his money.
	public void ddLose(){
		this.money-=this.bet*2;
	}
	//This method gets the bet of the player.
	public double getBet(){
		return this.bet;
	}
	//This method get the current balance of the player.
	public double getMoney(){
		return this.money;
	}
	//The toSting method checks if the player is winning or losing ater he decides to exit the game.
	public String toString(){
		if (this.money>=INITIAL_VALUE){
			return "Your current balance is: "+this.money+"$\nYou are up for: "+(this.money-INITIAL_VALUE)+"$";
		}
		else{
			return "Your current balance is: "+this.money+"$\nYou are down for: "+(INITIAL_VALUE-this.money)+"$";
		}
	}
}

/*Money class deals with all the betting things. Everything related to betting and its outcome if you win, lose or draw is defined here.
toString() will display the current balance of the player and sees if he is down or up when the player decides to leave the game. I chose to set the initial value of current 
balance to 800$.*/