package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    @Test
    public void newOrderInstantiatesCorrectly() {
        Order testOrder = setupOrder();
    }

    //helper
    public Order setupOrder(){
        return new Order("fname", "street", "city", "state",  "zip",  "email",  "phone");
    }
}