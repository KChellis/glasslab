package dao;

import models.Art;

import java.util.HashMap;
import java.util.List;

public interface ArtDao {
    List<Art> getAll();

    //CREATE
    void add (Art art);

    //READ
    Art findById(int id);
    List<String> getArtStylesByName(String artName);

    //UPDATE
    void update(int id, HashMap<String, Object> updateContent);

    //DELETE
    void deleteById(int id);
    void clearAllArts();
}
