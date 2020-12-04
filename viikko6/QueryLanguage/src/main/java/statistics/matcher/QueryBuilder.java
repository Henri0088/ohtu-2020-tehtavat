
package statistics.matcher;

public class QueryBuilder {
    
    private Matcher m;
    
    public QueryBuilder() {
        this.m = new All();
    }
    
    public QueryBuilder playsIn(String team) {
        this.m = new And(this.m, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int n, String code) {
        this.m = new And(this.m, new HasAtLeast(n, code));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int n, String code) {
        this.m = new And(this.m, new HasFewerThan(n, code));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.m = new Or(m1, m2);
        return this;
    }
    
    public Matcher build() {
        Matcher ready = m;
        this.m = new All();
        return ready;
    }
}
