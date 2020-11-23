package ohtu;

public class TennisGame {
    
    private int score1 = 0;
    private int score2 = 0;
    private String player1;
    private String player2;
    private String[][] scoreNames;

    public TennisGame(String name1, String name2) {
        this.player1 = name1;
        this.player2 = name2;
        this.scoreNames = getGameStates();
    }
    
    public void wonPoint(String name) {
        if (name.equals(player1)) {
            score1 += 1;
        } else if (name.equals(player2)) {
            score2 += 1;
        }
    }
    
    public String getScore() {
        return scoreNames[score1][score2];
    }
    
    public String[][] getGameStates() {
        String[][] gameStates = new String[17][17];
        
        gameStates[0][0] = "Love-All";
        gameStates[1][1] = "Fifteen-All";
        gameStates[2][2] = "Thirty-All";
        gameStates[3][3] = "Forty-All";
        gameStates[4][4] = "Deuce";
        
        gameStates[1][0] = "Fifteen-Love";
        gameStates[0][1] = "Love-Fifteen";
        gameStates[2][0] = "Thirty-Love";
        gameStates[0][2] = "Love-Thirty";
        gameStates[3][0] = "Forty-Love";
        gameStates[0][3] = "Love-Forty";
        gameStates[4][0] = "Win for player1";
        gameStates[0][4] = "Win for player2";
        
        gameStates[2][1] = "Thirty-Fifteen";
        gameStates[1][2] = "Fifteen-Thirty";
        gameStates[3][1] = "Forty-Fifteen";
        gameStates[1][3] = "Fifteen-Forty";
        gameStates[4][1] = "Win for player1";
        gameStates[1][4] = "Win for player2";
        
        gameStates[3][2] = "Forty-Thirty";
        gameStates[2][3] = "Thirty-Forty";
        gameStates[4][2] = "Win for player1";
        gameStates[2][4] = "Win for player2";
        
        gameStates[4][3] = "Advantage player1";
        gameStates[3][4] = "Advantage player2";
        gameStates[5][4] = "Advantage player1";
        gameStates[4][5] = "Advantage player2";
        gameStates[15][14] = "Advantage player1";
        gameStates[14][15] = "Advantage player2";
        
        gameStates[6][4] = "Win for player1";
        gameStates[4][6] = "Win for player2";
        gameStates[16][14] = "Win for player1";
        gameStates[14][16] = "Win for player2";
        
        return gameStates;
    }
}