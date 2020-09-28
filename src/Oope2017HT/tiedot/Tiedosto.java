/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oope2017ht.tiedot;

/**
 *
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi
 */
public class Tiedosto extends Tieto {

    // Attribuutti koolle.
    private int koko;

    // Rakentajametodi.
    public Tiedosto(StringBuilder n, int k) throws IllegalArgumentException {
        super(n);
        koko(k);

    }

    // Kopiorakentaja.
    public Tiedosto(Tiedosto kopioitava) throws IllegalArgumentException {
        super(kopioitava);
        if (kopioitava instanceof Tieto) {
            koko = kopioitava.koko();
        } else {
            throw new IllegalArgumentException("Rakentaminen epäonnistui!");
        }
    }

    // Asetusmetodi.
    public int koko() {
        return koko;
    }

    // Hakumetodi.
    public void koko(int k) {
        if (k > 0) {
            koko = k;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    /**
     * Korvataan toString metodi.
     */
    public String toString() {
        return super.toString() + " " + koko;
    }

}
