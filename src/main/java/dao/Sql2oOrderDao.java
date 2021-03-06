package dao;

import models.Art;
import models.Order;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sql2oOrderDao implements OrderDao{
    private final Sql2o sql2o;

    public Sql2oOrderDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM orders";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Order.class);
        }
    }

    @Override
    public void add(Order order) {
        String sql = "INSERT INTO orders (unread, sent, firstName, lastName, street, city, state, zip, email, phone, createdAt) VALUES (:unread, :sent, :firstName, :lastName, :street, :city, :state, :zip, :email, :phone, :createdAt)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(order)
                    .executeUpdate()
                    .getKey();
            order.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM orders WHERE id=:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Order.class);
        }
    }

    @Override
    public void update(int id, HashMap<String, Object> updateContent) {
        for(String key : updateContent.keySet()){
            String sql = "UPDATE orders SET " + key + " = :" + key + " WHERE id = :id";
            try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                        .addParameter(key, updateContent.get(key))
                        .addParameter("id", id)
                        .executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM orders WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllOrders() {
        String sql = "DELETE FROM orders";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void addArtToOrder(int artId, int orderId) {
        String sql = "INSERT INTO orders_art (artId, orderId) VALUES (:artId, :orderId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("artId", artId)
                    .addParameter("orderId", orderId)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Art> getArtInOrder(int orderId) {
        ArrayList<Art> art = new ArrayList<>();

        String joinQuery = "SELECT artId FROM orders_art WHERE orderId = :orderId";

        try (Connection con = sql2o.open()) {
            List<Integer> allArtIds = con.createQuery(joinQuery)
                    .addParameter("orderId", orderId)
                    .executeAndFetch(Integer.class);
            for (Integer artId : allArtIds){
                String restaurantQuery = "SELECT * FROM art WHERE id = :artId";
                art.add(
                        con.createQuery(restaurantQuery)
                                .addParameter("artId", artId)
                                .executeAndFetchFirst(Art.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return art;
    }
}
