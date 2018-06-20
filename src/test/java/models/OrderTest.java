package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    @Test
    public void newOrderInstantiatesCorrectly() {
        Order testOrder = setupOrder();
    }

    @Test
    public void newOrderInstantiatesWithIsNewTrue() {
        Order testOrder = setupOrder();
        assertTrue(testOrder.getUnread());
    }

    @Test
    public void newOrderInstantiatesWithSentFalse() {
        Order testOrder = setupOrder();
        assertFalse(testOrder.getSent());
    }

    @Test
    public void newOrderInstantiatesWithFirstName() {
        Order testOrder = setupOrder();
        assertEquals("firstName", testOrder.getFirstName());
    }

    @Test
    public void newOrderInstantiatesWithLastName() {
        Order testOrder = setupOrder();
        assertEquals("lastName", testOrder.getLastName());
    }

    @Test
    public void newOrderInstantiatesWithStreet() {
        Order testOrder = setupOrder();
        assertEquals("street", testOrder.getStreet());
    }

    @Test
    public void newOrderInstantiatesWithCity() {
        Order testOrder = setupOrder();
        assertEquals("city", testOrder.getCity());
    }

    @Test
    public void newOrderInstantiatesWithState() {
        Order testOrder = setupOrder();
        assertEquals("state", testOrder.getState());
    }

    @Test
    public void newOrderInstantiatesWithZip() {
        Order testOrder = setupOrder();
        assertEquals(97203, testOrder.getZip());
    }

    @Test
    public void newOrderInstantiatesWithEmail() {
        Order testOrder = setupOrder();
        assertEquals("email", testOrder.getEmail());
    }

    @Test
    public void newOrderInstantiatesWithPhone() {
        Order testOrder = setupOrder();
        assertEquals("phone", testOrder.getPhone());
    }

    @Test
    public void setNewChangesIsNew() {
        Order testOrder = setupOrder();
        testOrder.setUnread(false);
        assertFalse(testOrder.getUnread());
    }

    @Test
    public void setSentChangesSent() {
        Order testOrder = setupOrder();
        testOrder.setSent(true);
        assertTrue(testOrder.getUnread());
    }

    @Test
    public void setIdChangesId() {
        Order testOrder = setupOrder();
        testOrder.setId(1);
        assertEquals(1, testOrder.getId());
    }



    //helper
    public Order setupOrder(){
        return new Order("firstName", "lastName","street", "city", "state",  97203,  "email",  "phone");
    }
}