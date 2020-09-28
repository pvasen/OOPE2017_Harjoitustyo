/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oope2017ht.tiedot;

import oope2017ht.omalista.OmaLista;
import apulaiset.Komennettava;
import fi.uta.csjola.oope.lista.LinkitettyLista;

/**
 *
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi
 */
public class Hakemisto extends Tieto implements Komennettava<Tieto> {

    // Attribuutit.
    private OmaLista sisalto;
    private Hakemisto ylihakemisto;

    // Kaksiparametrinen konstruktori.
    public Hakemisto(StringBuilder n, Hakemisto viite) throws IllegalArgumentException {
        super(n);
        ylihakemisto = viite;
        sisalto = new OmaLista();

    }

    // Aksessorit.
    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    public LinkitettyLista sisalto() {
        return sisalto;
    }

    /**
     * Hakee hakemistosta tiedostoa tai alihakemistoa. Hyödyntää OmaLista-luokan
     * hae-operaatiota. Huomaa, että nimeä käyttäen haun avuksi voidaan luoda
     * väliaikainen tiedosto tai hakemisto.
     *
     * @param nimi haettavan tiedon nimi.
     * @return viite löydettyyn tietoon. Paluuarvo on null, jos tietoa ei
     * löydetä.
     */
    @Override
    public Tieto hae(String nimi) {
        // Tarkastetaan onko nimi null ja luodaan sitten väliakainen tiedosto.
        if (nimi != null) {
            Tiedosto valiAikainen = valiaikainenTiedosto(nimi);
            // Käydään läpi for-loopin avulla sisaltoa.
            for (int i = 0; i < sisalto.koko(); i++) {
                if (sisalto.alkio(i).equals(valiAikainen)) {
                    Tieto paluuArvo = (Tieto) sisalto.alkio(i);
                    return paluuArvo;
                }
            }
        }

        return null;

    }

    /**
     * Lisää hakemistoon tiedoston tai alihakemiston. Hyödyntää OmaLista-luokan
     * lisaa-operaatiota.
     *
     * @param lisattava viite lisättävään tietoon.
     * @return true, jos lisääminen onnistui ja false, jos tieto on
     * null-arvoinen tai hakemistossa on jo tieto parametrina annetulla nimellä.
     */
    @Override
    public boolean lisaa(Tieto lisattava) {
        for (int i = 0; i < sisalto.koko(); i++) {
            if (sisalto.alkio(i).equals(lisattava)) {
                return false;
            }
        }
        boolean onnistuuko = sisalto.lisaa(lisattava);
        return onnistuuko;
    }

    /**
     * Poistaa hakemistosta tiedoston tai alihakemiston. Hyödyntää
     * OmaLista-luokan poista-operaatiota. Huomaa, että nimeä käyttäen poiston
     * avuksi voidaan luoda väliaikainen tiedosto tai hakemisto.
     *
     * @param nimi poistettavan tiedon nimi.
     * @return viite poistettuun tietoon. Paluuarvo on null, jos tietoa ei
     * löydetä.
     */
    @Override
    public Tieto poista(String nimi) {
        // Tarkastetaan onko nimi null ja luodaan sitten väliakainen tiedosto.
        if (nimi != null) {
            Tiedosto koe2 = valiaikainenTiedosto(nimi);
            // Käydään läpi for-loopin avulla sisaltoa.
            for (int i = 0; i < sisalto.koko(); i++) {
                if (sisalto.alkio(i).equals(koe2)) {
                    Tieto poistettava = (Tieto) sisalto.poista(i);

                    return poistettava;
                }
            }

        }
        return null;

    }

    /**
     * Luo väliaikaisen tiedoston hakua varten.
     *
     * @param nimi, haettavan tiedoston nimi.
     * @return valiAikainen, viite haettevaan alkioon.
     */
    public Tiedosto valiaikainenTiedosto(String nimi) {
        StringBuilder sb = new StringBuilder(nimi);
        Tiedosto valiAikainen = new Tiedosto(sb, 42);
        return valiAikainen;
    }

    // Object-luokan toString -metodin korvaus.
    @Override
    public String toString() {
        return super.toString() + " " +  sisalto.koko();
    }

}
