import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Hangman
{
	
	private char[] secretWord;
	private int wrongGuess;
	private char letterGuessed;
	private char[] wordGuessed;
	private char[] lettersGuessed;

	private void Hangman(){
		secretWord = randomWord().toUpperCase().toCharArray();
		wordGuessed =  new char[secretWord.length];
		lettersGuessed = new char[20];
	}
	
	public static void launch()
	{
		Hangman obj = new Hangman();
		obj.prepGame();
		obj.startGame();
	}

	private void prepGame() {
		
		for(int i=0; i<secretWord.length; i++)
		{
			//to print all the vowels present in the word
			if(secretWord[i]=='A'||secretWord[i]=='E'||secretWord[i]=='I'||secretWord[i]=='O'||secretWord[i]=='U')
				wordGuessed[i]=secretWord[i];
			else
				wordGuessed[i]='_';	
		}
	}

	private void startGame(){
		Scanner sc = new Scanner(System.in);
		System.out.println(	"Welcome to the Hangman Game!\n"
						+	"The word to be guessed is " + secretWord.length + " letters long\n"
						+	"ALL THE BEST!\n"
						+	"\n"
						+	"Word:");
		for(int i=0; i<secretWord.length; i++)
			System.out.print(wordGuessed[i]+" ");
		System.out.println("\n");
		int guessCount = 0;
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
			case 0: break;
			case 1: System.out.println(" ( ");
					break;
			case 2: System.out.println(" ( )");
					break;
			case 3: System.out.println(	" ( )\n" +
										"  |");
					break;
			case 4: System.out.println(	" ( )\n" +
										"  |\n"	+
										" / ");
					break;
			case 5: System.out.println(" ( )");
					System.out.println("  |");
					System.out.print(" / ");
					System.out.println("\\");
					break;
			case 6: System.out.println(" ( )");
					System.out.println("  |");
					System.out.print(" / ");
					System.out.println("\\");
					System.out.println("  |");
					break;
			case 7: System.out.println(" ( )");
					System.out.println("  |");
					System.out.print(" / ");
					System.out.println("\\");
					System.out.println("  |");
					System.out.println(" / ");
					break;
			case 8: System.out.println(" ( )");
					System.out.println("  |");
					System.out.print(" / ");
					System.out.println("\\");
					System.out.println("  |");
					System.out.print(" / ");
					System.out.println("\\");
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