package com.cardsagainsthumanity.app;

import java.util.ArrayList;

/**
 * Class with fields and methods for each player of the game.
 */

public class Player{

    private Integer playerNum;
    private Integer numAwesomePoints;
    private ArrayList<WhiteCard> playerCards = new ArrayList<WhiteCard>();

    public Player(Integer n){

        this.playerNum = n;
        this.numAwesomePoints = 0;

    }

    public Integer getPlayerNum() {

        return this.playerNum;

    }

    public Integer getNumAwesomePoints() {

        return this.numAwesomePoints;

    }

    public ArrayList<WhiteCard> getPlayerCards() {

        return this.playerCards;

    }

    public WhiteCard getPlayerCard(int index) {

        return this.playerCards.get(index);

    }

    public void incAwesomePoints() {

        this.numAwesomePoints++;

    }

    public void addPlayerCard(WhiteCard card) {

        playerCards.add(card);

    }

    public void addPlayerCard(int index, WhiteCard card){

        playerCards.add(index, card);

    }

    /**
     * Method to be used when a player submits a white card from the GameScreen
     * @param index
     * @return WhiteCard
     */
    public WhiteCard removePlayerCard(int index){

        return this.playerCards.remove(index);

    }

    public String toString(){

        return "Player " + (this.getPlayerNum() + 1) + ": " + this.getNumAwesomePoints() + " Awesome Points\n";

    }

}

