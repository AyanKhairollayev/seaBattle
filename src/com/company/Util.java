package com.company;

public class Util {
    public static boolean checkCoordinate(String inputCoordinates) {

        String[] xy = inputCoordinates.split(",");
        if(xy.length != 2) {
            System.out.println("Координаты введены неверно (Правильный формат: x,y;x,y и т.д.)");
            return false;
        }

        try {
            Integer.parseInt(xy[0]);
            Integer.parseInt(xy[1]);
        } catch (NumberFormatException e) {
            System.out.println("Координаты должны содержать только целые числа.");
            return false;
        }

        if (Integer.parseInt(xy[0]) > 9 || Integer.parseInt(xy[1]) > 9
                || Integer.parseInt(xy[0]) < 0 || Integer.parseInt(xy[1]) < 0) {
            System.out.println("Введенные координаты должны быть в диапазоне от 0 до 9");
            return false;
        }

        return true;
    }

    public static boolean checkCoordinates(String userInput, int shipSize) {
        String[] splittedCoordinates= userInput.split(";");

        if(splittedCoordinates.length != shipSize) {
            System.out.println("Неверное количество координат. Необходимо " + shipSize);
            return false;
        }

        for(int i = 0; i < splittedCoordinates.length; i++) {
            if(!checkCoordinate(splittedCoordinates[i])) {
                return false;
            }
        }

        return true;
    }

    public static int[] parseCoordinate(String coordinate) {
        String[] splittedCoordinate = coordinate.split(",");

        return new int[] {Integer.parseInt(splittedCoordinate[0]),
                Integer.parseInt(splittedCoordinate[1])};
    }

    public static int[][] parseCoordinates(String inputCoordinates, int shipSize) {
        int[][] intCoordinates = new int[shipSize][2];

        String[] splittedCoordinates = inputCoordinates.split(";");

        for(int i = 0; i < intCoordinates.length; i++) {
            intCoordinates[i] = parseCoordinate(splittedCoordinates[i]);
        }

        return intCoordinates;
    }

    public static boolean checkShip(int[][] coordinates, int shipSize) {
        if(shipSize == 1) {
            return true;
        }

        int[] onlyX = new int[shipSize];
        int[] onlyY = new int[shipSize];

        for(int i = 0; i < shipSize; i++) {
            onlyX[i] = coordinates[i][0];
            onlyY[i] = coordinates[i][1];
        }

        if(!allValuesEqual(onlyX) && !allValuesEqual(onlyY)) {
            return false;
        }

        return allValuesAscending(onlyX) || allValuesAscending(onlyY);

    }

    public static boolean allValuesAscending(int[] coordinates) {
        for(int i = 0; i < coordinates.length - 1; i++) {
            if(coordinates[i + 1] - coordinates[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean allValuesEqual(int[] coordinates) {
        for(int i = 0; i < coordinates.length; i++) {
            if(coordinates[0] != coordinates[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean verticalOrHorizontal(int[][] coordinates) {
        int[] onlyX = new int[coordinates.length];

        for(int i = 0; i < onlyX.length; i++) {
            onlyX[i] = coordinates[i][0];
        }

        if(allValuesEqual(onlyX)) {
            return true;
        } else {
            return false;
        }
    }
}
