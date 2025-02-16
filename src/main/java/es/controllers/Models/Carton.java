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

    public boolean comprobarLinea(List<Integer> numerosMarcados) {
        for (int i = 0; i < numbers.length; i++) {
            boolean lineaCompleta = true;
            for (int j = 0; j < numbers[i].length; j++) {
                if (!numerosMarcados.contains(numbers[i][j])) {
                    lineaCompleta = false;
                    break;
                }
            }
            if (lineaCompleta) {
                return true;
            }
        }
        return false;
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

    public int comprobarLineaPosicion(List<Integer> numerosGuardados) {
        int[][] numbers = this.getNumbers();
        for (int i = 0; i < numbers.length; i++) {
            boolean lineaCompleta = true;
            for (int j = 0; j < numbers[i].length; j++) {
                if (!numerosGuardados.contains(numbers[i][j])) {
                    lineaCompleta = false;
                    break;
                }
            }
            if (lineaCompleta) {
                return i; // Devuelve la posición de la línea encontrada
            }
        }
        return -1; // No se encontró ninguna línea
    }

}