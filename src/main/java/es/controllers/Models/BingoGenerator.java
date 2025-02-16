package es.controllers.Models;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BingoGenerator {

    private final List<Carton> cartones = new ArrayList<>();
    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    public BingoGenerator(String filePath) throws IOException {
        this.filePath = filePath;
        ZipSecureFile.setMinInflateRatio(0.001);

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            this.workbook = new XSSFWorkbook(fileInputStream);
            this.sheet = workbook.getSheetAt(0); // Usamos la primera hoja por defecto
        }
    }

    public void limpiarExcel() {
        int numRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < numRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) { // Iterar sobre todas las celdas de la fila
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        // Limpiar contenido
                        cell.setCellValue("");

                        // Limpiar formato de la celda
                        CellStyle defaultStyle = workbook.createCellStyle();
                        defaultStyle.setBorderBottom(BorderStyle.NONE);  // Eliminar bordes
                        defaultStyle.setBorderTop(BorderStyle.NONE);
                        defaultStyle.setBorderLeft(BorderStyle.NONE);
                        defaultStyle.setBorderRight(BorderStyle.NONE);
                        defaultStyle.setFillPattern(FillPatternType.NO_FILL); // Eliminar color de fondo
                        defaultStyle.setAlignment(HorizontalAlignment.CENTER); // Alineación predeterminada
                        defaultStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Alineación vertical predeterminada

                        // Aplicar el estilo limpio a la celda
                        cell.setCellStyle(defaultStyle);
                    }
                }
            }
        }
    }


    public void generarCartones(int numCartones) {
        // Crear estilo de celdas
        CellStyle cellStyle = crearEstiloCeldas();
        CellStyle cellStylePrimeraFila = crearEstiloPrimeraFila();

        // Generar los cartones
        int startRow = 1, endRow = 5, startCol = 0, endCol = 4;
        for (int i = 0; i < numCartones; i++) {
            int idRow = startRow - 1;
            escribirCartonId(sheet, i + 1, idRow, cellStylePrimeraFila);
            int[][] numbers = generarNumerosRandom(sheet, startRow, endRow, startCol, endCol, cellStyle);

            // Ordenar cada fila sin afectar la celda central
            for (int fila = 0; fila < numbers.length; fila++) {
                if (fila == 2) {
                    // Ordenar ignorando la celda central
                    Arrays.sort(numbers[fila], 0, 2);
                    Arrays.sort(numbers[fila], 3, 5);
                } else {
                    Arrays.sort(numbers[fila]); // Orden normal
                }
            }

            cartones.add(new Carton(i + 1, numbers));
            startRow += 6; // Ajustar posición para el siguiente cartón
            endRow += 6;
        }
    }


    //guardar los cartones en el excel
    public void guardarCartonesEnExcel() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            workbook.write(fileOutputStream);
        }
    }


    public List<Carton> getCartones() {
        return new ArrayList<>(cartones);
    }

    private CellStyle crearEstiloCeldas() {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 20);
        font.setBold(false);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    private CellStyle crearEstiloPrimeraFila() {

        CellStyle cellStyle = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 18); // Tamaño de la fuente
        font.setFontName("Cambria");
        // Tipo de fuente
        font.setItalic(true); // Negrita
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
        xssfCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(10, 147, 244), null));


        //aplicar color
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return cellStyle;
    }



    private int[][] generarNumerosRandom(Sheet sheet, int startRow, int endRow, int startCol, int endCol, CellStyle cellStyle) {
        int[][] numbers = new int[5][5];

        // Crear listas de números para cada columna con su rango específico
        List<Integer> col1 = new ArrayList<>(), col2 = new ArrayList<>(), col3 = new ArrayList<>();
        List<Integer> col4 = new ArrayList<>(), col5 = new ArrayList<>();

        // Números dentro del rango B (1-15)
        for (int i = 1; i <= 15; i++) col1.add(i);
        // Números dentro del rango I (16-30)
        for (int i = 16; i <= 30; i++) col2.add(i);
        // Números dentro del rango N (31-45)
        for (int i = 31; i <= 45; i++) col3.add(i);
        // Números dentro del rango G (46-60)
        for (int i = 46; i <= 60; i++) col4.add(i);
        // Números dentro del rango O (61-75)
        for (int i = 61; i <= 75; i++) col5.add(i);

        // Mezclar los números de cada columna
        Collections.shuffle(col1);
        Collections.shuffle(col2);
        Collections.shuffle(col3);
        Collections.shuffle(col4);
        Collections.shuffle(col5);

        // Asignar los números a la matriz
        for (int i = 0; i < 5; i++) {
            numbers[i][0] = col1.get(i); // Columna 1 (B)
            numbers[i][1] = col2.get(i); // Columna 2 (I)
            numbers[i][2] = col3.get(i); // Columna 3 (N)
            numbers[i][3] = col4.get(i); // Columna 4 (G)
            numbers[i][4] = col5.get(i); // Columna 5 (O)
        }

        // Asegurar que la celda central (N3) es 0
        numbers[2][2] = 0;

        // Escribir los números directamente en el Excel
        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }

            for (int j = startCol; j <= endCol; j++) {
                if (i == startRow + 2 && j == startCol + 2) continue; // Saltar celda central

                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }

                cell.setCellValue(numbers[i - startRow][j - startCol]); // Escribir número en Excel
                cell.setCellStyle(cellStyle);
            }
        }

        return numbers;
    }



    //Escribir ID en el excel
    private void escribirCartonId(Sheet sheet, int cartonId, int rowIndex, CellStyle cellStyle) {

        Row idRow = sheet.getRow(rowIndex);
        if (idRow == null) {
            idRow = sheet.createRow(rowIndex);
        }

        for (int col = 0; col < 5; col++) {
            Cell idCell = idRow.getCell(col);
            if (idCell == null) {
                idCell = idRow.createCell(col);
            }

            if (col == 0) {
                idCell.setCellValue("Nº " + cartonId); // Texto en la primera celda
            } else if (col == 3) {
                idCell.setCellValue("Bingo"); // Vaciar el contenido de las demás celdas
            } else if (col == 4) {
                idCell.setCellValue("Mandingo");
            }
            idCell.setCellStyle(cellStyle); // Aplicar el estilo

        }
    }





    }
