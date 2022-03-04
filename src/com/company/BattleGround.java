package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BattleGround {
    // 1 - корабль
    // 0 - ореол корабля
    // -1 - пустое пространство
    // -2 - атакованная точка

    private int[][] ground;
    private String name;
    private boolean playerFieldArranged;

    public BattleGround(String name) {
        this.name = name;
        this.ground = new int[10][10];

        for(int i = 0; i < ground.length; i++) {
            for(int j = 0; j < ground.length; j++) {
                ground[i][j] = -1;
            }
        }
    }

    public int[][] getGround() {
        return ground;
    }

    public boolean isPlayerFieldArranged() {
        return playerFieldArranged;
    }

    public String getName() {
        return name;
    }

    public void printGround() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(ground[i][j] == 1) {
                    System.out.print("\\uD83D\\uDEE5");
                } else if(ground[i][j] == 0) {
                    System.out.print("\\uD83D\\uDFE6");
                } else if(ground[i][j] == -1) {
                    System.out.print("⬜");
                } else {
                    System.out.print("\\uD83D\\uDFE5");
                }
            }
            System.out.println();
        }
    }

    public void arrangePlayerField() {
        if(isPlayerFieldArranged()) {
            System.out.println("Поле игрока " + name + " уже готово.");
        } else {
            System.out.println(name + ", давай расставлять корабли! Другой игрок, не смотри!");

            Scanner scanner = new Scanner(System.in);
            String userInput;

            System.out.println("Введи координаты четырехпалубного корабля (формат: x,y;x,y;x,y;x,y)");
            userInput = scanner.nextLine();

            while(!arrangeShip(userInput, 4)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты первого трехпалубного корабля(формат: x,y;x,y;x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 3)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты второго трехпалубного корабля(формат: x,y;x,y;x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 3)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты первого двухпалубного корабля(формат: x,y;x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 2)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты второго двухпалубного корабля(формат: x,y;x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 2)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты третьего двухпалубного корабля(формат: x,y;x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 2)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты первого однопалубного корабля(формат: x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 1)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты второго однопалубного корабля(формат: x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 1)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты третьего однопалубного корабля(формат: x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 1)) {
                userInput = scanner.nextLine();
            }

            System.out.println("Введи координаты четвертого однопалубного корабля(формат: x,y)");
            userInput = scanner.nextLine();

            while (!arrangeShip(userInput, 1)) {
                userInput = scanner.nextLine();
            }

            playerFieldArranged = true;
        }
    }

    private boolean arrangeShip(String userInput, int shipSize) {
        if(!Util.checkCoordinates(userInput, shipSize)) {
            return false;
        }

        int[][] shipCoordinates = Util.parseCoordinates(userInput, shipSize);

        if(!Util.checkShip(shipCoordinates, shipSize)) {
            System.out.println("Корабль не валиден. Корабль можно ставить только вертикально или горизонтально.");
            return false;
        }

        if(!arrangementPossible(shipCoordinates, shipSize)) {
            System.out.println("Корабль невозможно поставить на это место.");
            return false;
        }

        arrangeShip(shipCoordinates, shipSize);

        return true;
    }

    private void arrangeShip(int[][] coordinates, int shipSize) {
        for (int[] shipCoordinate : coordinates) {
            ground[shipCoordinate[0]][shipCoordinate[1]] = 1;
        }

        List<Integer[]> shipAureole = getShipAureole(coordinates, shipSize);

        for(Integer[] shipAureoleCoordinate : shipAureole) {
            ground[shipAureoleCoordinate[0]][shipAureoleCoordinate[1]] = 0;
        }
    }

    private boolean arrangementPossible(int[][] coordinates, int shipSize) {
        for(int i = 0; i < shipSize; i++) {
            for(int j = 0; j < shipSize; j++) {
                if(ground[i][j] == 1) {
                    return false;
                }
            }
        }

        List<Integer[]> shipAureole = getShipAureole(coordinates, shipSize);

        for(Integer[] shipAureoleCoordinate : shipAureole) {
            if(ground[shipAureoleCoordinate[0]][shipAureoleCoordinate[1]] == 1) {
                return false;
            }
        }

        return true;
    }

    private List<Integer[]> getShipAureole(int[][] coordinates, int shipSize) {
        List<Integer[]> shipAureole = new ArrayList<>();

        boolean vertical = Util.verticalOrHorizontal(coordinates);

        if(vertical) {
            if(coordinates[0][1] + 1 <= 9) {
                for(int[] shipCoordinate : coordinates) {
                    shipAureole.add(new Integer[]{shipCoordinate[0], shipCoordinate[1] + 1});
                }

                if (coordinates[0][0] - 1 >= 0) {
                    shipAureole.add(new Integer[]{coordinates[0][0] - 1, coordinates[0][0] - 1});
                }

                if(coordinates[shipSize - 1][0] + 1 <= 9) {
                    shipAureole.add(new Integer[]{coordinates[0][0] - 1, coordinates[0][1] + 1});
                }
            }

            if(coordinates[0][1] - 1 >= 0) {
                for(int[] shipCoordinate : coordinates) {
                    shipAureole.add(new Integer[]{shipCoordinate[0], shipCoordinate[1] - 1});
                }
            }

            if(coordinates[0][0] - 1 >= 0) {
                shipAureole.add(new Integer[]{coordinates[0][0] - 1, coordinates[0][1] - 1});
            }

            if(coordinates[shipSize - 1][0] + 1 <= 9) {
                shipAureole.add(new Integer[]{coordinates[shipSize - 1][0] + 1, coordinates[shipSize - 1][1] - 1});
            }
            // top coordinate
            if(coordinates[0][0] - 1 >= 0) {
                shipAureole.add(new Integer[]{coordinates[0][0] - 1, coordinates[0][1]});
            }
            // bottom coordinate
            if(coordinates[shipSize - 1][0] + 1 <= 9) {
                shipAureole.add(new Integer[]{coordinates[shipSize - 1][0] + 1, coordinates[shipSize - 1][1]});
            }
        } else {
            // upper board
            if(coordinates[0][0] - 1 >= 0) {
                for(int[] shipCoordinate : coordinates) {
                    shipAureole.add(new Integer[]{shipCoordinate[0] - 1, shipCoordinate[1]});
                }

                // right upper coordinate
                if(coordinates[shipSize - 1][1] + 1 <= 9) {
                    shipAureole.add(new Integer[]{coordinates[shipSize - 1][0] - 1, coordinates[shipSize - 1][1] + 1});
                }

                //left upper coordinate
                if(coordinates[0][1] - 1 >= 0) {
                    shipAureole.add(new Integer[]{coordinates[0][0] - 1, coordinates[0][1] - 1});
                }
            }

            // bottom board
            if(coordinates[0][0] + 1 <= 9) {
                for(int[] shipCoordinate : coordinates) {
                    shipAureole.add(new Integer[]{shipCoordinate[0] + 1, shipCoordinate[1]});
                }

                //left bottom coordinate
                if(coordinates[0][1] - 1 >= 0) {
                    shipAureole.add(new Integer[]{coordinates[0][0] + 1, coordinates[0][1] - 1});
                }

                //right bottom coordinate
                if(coordinates[shipSize - 1][1] + 1 <= 9) {
                    shipAureole.add(new Integer[]{coordinates[shipSize - 1][0] + 1, coordinates[shipSize - 1][1] + 1});
                }

                //left coordinate
                if(coordinates[0][1] - 1 >= 0) {
                    shipAureole.add(new Integer[]{coordinates[0][0], coordinates[0][1] - 1});
                }

                //right coordinate
                if(coordinates[shipSize - 1][1] + 1 <= 9) {
                    shipAureole.add(new Integer[]{coordinates[shipSize - 1][0], coordinates[shipSize - 1][1] + 1});
                }
            }
        }

        return shipAureole;
    }
}
