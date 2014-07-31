package com.cardsagainsthumanity.app;

/**
 * Class with fields and methods specific to WhiteCards
 */

public class WhiteCard{

    private String text;                                //String that stores the white card text
    private Integer owner;                              //Integer that stores the owner of a white card


    /**
     * Constructor to assign the text string.\
     * @param str
     * Assigns String str to String text
     */
    public WhiteCard(String str) {

        this.text = str;

    }

    /**
     * Method used to retrieve text for black card
     * @return this.text
     */
    public String getText() {

        return this.text;

    }


    /**
     * Method used to retrieve the owner of a white card
     * @return this.numPrompts
     */

    public Integer getOwner() {

        return this.owner;

    }

    /**
     * Owner will be set to the index of the player into whose
     * deck the WhiteCard is dealt.
     * @param val
     */
    public void setOwner(Integer val) {

        this.owner = val;

    }

}