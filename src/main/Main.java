package main;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Phone phone = new Phone();

    public static void main(String[] args) {

        boolean quit = false;

        do {
            printActions();
            try {
                int action = scanner.nextInt();
                scanner.nextLine();
                if (action > 5 || action < 0) {
                    System.out.println("Incorrect data entry!");
                    continue;
                }
                if (action >= 0 && action < 6) {
                    switch (action) {
                        case 0:
                            System.out.println("Exiting the program...");
                            quit = true;
                            break;
                        case 1:
                            phone.printContacts();
                            break;
                        case 2:
                            System.out.println(phone.addNewContact(createContact()));
                            break;
                        case 3:
                            updateContact();
                            break;
                        case 4:
                            remove();
                            break;
                        case 5:
                            isOnFile();
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers!");
                scanner.nextLine();
            }
            System.out.println();
        } while (!quit);
    }

    private static void printActions() {
        System.out.println("Choose an option:");
        System.out.println("0 - exit\n" +
                "1 - View your contact list\n" +
                "2 - Add new contact\n" +
                "3 - Update an existing contact\n" +
                "4 - Remove contact\n" +
                "5 - Check whether the contact exists");
    }

    public static Contact createContact() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter phone number:");
        String number = scanner.nextLine();
        return new Contact(name,number);
    }

    public static void updateContact() {
        Contact contactToUpdate = askForContact();
        if (contactToUpdate == null) {
            System.out.println("Update failed");
            return;
        }
        System.out.println("* Entering new data *");
        phone.updateContact(contactToUpdate, createContact());
    }

    public static void remove() {
        Contact contactToRemove = askForContact();
        if (contactToRemove == null) {
            System.out.println("Deletion failed");
            return;
        }
        phone.removeContact(contactToRemove);
    }

    public static void isOnFile() {
        Contact searchedContact = askForContact();
        if (searchedContact == null) {
            return;
        }
        System.out.println("Contact named " + searchedContact.getName() + " exists");

    }

    public static Contact askForContact() {
        System.out.println("Enter the contact name:");
        String name = scanner.nextLine();
        Contact contact = phone.queryContact(name);
        if (contact == null) {
            System.out.println("Contact named " + name + " does not exist");
            return null;
        }
        return contact;
    }
}