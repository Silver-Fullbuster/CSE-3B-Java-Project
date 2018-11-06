public class Hangman1
{
	public void isWordGuessed(String[] secretWord, char letterGuessed)
	{
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
		System.oyut.println("max no.of wrong guesses is 8");
		System.out.println("ALL THE BEST!");
		char letterGuessed;
		while(WrongGuess<8)
		{
			System.out.println("enter the letter");
			letterGuessed=sc.newChar();

		}
	}
}