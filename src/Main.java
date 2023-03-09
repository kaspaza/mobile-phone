import java.util.InputMismatchException;
import java.util.Scanner;

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
                    System.out.println("Błędne dane!");
                    continue;
                }
                if (action >= 0 && action < 6) {
                    switch (action) {
                        case 0:
                            System.out.println("Zamykanie programu...");
                            quit = true;
                            break;
                        case 1:
                            phone.printContacts();
                            break;
                        case 2:
                            addNewContact();
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
                System.out.println("Proszę podać cyfry!");
                scanner.nextLine();
            }
            System.out.println();
        } while (!quit);
    }

    private static void printActions() {
        System.out.println("Wybierz opcję:");
        System.out.println("0 - zamknij program\n" +
                "1 - Wyświetl listę kontaktów\n" +
                "2 - Dodaj kontakt\n" +
                "3 - Zaktualizuj istnięjący kontakt\n" +
                "4 - Usuń kontakt\n" +
                "5 - Sprawdź, czy kontakt istnieje");
    }

    public static void addNewContact() {
        System.out.println("Podaj imię");
        String name = scanner.nextLine();
        System.out.println("Podaj numer telefonu");
        String number = scanner.nextLine();
        Contact newContact = Contact.createContact(name, number);
        if (phone.addNewContact(newContact)) {
            System.out.println("Nowy kontakt dodany");
        } else {
            System.out.println("Nie można dodać, kontakt już istnieje");
        }
    }

    public static void updateContact() {
        System.out.println("Podaj nazwę kontaktu, który chcesz zaktualizować:");
        String nameOld = scanner.nextLine();
        Contact oldContact = phone.queryContact(nameOld);
        if (oldContact == null) {
            System.out.println("Kontakt o nazwie " + nameOld + " nie istnieje");
            return;
        }
        System.out.println("Podaj nazwę nowego kontaktu:");
        String nameNew = scanner.nextLine();
        System.out.println("Podaj nowy numer telefonu:");
        String number = scanner.nextLine();
        Contact newContact = Contact.createContact(nameNew, number);
        if (phone.updateContact(oldContact, newContact)) {
            System.out.println("Operacja zakończona pomyślnie");
        } else {
            System.out.println("Nie udało się zaktualizować kontaktu");
        }
    }

    public static void remove() {
        System.out.println("Podaj nazwę kontaktu, który chcesz usunąć:");
        String nameOld = scanner.nextLine();
        Contact oldContact = phone.queryContact(nameOld);
        if (oldContact == null) {
            System.out.println("Kontakt o nazwie " + nameOld + " nie istnieje");
            return;
        }
        if (phone.removeContact(oldContact)) {
            System.out.println("Operacja zakończona pomyślnie");
        } else {
            System.out.println("Spróbuj ponownie");
        }
    }

    public static void isOnFile() {
        System.out.println("Aby sprawdzić, podaj nazwę kontaktu:");
        String nameOld = scanner.nextLine();
        Contact oldContact = phone.queryContact(nameOld);
        if (oldContact == null) {
            System.out.println("Kontakt o nazwie " + nameOld + " nie istnieje");
        } else {
            System.out.println("Kontakt o nazwie " + nameOld + " istnieje");
        }
    }
}