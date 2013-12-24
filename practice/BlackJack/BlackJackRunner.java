package BlackJack;

public class BlackJackRunner {
    
    private static String[] playerNames = { "jie" }; // , "kate", "chris", "tif" };
    
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame(playerNames, 1);
        game.play();
    }
}
