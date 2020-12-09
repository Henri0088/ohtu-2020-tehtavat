
package ohtu.kivipaperisakset;

import java.util.Scanner;


public class KSPPelaajaVsPelaaja extends KiviSaksetPaperi implements Peli {
    private static final Scanner lukija = new Scanner(System.in);
    
    public KSPPelaajaVsPelaaja() {
        
    }
    
    @Override
    public String annaSiirto() {
        System.out.println("Toisen pelaajan siirto: ");
        return lukija.nextLine();
    }
    
    @Override
    public void asetaSiirto(String s) {
        
    }
    
}
