package dao;

import models.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;

import static org.junit.Assert.*;

public class Sql2oOrderDaoTest {
    private Sql2oOrderDao orderDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        orderDao = new Sql2oOrderDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void getAll_returnsEmptyListIfNoMembers() {
        assertEquals(0, orderDao.getAll().size());
    }

    @Test
    public void add_setsId() {
        Order testOrder = setupOrder();
        assertEquals(1, testOrder.getId());
    }

    @Test
    public void getAll_returnsAllMembers() {
        Order testOrder = setupOrder();
        Order altOrder = setupOtherOrder();
        assertEquals(2, orderDao.getAll().size());
    }

    @Test
    public void findById() {
        Order testOrder = setupOrder();
        Order altOrder = setupOtherOrder();
        assertEquals(altOrder.getFirstName(), orderDao.findById(altOrder.getId()).getFirstName());
    }

    @Test
    public void update() {
        Order testOrder = setupOrder();
        HashMap<String, Object> updateContent = new HashMap<>();
        updateContent.put("firstName", "Kristen");
        orderDao.update(testOrder.getId(), updateContent);
        assertNotEquals(testOrder.getFirstName(), orderDao.findById(testOrder.getId()).getFirstName());
        assertEquals("Kristen", orderDao.findById(testOrder.getId()).getFirstName());

    }

    @Test
    public void deleteById() {
        Order testOrder = setupOrder();
        Order altOrder = setupOtherOrder();
        orderDao.deleteById(testOrder.getId());
        assertEquals(1, orderDao.getAll().size());
        assertEquals("firstName2", orderDao.findById(altOrder.getId()).getFirstName());
    }

    @Test
    public void clearAllOrders() {
        Order testOrder = setupOrder();
        Order altOrder = setupOtherOrder();
        orderDao.clearAllOrders();
        assertEquals(0, orderDao.getAll().size());
    }

    //helper
    public Order setupOrder(){
        Order order = new Order("firstName", "lastName","street", "city", "state",  97203,  "email",  "phone");
        orderDao.add(order);
        return order;
    }
    public Order setupOtherOrder(){
        Order order = new Order("firstName2", "lastName2","street2", "city2", "state2",  97217,  "email2",  "phone2");
        orderDao.add(order);
        return order;
    }
}