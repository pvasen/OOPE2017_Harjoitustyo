/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oope2017ht;

import apulaiset.In;

/**
 *
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi
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
    private Tulkki tulkki;

    public Kayttoliittyma() {
        tulkki = new Tulkki();
        ohjelmanKaynnistys();
    }

    /**
     * Metodi aloituksen tuloatamiseen.
     */
    public void ohjelmanKaynnistys() {
        String syote;
        System.out.println("Welcome to SOS.");
        String[] komennot = new String[3];
        do {
            System.out.println(tulkki.nykyinen() + " > ");
            syote = In.readString();
            komennot = jako(syote);
            switch (komennot[0]) {
                case md:
                    tulkki.md(komennot[1]);
                    break;
                case cd:
                    tulkki.cd(komennot[1]);
                    break;
                case rm:
                    tulkki.rm(komennot[1]);
                    break;
                case mf:
                    int koko = Integer.parseInt(komennot[2]);
                    tulkki.mf(komennot[1], koko);
                    break;
                case mv:
                    tulkki.mv(komennot[1], komennot[2]);
                    break;
                case ls:
                    tulkki.ls();
                    break;
                case cp:
                    tulkki.cp(komennot[1], komennot[2]);
                    break;
                case find:
                    System.out.println("Testi.");
                    break;
                default:
                    break;
            }
        } while (!syote.equals(exit));
    }

    public String[] jako(String t) {
        String[] jakoTaulukko = new String[3];
        String komento = "";
        String nimi = "";
        String koko = "";
        int k = 0;
        int maara = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == ' ') {
                komento = t.substring(k, i);
                jakoTaulukko[maara] = komento;
                maara++;
            }
        }
        return jakoTaulukko;
    }

    /**
     * Metodi aloituskehoituksen tulostamiseen.
     */
    public static void kehoiteAluksi() {
        System.out.println("/>");
    }

    /**
     * Metodi virheen tulostamiseen.
     */
    public static void virhe() {
        System.out.println("Error!");
    }

    public static void lopetus() {
        System.out.println("Shell terminated.");
    }
}
