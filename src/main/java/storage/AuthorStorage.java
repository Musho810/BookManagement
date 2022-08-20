package storage;

import bookModel.Author;

import java.util.Scanner;

public class AuthorStorage {
    static Scanner scanner = new Scanner(System.in);
    private static Author[] array = new Author[10];
    private static int size = 0;


    public void add(Author author) {
        if (array.length == size) {
            extend();
        }
        array[size++] = author;
    }

    private void extend() {
        Author [] temp = new Author [array.length + 10];
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

    public void printByAuthor() {
        System.out.println("Pleaseinput Author name");
        String authorName = scanner.nextLine();
        for (int i = 0; i < size; i++) {
            if (array[i].getName().equals(authorName)) {
                System.out.println(array[i]);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Author getAuthorByIndex(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return array[index];
    }

}

