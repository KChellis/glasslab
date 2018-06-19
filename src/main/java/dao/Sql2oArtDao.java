package dao;

import models.Art;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
        return null;
    }

    @Override
    public void update(int id, HashMap<String, Object> updateContent) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllArts() {

    }
}
