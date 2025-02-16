package es;

import com.spire.xls.*;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;


import javax.print.PrintService;
import java.awt.print.Book;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class MetodosImpresion {
    public static void convertXlsxToPdf(String inputPath, String outputPath) {
        // Crear una instancia de Workbook
        Workbook workbook = new Workbook();

        // Cargar el archivo XLSX
        workbook.loadFromFile(inputPath);


        // Guardar el archivo como PDF
        workbook.saveToFile(outputPath, FileFormat.PDF);

        // Liberar recursos
        workbook.dispose();
    }

    //abrir cuadro de dialogo para elegir impresora
    public static PrintService choosePrinter() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        if(printJob.printDialog()) {
            return printJob.getPrintService();
        }
        else {
            return null;
        }
    }

    //imprimir documento en la impresora seleccionada
    public static void imprimir(String pdfFilePath, PrintService printer)
            throws IOException, PrinterException {

        try (PDDocument doc = PDDocument.load(new File(pdfFilePath))) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintService(printer);

            // Usar PDFPageable para manejar todas las páginas
            job.setPageable(new PDFPageable(doc));

            job.print();
        }
    }
}



