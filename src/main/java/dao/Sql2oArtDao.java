package dao;

import models.Art;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sql2oArtDao implements ArtDao {
    private final Sql2o sql2o;

    public Sql2oArtDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Art> getAll() {
        String sql = "SELECT * FROM art";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Art.class);
        }
    }

    @Override
    public void add(Art art) {
        String sql = "INSERT INTO art (name, type, materials, price, images, description, keywords, style) VALUES (:name, :type, :materials, :price, :images, :description, :keywords, :style)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(art)
                    .executeUpdate()
                    .getKey();
            art.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Art findById(int id) {
        String sql = "SELECT * FROM art WHERE id=:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Art.class);
        }
    }

    @Override
    public void update(int id, HashMap<String, Object> updateContent) {
        for(String key : updateContent.keySet()){
            String sql = "UPDATE art SET " + key + " = :" + key + " WHERE id = :id";
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
        String sql = "DELETE FROM art WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllArts() {
        String sql = "DELETE FROM art";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<String> getArtStylesByName(String artName) {

        String joinQuery = "SELECT style FROM art WHERE name = :artName";

        try (Connection con = sql2o.open()) {
            return con.createQuery(joinQuery)
                    .addParameter("artName", artName)
                    .executeAndFetch(String.class);
        }
    }
}
