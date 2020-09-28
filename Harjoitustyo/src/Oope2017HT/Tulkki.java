package oope2017ht;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.tiedot.*;

/**
 * Tulkki-luokka on vastaa ohjelman logiikasta.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 * <p>
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi Luonnontieteiden
 * tiedekunta, Tampereen yliopisto.
 */
public class Tulkki {

    // Ylöspäin siirtymisen attribuutti.
    final String ylos = "..";
    // Juurihakemiston attribuutti.
    private Hakemisto juuri;
    private Hakemisto nykyinen;
    private Hakemisto aliHakemisto;

    /**
     * Oletusrakentaja.
     */
    public Tulkki() {
        juuri = new Hakemisto(new StringBuilder("/"), null);
        nykyHakemisto(juuri);
    }

    /**
     * Nykyisen hakemiston nimeä osoittava rakentaja.
     *
     * @param nimi
     */
    public void nykyHakemisto(Hakemisto nimi) {
        if (nimi != null) {
            nykyinen = nimi;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    /**
     * Aksessori.
     *
     * @return nykyinen
     */
    public Hakemisto nykyinen() {
        return nykyinen;
    }

    /**
     * Aksessori.
     *
     * @return juuri
     */
    public Hakemisto juuri() {
        return juuri;
    }

    /**
     * Aksessori.
     *
     * @return alihakemisto
     */
    public Hakemisto aliHakemisto() {
        return aliHakemisto;
    }

    /**
     * Uuden alihakemiston luova metodi.
     *
     * @param nimi, joka osoittaa luotavan alihakemiston nimen.
     */
    public void md(String nimi) {
        Hakemisto hakemisto = new Hakemisto(new StringBuilder(nimi), nykyinen());
        if (nykyinen.lisaa(hakemisto)) {
            nykyinen.lisaa(hakemisto);
        } else {
            throw new IllegalArgumentException("Error!");
        }

    }

    /**
     * Hakemiston vaihtamista osoittava metodi.
     *
     * @param nimi, vaihdettavan hakemiston nimi.
     */
    public void cd(String nimi) {
        if (nimi.equals(ylos) && nykyinen.ylihakemisto() != null) {
            nykyinen = nykyinen.ylihakemisto();
        } else if (nykyinen.hae(nimi) != null && nykyinen.hae(nimi) instanceof Hakemisto) {
            nykyinen = (Hakemisto) nykyinen.hae(nimi);
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    /**
     * Hakemiston vaihtamisen kuormitusmetodi.
     */
    public void cd() {
        nykyinen = juuri;
    }

    /**
     * Tiedoston luomista toteuttava metodi.
     *
     * @param nimi, luotavan tiedoston nimi.
     * @param koko, luotavan tiedoston koko.
     */
    public void mf(String nimi, int koko) {
        Tiedosto tiedosto = new Tiedosto(new StringBuilder(nimi), koko);
        nykyinen.lisaa(tiedosto);
    }

    /**
     * Tiedoston poistamista osoittava metodi.
     *
     * @param nimi, poistettavan metodin nimi.
     */
    public void rm(String nimi) {
        nykyinen.poista(nimi);
    }

    /**
     * Tiedoston uudelleennimeämistä varten toteutettu metodi.
     *
     * @param vanhaNimi, tiedoston vanha nimi.
     * @param uusiNimi, uusi nimi uudelleennimeämisen jälkeen.
     */
    public void mv(String vanhaNimi, String uusiNimi) {
        if ((nykyinen.hae(vanhaNimi)) != null || nykyinen.hae(uusiNimi) == null) {
            Tieto nimettava = nykyinen.hae(vanhaNimi);
            nimettava.tiedonNimi(new StringBuilder(uusiNimi));
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    /**
     * Metodi hakemiston listaamista varten.
     */
    public void ls() {
        for (int i = 0; i < nykyinen.sisalto().koko(); i++) {
            if (nykyinen.sisalto().alkio(i) instanceof Hakemisto) {
                Hakemisto listattava = (Hakemisto) nykyinen.sisalto().alkio(i);
                System.out.println(listattava + " " + listattava.sisalto().koko());
            } else {
                System.out.println(nykyinen.sisalto().alkio(i));
            }
        }
    }

    /**
     * Kuormittava metodi hakemiston listaamista varten.
     *
     * @param nimi , listattavan hakemiston nimi.
     */
    public void ls(String nimi) {
        if (nykyinen.hae(nimi) != null) {
            System.out.println(nykyinen.hae(nimi));
        } else {
            throw new IllegalArgumentException("Error!");

        }
    }

    /**
     * Metodi tiedoston kopiointia varten.
     *
     * @param vanhaNimi, aiempi tiedoston nimi.
     * @param uusiNimi, tiedoston nimi kopioinnin jälkeen.
     */
    public void cp(String vanhaNimi, String uusiNimi) {
        if ((nykyinen.hae(vanhaNimi)) != null && nykyinen.hae(uusiNimi) == null) {
            Tiedosto kopioitava = new Tiedosto((Tiedosto) nykyinen.hae(vanhaNimi));
            kopioitava.tiedonNimi(new StringBuilder(uusiNimi));
            nykyinen.lisaa(kopioitava);
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    /**
     * Metodi hakemiston liistaamiseksi rekursiivisesti.
     */
    public void find() {
        puunTulostus(nykyinen);
    }

    public boolean exit(boolean b) {
        if (b) {
            return true;
        }
        return false;
    }

    /**
     * Metodi puun listaamiseksi rekursiivisesti.
     *
     * @param hakemisto listaamista varten.
     */
    private void puunTulostus(Hakemisto hakemisto) {
        if (hakemisto != null) {
            LinkitettyLista sisalto = hakemisto.sisalto();
            String polku = (hakemisto.polunTulostus(sisalto));

            for (int i = 0; i < sisalto.koko(); i++) {
                Tieto alkio = (Tieto) sisalto.alkio(i);

                System.out.println(polku + "" + alkio);

                if (alkio instanceof Hakemisto) {
                    System.out.println(alkio);
                }
            }
        }
        // public void puunTulostus(Hakemisto hakemisto) {
        // if (hakemisto != null) {
        // Asetetaan apuviite hakemiston tiedot säilövään listaan.
        //  LinkitettyLista sisalto = hakemisto.sisalto();

        // Käydään hakemiston sisältö läpi yksi tieto kerrallaan.
        // int i = 0;
        //      if (i < sisalto.koko()) {
        // Tulostetaan tiedon merkkijonoesitys.
        //     Tieto tieto = (Tieto) sisalto.alkio(i);
        //       System.out.println(tieto);
        // Tulostetaan alihakemiston sisältö.
        //        if (tieto instanceof Hakemisto) {
        //        puunTulostus((Hakemisto) tieto);
        //         }
        //          i++;
        //       }
        //  }
        //  }
        /**
         * Metodi sen hetkistä polun tulostamista varten.
         *
         * @param polunNimi
         * @return polku
         */
    }

    public String polunTulostus(Hakemisto polunNimi) {
        String polku = "";
        String apuPolku = "";
        while (polunNimi != null) {
            apuPolku = polunNimi.toString();
            polku = apuPolku + polku;
            polunNimi = polunNimi.ylihakemisto();
        }
        return polku;
    }

}
