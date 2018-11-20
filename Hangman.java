import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Hangman extends Score
{
	
	private char[] secretWord;
	private int wrongGuess;
	private char letterGuessed;
	private char[] wordGuessed;
	private char[] lettersGuessed;
	private int guessCount=0;
	private static Scanner sc;

	static{
		sc = new Scanner(System.in);
	}

	private Hangman(){
		secretWord = randomWord().toUpperCase().toCharArray();
		wordGuessed =  new char[secretWord.length];
		lettersGuessed = new char[30];
		Super(name, time);
	}
	
	public static void launch(HighScore scores)
	{
		Hangman obj = new Hangman();
		obj.prepGame();
		final int startTime = System.currentTimeMillis();
		obj.startGame();
		final int endTime = System.currentTimeMillis();
		if(obj.guessed){
			System.out.println("Enter name: ");
			scores.addScore(new HangmanScore(sc.next(), endTime - startTime, wrongGuess));
		}
			
	}

	private void prepGame() {
		
		for(int i=0; i<secretWord.length; i++)
		{
			//to print all the vowels present in the word
			if(secretWord[i]=='A'||secretWord[i]=='E'||secretWord[i]=='I'||secretWord[i]=='O'||secretWord[i]=='U'){
				wordGuessed[i]=secretWord[i];
				lettersGuessed[guessCount++]=secretWord[i];
			}
			else
				wordGuessed[i]='_';	
		}
	}

	private void startGame(){
		System.out.print(	" _                                             \n" +
					"| |                                            \n" +
					"| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" +
					"| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" +
					"| | | | (_| | | | | (_| | | | | | | (_| | | | |\n" +
					"|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" +
					"                    __/ |                      \n" +
					"                   |___/                       \n");
		System.out.println(	"Welcome to the Hangman Game!\n"
						+	"The word to be guessed is " + secretWord.length + " letters long\n"
						+	"ALL THE BEST!\n"
						+	"\n"
						+	"Word:");
		for(int i=0; i<secretWord.length; i++)
			System.out.print(wordGuessed[i]+" ");
		System.out.println("\n");
		wrongGuess=0;
		while(wrongGuess<8)
		{
			boolean flag;
			do{
			flag=false;
			System.out.print("Guess the letter: ");
			letterGuessed=sc.next().toUpperCase().charAt(0);
			for(int i=0; i<guessCount; i++){
				if(lettersGuessed[i]==letterGuessed){
					System.out.println("This letter has already been guessed, please try with another letter");
					flag=true;
					break;
				}
			}
			lettersGuessed[guessCount++] = letterGuessed;
			}while(flag==true);
			if(isLetterGuessed(secretWord, letterGuessed)) {
				for(int i = 0; i < secretWord.length; i++)
					if (secretWord[i] == letterGuessed)
						wordGuessed[i] = letterGuessed;
				for(int i = 0; i < secretWord.length; i++)
					System.out.print(wordGuessed[i] + " ");
				System.out.println("");
				diagram(wrongGuess, secretWord, secretWord.length);
				if(guessed(secretWord, wordGuessed, secretWord.length)==true)
				{
					System.out.println("hurray! you won");
					break;
				}
			}
			else
			{
				System.out.println("oops! this letter is not present in the word");
				wrongGuess++;
				diagram(wrongGuess, secretWord, secretWord.length);
			}
		}
	}


	public boolean isLetterGuessed(char[] secretWord, char letterGuessed)
	{
		//to check if the letter guessed is present in the word
		//secretWord is the word to be guessed.
		boolean flag=false;
		int i;
		for(i=0; i<secretWord.length; i++)
		{
			if(secretWord[i]==letterGuessed)
			{
				flag=true;
				break;
			}
		}
		return flag;
	}
	public void diagram(int wrongGuess, char[] secretWord, int count)
	{
		//to print the hangman diagram
		switch(wrongGuess)
		{
			case 0:  System.out.println( " _______  \n"+
                                        " |/      | \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        "_|___      \n");
					break;
			case 1: System.out.println( " _______   \n"+
                                        " |/      | \n"+
                                        " |      (  \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        "_|___      \n");
					break;
			case 2:System.out.println( " _______    \n"+
                                        " |/      | \n"+
                                        " |      (_)\n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        "_|___      \n");
					break;
			case 3: System.out.println( " _______   \n"+
                                        " |/      | \n"+
                                        " |      (_)\n"+
                                        " |      \\ \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        "_|___      \n");
					break;
			case 4: System.out.println( " _______   \n"+
                                        " |/      | \n"+
                                        " |      (_)\n"+
                                        " |      \\|\n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        "_|___      \n");
					break;
			case 5: System.out.println( " _______   \n"+
                                        " |/      | \n"+
                                        " |      (_)\n"+
                                        " |      \\|/\n"+
                                        " |         \n"+
                                        " |         \n"+
                                        " |         \n"+
                                        "_|___      \n");
					break;
			case 6:  System.out.println( " _______   \n"+
                                         " |/      | \n"+
                                         " |      (_)\n"+
                                         " |      \\|/\n"+
                                         " |       | \n"+
                                         " |         \n"+
                                         " |         \n"+
                                         "_|___      \n");
					break;
			case 7: System.out.println( " _______   \n"+
                     				    " |/      | \n"+
                                        " |      (_)\n"+
                                        " |      \\|/\n"+
                                        " |       | \n"+
                                        " |      /  \n"+
                                        " |         \n"+
                                        "_|___      \n");
					break;
			case 8:  System.out.println( " _______   \n"+
                                         " |/      | \n"+
                                         " |      (_)\n"+
                                         " |      \\|/\n"+
                                         " |       | \n"+
                                         " |      / \\\n"+
                                         " |         \n"+
                                         "_|___      \n");
					System.out.print("Sorry, you ran out of guesses. The answer is: ");
					for(int i=0; i<count; i++)
					{
						System.out.print(secretWord[i]);
					}
					break;
		}
	}
	public boolean guessed(char[] secretWord, char[] wordGuessed, int count)
	{
		boolean flag1=true;
		for(int i=0; i<count; i++)
		{
			if(secretWord[i]!=wordGuessed[i])
			{
				flag1=false;
				break;
			}
		}
		return flag1;
	}
	public String randomWord()
	{
		String[] array={"hangman", "avenue", "ivory", "oxygen", "rythmic", "zombie", "bamboozled", "arrangement", "independent", "discussion", "manufacturing", "exchange", "mathematics", "project", "jinx", "zweiback", "fluorescent", "psychiatrist", "questionnaire", "mischievous", "millennium", "documentation", "humming", "flamingo", "photograph"};
		int word=ThreadLocalRandom.current().nextInt(array.length);
		return array[word];
	}
}