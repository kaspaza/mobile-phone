package test;

import main.Contact;
import main.Phone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PhoneTest {
    private Phone phone;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        phone = new Phone();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldPrintContactsJoe123Harry456() {
        phone.addNewContact(new Contact("Joe", "123"));
        phone.addNewContact(new Contact("Harry", "456"));
        String expectedPrint = "Contact list:\r\nJoe: 123\r\nHarry: 456";
        phone.printContacts();
        assertEquals(expectedPrint, outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldPrintThatNewContactAdded() {
        assertEquals("New contact added!",
                phone.addNewContact(new Contact("Kate", "0987")));
    }

    @Test
    public void shouldPrintThatCannotAddContact() {
        phone.addNewContact(new Contact("Tom", "123"));
        assertEquals("Cannot add, contact already exists",
                phone.addNewContact(new Contact("Tom", "123")));
    }


}
