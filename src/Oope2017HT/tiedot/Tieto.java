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
public abstract class Tieto implements Comparable<Tieto> {

    // Attribuutit.
    private StringBuilder tiedonNimi;

    // Rakentaja.
    public Tieto(StringBuilder n) throws IllegalArgumentException {

        if (n != null) {
            for (int i = 0; i < n.toString().length(); i++) {
                char c = n.charAt(i);
                if (c == '$' || c == '%' || c == '?' || c == '£' || c == '-'
                        || c == '§') {
                    throw new IllegalArgumentException("Error!");
                }
                tiedonNimi = n;
            }

        }
    }

    // Kopiorakentaja.
    public Tieto(Tieto haettava) throws IllegalArgumentException {
        if (haettava instanceof Tieto) {
            StringBuilder kopioNimi = new StringBuilder(haettava.tiedonNimi());
            tiedonNimi(kopioNimi);
        } else {
            throw new IllegalArgumentException("Rakentaminen epäonnistui!");
        }
    }

    public void tiedonNimi(StringBuilder sb) throws IllegalArgumentException {
        tiedonNimi = sb;
    }

    // Asetusmetodi.
    public StringBuilder tiedonNimi() {
        return tiedonNimi;
    }

    /**
     * Korvataan toString metodi.
     */
    public String toString() {
        return tiedonNimi.toString();
    }

    /**
     * Korvataan compareTo metodi.
     *
     * @param vertailtava = vertailtava tieto.
     * @return arvo, onko tiedosto sama.
     */
    @Override
    public int compareTo(Tieto vertailtava) {
        String nimi = tiedonNimi.toString();
        return nimi.compareTo(vertailtava.tiedonNimi().toString());
    }

    /**
     * Korvataan Equals metodi.
     *
     * @param obj = objekti.
     * @return tiedonNimi, mikäli ei löydy niin false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Tieto) {
            Tieto t = (Tieto) obj;
            boolean samat = tiedonNimi().toString().equals(t.tiedonNimi().toString());
            if (samat) {
                return true;
            }
        }
        return false;
    }
}
