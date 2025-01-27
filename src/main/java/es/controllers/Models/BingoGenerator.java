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

    public void limpiarExcel(){
        int numRows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < numRows; i++)  {
            Row row = sheet.getRow(i);
            if (row!= null) {
                for (int j = 0; j < row.getLastCellNum(); j++) { // Iterar sobre todas las celdas de la fila
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        cell.setCellValue("");
                        CellStyle whiteStyle = workbook.createCellStyle();
                        whiteStyle.cloneStyleFrom(cell.getCellStyle()); // Clonar el estilo existente
                        whiteStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex()); // Establecer color blanco
                        whiteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Rellenar con el color establecido

                        cell.setCellStyle(whiteStyle); // Aplicar el nuevo estilo a la celda// Establecer el valor de la celda como vacío
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
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numeros.add(i);
        }

        Collections.shuffle(numeros);

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    numbers[i][j] = 0; // Celda central vacía
                } else {
                    numbers[i][j] = numeros.get(index++);
                }
            }
        }

        index = 0;
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                if (i == startRow + 2 && j == startCol + 2) {
                    continue; // Saltar celda central
                }

                Row row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }

                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }

                int number = numbers[i - startRow][j - startCol];
                cell.setCellValue(number);
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
