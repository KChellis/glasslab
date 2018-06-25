package dao;

import models.Art;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class Sql2oArtDaoTest {
    private Sql2oArtDao artDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        artDao = new Sql2oArtDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void getAll_returnsEmptyListIfNoMembers() {
        assertEquals(0, artDao.getAll().size());
    }

    @Test
    public void add_setsId() {
        Art testArt = setupArt();
        assertEquals(1, testArt.getId());
    }

    @Test
    public void getAll_returnsAllMembers() {
        Art testArt = setupArt();
        Art altArt = setupOtherArt();
        assertEquals(2, artDao.getAll().size());
    }

    @Test
    public void findById() {
        Art testArt = setupArt();
        Art altArt = setupOtherArt();
        assertEquals(altArt.getName(), artDao.findById(altArt.getId()).getName());
    }

    @Test
    public void update() {
        Art testArt = setupArt();
        HashMap<String, Object> updateContent = new HashMap<>();
        updateContent.put("name", "necklace");
        artDao.update(testArt.getId(), updateContent);
        assertNotEquals(testArt.getName(), artDao.findById(testArt.getId()).getName());
        assertEquals("necklace", artDao.findById(testArt.getId()).getName());

    }

    @Test
    public void deleteById() {
        Art testArt = setupArt();
        Art altArt = setupOtherArt();
        artDao.deleteById(testArt.getId());
        assertEquals(1, artDao.getAll().size());
        assertEquals("name2", artDao.findById(altArt.getId()).getName());
    }

    @Test
    public void clearAllArts() {
        Art testArt = setupArt();
        Art altArt = setupOtherArt();
        artDao.clearAllArts();
        assertEquals(0, artDao.getAll().size());
    }

    @Test
    public void getArtStylesByName_returnsArtStyles() {
        Art testArt = setupArt();
        Art altArt = setupOtherArt();
        artDao.getArtStylesByName(testArt.getName());
        assertEquals(1, artDao.getArtStylesByName(testArt.getName()).size());
    }

    //helper
    public Art setupArt(){
        Art art = new Art("name", "type", "materials", 500, "images", "description", "keywords", "style");
        artDao.add(art);
        return art;
    }
    public Art setupOtherArt(){
        Art art = new Art("name2", "type2", "materials2", 600, "images2", "description2", "keywords2", "style2");
        artDao.add(art);
        return art;
    }
}