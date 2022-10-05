package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }

    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall = 0;            // antall noder i listen
    private int endringer = 0;         // antall endringer i listen

    public DobbeltLenketListe() {

        antall = 0;
        endringer = 0;

        //throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        if (a==null) {
            throw new NullPointerException("Tabellen a er null!");
        }
        else if (a.length==0) {
            return;
        }
        else{
            int count = 0;
            Node<T> current = new Node<T>(a[count]);
            while (true) { //finner først hode som ikke er null
                if (count < a.length) {
                    if (a[count] != null) {
                        current.verdi = a[count];
                        hode = current;
                        antall++;
                        break;
                    }
                    count++;
                }
                else {
                    break;
                }
            }
            for (int i = count+1;i<a.length;i++) { //fortsetter å legge til noder etter hode
                if (a[i]!=null) {
                    Node<T> next = new Node<T>(a[i]);
                    current.neste = next;
                    next.forrige = current;
                    current = next;
                    antall++;
                }
            }
            hale = current;
        }

    }

    private Node<T> finnNode(int indeks) {

        int midten = antall/2;

        Node<T> current;

        if (indeks<midten) {
            current = hode;
            for (int i = 1;i<=indeks;i++) {
                current = current.neste;
            }
        }
        else {
            current = hale;
            for (int i = antall-1;i>indeks;i--) {
                current = current.forrige;
            }
        }
        return current;
    }

    private void fratilKontroll(int antall, int fra, int til) { // redigert programkode 1.2.3 a) fra kompendiet
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);

        Liste<T> resultat = new DobbeltLenketListe<T>();

        if (fra == til) {
            return resultat;
        }

        Node<T> current = finnNode(fra);

        for (int i = fra; i<til; i++) {
            resultat.leggInn(current.verdi);
            current = current.neste;
        }

        return resultat;
        //throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {

        return hode == null;

        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi);

        if (antall==0) {
            hode = new Node<T>(verdi);
            hale = hode;
        }
        else {
            Node<T> ny = new Node<T>(verdi);
            hale.neste = ny;
            ny.forrige = hale;
            hale = ny;
        }

        antall++;
        endringer++;

        return true;
        //throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
        //throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {

        Node<T> current = hode;

        for (int i = 0; i<antall; i++) {
            if (verdi == current.verdi) {
                return i;
            }
            current = current.neste;
        }

        return -1;
        //throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks,false);
        Objects.requireNonNull(nyverdi);
        Node<T> node = finnNode(indeks);
        T gammel = node.verdi;
        node.verdi = nyverdi;
        endringer++;
        return gammel;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {

        if (antall==0){
            return "[]";
        }

        StringBuilder result = new StringBuilder();

        Node<T> current = hode;
        result.append("[").append(current.verdi);


        for (int i = 1;i<antall;i++) {
            current = current.neste;
            result.append(", ").append(current.verdi);
        }

        result.append("]");
        return result.toString();

    }

    public String omvendtString() {

        if (antall==0){
            return "[]";
        }

        StringBuilder result = new StringBuilder();

        Node<T> current = hale;
        result.append("[").append(current.verdi);


        for (int i = antall-2;i>=0;i--) {
            current = current.forrige;
            result.append(", ").append(current.verdi);
        }

        result.append("]");
        return result.toString();

    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


