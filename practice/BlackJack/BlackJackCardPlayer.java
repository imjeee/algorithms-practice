package BlackJack;

public class BlackJackCardPlayer extends CardPlayer {
    
    private long bet;
    
    public BlackJackCardPlayer(String name, long money) {
        super(name, money);
    }
    
    public long getBet() {
        return bet;
    }
    
    public void setBet(long bet) {
        this.bet = bet;
    }
    
}
