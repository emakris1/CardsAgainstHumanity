package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class defines all of the methods and fields to be used by the activity screens to handle game play.
 */

public class Game extends Activity
{

    public static LinkedList<BlackCard> blackDeck;
    public static LinkedList<WhiteCard> whiteDeck;
    public static ArrayList<Player> players;
    public static int numPlayers;
    public static Resources res;
    public static InputStream inputS;
    public static Scanner sc;
    public static Boolean isDirty;
    public static ArrayList<String> howToPlayList;
    public static BlackCard currentBlackCard;
    public static ArrayList<ArrayList<WhiteCard>> submittedCards;   //two dimensional ArrayList for cases when multiple white cards are submitted
    public static int maxAwesomePoints;
    public static int cardCzar;
    public static int winningPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        res = getResources();
        readInput();
    }

    /**
     * Reads input; checks if the user is playing the
     * clean or dirty game, and then reads from the
     * appropriate deck.
     */
    public static void readInput()
    {
        byte[] buffer;
        blackDeck = new LinkedList<BlackCard>();
        whiteDeck = new LinkedList<WhiteCard>();

        try
        {
            if (isDirty)
                inputS = res.openRawResource(R.raw.dirtyblack);
            else
                inputS = res.openRawResource(R.raw.cleanblack);
            buffer = new byte[inputS.available()];
            inputS.read(buffer);
            inputS.close();
            sc = new Scanner(new String(buffer));
        }
        catch (IOException ex) {ex.printStackTrace();}

        while (sc.hasNext())
            blackDeck.add(new BlackCard(sc.nextInt(), sc.nextLine()));

        sc.close();

        try
        {
            if (isDirty)
                inputS = res.openRawResource(R.raw.dirtywhite);
            else
                inputS = res.openRawResource(R.raw.cleanwhite);
            buffer = new byte[inputS.available()];
            inputS.read(buffer);
            inputS.close();
            sc = new Scanner(new String(buffer));
        }
        catch (IOException ex) {ex.printStackTrace();}

        while (sc.hasNext())
            whiteDeck.add(new WhiteCard(sc.nextLine()));

        sc.close();

        Collections.shuffle(blackDeck);
        Collections.shuffle(whiteDeck);
    }

    /**
     * Method to create the list of players
     * Deals the white cards
     * Sets the first player in the list to be Card Czar
     */
    public static void createPlayers()
    {
        players = new ArrayList<Player>();

        for (int i = 0; i < numPlayers; i++)
        {
            Player temp = new Player(i);
            for (int j = 0; j < 10; j++)
            {
                temp.addPlayerCard(whiteDeck.removeFirst());
                temp.getPlayerCards().get(j).setOwner(j);
            }

            players.add(temp);
            //players.get(0).toggleIsCardCzar();
            cardCzar = 0;
        }
    }

    /**
     * Method to alternate the role of card czar.
     * To be called at the end of each round.
     */
    public static void switchCardCzar()
    {
        //players.get(cardCzar).toggleIsCardCzar();
        //if we are keeping track of the Card Czar with the int, we really don't need to have a field for it within the player class
        if (cardCzar < numPlayers - 1)
            cardCzar++;
        else
            cardCzar = 0;
    }

    /**
     * Method to set the current black card;
     * to be called at the beginning of each round
     */
    public static void setCurrentBlackCard()
    {currentBlackCard = blackDeck.remove();}

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
    public static Boolean checkAwesomePoint()
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
