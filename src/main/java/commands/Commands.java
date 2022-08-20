package commands;

public interface Commands {
    int LOG_OUT = 0;
    int ADD_BOOK = 1;
    int PRINT_ALL = 2;
    int PRINT_BY_AUTHOR = 3;
    int PRINT_BY_GENRE = 4;
    int PRINT_BY_PRICE = 5;
    int ADD_AUTHOR = 6;
    int PRINT_ALL_AUTHOR= 7;
    int DOWNLOAD_BOOKS_TO_EXCELL = 8;

    int EXIT =0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void printAdminCommands() {
        System.out.println("please input " + LOG_OUT + " for exit");
        System.out.println("please input " + ADD_BOOK + " for add book");
        System.out.println("please input " + PRINT_ALL + " for print all books");
        System.out.println("please input " + PRINT_BY_AUTHOR + " for print books by author name");
        System.out.println("please input " + PRINT_BY_GENRE + " for print books by genre");
        System.out.println("please input " + PRINT_BY_PRICE + " for print books by price_range");
        System.out.println("please input " + ADD_AUTHOR + " for add author");
        System.out.println("please input " + PRINT_ALL_AUTHOR + " for print all author");
        System.out.println("please input " + DOWNLOAD_BOOKS_TO_EXCELL + " for download books excel");

    }


    static void printUserCommands() {
        System.out.println("please input " + LOG_OUT + " for logout");
        System.out.println("please input " + PRINT_ALL + " for print all books");
        System.out.println("please input " + PRINT_BY_AUTHOR + " for print books by author name");
        System.out.println("please input " + PRINT_BY_GENRE + " for print books by gander");
        System.out.println("please input " + PRINT_BY_PRICE + " for print books by price_range");
        System.out.println("please input " + PRINT_ALL_AUTHOR + " for print all author");
        System.out.println("please input " + DOWNLOAD_BOOKS_TO_EXCELL + " for download books excel");

    }
    static void printLoginCommands() {
        System.out.println("please input " + EXIT + " for exit");
        System.out.println("please input " + LOGIN + " for LOGIN");
        System.out.println("please input " + REGISTER + " for REGISTER");

    }
}
