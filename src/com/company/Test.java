package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Player 1, please enter your name: ");
        String player1Name = scanner.nextLine();
        BattleGround battleGround1 = new BattleGround(player1Name);
        System.out.println("Hello, " + battleGround1.getName() + "!");
        battleGround1.arrangePlayerField();
        battleGround1.printGround();

        System.out.print("Player 2, please enter your name: ");
        String player2Name = scanner.nextLine();
        BattleGround battleGround2 = new BattleGround(player2Name);
        System.out.println("Hello, " + battleGround2.getName() + "!");
    }
}
