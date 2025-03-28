package Test;

	import java.util.Scanner;

	public class OddorEvenNumbersJunit {

		public static void main(String[] args) {
			Scanner input = new Scanner(System.in);
			int number;
			
			// accept input from user
			System.out.println("Enter a number: ");
			number = input.nextInt();
			
			if(isEven(number)) {
				System.out.println(number + "is even");
			}
			
			else {
				System.out.println(number + "is odd");
			}
		}
		
		public static boolean isEven(int num) {
			return ((num % 2) == 0);
		
		}
	}


