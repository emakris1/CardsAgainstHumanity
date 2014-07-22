package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * There are 24 card images in the Linear Layout; not all of them will have a submitted white card associated with them
 * use FrameLayout.setVisibility(View.GONE); to disable cards that aren't related to submitted cards
 */

public class CardCzarSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_czar_select);
        displaySubmittedCardText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.card_czar_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displaySubmittedCardText(){

        ArrayList<RelativeLayout> rl = new ArrayList<RelativeLayout>();
        ArrayList<TextView> temp = new ArrayList<TextView>();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LLCardCzarSelect);

        for(int i = 0; i < 24; i++){
            rl.add((RelativeLayout) linearLayout.getChildAt(i));
            RelativeLayout tempRel = (RelativeLayout) linearLayout.getChildAt(i);   //I tried doing the TextView on one line,
            temp.add((TextView) tempRel.getChildAt(1));                             //but it threw an error
        }

        for(int i = 0; i < 24; i++){
            rl.get(i).setVisibility(View.VISIBLE);
        }

        for(int i = 23; i >= Game.getCurrentBlackCard().getNumPrompts()*Game.numPlayers; i--){
            rl.get(i).setVisibility(View.GONE);
        }

        int k = 0;

        for(int i = 0; i < Game.numPlayers; i++) {
            for(int j = 0; j < Game.getCurrentBlackCard().getNumPrompts(); j++){
                temp.get(k).setText(Game.submittedCards.get(i).get(j).getText());
                k++;
            }
        }
    }

}
