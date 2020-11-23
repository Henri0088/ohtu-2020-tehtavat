
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenMaara;    // Tyhjässä joukossa alkioiden_määrä on nolla. 
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kapasiteetin ja kasvatuskoon tulee olla > 0");
        }
        this.joukko = new int[kapasiteetti];
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            joukko[alkioidenMaara] = luku;
            alkioidenMaara++;
            if (alkioidenMaara % joukko.length == 0) {
                laajennaJoukkoa();
            }
            return true;
        }
        return false;
    }
    
    public void laajennaJoukkoa() {
        int[] uusiJoukko = new int[alkioidenMaara + kasvatuskoko];
        for (int i = 0; i < uusiJoukko.length; i++) {
            if (i < alkioidenMaara) {
                uusiJoukko[i] = joukko[i];
            } else {
                uusiJoukko[i] = 0;
            }
        }
        joukko = uusiJoukko;
    }
    
    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }
    
    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == joukko[i]) {
                joukko[i] = 0;
                tiivistaTaulukko(i);
                alkioidenMaara--;
                return true;
            }
        }
        return false;
    }
    
    public void tiivistaTaulukko(int poistettuI) {
        for (int i = poistettuI; i < alkioidenMaara - 1; i++) {
            joukko[i] = joukko[i + 1];
        }
    }

    public int mahtavuus() {
        return alkioidenMaara;
    }
    
    @Override
    public String toString() {
        if (alkioidenMaara == 0) {
            return "{}";
        }
        
        String mj = "{" + joukko[0];
        for (int i = 1; i < alkioidenMaara; i++) {
            mj += ", " + joukko[i];
        }
        return mj += "}";
    }
    
    public int[] toIntArray() {
        int[] taulu = new int[alkioidenMaara];
        System.arraycopy(joukko, 0, taulu, 0, taulu.length);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                    break;
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
        return z;
    }
        
}
