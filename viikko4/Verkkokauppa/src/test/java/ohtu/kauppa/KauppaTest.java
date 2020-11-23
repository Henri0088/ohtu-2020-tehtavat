package ohtu.kauppa;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    private Pankki pankki;
    private Varasto varasto;
    private Viitegeneraattori viite;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        
        when(viite.uusi()).thenReturn(33);
        
        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaatyttyaPankinTilisiirtoaKutsutaanOikein() {
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("tiikeri", "444555");
        
        verify(pankki).tilisiirto(eq("tiikeri"), anyInt(), eq("444555"), anyString(), eq(5));
    }
    
    @Test
    public void monenEriTuotteenOstosTilisiirtoOikein() {
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "tuoremehu", 7));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Pena", "44777");
        
        verify(pankki).tilisiirto(eq("Pena"), anyInt(), eq("44777"), anyString(), eq(12));
    }
    
    @Test
    public void monenSamanTuotteenOstosTilisiirtoOikein() {
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("Timo", "55666");
        
        verify(pankki).tilisiirto(eq("Timo"), anyInt(), eq("55666"), anyString(), eq(10));
    }
    
    @Test
    public void toinenTuoteLoppuTilisiirtoOikein() {
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "tuoremehu", 7));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Simo", "55666");
        
        verify(pankki).tilisiirto(eq("Simo"), anyInt(), eq("55666"), anyString(), eq(5));
    }
    
    @Test
    public void aloitaAsiointiNollaaTiedot() {
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Jarmo", "12345");
        
        verify(pankki).tilisiirto(eq("Jarmo"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void kauppaPyytaaAinaUudenViiteNumeron() {
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        when(viite.uusi()).thenReturn(33).thenReturn(34);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Pena", "123445");
        verify(pankki).tilisiirto(eq("Pena"), eq(33), eq("123445"), anyString(), eq(5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Jeppe", "123123");
        verify(pankki).tilisiirto(eq("Jeppe"), eq(34), eq("123123"), anyString(), eq(5));
    }
    
    @Test
    public void koristaVoiPoistaaTuotteen() {
        Kauppa k = new Kauppa(varasto, pankki, viite);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("Pena", "123123");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(5));
    }
    
}

