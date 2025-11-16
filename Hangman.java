//Authors : Suhani Rai, Joshua Kim
//Potpurri : Used do-while statement Line 133-156
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Hangman{
	//A constant list for all the letters in the alphabet lowercase that will not be changed.
	final private static String[] ALPHABET = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
											  "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	/* An ArrayList that is the blueprint for the word that the user has to guess
	gets modified as user guesses letters.*/
	private static ArrayList<String> revealedLetters;
	//An ArrayList with all the words that are possible for the user to guess.
	private static ArrayList<String> wordList;
	/*
		Plays the game of hangman by calling the methods below.
	*/
	public static void main(String[] args){
	    wordList = new ArrayList<String>();
	    revealedLetters = new ArrayList<String>();
	    /*If the user does not enter any txt file prints out to the terminal asking the
	  	user to enter a file.
	    Or else checks if the correct file is entered and if not asks user to enter 
	    the correct file, and runs a try-catch method for exceptions.*/
	    if(args.length == 0){
	    	System.out.println("Please input a file name.");
	    }else{
	    	try {
    			File myObj = new File(args[0]);
    			Scanner lineReader = new Scanner(myObj);
    			//Reads every line of the txt file while there are lines left and adds it to wordList
        		while (lineReader.hasNextLine()) {
        			wordList.add(lineReader.nextLine());
      			}

      			lineReader.close();
      			//Ensures if the txt file is empty, adds in five words to the list answer choices.
      			if(wordList.size() == 0){
        			wordList.add("headphone");
        			wordList.add("notebook");
        			wordList.add("apple");
        			wordList.add("icecream");
        			wordList.add("bluetooth");
        		}
      			playHangman(initializeRevealedLetters());
      		//Catches the FileNotFoundExceptions that could be thrown when inputting the file.
    		} catch (FileNotFoundException e) {
      			System.out.println("The file name was not found, try inputting the correct file name.");
      		}
	    }
	}	
	/* Pre: wordList is initialized with words. 
		Post: Sets the variable revealedLetters to have the correct amount of '_' as there are
			  letters in the selected word from the list wordList.
			  Returns the word chosen from the list wordList.
	*/
	public static String initializeRevealedLetters()
	{
		int answerIndex = (int)(Math.random() * wordList.size());
		String word = wordList.get(answerIndex);
		//Initialize revealedLetters with "_" depending on the size of word from the list wordList.
		for(int i = 0; i < wordList.get(answerIndex).length(); i++){
			revealedLetters.add("_ ");
		}
		return word;
	}
	/* Pre: Takes in the word chosen from the list wordList, takes in the letter the user has inputted
			and revealedLetters had been intialized with the game board.
		Post: If the correct letter is found, sets the correct space(s) in the list revealedLetters to the
			 letter guessed.
			Returns if the correct letter was found or not in a boolean.
	*/
	public static boolean letterCheck(String word, String userLetter){
		boolean foundCorrectLetter = false;
		// checks every letter from the parameter word 
		for(int i = 0; i < word.length(); i++){
			//if the letter the user guesses is in the word, then sets it at the corresponding index in
			//revealedLetters and sets foundCorrectLetter to true.
			if(userLetter.equals(word.substring(i,i+1))){
				revealedLetters.set(i, userLetter);
				foundCorrectLetter = true;
			}
		}
		System.out.println(joinList(revealedLetters));
		return foundCorrectLetter;
	}
	/* Pre: Takes in an arrayList that has one or more elements in it.
		Post: Returns a sring that consists of the joined characters in the ArrayList.
	*/
	public static String joinList(ArrayList<String> list){
		String joinLetters = String.join(" ", list);
		return joinLetters;

	}
	/*
		Pre: Takes in the word chosen in the list wordList.
		Post: Returns the boolean value depending on whether all the correct letters have been guessed.
	*/
	public static boolean finalAnswerCheck(String word){
		String finalAnswer = String.join("", revealedLetters);
		return finalAnswer.equals(word);
	}
	/*
		Pre: Takes in the what is inputted by the user.
		Post: Checks if what the user inputted is a valid letter and is in array ALPHABET 
				and makes sure there is only one letter inputted.
	*/
	public static boolean checkIfString(String userLetter){
		//If there is only one character inputted, checks if it is in array ALPHABET.
		if(userLetter.length() == 1){
			//Goes through array ALPHABET 
			for(int i = 0; i < ALPHABET.length; i++){
				//If the letter is found in array ALPHABET returns true.
				if(ALPHABET[i].equals(userLetter)){
					return true;
				}
			}
		}
		return false;
	}
	/*  Pre: Takes in the word chosen by wordList.
		Post: Modifies revealedLetters based on the correct guesses by the user and prints if they were
		      sucessful or not.
	*/
	public static void playHangman(String word){
		Scanner input = new Scanner(System.in);
		ArrayList<String> incorrectLetters = new ArrayList<String>();
		int attempts = 0;
		System.out.println(joinList(revealedLetters));
		//This loop runs as long as the user doesn't guess 9 incorrect letters.
		do {	
			System.out.println("Please enter a SINGLE LETTER: ");
			String userLetter = input.nextLine().toLowerCase();
			//This calls the method checkIfString for userLetters and sees if it is true.
			if(checkIfString(userLetter) == true){
				// This calls the method letterCheck and if it is false (user did not guess correct letter),
				//increments attempts and adds the letter guessed to the list incorrectLetters.
				if(letterCheck(word, userLetter) == false){		
					attempts++;
					incorrectLetters.add(userLetter);
				}
				System.out.println("Your incorrect guesses: " + joinList(incorrectLetters));	
				//Calls method finalAnswerCheck and if true, tell the user they have correctly guessed
				//the letter and ends the game as well as prints the word.
				if(finalAnswerCheck(word) == true){
					System.out.println("You did it! The word was: " + word);
					break;
				}
			}else{
				System.out.println("That's not a SINGLE LETTER! Please try again.");
				System.out.println(joinList(revealedLetters));
				System.out.println("Your incorrect guesses: " + joinList(incorrectLetters));
			}
		} while(attempts < 9);
		//Checks to see if the user has used up all of their attempts and that they have
		//not guessed the word to let them know the user lost and prints the word.
		if(attempts == 9 && finalAnswerCheck(word) == false){
			System.out.println("You lost! The word was: " + word);
		}
	}
}