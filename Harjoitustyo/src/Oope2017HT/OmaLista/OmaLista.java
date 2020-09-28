package oope2017ht.omalista;

import apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.*;

/**
 * OmaLista-luokka on harjoitustyön pääasiallinen tietorakenne.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 * <p>
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi Luonnontieteiden
 * tiedekunta, Tampereen yliopisto.
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
     * @param lisattava viite olioon, jonka luokan tai luokan esivanhemman
     * oletetaan toteuttaneen Comparable-rajapinnan.
     * @return true, jos lis�ys onnistui ja false, jos uutta alkiota ei voitu
     * vertailla. Vertailu ep�onnistuu, kun parametri on null-arvoinen tai sill�
     * ei ole vastoin oletuksia Comparable-rajapinnan toteutusta.
     */
    @Override
    public boolean lisaa(Object lisattava) {
        // Tarkastetaan aluksi, on lisättavä tyhjä.
        if (lisattava == null) {
            return false;
        }
        // Jos tyhjä, lisätään alkuun ja palautetaan true.
        if (super.onkoTyhja()) {
            lisaaAlkuun(lisattava);
            return true;
        } else {
            // Käydään listaa lävitse for-loopin avulla ja luodaan avuksi apumuuttuja.
            int k = 0;
            for (int i = 0; i < koko(); i++) {

                Object nykyinen = alkio(i);
                Comparable vertailtava = (Comparable) nykyinen;
                if (vertailtava.compareTo(lisattava) > 0) {
                    lisaa(i, lisattava);
                    return true;
                } else if (vertailtava.compareTo(lisattava) < 0) {

                    k++;
                }
            }
            // Jos k = koko, palautetaan true ja lisätään loppuun.
            if (k == koko) {
                lisaaLoppuun(lisattava);
                return true;
            }
            // Jos k > 0, lisätään viite k ja lisattava.
            if (k > 0) {
                lisaa(k, lisattava);
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
        // Tarkastetaan, onko tyhjä.
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
