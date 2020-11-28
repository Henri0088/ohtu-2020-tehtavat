
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Plus extends Komento {
    private int edellinenArvo;
    
    public Plus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        this.edellinenArvo = 0;
    }
    
    @Override
    public void suorita() {
        int arvo = sovellus.tulos();
        edellinenArvo = arvo;
        sovellus.plus(Integer.parseInt(syotekentta.getText()));
        syotekentta.setText("");
        tuloskentta.setText(String.valueOf(sovellus.tulos()));
        if (nollaa.disableProperty().get()) {
            nollaa.disableProperty().set(false);
        } 
    }
    
    @Override
    public void peru() {
        tuloskentta.setText(String.valueOf(edellinenArvo));
    }
    
}
