import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.io.*;
/*
Name: Terrence Yang.
Date: May 10 2024. 
Teacher: Mr.Chu.
Description: ____________________
 */


public class tester {

	public static void main(String[] args) throws IOException {








		System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. "
				+"\n| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |"
				+"\n| |    _______   | || |     ____     | || |  _______     | || |  _______     | || |  ____  ____  | || |              | |"
				+"\n| |   /  ___  |  | || |   .'    `.   | || | |_   __ |    | || | |_   __ |    | || | |_  _||_  _| | || |      _       | |"
				+"\n| |  |  (__ |_|  | || |  |  .--.  |  | || |   | |__) |   | || |   | |__) |   | || |   | |  / /   | || |     | |      | |"
				+"\n| |   '.___`-.   | || |  | |    | |  | || |   |  __ /    | || |   |  __ /    | || |    | |/ /    | || |     | |      | |"
				+"\n| |  |`|____) |  | || |  |  `--'  |  | || |  _| |  | |_  | || |  _| |  | |_  | || |    _|  |_    | || |     | |      | |"
				+"\n| |  |_______.'  | || |   `.____.'   | || | |____| |___| | || | |____| |___| | || |   |______|   | || |     |_|      | |"
				+"\n| |              | || |              | || |              | || |              | || |              | || |     (_)      | |"
				+"\n| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |"
				+"\n '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");





		Scanner scan = new Scanner(System.in);
		File file = new File("SaveProgress.txt"); // Create file.
		PrintWriter newFile = new PrintWriter(new FileWriter("SaveProgress.txt", true)); //Append.
		PrintWriter print = new PrintWriter("SaveProgress.txt"); //Prints in file but flushes. 
		Scanner read = new Scanner(file); //Read info from file. 

		String cardDesc[] = {"Card1 (1/2): 1. Move a pawn one space forward. 2. Move a pawn out of spawn. ", "Card2: (1/2): 1. Move a pawn two spaces forward. 2. Move a pawn out of spawn. ", "Card3: Which pawn do you want to move 3 spaces forward?: ", "Card4: Which pawn do you want to move 4 spaces backwards?: ", "Card5: Which pawn do you want to move five spaces forward?: ", "Card7(1/2): 1. Move a pawn 7 spaces forward. 2. Split 7 spaces forward with 2 pawns. ", "Card8: Which pawn do you want to move eight spaces forward?: ", "Card10(1/2): 1. Move ten spaces forward. 2. Move a pawn backwards.", "Card11(1/2): 1. Move eleven spaces forward. 2. Switch your pawn with an opponents pawn. ", "Card12: Which pawn do you want to move twelve spaces forward?: ", "CardSorry!: Move a pawn from your start and place it on a space occupied by an opponents pawn and\nbump thier pawn back to their start"};
		String color[] = {"red", "green", "blue", "yellow"};
		String name;
		String choice; 
		int cardCount = 0;





		System.out.println("♟♟");

		String gameBoard[] = {"________________________________________________________________________________",
				"\n|_", "_", "_|", "|_", "_", "_|" , "|_", "B", "_|",  "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|", "|_", "_", "_|" , "|_", "_", "_|" , "|_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "Y", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",                                                                   
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","                                                                      |_", "_", "_|",
				"\n|_", "R", "_|","                                                                      |_", "_", "_|",
				"\n|_", "_", "_|","______________________________________________________________________|_", "_", "_|",
				"\n|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "_", "_|","|_", "G", "_|","|_", "_", "_|","|_", "_", "_|"}; 

		int cardNumbers[] = new int[45]; //six is 7, 7 is 8 remember this
		int playerColor;
		char temp;
		char rules; 
		char winners; 

		boolean gotColor = false;

		//Randomizing cards for loop.
		for(int i = 0; i < cardNumbers.length; i++) {
			double random =  Math.random()*12;
			if(random == 6 || random == 9){
				i--;
			}

			else{
				cardNumbers[i] = (int) random;

			}

		}


		System.out.println("Hello! what is your name?: ");
		name = scan.next();

		System.out.println("Hello Terrence! Welcome to the game Sorry! would you like to read the rules?(y/n): ");
		rules = scan.next().charAt(0);

		if(rules == 'y') {
			System.out.println("Rules: ___");

		}

		else if(rules == 'n') {
			System.out.println("Ok moving on..");
		}


		System.out.println("Do you want to look at the past winners? (y/n)");
		winners = scan.next().charAt(0);

		if(winners == 'y') {
			System.out.println("The are the winners:" );
			//            while(read.hasNext()) {
			//                
			//            }
		}

		else if(winners == 'n') {
			System.out.println("Ok on to the game!");
		}


		System.out.println("These are the colours: ");
		System.out.println("1. red\n2. green\n3. blue\n4. black");
		System.out.println("What color do you want to be?(1/2/3/4): ");


		while(!gotColor){
			temp = scan.next().charAt(0);
			playerColor = temp-48;
			System.out.println(playerColor);
			if(playerColor <= 4 && playerColor >= 1){
				gotColor = true;
			}
		}
		System.out.println("Ok lets start the game!!!");
		System.out.println("Enter the letter 'y' to get a card or the word 'exit' to exit the game (progress will be saved): ");
		choice = scan.next();
		if(choice.equals("y")){
			System.out.println(cardNumbers[cardCount]); //maybe i dont even need numbers maybe i can just get a random thing inside cardDesc array[];
			System.out.println(cardDesc[cardNumbers[cardCount]-1]);

			cardCount++;

		}








		for(int i = 0; i < gameBoard.length; i++){
			//			if(gameBoard[i] == "_"){
			//				gameBoard[i] ="R♟";
			//				
			//			}
		}
		for(int i = 0; i < gameBoard.length; i++){
			System.out.print(gameBoard[i]);
		}







		//The sorry card read it





		read.close();
		scan.close();

	}
	
	//create methods for cards
}