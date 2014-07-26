package com.cardsagainsthumanity.app;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class defines all of the methods and fields to be used by the activity screens to handle game play.
 */

public class Game
{

    public static LinkedList<BlackCard> blackDeck;
    public static LinkedList<WhiteCard> whiteDeck;
    public static BlackCard currentBlackCard;
    public static ArrayList<ArrayList<WhiteCard>> submittedCards;   //two dimensional ArrayList for cases when multiple white cards are submitted
    public static ArrayList<Player> players;
    public static Boolean isDirty;
    public static int maxAwesomePoints;
    public static int numPlayers;
    public static int currentCardCzar;
    public static int currentPlayer;
    public static int winningPlayer;
    public static boolean gameWon;
    public static boolean deckEmpty;

    /**
     * Method to alternate the role of card czar.
     * To be called at the end of each round.
     */
    public static void switchCardCzar()
    {
        if (currentCardCzar < numPlayers - 1)
            currentCardCzar++;
        else
            currentCardCzar = 0;
    }

    /**
     * Method to alternate the current player
     * To be called after each player's turn.
     */
    public static void switchPlayer()
    {
        if (currentPlayer < numPlayers - 1)
            currentPlayer++;
        else
            currentPlayer = 0;
    }

    /**
     * Method to reset the current player on a new round.
     * To be called at the end of each round.
     */
    public static void setNewRoundPlayer()
    {
        if (currentCardCzar < numPlayers - 1)
            currentPlayer = currentCardCzar + 1;
        else
            Game.currentPlayer = 0;
    }

    /**
     * Method to set the current black card;
     * to be called at the beginning of each round
     */
    public static void setCurrentBlackCard() {currentBlackCard = blackDeck.remove();}

    /**
     * Method to get the current black card;
     * to be called whenever the black card
     * is rendered on screen.
     */
    public static BlackCard getCurrentBlackCard() {return currentBlackCard;}


    /**
     * Increments the awesome points of the owner of the argument WhiteCard
     * To be called when the Card Czar selects a round winning card.
     * The selected card to be used as the parameter
     * @param cd
     */
    public static void selectWhiteCard(ArrayList<WhiteCard> cd)
    {players.get(cd.get(0).getOwner()).incAwesomePoints();}

    /**
     * Method to check if the target number of Awesome points has been reached.
     * If this target has been reached, return true; otherwise, return false.
     * This will be used to check if the game is over.
     * @return
     */
    public static Boolean checkAwesomePoints()
    {
        for (int i = 0; i < numPlayers; i++)
        {
            if (players.get(i).getNumAwesomePoints() == maxAwesomePoints)
            {
                winningPlayer = i;
                return true;
            }
        }
        return false;
    }

}


