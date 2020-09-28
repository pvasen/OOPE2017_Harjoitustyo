package oope2017ht;

import apulaiset.In;

/**
 * Käyttöliittymä-luokka huolehtii käyttäjän ja tietokoneen välisestä
 * vuorovaikutuksesta.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 * <p>
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi Luonnontieteiden
 * tiedekunta, Tampereen yliopisto.
 */
public class Kayttoliittyma {

    // Tulosteet vakioidaan.
    final String md = "md";
    final String cd = "cd";
    final String rm = "rm";
    final String mf = "mf";
    final String mv = "mv";
    final String ls = "ls";
    final String cp = "cp";
    final String find = "find";
    final String exit = "exit";
    final String virhe = "Error!";
    private Tulkki tulkki;

    /**
     * Oletusrakentaja, missä luodaan Tulkki ja toteutetaan ohjelman
     * käynnistämistä tekevä metodi.
     */
    public Kayttoliittyma() {
        tulkki = new Tulkki();
        ohjelmanKaynnistys();
    }

    /**
     * Ohjelman pääsilmukka.
     */
    public void ohjelmanKaynnistys() {
        String syote = "";
        System.out.println("Welcome to SOS.");
        String[] komennot = new String[3];
        do {
            try {
                System.out.print(tulkki.polunTulostus(tulkki.nykyinen()) + ">");
                syote = In.readString();
                komennot = jako(syote);

                if (komennot[0].equals(md)) {
                    tulkki.md(komennot[1]);
                } else if (komennot[0].equals(cd)) {
                    if (komennot[1] != null) {
                        tulkki.cd(komennot[1]);
                    } else {
                        tulkki.cd();
                    }
                } else if (komennot[0].equals(rm)) {
                    tulkki.rm(komennot[1]);

                } else if (komennot[0].equals(mf)) {
                    int koko = Integer.parseInt(komennot[2]);
                    tulkki.mf(komennot[1], koko);
                } else if (komennot[0].equals(mv)) {
                    tulkki.mv(komennot[1], komennot[2]);
                } else if (komennot[0].equals(ls)) {
                    if (komennot[1] != null) {
                        tulkki.ls(komennot[1]);
                    } else {
                        tulkki.ls();
                    }
                } else if (komennot[0].equals(cp)) {
                    tulkki.cp(komennot[1], komennot[2]);
                } else if (komennot[0].equals(find)) {
                    tulkki.find();

                } else if (komennot[0].equals(exit)) {
                    lopetus();

                } else {
                System.out.println("Error!");
            }
            } catch (Exception e) {
                System.out.println(virhe);
            }
        } while (!syote.equals(exit));

    }

    /**
     * Jaetaan syöte osiin taulukkoon
     *
     * @param taulukoon
     * @return jakoTaulukko
     */
    public String[] jako(String taulukoon) {
        String[] jakoTaulukko = new String[3];
        String komento = "";
        String nimi = "";
        String koko = "";
        int k = 0;
        int maara = 0;
        for (int i = 0; i < taulukoon.length(); i++) {
            if (taulukoon.charAt(i) == ' ') {
                komento = taulukoon.substring(k, i);
                jakoTaulukko[maara] = komento;
                maara++;
                k = i + 1;
            } else if (i == taulukoon.length() - 1) {
                komento = taulukoon.substring(k, i + 1);
                jakoTaulukko[maara] = komento;
            }
        }
        return jakoTaulukko;
    }

    // Metodi ohjelman lopetukselle.
    public static void lopetus() {
        System.out.println("Shell terminated.");
    }

    // Metodi virheelle.
    public static void virhe() {
        System.out.println("Error!");
    }
}
