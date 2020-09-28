package oope2017ht.tiedot;

import oope2017ht.omalista.OmaLista;
import apulaiset.Komennettava;
import fi.uta.csjola.oope.lista.LinkitettyLista;

/**
 * Hakemisto-luokka on Tieto-luokasta peritty konkreettinen aliluokka, joka
 * sisältää viitteet hakemiston sisältämiin tiedostoihin ja muihin
 * hakemistoihin.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 * <p>
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi Luonnontieteiden
 * tiedekunta, Tampereen yliopisto.
 */
public class Hakemisto extends Tieto implements Komennettava<Tieto> {

    // Attribuutit.
    public char jakoviiva = '/';
    private OmaLista sisalto;
    private Hakemisto ylihakemisto;

    // Kaksiparametrinen konstruktori.
    public Hakemisto(StringBuilder nimi, Hakemisto viite) throws IllegalArgumentException {
        super(nimi);
        if (viite == null) {
            ylihakemisto = null;
            sisalto = new OmaLista();
        } else if (viite instanceof Hakemisto) {
            ylihakemisto = viite;
            sisalto = new OmaLista();
        } else {
            throw new IllegalArgumentException("Error!");
        }

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
     * @param haettavaNimi haettavan tiedon nimi.
     * @return viite löydettyyn tietoon. Paluuarvo on null, jos tietoa ei
     * löydetä.
     */
    @Override
    public Tieto hae(String haettavaNimi) {
        // Tarkastetaan onko nimi null ja luodaan sitten väliakainen tiedosto.
        if (haettavaNimi != null) {
            Tiedosto valiAikainen = valiaikainenTiedosto(haettavaNimi);
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
     * @param poistettavaNimi poistettavan tiedon nimi.
     * @return viite poistettuun tietoon. Paluuarvo on null, jos tietoa ei
     * löydetä.
     */
    @Override
    public Tieto poista(String poistettavaNimi) {
        // Tarkastetaan onko nimi null ja luodaan sitten väliakainen tiedosto.
        if (poistettavaNimi != null) {
            Tiedosto koe2 = valiaikainenTiedosto(poistettavaNimi);
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
    
    public String polunTulostus() {
        String polku = "";
        String apuPolku = "";
        while (polunNimi != null) {
            apuPolku = polunNimi.toString();
            polku = apuPolku + polku;
            polunNimi = polunNimi.ylihakemisto();
        }
        return polku;
    }



    /**
     * Luo väliaikaisen tiedoston hakua varten.
     *
     * @param valiaikaisenTiedostonNimi, haettavan tiedoston nimi.
     * @return valiAikainen, viite haettevaan alkioon.
     */
    public Tiedosto valiaikainenTiedosto(String valiaikaisenTiedostonNimi) {
        StringBuilder sb = new StringBuilder(valiaikaisenTiedostonNimi);
        Tiedosto valiAikainen = new Tiedosto(sb, 42);
        return valiAikainen;
    }

    // Object-luokan toString -metodin korvaus.
    @Override
    public String toString() {
        if (this.ylihakemisto != null) {
            return super.toString() + jakoviiva;
        } else {
            return super.toString();

        }

    }
}
