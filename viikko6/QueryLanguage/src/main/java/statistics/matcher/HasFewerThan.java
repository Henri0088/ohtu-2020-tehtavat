
package statistics.matcher;

import statistics.Player;


public class HasFewerThan implements Matcher {
    
    private int value;
    private String category;
    
    public HasFewerThan(int value, String category) {
        this.value = value;
        this.category = category;
    }
    
    public boolean matches(Player p) {
        Matcher m = new Not(new HasAtLeast(value, category));
        return m.matches(p);
    }
}
