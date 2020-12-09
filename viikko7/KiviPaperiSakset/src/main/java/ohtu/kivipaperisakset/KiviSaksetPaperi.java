
package ohtu.kivipaperisakset;

import java.util.Scanner;


public abstract class KiviSaksetPaperi {
    private static final Scanner lukija = new Scanner(System.in);
    
    public void pelaa() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        Tuomari tuomari = new Tuomari();
        String ekaSiirto = ekaSiirto();
        String tokaSiirto = annaSiirto();
        
        while (onkoOkSiirto(ekaSiirto) && onkoOkSiirto(tokaSiirto)) {
            tuomari.kirjaaSiirto(ekaSiirto, tokaSiirto);
            System.out.println(tuomari + "\n");
            
            ekaSiirto = ekaSiirto();
            tokaSiirto = annaSiirto();
            
            asetaSiirto(ekaSiirto);
        }
        System.out.println("\nKiitos!");
        System.out.println(tuomari);
    }
    
    protected abstract String annaSiirto();
    
    protected abstract void asetaSiirto(String siirto);
    
    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    private static String ekaSiirto() {
        System.out.println("Ensimmäisen pelaajan siirto: ");
        return lukija.nextLine();
    }
    
}
