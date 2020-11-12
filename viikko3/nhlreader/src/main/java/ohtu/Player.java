
package ohtu;

public class Player implements Comparable {
    private String name;
    private String nationality;
    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setNationality(String nat) {
        this.nationality = nat;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setGoals(int goals) {
        this.goals = goals;
    }
    
    public int getGoals() {
        return goals;
    }
    
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    public int getAssists() {
        return assists;
    }

    @Override
    public String toString() {
        return name + " " + goals + " + " + assists + " = " + (goals+assists);
    }
    
    public int compareTo(Object o) {
        if (!(o instanceof Player)) {
            return 0;
        }
        Player p2 = (Player) o;
        return (goals+assists) - (p2.getGoals()+p2.getAssists());
    }
}
