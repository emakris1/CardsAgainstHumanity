package com.cardsagainsthumanity.app;

/**
 * Class with fields and methods specific to BlackCards
 */

public class BlackCard
{

    private String text;                                //String that stores the black card text
    private Integer numPrompts;                         //Integer that stores the number of Prompts for the black card

    /**
     * Constructor to assign the number of prompts and the text string.
     * @param n
     * @param str
     * Assigns Integer n to Integer numPrompts & String str to String text
     */
    public BlackCard(Integer n, String str)
    {
        this.text = str;
        this.numPrompts = n;
    }

    /**
     * Method used to retrieve text for black card
     * @return this.text
     */
    public String getText() {

        return this.text;

    }

    /**
     * Method used to retrieve the number of prompts
     * @return this.numPrompts
     */

    public Integer getNumPrompts() {

        return this.numPrompts;

    }

}