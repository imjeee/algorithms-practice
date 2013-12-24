package BlackJack;
import java.util.ArrayList;

public class CardDealerOperations {
    
    private String[] suits;
    private String[] numbers;
    
    public CardDealerOperations(String[] suits, String[] numbers) {
        this.suits = suits;
        this.numbers = numbers;
    }
    
    public ArrayList<Card> buildDecks(int numberOfDecks) {
        ArrayList<Card> newDeck = new ArrayList<Card>();
        boolean preShuffled = false;
        for (int i = 0; i < numberOfDecks; i++) {
            newDeck.addAll(buildSingleDeck(preShuffled));
        }
        shuffleDeck(newDeck);
        return newDeck;
    }
    
    public ArrayList<Card> buildSingleDeck(boolean shuffle) {
        ArrayList<Card> newDeck = new ArrayList<Card>();
        for (int i = 0; i < this.suits.length; i++) {
            for (int j = 0; j < this.numbers.length; j++) {
                newDeck.add(new Card(this.suits[i], this.numbers[j]));
            }
        }
        if (shuffle)
            shuffleDeck(newDeck);
        return newDeck;
    }
    
    public void shuffleDeck(ArrayList<Card> deck) {
        int random = (int) (Math.random() * 51 + 1);
        for (int i = 0; i < deck.size(); i++) {
            swapCards(deck, i, random);
            random = (int) (Math.random() * 51 + 1);
        }
    }
    
    private void swapCards(ArrayList<Card> deck, int pos1, int pos2) {
        Card card1 = new Card(deck.get(pos1));
        deck.set(pos1, deck.get(pos2));
        deck.set(pos2, card1);
    }
}
