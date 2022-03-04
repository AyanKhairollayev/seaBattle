package com.company;

public class Game {
    private BattleGround player1Ground;
    private BattleGround player2Ground;

    public Game(BattleGround player1Ground, BattleGround player2Ground) {
        this.player1Ground = player1Ground;
        this.player2Ground = player2Ground;

        System.out.println("Игра началась!");
    }
}
