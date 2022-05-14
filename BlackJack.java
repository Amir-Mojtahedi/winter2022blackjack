import java.util.Scanner;
public class BlackJack{
	public static void main(String[] args){
		Scanner userInput=new Scanner(System.in);
		HelperBlackJack help=new HelperBlackJack();
		String situation="";
		help.newGame();
		System.out.println("WELCOME TO BLACKJACK\n\n\n");
		System.out.println("You have 800$ to spend\n\n\n");
		System.out.println("Please enter your bet: \n");
		double playerBet=help.setBet();
		
		while(!(situation.equals("EXIT"))){
			System.out.println("Your Hand is:\n"+help.getPlayerHand());
			System.out.println("\nDealer Hand is:\n"+help.getDealerHand().getFirstCard()+"\nX");
			if(help.getPlayerHand().blackJack()){	
				if(blackJack(situation,help)){
					situation="EXIT";
				}
			}
			else{
				System.out.println("\nDo you want to double down your bet? Type 'YES' to do so.\n");
				situation=userInput.next();
				if(situation.equals("YES") && !(help.getPlayerHand().blackJack())){
					if(doubleDown(situation,help)){
						continue;
					}
					else{
						break;
					}
				}
				else{
					System.out.println("\nType 'HIT' to draw one more card, type 'SURRENDER' to only lose half of your bet or type 'STAND' to play with your current hand\n");
					situation=userInput.next();
					while(!(situation.equals("HIT") || situation.equals("STAND") || situation.equals("SURRENDER"))){
						System.out.println("Your input is incorrect\n");
						System.out.println("Type 'HIT' to draw one more card, type 'SURRENDER' to only lose half of your bet or type 'STAND' to play with your current hand\n");
						situation=userInput.next();
					}
					if(situation.equals("SURRENDER")){
						System.out.println("You surrendered\n");
						help.getBet().surrender();
					}
					else if(situation.equals("HIT")){
						if(hit(situation,help)){
							situation="STAND";
						}
					}
					stand(situation,help);
				
					}	
					if(help.getBet().getMoney()==0){
						System.out.println("Sorry you lost all your money :( \n");
						break;
					}
					else{
						if(help.getBet().getMoney()==0){
							throw new IllegalArgumentException("The players's money is 0 and it was accepted.");
						}
						System.out.println("Do you want to play another round? Type 'EXIT' to stop the game\n");
						situation=userInput.next();
						if(situation.equals("EXIT")){
							System.out.println("Thanks for playing our game.\n");
							System.out.println(help.getBet());
						}
						else{
							help.newGame();
							help.setBet();
						}
					}
				}
			}
		}
		
	public static boolean blackJack(String situation,HelperBlackJack help){
		Scanner userInput=new Scanner(System.in);
		System.out.println("\nYou got a BLACKJACK!\n");
		while(help.getDealerHand().calculatePoints()<17){
			help.getDealerHand().addCard(help.getGameDeck().getRandomCard());
			help.getGameDeck().removeFromDeck();
		}
		System.out.println("Dealer Hand is:\n"+help.getDealerHand());
		if(help.getDealerHand().calculatePoints()==help.getPlayerHand().calculatePoints()){
			System.out.println("\nPUSH");
		}
		else{
			System.out.println("\nYou WON!");
			help.getBet().win();
		}
		System.out.println("\nDo you want to play another round? Type 'EXIT' to stop the game");
		situation=userInput.next();	
		if(situation.equals("EXIT")){
			System.out.println("Thanks for playing our game.\n");
			System.out.println(help.getBet());
			return true;
		}
		else{
			help.newGame();
			help.setBet();
			return false;
		}  
	}
	
	public static void stand(String situation,HelperBlackJack help){
		if(situation.equals("STAND")){
			System.out.println("\nYour Hand is:\n"+help.getPlayerHand()+"\n");
			while(help.getDealerHand().calculatePoints()<17 && help.getDealerHand().calculatePoints()<help.getPlayerHand().calculatePoints()){
				help.getDealerHand().addCard(help.getGameDeck().getRandomCard());
				help.getGameDeck().removeFromDeck();
			}
			if(help.getDealerHand().calculatePoints()==help.getPlayerHand().calculatePoints()){
				System.out.println("Dealer Hand is:\n"+help.getDealerHand());
				System.out.println("PUSH\n");
			}
			else if(help.getPlayerHand().calculatePoints()>help.getDealerHand().calculatePoints() || help.getDealerHand().calculatePoints()>21){
				System.out.println("Dealer Hand is:\n"+help.getDealerHand());
				System.out.println("You WON!");
				help.getBet().win();
			}
			else{
				System.out.println("Dealer Hand is:\n"+help.getDealerHand());
				System.out.println("You lost!");
				help.getBet().lose();
			}
		}
	}
	
	public static boolean doubleDown(String situation,HelperBlackJack help){
		Scanner userInput=new Scanner(System.in);
		if(help.doubleDown(situation) && !(help.getPlayerHand().blackJack())){
			help.getPlayerHand().addCard(help.getGameDeck().getRandomCard());
			help.getGameDeck().removeFromDeck();
			help.getPlayerHand().calculatePoints();
			System.out.println("Your Hand is:\n"+help.getPlayerHand());
			if(help.getPlayerHand().calculatePoints()>21){
						System.out.println("\nBUSTED!");
						help.getBet().ddLose();
			}
			else{
				while(help.getDealerHand().calculatePoints()<17 && help.getDealerHand().calculatePoints()<help.getPlayerHand().calculatePoints()){
					help.getDealerHand().addCard(help.getGameDeck().getRandomCard());
					help.getGameDeck().removeFromDeck();
				}
				System.out.println("Dealer Hand is:\n"+help.getDealerHand());
				if(help.getDealerHand().calculatePoints()==help.getPlayerHand().calculatePoints()){
					System.out.println("\nPUSH");
				}
				else if(help.getDealerHand().calculatePoints()>help.getPlayerHand().calculatePoints() && help.getDealerHand().calculatePoints()<=21){
					System.out.println("\nYou lost!");
					help.getBet().ddLose();
				}
				else{
					System.out.println("\nYou WON!");
					help.getBet().ddWin();
				}
			}
			System.out.println("\nDo you want to play another round? Type 'EXIT' to stop the game");
			situation=userInput.next();	
			if(situation.equals("EXIT")){
				System.out.println("Thanks for playing our game.\n");
				System.out.println(help.getBet());
				return false;
			}
			else{
				help.newGame();
				help.setBet();
				return true;
			}  
		}
		throw new IllegalArgumentException("To supress missing return statment error.");
	}
	
	public static boolean hit(String situation,HelperBlackJack help){
		Scanner userInput=new Scanner(System.in);
		while(situation.equals("HIT") && help.getPlayerHand().calculatePoints()<21){
			help.getPlayerHand().addCard(help.getGameDeck().getRandomCard());
			help.getGameDeck().removeFromDeck();
			if(help.getPlayerHand().calculatePoints()>21){
				System.out.println("Your Hand is:\n"+help.getPlayerHand());
				System.out.println("\nBUSTED!");
				help.getBet().lose();
				return false;
			}
			else{
				System.out.println("Your Hand is:\n"+help.getPlayerHand());
				if(help.getPlayerHand().blackJack()){
					System.out.println("\nYou got a BLACKJACK!");
					while(help.getDealerHand().calculatePoints()<17){
						help.getDealerHand().addCard(help.getGameDeck().getRandomCard());
						help.getGameDeck().removeFromDeck();
					}
					System.out.println("\nDealer Hand is:\n"+help.getDealerHand());
					if(help.getDealerHand().calculatePoints()==help.getPlayerHand().calculatePoints()){
						System.out.println("PUSH");
						return false;
					}
					else{
						System.out.println("You WON!\n");
						help.getBet().win();
						return false;
					}
				}
				else{
					System.out.println("\nDealer Hand is:\n"+help.getDealerHand().getFirstCard()+"\nX");
					System.out.println("Type 'HIT' to draw one more card or type 'STAND' to play with your current hand\n");
					situation=userInput.next();
					while(!(situation.equals("HIT") || situation.equals("STAND"))){
						System.out.println("Your input is incorrect\n");
						System.out.println("Type 'HIT' to draw one more card or type 'STAND' to play with your current hand\n");
						situation=userInput.next();
					}
					if(situation.equals("STAND")){
						return true;
					}
				}
			}
		}
		throw new IllegalArgumentException("To supress the missing return statment error.");
	}
	
}
/*This class is where all the separate components of the game come together to create the actual game. I mostly used my HelperBlackJack class and its object in order to avoind repetition.
My game ends when either the user decides to by typing EXIT or when his balance hits 0$. I also added the double down feature.*/

