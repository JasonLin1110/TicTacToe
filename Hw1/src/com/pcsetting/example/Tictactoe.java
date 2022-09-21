package com.pcsetting.example;
import java.util.Scanner;

public class Tictactoe {
	public enum cell{
		X, O, EMPTY;
	}
	private cell[][] table;
	private Scanner scanner = new Scanner(System.in);
	public Tictactoe(){
		this.table= new cell[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				table[i][j]=cell.EMPTY;
			}
		}
	}
	public void play() {
		int win=0,count=0;
		while(win==0) {
			win = playerXchoose();
			count++;
			print();
			if(win==1) {
				System.out.println("Player X WIN");
				scanner.close();
				return;
			}
			if(count==9) break;
			win=playerOchoose();
			count++;
			print();
			if(win==1) System.out.println("Player O WIN");
		}
		scanner.close();
		if(count==9 && win==0) {
			System.out.println("This Game Is Draw");
		}
		return;
	}
	private int playerXchoose() {
		int row,column;
		System.out.println("Player X choose seat:");
		System.out.print("Please enter row   : ");
		row=scanner.nextInt();
		System.out.print("Please enter column: ");
		column=scanner.nextInt();
		while(row<1 || row>3 || column<1 || column>3 || table[row-1][column-1]!=cell.EMPTY) {
			System.out.println("Seat does not exist or is not EMPTY, choose another one");
			System.out.print("Please enter row   : ");
			row=scanner.nextInt();
			System.out.print("Please enter column: ");
			column=scanner.nextInt();
		}
		table[row-1][column-1]=cell.X;
		if(X_win()) return 1;
		return 0;
	}
	private int playerOchoose() {
		int row,column;
		System.out.println("Player O choose seat:");
		System.out.print("Please enter row   : ");
		row=scanner.nextInt();
		System.out.print("Please enter column: ");
		column=scanner.nextInt();
		while(row<1 || row>3 || column<1 || column>3 || table[row-1][column-1]!=cell.EMPTY) {
			System.out.println("Seat does not exist or is not EMPTY, choose another one");
			System.out.print("Please enter row   : ");
			row=scanner.nextInt();
			System.out.print("Please enter column: ");
			column=scanner.nextInt();
		}
		table[row-1][column-1]=cell.O;
		if(O_win()) return 1;
		return 0;
	}
	private boolean X_win() {
		for(int i=0;i<3;i++) {
			if(table[i][0]==cell.X && table[i][1]==cell.X && table[i][2]==cell.X) return true;
		}
		for(int i=0;i<3;i++) {
			if(table[0][i]==cell.X && table[1][i]==cell.X && table[2][i]==cell.X) return true;
		}
		if(table[0][0]==cell.X && table[1][1]==cell.X && table[2][2]==cell.X)return true;
		if(table[2][0]==cell.X && table[1][1]==cell.X && table[0][2]==cell.X)return true;
		return false;
	}
	private boolean O_win() {
		for(int i=0;i<3;i++) {
			if(table[i][0]==cell.O && table[i][1]==cell.O && table[i][2]==cell.O) return true;
		}
		for(int i=0;i<3;i++) {
			if(table[0][i]==cell.O && table[1][i]==cell.O && table[2][i]==cell.O) return true;
		}
		if(table[0][0]==cell.O && table[1][1]==cell.O && table[2][2]==cell.O)return true;
		if(table[2][0]==cell.O && table[1][1]==cell.O && table[0][2]==cell.O)return true;
		return false;
	}
	private void print() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(table[i][j]==cell.X) {
					System.out.print('X');
				}
				else if(table[i][j]==cell.O) {
					System.out.print('O');
				}
				else System.out.print(' ');
				if(j!=2) System.out.print('|');
				else System.out.println("");
			}
			if(i!=2) System.out.println("-----");
		}
		return;
	}
}
