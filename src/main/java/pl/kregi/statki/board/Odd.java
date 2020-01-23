package pl.kregi.statki.board;
import java.util.Scanner;
public class Odd {
    public static void main(String arg[]){
        int num;
        //Read a number
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number to check its Even or Odd");
        num = input.nextInt();
        // Conditional operator
        System.out.println((num%2)==0 ? "even number":"odd number");
        StringBuilder sb = new StringBuilder();
        sb.append("Java");
        sb.append(' ');
        sb.append("Rocks").append("!").append(0);
        String value = sb.toString();
        System.out.println(value);
    }
}
