package es.controllers.Models;

import java.util.List;

public class Carton {
    private final int id;
    private final int[][] numbers;

    public Carton(int id, int[][] numbers) {
        this.id = id;
        this.numbers = numbers;
    }

    public int getId() {
        return id;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("id" + id + " numeros=\n");
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                sb.append(numbers[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
