package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * There are 24 card images in the Linear Layout; not all of them will have a submitted white card associated with them
 * use FrameLayout.setVisibility(View.GONE); to disable cards that aren't related to submitted cards
 */

public class CardCzarSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_czar_select);
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
        int i = 0;
        int k = 0;
        int j = 0;

        TextView temp = (TextView) findViewById(R.id.txtSubWhiteCard1);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.RLSubCard1);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard2);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard2);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard3);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard3);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard4);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard4);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard5);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard5);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard6);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard6);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard7);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard7);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard8);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard8);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard9);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard9);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard10);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard10);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard11);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard11);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard12);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard12);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard13);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard13);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard14);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard14);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard15);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard15);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard16);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard16);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard17);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard17);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard18);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard18);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard19);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard19);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard20);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard20);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard21);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard21);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard22);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard22);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard23);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard23);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }

        j++;
        temp = (TextView) findViewById(R.id.txtSubWhiteCard24);
        rl = (RelativeLayout) findViewById(R.id.RLSubCard24);

        if(j < Game.numPlayers*Game.getCurrentBlackCard().getNumPrompts()){
            temp.setText(Game.submittedCards.get(i).get(k).getText());
            k++;

            if(k == Game.getCurrentBlackCard().getNumPrompts()){
                k = 0;
                i++;
            }
        }

        else{
            rl.setVisibility(View.GONE);
        }
    }

}
