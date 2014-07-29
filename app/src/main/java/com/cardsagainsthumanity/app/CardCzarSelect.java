package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * There are 24 card images in the Linear Layout; not all of them will have a submitted white card associated with them
 * use FrameLayout.setVisibility(View.GONE); to disable cards that aren't related to submitted cards
 */

public class CardCzarSelect extends Activity{

    public int roundWinner;
    private boolean found = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_czar_select);
        Collections.shuffle(Game.submittedCards);
        displayBlackCardText();
        displaySubmittedCardText();
        onWhiteCardClick();

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.card_czar_select, menu);
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        return true;

    }

    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed(){

        showQuitDialog();

    }

    public void displayBlackCardText(){

        final TextView txtBlackCardLarge = (TextView) findViewById(R.id.txtCZBlackCardLarge);
        txtBlackCardLarge.setText(Game.getCurrentBlackCard().getText());

    }

    //Only include 21 cards in the CardCzarSelect xml. Only allow eight players to play
    public void displaySubmittedCardText(){

        ArrayList<RelativeLayout> rl = new ArrayList<RelativeLayout>();
        ArrayList<TextView> txtWhiteCards = new ArrayList<TextView>();
        ArrayList<TextView> txtGroups = new ArrayList<TextView>();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LLCardCzarSelect);

        for(int i = 0; i < linearLayout.getChildCount(); i++){
            rl.add((RelativeLayout) linearLayout.getChildAt(i));
            RelativeLayout tempRel = (RelativeLayout) linearLayout.getChildAt(i);   //I tried doing the TextView on one line,
            txtWhiteCards.add((TextView) tempRel.getChildAt(1));                             //but it threw an error
            txtGroups.add((TextView) tempRel.getChildAt(2));
        }

        for(int i = 0; i < linearLayout.getChildCount(); i++){
            rl.get(i).setVisibility(View.VISIBLE);
        }

        for(int i = (linearLayout.getChildCount() - 1); i >= Game.getCurrentBlackCard().getNumPrompts()*(Game.numPlayers - 1); i--){
            rl.get(i).setVisibility(View.GONE);
        }

        int k = 0;

        for(int i = 0; i < Game.numPlayers - 1; i++) {
            for(int j = 0; j < Game.getCurrentBlackCard().getNumPrompts(); j++){
                txtWhiteCards.get(k).setText(Game.submittedCards.get(i).get(j).getText());
                txtGroups.get(k).setText("Set " + (i+1));
                k++;
            }
        }
    }

    // Get every White Card inside of the HorizontalScrollView and add a long click event
    // listener that submits the selected card and disables it
    public void onWhiteCardClick(){

        LinearLayout ll = (LinearLayout) findViewById(R.id.LLCardCzarSelect);

        for (int i = 0; i < ll.getChildCount(); i++){
            RelativeLayout rl = (RelativeLayout) ll.getChildAt(i);
            final ImageView imgWhiteCard = (ImageView) rl.getChildAt(0);
            final TextView txtWhiteCard = (TextView) rl.getChildAt(1);
            final int index = i;    // white card index

            if (imgWhiteCard != null){
                imgWhiteCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int j = 0; j < Game.submittedCards.size(); j++) {
                            for (int k = 0; k < Game.currentBlackCard.getNumPrompts(); k++) {
                                if (txtWhiteCard.getText() == Game.submittedCards.get(j).get(k).getText()) {
                                    roundWinner = Game.submittedCards.get(j).get(k).getOwner();
                                    Game.players.get(roundWinner).incAwesomePoints();
                                    found = true;
                                    break;
                                }
                            }

                            if (found) {
                                break;
                            }
                        }

                        if (Game.checkAwesomePoints()) {
                            Game.gameWon = true;
                        }

                        showRoundWinnerDialog();
                    }
                });
            }
        }

    }

    public void showRoundWinnerDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        // Check whether Awesome Point(s) should be plural or not.
        String awesomePoints;

        if (Game.maxAwesomePoints > 1) {
            awesomePoints = Game.maxAwesomePoints + " Awesome Points!";
        }

        else {
            awesomePoints = "1 Awesome Point!";
        }

        if(Game.deckEmpty) {
            dialogBuilder.setTitle("Game Over");

            if (Game.isDirty) {
                dialogBuilder.setMessage("The deck has been depleted. Fuck you.");
            }

            else {
                dialogBuilder.setMessage("The deck has been depleted.");
            }

            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), ScoreBoard.class));
                }
            });
        }

        else if(Game.gameWon) {
            dialogBuilder.setTitle("Game Won!");

            if (Game.isDirty) {
                dialogBuilder.setMessage("All hail the Supreme Ruler of the Universe, Douchebag " + (Game.winningPlayer + 1)
                        + ".\n\n This lucky asshole was the first to reach " + awesomePoints);
            }

            else{
                dialogBuilder.setMessage("All hail the Supreme Ruler of the Universe, Player " + (Game.winningPlayer + 1)
                        + ".\n\n This lucky person was the first to reach " + awesomePoints);
            }

            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), ScoreBoard.class));
                }
            });
        }

        else {
            dialogBuilder.setTitle("Round Won!");

            if(Game.isDirty) {
                dialogBuilder.setMessage("Douchebag " + (roundWinner + 1) + " is the Round Winner!");
            }

            else{
                dialogBuilder.setMessage("Player " + (roundWinner + 1) + " is the Round Winner!");
            }

            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), ScoreBoard.class));
                }
            });
        }

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;                                                                   // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

    public void showQuitDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Quit to Main Menu");
        dialogBuilder.setMessage("If you back out now, your game will be aborted. Are you sure you want to quit?");
        dialogBuilder.setCancelable(false);

        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
            }
        });

        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;                                                                   // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

    }

}
