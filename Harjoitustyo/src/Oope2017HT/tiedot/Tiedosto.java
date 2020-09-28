package oope2017ht.tiedot;

/**
 * Tiedosto-luokka on Tieto-luokasta peritty konkreettinen aliluokka, joka
 * sisältää tiedon piirteitä.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 * <p>
 * @author Petrus Vasenius, Vasenius.Petrus.S@student.uta.fi Luonnontieteiden
 * tiedekunta, Tampereen yliopisto.
 */
public class Tiedosto extends Tieto {

    // Attribuutti koolle.
    private int koko;

    // Rakentajametodi.
    public Tiedosto(StringBuilder nimi, int koko) throws IllegalArgumentException {
        super(nimi);
        koko(koko);

    }

    /**
     * Kopiorakentaja, jossa tiedoston nimi syväkopioidaan.
     *
     * @param kopioitava
     * @throws IllegalArgumentException
     */
    public Tiedosto(Tiedosto kopioitava) throws IllegalArgumentException {
        super(kopioitava);
        if (kopioitava instanceof Tieto) {
            koko = kopioitava.koko();
        } else {
            throw new IllegalArgumentException("Rakentaminen epäonnistui!");
        }
    }

    // Asetusmetodi.
    public int koko() {
        return koko;
    }

    /**
     * Hakumetodi.
     *
     * @param haettavaKoko
     */
    public void koko(int haettavaKoko) {
        if (haettavaKoko > 0) {
            koko = haettavaKoko;
        } else {
            throw new IllegalArgumentException("Error!");
        }
    }

    /**
     * Korvataan toString metodi.
     */
    public String toString() {
        return super.toString() + " " + koko;
    }

}
