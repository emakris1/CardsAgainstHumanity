package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Class with fields and methods specific to the How To Play
 */

public class HowToPlay extends Activity{

    /**
     * Method to create the how to play user interface;
     * creates the instance, sets the view, and displays the how to play texts
     *@param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);                             //creates the instance activity screen
        setContentView(R.layout.activity_how_to_play);                  //sets the layout
        displayHowToPlayText();                                         //method called to display the text

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.how_to_play, menu);
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        return true;

    }

    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    /**
     * Method to display the how to play text;
     * to be called at the beginning of each round
     */
    public void displayHowToPlayText(){

        TextView textRule1 = (TextView) findViewById(R.id.textViewRule1);           //text view created for rule 1 to get the rule 1 text
        TextView textRule2 = (TextView) findViewById(R.id.textViewRule2);           //text view created for rule 2 to get the rule 2 text
        TextView textRule3 = (TextView) findViewById(R.id.textViewRule3);           //text view created for rule 3 to get the rule 3 text
        TextView textRule4 = (TextView) findViewById(R.id.textViewRule4);           //text view created for rule 4 to get the rule 4 text
        TextView textRule5 = (TextView) findViewById(R.id.textViewRule5);           //text view created for rule 5 to get the rule 5 text
        TextView textRule6 = (TextView) findViewById(R.id.textViewRule6);           //text view created for rule 6 to get the rule 6 text
        TextView textRule7 = (TextView) findViewById(R.id.textViewRule7);           //text view created for rule 7 to get the rule 7 text
        TextView textRule8 = (TextView) findViewById(R.id.textViewRule8);           //text view created for rule 8 to get the rule 8 text

        //sets each text to specific rule
        textRule1.setText("Once the game starts, each player is given 10 white cards");
        textRule2.setText("Player 1 is automatically set to Card Czar while player 2 is first to submit their card(s).");
        textRule3.setText("Everyone answers the question by passing one, two, or three white cards to the Card Czar. The game will automatically shuffle the submitted cards.");
        textRule4.setText("The Card Czar will read each card combination with the other players. For full effect re-read the Black Card before presenting each answer.");
        textRule5.setText("The Card Czar will then select the funniest cards and whomever submitted the card receives an Awesome Point!");
        textRule6.setText("After the round, the next player becomes the Card Czar, and everyone is drawn back up to ten White Cards.");
        textRule7.setText("Rules for pick 2 or 3 white Cards: Each player picks 2 or 3 white cards in combination. The cards must be played in the order the Card Czar should read them.");
        textRule8.setText("Lastly, remember to have fun!");

    }

}