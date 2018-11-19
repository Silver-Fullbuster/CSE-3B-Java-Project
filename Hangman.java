import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Hangman1
{
	public boolean isWordGuessed(char[] secretWord, char letterGuessed)
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

public class Hangman
{
	public static void launch()
	{
		Hangman1 obj=new Hangman1();
		//char[] secretWord=new char[20];
		//System.out.println("enter secret word: ");
		Scanner sc=new Scanner(System.in);
		int i, count=0;
		String word=obj.randomWord();
		char[] secretWord=word.toUpperCase().toCharArray();
		/*String inp;
		inp=sc.next();
		inp = inp.toUpperCase();
		for(i=0; i<inp.length(); i++)
		{
			secretWord[i]=inp.charAt(i);
			count++;
		}*/
		System.out.println("welcome to the hangman game!");
		System.out.println("the word to be guessed is "+secretWord.length+" letters long" );
		int wrongGuess=0;
		System.out.println("ALL THE BEST!");
		char letterGuessed;
		char[] wordGuessed=new char[20];
		System.out.println("word: ");
		char temp;
		for(i=0; i<secretWord.length; i++)
		{
			//to print all the vowels present in the word

			if(secretWord[i]=='A'||secretWord[i]=='E'||secretWord[i]=='I'||secretWord[i]=='O'||secretWord[i]=='U')
			{
				wordGuessed[i]=secretWord[i];
			}
			else
				wordGuessed[i]='_';
			System.out.print(wordGuessed[i]+" ");	
		}
		System.out.println("");
		while(wrongGuess<8)
		{
			System.out.println("guess the letter: ");
			letterGuessed=sc.next().toUpperCase().charAt(0);
			if(obj.isWordGuessed(secretWord, letterGuessed)==true)
			{
				for(i=0; i<secretWord.length; i++)
				{
					if(secretWord[i]==letterGuessed)
					{
						wordGuessed[i]=letterGuessed;
					}
				}
				for(i=0; i<secretWord.length; i++)
				{
					System.out.print(wordGuessed[i]+" ");
				}
				System.out.println("");
				obj.diagram(wrongGuess, secretWord, secretWord.length);
				if(obj.guessed(secretWord, wordGuessed, secretWord.length)==true)
				{
					System.out.println("hurray! you won");
					break;
				}
			}
			else
			{
				System.out.println("oops! this letter is not present in the word");
				wrongGuess++;
				obj.diagram(wrongGuess, secretWord, secretWord.length);
			}
		}
	}
}