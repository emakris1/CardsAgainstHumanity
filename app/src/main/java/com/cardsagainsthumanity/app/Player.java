package com.cardsagainsthumanity.app;

import java.util.ArrayList;

/**
 * Class with fields and methods for each player of the game.
 */

public class Player{

    private Integer playerNum;                                                                      //Integer that stores the current player number
    private Integer numAwesomePoints;                                                               //Integer that stores the number of awesome points for a player
    private ArrayList<WhiteCard> playerCards = new ArrayList<WhiteCard>();                                  //Arraylist of white cards that stores the white cards for a player


    /**
     * Constructor that assign the player number and their awesome points.
     * @param n
     * Assigns Integer n to Integer playerNum & sets their current awesome points to zero
     */
    public Player(Integer n){

        this.playerNum = n;
        this.numAwesomePoints = 0;

    }

    /**
     * Method used to retrieve the players number
     * @return this.playerNum
     */
    public Integer getPlayerNum() {

        return this.playerNum;

    }
    /**
     * Method used to retrieve the players number of awesome points
     * @return this.numAwesomePoints
     */
    public Integer getNumAwesomePoints() {

        return this.numAwesomePoints;

    }

    /**
     * Method used to retrieve a players white cards
     * @return this.playerCards
     */
    public ArrayList<WhiteCard> getPlayerCards() {

        return this.playerCards;

    }

    /**
     * Method used to retrieve a specific white card
     * @param index
     * @return this.playerCards.get(index)
     */
    public WhiteCard getPlayerCard(int index) {

        return this.playerCards.get(index);

    }

    /**
     * Method used to increment the players awesome points
     */
    public void incAwesomePoints() {

        this.numAwesomePoints++;

    }
    /**
     * Method used to add a white card to a players hand
     * @param card
     */
    public void addPlayerCard(WhiteCard card) {

        playerCards.add(card);

    }

    /**
     * Method to be used when a player submits a white card from the GameScreen
     * @param index
     * @return WhiteCard
     */
    public WhiteCard removePlayerCard(int index){

        return this.playerCards.remove(index);

    }

    /**
     * Method used to format the display of the player number and their number of awesome points
     */
    public String toString(){

        return "Player " + (this.getPlayerNum() + 1) + ": " + this.getNumAwesomePoints() + " Awesome Points\n";

    }

}