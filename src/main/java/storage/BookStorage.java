package storage;


import bookModel.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class BookStorage {
    static Scanner scanner = new Scanner(System.in);
    private static Book[] array = new Book[10];
    private static int size = 0;


    public void addBook(Book book) {
        if (array.length == size) {
            extend();
        }
        array[size++] = book;
    }

    private void extend() {
        Book[] temp = new Book[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void printall() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }

    }



    public void printByGenre(String genre) {
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().equals(genre)) {
                System.out.println(array[i]);
            }
        }
    }

    public void printByPrice() {
        System.out.println("Pleaseinput min price");
        double minprice = Double.parseDouble(scanner.nextLine());
        System.out.println("please input max price");
        double maxprice= Double.parseDouble(scanner.nextLine());

        for (int i = 0; i < size; i++) {
            if (array[i].getPrice() > minprice && array[i].getPrice() < maxprice) {
                System.out.println(array[i]);
            }
        }
    }






    public void printByAuthor(String authorName) {
        for (int i = 0; i <= size; i++) {
          if ( array[i].getAuthor().getName().equals(authorName)){
                System.out.println(array[i]);
            }
        }
    }
    public void downloadBooksExcel( )throws IOException {
        System.out.println("Please input file directory");
        String fileDir = scanner.nextLine();
            File directory = new File(fileDir);
            if (directory.isFile()) {
                throw new RuntimeException("fileDir must be a directory!");
            }
            File excelFile = new File(fileDir, "book " + System.currentTimeMillis() + ".xlsx");
            excelFile.createNewFile();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("books");
            Row headerRow = sheet.createRow(0);

            Cell nameCell = headerRow.createCell(0);
            nameCell.setCellValue("title");

            Cell authorCell = headerRow.createCell(1);
            authorCell.setCellValue("author");

            Cell priceCell = headerRow.createCell(2);
            priceCell.setCellValue("price");

            Cell generCell = headerRow.createCell(3);
            generCell.setCellValue("gener");

            for (int i = 0; i < size; i++) {
                Book book = array[i];
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(book.getTitle());
                row.createCell(1).setCellValue(book.getAuthor().getName());
                row.createCell(2).setCellValue(book.getPrice());
                row.createCell(3).setCellValue(book.getGenre());
            }
            workbook.write(new FileOutputStream(excelFile));
            System.out.println("excel was created successfully");
        }
    }


