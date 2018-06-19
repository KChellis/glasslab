package dao;

import models.Art;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

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
    public void findById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAllArts() {
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