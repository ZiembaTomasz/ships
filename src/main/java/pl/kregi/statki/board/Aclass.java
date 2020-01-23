package pl.kregi.statki.board;

import java.util.Scanner;

public class Aclass {
    public static void main(String[] args) {
        int a = 1; int b = 1; int temp;

        System.out.print("Którą liczbę Fibonacciego wyznaczyć? ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for(int i = 0; i < n; ++i) {
            temp = a;
            a = b;
            b += temp;
        }

        System.out.println(Integer.toString(n) + ". liczba Fibonacciego to " +
                Integer.toString(a));
    }
}




