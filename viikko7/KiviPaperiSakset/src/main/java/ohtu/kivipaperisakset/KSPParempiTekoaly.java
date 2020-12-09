
package ohtu.kivipaperisakset;


public class KSPParempiTekoaly extends KiviSaksetPaperi implements Peli {
    
    private String[] muisti;
    private int vapaaIndeksi;
    
    public KSPParempiTekoaly(int muistinKoko) {
        this.muisti = new String[muistinKoko];
        this.vapaaIndeksi = 0;
    }
    
    @Override
    public String annaSiirto() {
        if (vapaaIndeksi < 2) {
            return "k";
        }
        
        int k = 0, s = 0, p = 0;
        String viimeisinSiirto = muisti[vapaaIndeksi - 1];
        
        for (int i = 0; i < vapaaIndeksi - 1; i++) {
            if (muisti[i].equals(viimeisinSiirto)) {
                switch (muisti[i + 1]) {
                    case "k":
                        k++;
                        break;
                    case "s":
                        s++;
                        break;
                    case "p":
                        p++;
                        break;
                }
            }
        }
        
        String vastaus;
        if (k > p && k > s) {
            vastaus = "p";
        } else if (p > k && p > s) {
            vastaus = "s";
        } else {
            vastaus = "k";
        }
        System.out.println("Tietokone valitsi: " + vastaus);
        return vastaus;
    }
    
    @Override
    public void asetaSiirto(String siirto) {
        if (vapaaIndeksi == muisti.length) {
            for (int i = 1; i < muisti.length; i++) {
                muisti[i - 1] = muisti[i];
            }
            vapaaIndeksi--;
        }
        muisti[vapaaIndeksi] = siirto;
        vapaaIndeksi++;
    }
}
