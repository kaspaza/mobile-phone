package main;

import java.util.ArrayList;

public class Phone {

    private ArrayList<Contact> myContacts;

    public Phone(){
        this.myContacts = new ArrayList<>();
    }

    public void printContacts() {
        System.out.println("Contact list:");
        myContacts.forEach(c -> {System.out.println(c.getName() + ": " + c.getNumber());});
    }

    public String addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            return "Cannot add, contact already exists";
        } else {
            myContacts.add(contact);
            return "New contact added!";
        }
    }

    public void removeContact(Contact contact) {
        myContacts.remove(findContact(contact));
        System.out.println("Contact named: " + contact.getName() + " has been removed");
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

    public void updateContact(Contact oldContact, Contact newContact) {
        myContacts.set(findContact(oldContact), newContact);
        System.out.println("Contact: " + oldContact.getName() + ": " + oldContact.getNumber() +
                " was replaced by " + newContact.getName() + ": " + newContact.getNumber());
    }


}
