package laskin;

import java.util.HashMap;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    
    private Komento edellinen = null;
    private Button undo;
    private Sovelluslogiikka sovellus;
    private HashMap<Button, Komento> komennot;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Plus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(miinus, new Miinus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));
    }
    
    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = this.komennot.get((Button) event.getTarget());
            komento.suorita();
            this.edellinen = komento;
            undo.disableProperty().set(false);
        } else {
            edellinen.peru();
            edellinen = null;
            undo.disableProperty().set(true);
        }
        
       
    }

}
