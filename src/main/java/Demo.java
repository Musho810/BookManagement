


import bookEnum.Gender;
import bookEnum.UserType;
import bookModel.Author;
import bookModel.Book;
import bookModel.User;
import commands.Commands;
import storage.AuthorStorage;
import storage.BookStorage;
import storage.UserStorage;

import java.io.IOException;
import java.util.Scanner;

public class Demo implements Commands {
    static Scanner scanner = new Scanner(System.in);
    private static BookStorage bookStorage = new BookStorage();
    private static AuthorStorage authorStorage = new AuthorStorage();
    private static UserStorage userStorage = new UserStorage();


    public static void main(String[] args) throws IOException {
        initData();
        int command;

        boolean run = true;

        while (run) {
           Commands.printLoginCommands();
           try {
               command = Integer.parseInt(scanner.nextLine());
           } catch (NumberFormatException e) {
               System.out.println("Please input correct command");
               command = -1;
           }
           switch (command) {
               case EXIT -> run=false;
               case LOGIN -> login();
               case REGISTER -> registration();
               default -> System.out.println("Invalid command");
           }
        }


    }

    private static void registration() {
        System.out.println("Please input name,surname,email,password");
        String userDataStr = scanner.nextLine();
        String [] userData = userDataStr.split(",");

        if(userData.length<4){
            System.out.println("Invalid data");
        } else {
            if (userStorage.getUserByEmail(userData[2])==null){
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setUserType(UserType.USER);
                userStorage.add(user);
                System.out.println("User Created sucssecfuly");
            } else {
                System.out.println(userData[2]  + "  user allready exist");
            }

        }

    }

    private static void login() throws IOException {
        System.out.println("please input email , password  ");
        String data = scanner.nextLine();
        String[] dataStr = data.split(",");
        User userByEmail = userStorage.getUserByEmail(dataStr[0]);
        if (userByEmail == null) {
            System.out.println("Incorrect email or password");
        } else {
            if (!userByEmail.getPassword().equals(dataStr[1])) {
                System.out.println("Incorrect email or password");
            } else {
                if (userByEmail.getUserType().equals(UserType.ADMIN)) {
                    adminCommands();
                } else {
                    userCommands();
                }
            }
        }
    }

    private static void userCommands() throws IOException {
        boolean run = true;
        int command;
        while (run) {
            Commands.printUserCommands();
            try { command=Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Please input correct number");
                command=-1;
            }
            switch (command) {
                case LOG_OUT -> run = false;
                case PRINT_ALL -> bookStorage.printall();
                case PRINT_BY_AUTHOR -> {
                    System.out.println("Please input author name");
                    authorStorage.printByAuthor();
                }
                case PRINT_BY_GENRE -> {
                    System.out.println("Please input genre");
                    bookStorage.printByGenre(scanner.nextLine().trim());
                }
                case PRINT_BY_PRICE -> bookStorage.printByPrice();
                case PRINT_ALL_AUTHOR -> authorStorage.printByAuthor();
                case DOWNLOAD_BOOKS_TO_EXCELL -> bookStorage.downloadBooksExcel();
                default -> System.err.println("Invalid index");
            }

        }

    }



    private static void adminCommands() throws IOException {
        boolean run = true;
        int command;
        while (run) {
            Commands.printUserCommands();
            try { command=Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Please input correct number");
                command=-1;
            }

            switch (command) {
                case LOG_OUT -> run = false;
                case ADD_BOOK -> addBook();
                case PRINT_ALL -> printall();
                case PRINT_BY_AUTHOR -> printByAuthor();
                case PRINT_BY_GENRE -> printByGenre();
                case PRINT_BY_PRICE -> bookStorage.printByPrice();
                case ADD_AUTHOR -> addauthor();
                case PRINT_ALL_AUTHOR -> authorStorage.printall();
                case DOWNLOAD_BOOKS_TO_EXCELL ->bookStorage.downloadBooksExcel();

                default -> System.out.println("please input correct index");
            }
        }

    }

    private static void addauthor() {
        System.out.println("please input author name");
        String name = scanner.nextLine();
        System.out.println("Please input author surname");
        String surname = scanner.nextLine();
        System.out.println("Please input author email");
        String email = scanner.nextLine();
        System.out.println("please input author gender");
        Gender gender = Gender.valueOf(scanner.nextLine());

        Author author = new Author(name, surname, email, gender);
        authorStorage.add(author);
        System.out.println("Author created");

    }

    private static void printall() {
        bookStorage.printall();
    }




    private static void printByGenre() {
        System.out.println("please input genre");
        String genre = scanner.nextLine();
        bookStorage.printByGenre(genre);
    }

    private static void printByAuthor() {
        System.out.println("please input author name");
        String authorName = scanner.nextLine();
        bookStorage.printByAuthor(authorName);
    }


    private static void addBook() {
        if (authorStorage.getSize() != 0) {
            authorStorage.printall();
            System.out.println("Please input author by index");
            int index = Integer.parseInt(scanner.nextLine());
            Author author = authorStorage.getAuthorByIndex(index);
            if (author == null) {
                System.out.println("Please input correct index");
            } else {
                System.out.println("Please input book's title");
                String title = scanner.nextLine();

                System.out.println("Please input book's price");
                String priceStr = scanner.nextLine();
                System.out.println("Please input book's count");
                String countStr = scanner.nextLine();
                System.out.println("Please input book's genre");
                String genre = scanner.nextLine();

                double price = Double.parseDouble(priceStr);
                int count = Integer.parseInt(countStr);

                Book book = new Book(title, author, price, count, genre);
                bookStorage.addBook(book);
                System.out.println("book saved");
            }
        } else {
            System.out.println("Please input author for add book");
            addauthor();
        }
    }

    private static void initData() {
        User admin = new User("admin", "admin", "admin@mail.com", "admin", UserType.ADMIN);
        userStorage.add(admin);
        User user = new User("poxos", "poxosyan", "poxos@mail.com", "poxos", UserType.USER);
        userStorage.add(user);


        Author author1 = new Author("Hovhannes", "Tumanyan", "tumanyan@mail.com", Gender.MALE);
        Author author2 = new Author("Hovhannes", "Shiraz", "shiraz@mail.com@", Gender.MALE);
        Author author3 = new Author("Vahan", "Teryan", "teryan@mail.com", Gender.MALE);
        authorStorage.add(author1);
        authorStorage.add(author2);
        authorStorage.add(author3);

        bookStorage.addBook(new Book("asd", author1, 54, 5, "heqiat"));
        bookStorage.addBook(new Book("err", author2, 54, 7, "banastextsutyun"));
        bookStorage.addBook(new Book("atffsd", author3, 54, 2, "banstextsutyun"));
    }


}
