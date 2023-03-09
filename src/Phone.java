import java.util.ArrayList;

public class Phone {

    private ArrayList<Contact> myContacts;

    public Phone(){
        this.myContacts = new ArrayList<Contact>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            return false;
        } else {
            myContacts.add(contact);
            return true;
        }
    }

    public boolean removeContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            int foundPosition = findContact(contact);
            myContacts.remove(foundPosition);
            System.out.println("Kontakt o nazwie: " + contact.getName() + " został usunięty");
            return true;
        }
        System.out.println("Kontakt nie może zostać usunięty. Kontakt o nazwie: " + contact.getName() + " nie istnieje");
        return false;
    }

    private int findContact(Contact contact) {
        return myContacts.indexOf(contact);
    }

    private int findContact(String contactName) {
        for (int i=0; i<myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            if (contact.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >=0) {
            return myContacts.get(position);
        }
        return null;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int foundPosition = findContact(oldContact);
        if (foundPosition <0) {
            System.out.println(oldContact.getName() + " nie istnieje");
            return false;
        } else if (findContact(newContact.getName()) != -1) {
            System.out.println("Kontakt o nazwie " + newContact.getName() + " już istnieje. Aktualizacja nie powiodła się");
            return false;
        }
        myContacts.set(foundPosition, newContact);
        System.out.println("Kontakt: " + oldContact.getName() + ": " + oldContact.getNumber() +
                " został zastąpiony przez " + newContact.getName() + ": " + newContact.getNumber());
        return true;
    }

    public void printContacts() {
        System.out.println("Lista kontaktów:");
        for (int i=0; i < myContacts.size(); i++) {
            System.out.println((i+1) + " - " + myContacts.get(i).getName() + ": " + myContacts.get(i).getNumber());
        }
    }
}
