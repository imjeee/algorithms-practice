package BlackJack;

import java.util.ArrayList;

public class CardPlayer {
    private String name;
    private long money;
    private ArrayList<Card> cardsInHand;
    private boolean dealer;
    
    public CardPlayer(String name, long money) {
        this.setName(name);
        this.setMoney(money);
        this.cardsInHand = new ArrayList<Card>();
        this.dealer = false;
    }
    
    public void addCard(Card card) {
        this.cardsInHand.add(card);
    }
    
    public void addMoney(long money) {
        this.money += money;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getMoney() {
        return money;
    }
    
    public void setMoney(long money) {
        this.money = money;
    }
    
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
    
    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }
    
    public boolean isDealer() {
        return dealer;
    }
    
    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }
}
