package com.pcsetting.example;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		String line1, line2;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input HugeInteger a: ");
        line1=scanner.next();
        System.out.println("Please input HugeInteger b: ");
        line2=scanner.next();
        //scanner.close();
		HugeInteger a=new HugeInteger(line1);
		HugeInteger b=new HugeInteger(line2);
		if(a.isEqualTo(b)) {
			a.print();
			System.out.print(" Is Equal To ");
			b.println();
		}
		if(a.isNotEqualTo(b)) {
			a.print();
			System.out.print(" Is Not Equal To ");
			b.println();
		}
		if(a.isGreaterThan(b)) {
			a.print();
			System.out.print(" Is Greater Than ");
			b.println();
		}
		if(a.isLessThan(b)) {
			a.print();
			System.out.print(" Is Less Than ");
			b.println();
		}
		if(a.isGreaterThanOrEqualTo(b)) {
			a.print();
			System.out.print(" Is Greater Than Or Equal To ");
			b.println();
		}
		if(a.isLessThanOrEqualTo(b)) {
			a.print();
			System.out.print(" Is Less Than Or Equal To ");
			b.println();
		}
		if(a.isZero()) {
			a.print();
			System.out.println(" Is zero.");
		}
		System.out.println("a value is " + a.tostring() );
		System.out.println("b value is " + b.tostring() );
		b.subtract(a);
		System.out.println("b subtract a value is " + b.tostring() );
		System.out.println("a value is " + a.tostring() );
		System.out.println("b value is " + b.tostring() );
		Tictactoe ticTacToe = new Tictactoe();
		ticTacToe.play();
		scanner.close();
		return;
	}

}
