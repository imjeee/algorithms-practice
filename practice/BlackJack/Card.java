package BlackJack;
public class Card {
    private String number;
    private String suit;
    
    public Card(String suit, String number) {
        this.setNumber(number);
        this.setSuit(suit);
    }
    
    public Card(Card card) {
        this.setSuit(card.getSuit());
        this.setNumber(card.getNumber());
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public void setSuit(String suit) {
        this.suit = suit;
    }
    
}
