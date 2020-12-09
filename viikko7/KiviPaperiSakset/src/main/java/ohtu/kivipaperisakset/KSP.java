
package ohtu.kivipaperisakset;


public class KSP {
    
    private final Peli peli;
    
    public static KSP pelaajaVsPelaaja() {
        return new KSP(new KSPPelaajaVsPelaaja());
    }
    
    public static KSP tekoaly() {
        return new KSP(new KSPTekoaly());
    }
    
    public static KSP parempiTekoaly(int muisti) {
        return new KSP(new KSPParempiTekoaly(muisti));
    }
    
    public KSP(Peli peli) {
        this.peli = peli;
    }
    
    public void pelaa() {
        peli.pelaa();
    }
}
