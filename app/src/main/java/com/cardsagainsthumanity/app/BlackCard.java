package com.cardsagainsthumanity.app;

/**
 * Class with fields and methods specific to BlackCards
 */

public class BlackCard
{

    private String text;
    private Integer numPrompts;

    public BlackCard(Integer n, String str)
    {
        this.text = str;
        this.numPrompts = n;
    }

    public String getText() {

        return this.text;

    }

    public Integer getNumPrompts() {

        return this.numPrompts;

    }

}