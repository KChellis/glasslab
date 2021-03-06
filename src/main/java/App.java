import com.google.gson.Gson;
import dao.Sql2oArtDao;
import dao.Sql2oOrderDao;
import models.Art;
import models.Order;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oArtDao artDao;
        Sql2oOrderDao orderDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/art.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        artDao = new Sql2oArtDao(sql2o);
        orderDao = new Sql2oOrderDao(sql2o);
        conn = sql2o.open();

        get("/art", "application/json", (req, res) -> {
            return gson.toJson(artDao.getAll());
        });

        post("/art/new", "application/json", (req, res) -> {
            Art art = gson.fromJson(req.body(), Art.class);
            artDao.add(art);
            res.status(201);
            return gson.toJson(art);
        });

        get("/art/:artId", "application/json", (req, res) -> {
            int artId = Integer.parseInt(req.params("artId"));
            return gson.toJson(artDao.findById(artId));
        });

        get("/art/:artId/delete", "application/json", (req, res) -> {
            int artId = Integer.parseInt(req.params("artId"));
            artDao.deleteById(artId);
            return "{\"message\":\"Art Deleted\"}";
        });

        post("/art/:artId/update", "application/json", (req, res) -> {
            int artId = Integer.parseInt(req.params("artId"));
            HashMap<String, Object> updateContent = gson.fromJson(req.body(), HashMap.class);
            res.status(201);
            artDao.update(artId, updateContent);
            return gson.toJson(artDao.findById(artId));
        });

        get("/art/:artId/:artName", "application/json", (req, res) -> {
            int artId = Integer.parseInt(req.params("artId"));
            String artName = req.params("artName");
            return gson.toJson(artDao.getArtStylesByName(artName));
        });

        get("/order", "application/json", (req, res) -> {
            return gson.toJson(orderDao.getAll());
        });

        post("/order/new", "application/json", (req, res) -> {
            Order order = gson.fromJson(req.body(), Order.class);
            orderDao.add(order);
            res.status(201);
            return gson.toJson(order);
        });

        get("/order/:orderId", "application/json", (req, res) -> {
            int orderId = Integer.parseInt(req.params("orderId"));
            return gson.toJson(orderDao.findById(orderId));
        });

        get("/order/:orderId/delete", "application/json", (req, res) -> {
            int orderId = Integer.parseInt(req.params("orderId"));
            orderDao.deleteById(orderId);
            return "{\"message\":\"Order Deleted\"}";
        });

        post("/order/:orderId/update", "application/json", (req, res) -> {
            int orderId = Integer.parseInt(req.params("orderId"));
            HashMap<String, Object> updateContent = gson.fromJson(req.body(), HashMap.class);
            res.status(201);
            orderDao.update(orderId, updateContent);
            return gson.toJson(orderDao.findById(orderId));
        });

        get("/order/:orderId/art", "application/json", (req, res) -> {
            int orderId = Integer.parseInt(req.params("orderId"));
            orderDao.deleteById(orderId);
            return gson.toJson(orderDao.getArtInOrder(orderId));
        });

        post("/order/:orderId/art/:artId", "application/json", (req, res) -> {
            int orderId = Integer.parseInt(req.params("orderId"));
            int artId = Integer.parseInt(req.params("artId"));
            res.status(201);
            orderDao.addArtToOrder(artId, orderId);
            return gson.toJson(orderDao.findById(orderId));
        });



        after((request, response) -> {
            response.type("application/json");
        });

    }
}
