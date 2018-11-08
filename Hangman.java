public class Hangman1
{
	public void isWordGuessed(char[] secretWord, char letterGuessed)
	{
		//to check if the letter guessed is present in the word
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
	}
}

public class Hangman
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("welcome to the hangman game!");
		System.out.println("the word to be guessed is "+secretWord.length+" letters long" );
		int i=0, wrongGuess=0;
		System.out.println("max no.of wrong guesses is 8");
		System.out.println("ALL THE BEST!");
		char letterGuessed;
		char[] wordGuessed;
		for(i=0; i<secretWord.length; i++)
		{
			//to print all the vowels present in the word
			if(secretWord[i]=='a'||secretWord[i]=='e'||secretWord[i]=='i'||secretWord[i]=='o'||secretWord[i]=='u'||secretWord[i]=='A'||secretWord[i]=='E'||secretWord[i]=='I'||secretWord[i]=='O'||secretWord[i]=='U')
			{
				wordGuessed[i]=secretWord[i];
			}
			else
				wordGuessed[i]=" ";
			System.out.print("word: "+wordGuessed[i]);
		}
		while(WrongGuess<8)
		{
			System.out.println("enter the letter");
			letterGuessed=sc.newChar();
			if(isWordGuessed(secretWord, letterGuessed)==true)
			{
				for(i=0; i<secretWord.length; i++)
				{
					if(secretWord[i]==letterGuessed)
					{
						System.out.println()
					}
				}
			}
		}
	}
}