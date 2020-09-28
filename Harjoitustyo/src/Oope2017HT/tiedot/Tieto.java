/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oope2017ht.tiedot;

/**
 * Tieto-luokka on abstrakti yliluokka.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 * <p>
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi Luonnontieteiden
 * tiedekunta, Tampereen yliopisto
 */
public abstract class Tieto implements Comparable<Tieto> {

    // Attribuutit.
    private StringBuilder tiedonNimi;

    // Rakentaja.
    /**
     * Annetaan tieto StringBuilder -tyyppisenä, ja tarkistetaan ettei se
     * sisällä kiellettyjä merkkejä.
     *
     * @param nimi
     * @throws IllegalArgumentException
     */
    public Tieto(StringBuilder nimi) throws IllegalArgumentException {

        if (nimi != null) {
            for (int i = 0; i < nimi.toString().length(); i++) {
                char c = nimi.charAt(i);
                if (c == '$' || c == '%' || c == '?' || c == 'ä' || c == 'ö'
                        || c == '§' || c == 'Ö' || c == 'Ä' || c == '\'') {
                    throw new IllegalArgumentException("Error!");
                }
                tiedonNimi = nimi;
            }

        }
    }

    // Kopiorakentaja.
    public Tieto(Tieto haettavaTieto) throws IllegalArgumentException {
        if (haettavaTieto instanceof Tieto) {
            StringBuilder kopioNimi = new StringBuilder(haettavaTieto.tiedonNimi());
            tiedonNimi(kopioNimi);
        } else {
            throw new IllegalArgumentException("Rakentaminen epäonnistui!");
        }
    }

    /**
     * Rakentaja tiedon nimelle.
     *
     * @param nimi
     * @throws IllegalArgumentException
     */
    public void tiedonNimi(StringBuilder nimi) throws IllegalArgumentException {
        tiedonNimi = nimi;
    }

    // Asetusmetodi.
    public StringBuilder tiedonNimi() {
        return tiedonNimi;
    }

    /**
     * Korvataan toString metodi.
     */
    @Override
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
