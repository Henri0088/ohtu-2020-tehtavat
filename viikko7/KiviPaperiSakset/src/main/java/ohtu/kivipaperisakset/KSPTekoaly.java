
package ohtu.kivipaperisakset;

public class KSPTekoaly extends KiviSaksetPaperi implements Peli {
    
    private int siirto;
    
    public KSPTekoaly() {
        this.siirto = 0;
    }
    
    @Override
    public String annaSiirto() {
        siirto++;
        int vastaus = siirto % 3;
        
        String valine;
        switch (vastaus) {
            case 0:
                valine = "k";
                break;
            case 1:
                valine = "s";
                break;
            default:
                valine = "p";
                break;
        }
        System.out.println("Tietokone valitsi: " + valine);
        return valine;
    }
    
    @Override
    public void asetaSiirto(String s) {
        
    }
    
}
