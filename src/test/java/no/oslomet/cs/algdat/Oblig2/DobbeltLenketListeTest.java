package no.oslomet.cs.algdat.Oblig2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DobbeltLenketListeTest {

    @Test
    void subliste() {
    }

    @Test
    void antall() {
    }

    @Test
    void tom() {
    }

    @Test
    void leggInn() {

    }

    @Test
    void testLeggInn() {
    }

    @Test
    void inneholder() {
    }

    @Test
    void hent() {
        Integer[] list = {1,2,3,4,5,6};
        DobbeltLenketListe<Integer> test = new DobbeltLenketListe<>(list);
        assertEquals(6,test.hent(5));
    }

    @Test
    void indeksTil() {
    }

    @Test
    void oppdater() {
    }

    @Test
    void fjern() {
    }

    @Test
    void testFjern() {
    }

    @Test
    void nullstill() {
    }

    @Test
    void testToString() {
        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        assertEquals("[]",l1.toString());
        assertEquals("[A]",l2.toString());
        assertEquals("[A, B]",l3.toString());

    }

    @Test
    void omvendtString() {
        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        assertEquals("[]",l1.omvendtString());
        assertEquals("[A]",l2.omvendtString());
        assertEquals("[B, A]",l3.omvendtString());
    }


    @Test
    void iterator() {
    }

    @Test
    void testIterator() {
    }

    @Test
    void sorter() {
    }
}