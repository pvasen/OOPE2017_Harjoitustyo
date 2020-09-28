/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oope2017ht.omalista;

import apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.*;

/**
 *
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva {

    /**
     * Tutkii onko listalla haettavaa oliota equals-mieless� vastaava alkio,
     * joita oletetaan olevan korkeintaan yksi kappale.
     *
     * Jos parametri liittyy esimerkiksi merkkijonoon "ab" ja listan tietoalkiot
     * ovat [ "AB, "Ab", "aB", "ab" ], palauttaa operaatio viitteen viimeiseen
     * tietoalkioon, koska "ab".equals("ab") == true.
     *
     * @param haettava listalta haettava alkio, jonka luokan tai luokan
     * esivanhemman oletetaan korvanneen Object-luokan equals-metodin.
     * @return viite l�ydettyyn alkioon. Paluuarvo on null, jos vastaavaa
     * alkiota ei l�ydy, parametri on null-arvoinen tai lista on tyhj�.
     */
    @Override
    public Object hae(Object haettava) {
        // Tarkistetaan, onko lista tyhjä. Null, jos tyhjä.
        if (super.onkoTyhja()) {
            return null;
        }
        Object o = null;
        // Käydään listaa lävitse for-loopin avulla, kunnes haettava alkio
        // löydetään.
        for (int i = 0; i < super.koko(); i++) {
            if (super.alkio(i).equals(haettava)) {
                o = super.alkio(i);
            }
        }
        return o;
    }

    /**
     * Listan alkioiden v�lille muodostuu kasvava suuruusj�rjestys, jos lis�ys
     * tehd��n vain t�ll� operaatiolla, koska uusi alkion lis�t��n listalle
     * siten, ett� alkio sijoittuu kaikkien itse��n pienempien tai yht� suurien
     * alkioiden j�lkeen ja ennen kaikkia itse��n suurempia alkioita. Alkioiden
     * suuruusj�rjestys selvitet��n Comparable-rajapinnan compareTo-metodilla.
     *
     * Jos parametri liittyy esimerkiksi kokonaislukuun 2 ja listan tietoalkiot
     * ovat [ 0, 3 ], on listan sis�lt� lis�yksen j�lkeen [ 0, 2, 3 ], koska 0 <
     * 2 < 3.
     *
     * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
     * toteuttaneen Comparable-rajapinnan.
     * @return true, jos lis�ys onnistui ja false, jos uutta alkiota ei voitu
     * vertailla. Vertailu ep�onnistuu, kun parametri on null-arvoinen tai sill�
     * ei ole vastoin oletuksia Comparable-rajapinnan toteutusta.
     */
    @Override
    public boolean lisaa(Object uusi) {
        // Tarkastetaan aluksi, on ko
        if (uusi == null) {
            return false;
        }
        if (super.onkoTyhja()) {
            lisaaAlkuun(uusi);
            return true;
        } else {
            int k = 0;
            for (int i = 0; i < koko(); i++) {

                Object nykyinen = alkio(i);
                Comparable vertailtava = (Comparable) nykyinen;
                if (vertailtava.compareTo(uusi) > 0) {
                    lisaa(i, uusi);
                    return true;
                } else if (vertailtava.compareTo(uusi) < 0) {

                    k++;
                }
            }
            if (k == koko) {
                lisaaLoppuun(uusi);
                return true;
            }

            if (k > 0) {
                lisaa(k, uusi);
                return true;

            }
            return false;
        }

    }

    /**
     * Poistaa listalta annettua oliota equals-mieless� vastaavan alkion, joita
     * oletetaan olevan korkeintaan yksi kappale.
     *
     * Jos parametri liittyy esimerkiksi merkkijonoon "aB" ja listan tietoalkiot
     * ovat [ "AB, "Ab", "aB", "ab" ], on listan sis�lt� poiston j�lkeen [ "AB,
     * "Ab", "ab" ] ja operaatio palauttaa viitteen alkuper�isen listan
     * kolmanteen tietoalkioon, koska "aB".equals("aB") == true.
     *
     * @param poistettava viite poistettavaan tietoalkioon, jonka luokan tai
     * luokan esivanhemman oletetaan korvanneen Object-luokan equals-metodin.
     * @return viite poistettuun tietoalkioon. Paluuarvo on null, jos
     * poistettavaa alkiota ei l�ydy, parametri on null-arvoinen tai lista on
     * tyhj�.
     */
    @Override
    public Object poista(Object poistettava) {
        if (super.onkoTyhja() | poistettava == null) {
            return null;
        }
        Object o = null;
        for (int i = 0; i < super.koko(); i++) {
            if (super.alkio(i).equals(poistettava)) {
                o = super.poista(i);
            }
        }
        return o;
    }
}
