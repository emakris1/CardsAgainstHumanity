package com.cardsagainsthumanity.app;

/**
 * Class with fields and methods specific to WhiteCards
 */

public class WhiteCard
{

    private String text;
    private Integer owner;

    public WhiteCard(String str) {this.text = str;}

    public String getText() {return this.text;}

    public Integer getOwner() {return this.owner;}

    /**
     * Owner will be set to the index of the player into whose
     * deck the WhiteCard is dealt.
     * @param val
     */
    public void setOwner(Integer val) {this.owner = val;}

}