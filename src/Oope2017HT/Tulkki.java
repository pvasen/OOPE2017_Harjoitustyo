/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oope2017ht;

import oope2017ht.tiedot.*;

/**
 *
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi
 */
public class Tulkki {

    // Ylöspäin siirtymisen attribuutti.
    final String ylos = "..";
    // Juurihakemiston attribuutti.
    private Hakemisto juuri;
    private Hakemisto nykyinen;
    private Hakemisto aliHakemisto;

    public Tulkki() {
        juuri = new Hakemisto(new StringBuilder("/"), null);
        nykyinen(juuri);
    }

    public void nykyinen(Hakemisto n) {
        if (n != null) {
            nykyinen = n;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    public Hakemisto nykyinen() {
        return nykyinen;
    }

    public Hakemisto aliHakemisto() {
        return aliHakemisto;
    }

   

    

    public void md(String nimi) {
         nykyinen = new Hakemisto(new StringBuilder(nimi), nykyinen());

    }

    public void cd(String nimi) {
        if (nimi.equals(ylos)) {
            nykyinen = nykyinen.ylihakemisto();
        } else if (nimi.equals(aliHakemisto)) {
            nykyinen = aliHakemisto();
        } else if (nimi.length() == 0) {
            nykyinen = juuri;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    public void mf(String nimi, int koko) {
        Tiedosto tiedosto = new Tiedosto(new StringBuilder(nimi), koko);
        nykyinen.lisaa(tiedosto);
    }

    public void rm(String nimi) {
        nykyinen.poista(nimi);
    }

    public void mv(String vanhaNimi, String uusiNimi) {
        if ((nykyinen.hae(vanhaNimi)) != null || nykyinen.hae(uusiNimi) == null) {
            nykyinen.hae(vanhaNimi).tiedonNimi(new StringBuilder(uusiNimi));
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    public void ls() {
        for (int i = 0; i < nykyinen.sisalto().koko(); i++) {
            System.out.println(nykyinen.sisalto().alkio(i));
        }
    }

    public void ls(String nimi) {
        if (nykyinen.hae(nimi) != null) {
            System.out.println(nykyinen.hae(nimi));
        }
        for (int i = 0; i < nykyinen.sisalto().koko(); i++) {
            System.out.println(nykyinen.sisalto().alkio(i));
        }
    }

    public void cp(String vanhaNimi, String uusiNimi) {
        if ((nykyinen.hae(vanhaNimi)) != null || nykyinen.hae(uusiNimi) == null) {
            Tiedosto kopio = new Tiedosto((Tiedosto) nykyinen.hae(vanhaNimi));
            kopio.tiedonNimi(new StringBuilder(uusiNimi));
        }
    }

    public void find(Hakemisto rekListattava) {

    }

    public boolean exit(boolean b) {
        if (b) {
            return true;
        }
        return false;
    }

    // Metodi puun tulostamiselle.
   // public Hakemisto puunTulostus(Hakemisto hakemisto) {

    // {
}
