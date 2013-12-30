package BlackJack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class BlackJackGame {
    private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
    private static String[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    
    private CardDealerOperations dealerOperations;
    private ArrayList<Card> deck;
    private HashMap<String, BlackJackCardPlayer> players;
    private int numOfDecks;
    
    public BlackJackGame(String[] playerNames, int numberOfDecks) {
        this.numOfDecks = numberOfDecks;
        this.dealerOperations = new CardDealerOperations(suits, numbers);
        this.deck = this.dealerOperations.buildDecks(this.numOfDecks);
        this.players = createPlayersAndDealer(playerNames);
    }
    
    public void play() {
        while (true) {
            playersPutWageDown();
            dealRoundsOfCardToPlayers(2);
            printWhatsOnTheTable();
            playersDecisionRound();
            dealersRound();
            payupAtEndOfRound();
            printHowMuchMoneyPlayersHave();
        }
    }
    
    private void payupAtEndOfRound() {
        Set<String> playerNames = players.keySet();
        int dealerCardsValue = getPlayerCardsTotalValue("dealer");
        
        for (String playerName : playerNames) {
            BlackJackCardPlayer player = players.get(playerName);
            if (!player.isDealer()) {
                int playerCardsValue = getPlayerCardsTotalValue(playerName);
                if (playerCardsValue > 21) {
                    // busted
                } else if (playerCardsValue == 21 || dealerCardsValue > 21 || playerCardsValue > dealerCardsValue) {
                    long playerBet = player.getBet();
                    player.addMoney(playerBet * 2);
                }
            }
            player.setCardsInHand(new ArrayList<Card>());
            player.setBet(0L);
        }
    }
    
    private void printHowMuchMoneyPlayersHave() {
        Set<String> playerNames = players.keySet();
        for (String playerName : playerNames) {
            BlackJackCardPlayer player = players.get(playerName);
            if (!player.isDealer()) {
                printPlayerInfo(playerName);
            }
        }
    }
    
    private void printPlayerInfo(String playerName) {
        BlackJackCardPlayer player = players.get(playerName);
        // ArrayList<Card> playerCardsInHand = player.getCardsInHand();
        long playerMoney = player.getMoney();
        System.out.println(playerName + " has $" + playerMoney);
    }
    
    private void playersPutWageDown() {
        Set<String> playerNames = players.keySet();
        for (String playerName : playerNames) {
            BlackJackCardPlayer player = players.get(playerName);
            if (!player.isDealer()) {
                singlePlayerPutWageDown(playerName);
            }
        }
    }
    
    private void singlePlayerPutWageDown(String playerName) {
        Scanner reader = new Scanner(System.in);
        BlackJackCardPlayer player = players.get(playerName);
        long playerMoney = player.getMoney();
        System.out.println(playerName + "' has $" + playerMoney);
        System.out.println("put number down for wager");
        long wager = reader.nextInt();
        wager = wager < playerMoney ? wager : playerMoney;
        player.setBet(wager);
        player.setMoney(playerMoney - wager);
    }
    
    private void dealersRound() {
        while (getPlayerCardsTotalValue("dealer") < 17) {
            System.out.println("dealer's card total is now at " + getPlayerCardsTotalValue("dealer"));
            dealACardToAPlayer("dealer");
        }
        System.out.println("dealer's card total is " + getPlayerCardsTotalValue("dealer"));
        if (getPlayerCardsTotalValue("dealer") > 21)
            System.out.println("dealer's busted!");
    }
    
    private void playersDecisionRound() {
        Set<String> playerNames = players.keySet();
        for (String playerName : playerNames) {
            BlackJackCardPlayer player = players.get(playerName);
            if (!player.isDealer()) {
                singlePlayerRound(playerName);
            }
        }
    }
    
    private void singlePlayerRound(String playerName) {
        while (getPlayerCardsTotalValue(playerName) < 21) {
            Scanner reader = new Scanner(System.in);
            System.out.println(playerName + "' card's total is at " + getPlayerCardsTotalValue(playerName));
            System.out.println("to hit, enter 1, pass, enter any other number:");
            int decision = 0;
            try {
                decision = reader.nextInt();
                if (decision == 1) {
                    dealACardToAPlayer(playerName);
                    int playerCardsTotalValue = getPlayerCardsTotalValue(playerName);
                    System.out.println(playerName + "'s card's total is now at " + playerCardsTotalValue);
                    
                    if (playerCardsTotalValue > 21) {
                        System.out.println(playerName + "'s busted!");
                    } else if (playerCardsTotalValue == 21) {
                        System.out.println("BlackJack!");
                    }
                } else {
                    break;
                }
            } catch (Exception e) {
                break;
            }
            
        }
    }
    
    private int getPlayerCardsTotalValue(String playerName) {
        BlackJackCardPlayer player = this.players.get(playerName);
        int playerCardsTotal = 0;
        for (Card card : player.getCardsInHand()) {
            playerCardsTotal += convertCardToInt(card.getNumber());
        }
        return playerCardsTotal;
    }
    
    private int convertCardToInt(String cardNumber) {
        if (cardNumber.equals("A"))
            return 11;
        else if (cardNumber.equals("J") || cardNumber.equals("Q") || cardNumber.equals("K"))
            return 10;
        else
            return Integer.valueOf(cardNumber);
    }
    
    private void dealRoundsOfCardToPlayers(int rounds) {
        for (int i = 0; i < rounds; i++)
            dealCardsToPlayers();
    }
    
    private void printWhatsOnTheTable() {
        Set<String> playerNames = players.keySet();
        for (String playerName : playerNames) {
            BlackJackCardPlayer player = players.get(playerName);
            Card card1 = player.getCardsInHand().get(0);
            Card card2 = player.getCardsInHand().get(1);
            String card1Output = card1.getNumber() + "/" + card1.getSuit();
            if (player.isDealer()) {
                card1Output = "hidden";
            }
            String card2Output = card2.getNumber() + "/" + card2.getSuit();
            System.out.println(player.getName() + "'s " + card1Output + " " + card2Output + ", ");
        }
    }
    
    private void dealCardsToPlayers() {
        Set<String> playerNames = players.keySet();
        for (String playerName : playerNames) {
            dealACardToAPlayer(playerName);
        }
    }
    
    private void dealACardToAPlayer(String playerName) {
        if (deck.size() == 0)
            deck = dealerOperations.buildDecks(this.numOfDecks);
        BlackJackCardPlayer player = players.get(playerName);
        Card nextCard = deck.remove(0);
        System.out.println("next card dealt to " + playerName + " is " + nextCard.getNumber() + "/" + nextCard.getSuit());
        player.addCard(nextCard);
    }
    
    private static HashMap<String, BlackJackCardPlayer> createPlayersAndDealer(String[] playerNames) {
        HashMap<String, BlackJackCardPlayer> players = new HashMap<String, BlackJackCardPlayer>();
        for (int i = 0; i < playerNames.length; i++) {
            players.put(playerNames[i], new BlackJackCardPlayer(playerNames[i], 20000L));
        }
        BlackJackCardPlayer dealer = new BlackJackCardPlayer("dealer", 0L);
        dealer.setDealer(true);
        players.put(dealer.getName(), dealer);
        return players;
    }
}
