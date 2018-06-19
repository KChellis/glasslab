package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtTest {

    @Test
    public void newArtInstantiatesCorrectly() {
        Art testArt = setupArt();
        assertTrue(testArt instanceof Art);
    }

    @Test
    public void newArtInstantiatesWithName() {
        Art testArt = setupArt();
        assertEquals("name", testArt.getName());
    }

    @Test
    public void newArtInstantiatesWithType() {
        Art testArt = setupArt();
        assertEquals("type", testArt.getType());
    }

    @Test
    public void newArtInstantiatesWithMaterials() {
        Art testArt = setupArt();
        assertEquals("materials", testArt.getMaterials());
    }

    @Test
    public void newArtInstantiatesWithPrice() {
        Art testArt = setupArt();
        assertEquals(500, testArt.getPrice());
    }

    @Test
    public void newArtInstantiatesWithImages() {
        Art testArt = setupArt();
        assertEquals("images", testArt.getImages());
    }

    @Test
    public void newArtInstantiatesWithDescription() {
        Art testArt = setupArt();
        assertEquals("description", testArt.getDescription());
    }

    @Test
    public void newArtInstantiatesWithKeywords() {
        Art testArt = setupArt();
        assertEquals("name", testArt.getName());
    }

    @Test
    public void newArtInstantiatesWithStyle() {
        Art testArt = setupArt();
        assertEquals("style", testArt.getStyle());
    }

    @Test
    public void setId_setsId() {
        Art testArt = setupArt();
        testArt.setId(1);
        assertEquals(1, testArt.getId());
    }


    //helper
    public Art setupArt(){
        return new Art("name", "type", "materials", 500, "images", "description", "keywords", "style");
    }
}