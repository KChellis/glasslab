package dao;

import models.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderDao {
    List<Order> getAll();

    //CREATE
    void add (Order order);

    //READ
    Order findById(int id);

    //UPDATE
    void update(int id, HashMap<String, Object> updateContent);

    //DELETE
    void deleteById(int id);
    void clearAllOrders();
}
