
package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            
            players.add(new Player("Simo", "EDM", 4, 12));
            players.add(new Player("Timo", "PIT", 7, 23));
            players.add(new Player("Petteri", "EDM", 20, 12));
            players.add(new Player("Pekkinen", "DET", 100, 100));
            players.add(new Player("Henkkis", "EDM", 1100, 12));
            return players;
        }
    };
    
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void truePlayerSearch() {
        Player plr = stats.search("Simo");
        
        assertEquals("Simo", plr.getName());
        assertEquals("EDM", plr.getTeam());
        assertEquals(4, plr.getGoals());
        assertEquals(12, plr.getAssists());
    }
    
    @Test
    public void falsePlayerSearch() {
        Player plr = stats.search("Lauri");
        assertNull(plr);
    }
    
    @Test
    public void trueTeamPlayers() {
        List<Player> plrTeam = stats.team("EDM");
        
        List<Player> testTeam = new ArrayList<>();
        testTeam.add(new Player("Simo", "EDM", 4, 12));
        testTeam.add(new Player("Petteri", "EDM", 20, 12));
        testTeam.add(new Player("Henkkis", "EDM", 1100, 12));
        
        assertEquals(testTeam, plrTeam);
    }
    
    @Test
    public void falseTeamPlayers() {
        List<Player> plrTeam = stats.team("EDM");
        
        List<Player> testTeam = new ArrayList<>();
        testTeam.add(new Player("Simo", "EDM", 4, 12));
        testTeam.add(new Player("Petteri", "EDM", 20, 12));
        
        assertNotEquals(testTeam, plrTeam);
    }
    
    @Test
    public void correctTop3Scorers() {
        List<Player> topPlr = stats.topScorers(2);
        
        List<Player> testTopPlr = new ArrayList<>();
        testTopPlr.add(new Player("Henkkis", "EDM", 1100, 12));
        testTopPlr.add(new Player("Pekkinen", "DET", 100, 100));
        testTopPlr.add(new Player("Petteri", "EDM", 20, 12));
        
        assertEquals(testTopPlr, topPlr);
    }
}
