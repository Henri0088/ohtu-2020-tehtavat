package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            
            if (vastaus.equals("a")) {
                KSP ksp = KSP.pelaajaVsPelaaja();
                ksp.pelaa();
            } else if (vastaus.equals("b")) {
                KSP ksp = KSP.tekoaly();
                ksp.pelaa();
            } else if (vastaus.equals("c")) {
                KSP ksp = KSP.parempiTekoaly(20);
                ksp.pelaa();
            } else {
                break;
            }
        }

    }
}
