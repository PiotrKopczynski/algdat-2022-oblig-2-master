package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


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
            Node<T> current = new Node<>(a[count]);
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
                    Node<T> next = new Node<>(a[i]);
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

        Liste<T> resultat = new DobbeltLenketListe<>();

        if (fra == til) {
            return resultat;
        }

        Node<T> current = finnNode(fra);

        for (int i = fra; i<til; i++) {
            resultat.leggInn(current.verdi);
            current = current.neste;
        }

        return resultat;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {

        return hode == null;
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi);

        if (antall==0) {
            hode = new Node<>(verdi);
            hale = hode;
        }
        else {
            Node<T> ny = new Node<>(verdi);
            hale.neste = ny;
            ny.forrige = hale;
            hale = ny;
        }

        antall++;
        endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);
        Objects.requireNonNull(verdi);
        if (antall == 0) {
            leggInn(verdi);
        }
        else if (indeks == 0) {
            Node<T> ny = new Node<>(verdi);
            hode.forrige = ny;
            ny.neste=hode;
            hode=ny;
            endringer++;
            antall++;
        }
        else if (indeks == antall) {
            Node<T> ny = new Node<>(verdi);
            hale.neste = ny;
            ny.forrige = hale;
            hale = ny;
            endringer++;
            antall++;
        }
        else {
            Node<T> ny = new Node<>(verdi);
            Node<T> temp = finnNode(indeks);
            temp.forrige.neste = ny;
            ny.forrige = temp.forrige;
            ny.neste = temp;
            temp.forrige = ny;
            endringer++;
            antall++;
        }
    }

    @Override
    public boolean inneholder(T verdi) {

        int indeks = indeksTil(verdi);

        return indeks != -1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {

        Node<T> current = hode;

        for (int i = 0; i<antall; i++) {
            if (current.verdi.equals(verdi)) {
                return i;
            }
            current = current.neste;
        }
        return -1;
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
    }

    @Override
    public boolean fjern(T verdi) {

        boolean result = false;

        if (antall == 0) {
            return false;
        }
        else {
            Node<T> fjernes = hode;
            for (int i = 0; i<antall; i++) {
                if (fjernes.verdi.equals(verdi)) {
                    if (antall == 1) {
                        hode = null;
                        hale = null;
                        result = true;
                    }
                    else {
                        if (i == 0) {
                            hode.neste.forrige = null;
                            hode = hode.neste;
                            result = true;
                        }
                        else if (i == antall-1) {
                            hale.forrige.neste = null;
                            hale = hale.forrige;
                            result = true;
                        }
                        else {
                            fjernes.neste.forrige = fjernes.forrige;
                            fjernes.forrige.neste = fjernes.neste;
                            result = true;
                        }
                    }
                    antall--;
                    endringer++;
                    break;
                }
                fjernes = fjernes.neste;
            }
        }
        return result;
    }

    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks,false);

        T gammel;

        if (antall == 0) {
            return null;
        }
        if (antall == 1) {
            gammel = hode.verdi;
            hode = null;
            hale = null;
        }
        else if (indeks == 0){
            gammel = hode.verdi;
            hode.neste.forrige = null;
            hode = hode.neste;
        }
        else if (indeks == antall-1) {
            gammel = hale.verdi;
            hale.forrige.neste = null;
            hale = hale.forrige;
        }
        else {
            Node<T> fjernes = finnNode(indeks);
            gammel = fjernes.verdi;
            fjernes.neste.forrige = fjernes.forrige;
            fjernes.forrige.neste = fjernes.neste;
        }
        antall--;
        endringer++;
        return gammel;
    }

    @Override
    public void nullstill() {
        //Måte: 1
        Node<T> current = hode;
        for (int i = 0; i<antall; i++) {
            current.verdi = null;
            current.neste = null;
        }

        hode = null;
        hale = null;
        antall = 0;
        endringer++;

        //Måte 2
        /*
        int size = antall;
        for (int i = 0; i<size; i++) {
            fjern(0);
        }
         */
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

        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {

        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
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

            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {

            if (iteratorendringer!=endringer) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            fjernOK = true;
            T temp = denne.verdi;
            denne = denne.neste;
            return temp;
        }

        @Override
        public void remove() {
            if (!fjernOK) {
                throw new IllegalStateException("Ulovlig tilstand!");
            }
            if (endringer != iteratorendringer) {
                throw new ConcurrentModificationException();
            }
            fjernOK = false;

            if (antall == 1) {
                hode = hale = null;
            }
            else if (denne == null) {
                hale = hale.forrige;
                hale.neste = null;
            }
            else if (denne.forrige == hode)  {
                hode = denne;
                denne.forrige = null;
            }
            else {
                denne.forrige.forrige.neste = denne;
                denne.forrige = denne.forrige.forrige;
            }

            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        if (liste.antall() == 0 || liste.antall() == 1) {
            return;
        }
        int dynamicAntall = liste.antall();
        for (int i = 0; i<liste.antall()-1; i++) {
            int minIndeks = 0;
            for (int j = 1; j < dynamicAntall; j++) {

                if (c.compare(liste.hent(j), liste.hent(minIndeks)) < 0) {
                    minIndeks = j;
                }
            }
            liste.leggInn(liste.fjern(minIndeks));
            dynamicAntall--;
        }
        liste.leggInn(liste.fjern(0));
    }

} // class DobbeltLenketListe


