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

    @Test
    public void shouldRemoveContactNamedKate() {
        Contact contact = new Contact("Kate", "123");
        phone.addNewContact(contact);
        phone.removeContact(contact);
        String expectedPrint = "Contact named: Kate has been removed";
        assertEquals(expectedPrint,outputStreamCaptor.toString().trim());
    }

    @Test
    public void queryContactKateShoudNotBeNull() {
        phone.addNewContact(new Contact("Kate", "123"));
        assertNotNull(phone.queryContact("Kate"));
    }

    @Test
    public void queryContactTomShoudBeNull() {
        phone.addNewContact(new Contact("Kate", "123"));
        assertNull(phone.queryContact("Tom"));
    }

    @Test
    public void updatingContactTom123ByHarry321ShouldBePrinted() {
        Contact tom = new Contact("Tom", "123");
        phone.addNewContact(tom);
        phone.updateContact(tom, new Contact("Harry", "321"));
        String expectedPrint = "Contact: Tom: 123 was replaced by Harry: 321";
        assertEquals(expectedPrint.trim(), outputStreamCaptor.toString().trim());
    }

}
