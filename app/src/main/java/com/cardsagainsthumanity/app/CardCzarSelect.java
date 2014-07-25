package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class CardCzarSelect extends Activity
{

    public int roundWinner;
    private boolean found = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_czar_select);
        Collections.shuffle(Game.submittedCards);
        displayBlackCardText();
        displaySubmittedCardText();
        onWhiteCardLongPress();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.card_czar_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        showQuitDialog();
    }

    public void displayBlackCardText()
    {
        final TextView txtBlackCardLarge = (TextView) findViewById(R.id.txtCZBlackCardLarge);
        txtBlackCardLarge.setText(Game.getCurrentBlackCard().getText());
    }

    public void displaySubmittedCardText()
    {
        ArrayList<RelativeLayout> rl = new ArrayList<RelativeLayout>();
        ArrayList<TextView> txtWhiteCards = new ArrayList<TextView>();
        ArrayList<TextView> txtGroups = new ArrayList<TextView>();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LLCardCzarSelect);

        for(int i = 0; i < 21; i++){
            rl.add((RelativeLayout) linearLayout.getChildAt(i));
            RelativeLayout tempRel = (RelativeLayout) linearLayout.getChildAt(i);   //I tried doing the TextView on one line,
            txtWhiteCards.add((TextView) tempRel.getChildAt(1));                             //but it threw an error
            txtGroups.add((TextView) tempRel.getChildAt(2));
        }

        for(int i = 0; i < 21; i++){
            rl.get(i).setVisibility(View.VISIBLE);
        }

        for(int i = 20; i >= Game.getCurrentBlackCard().getNumPrompts()*(Game.numPlayers - 1); i--){
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

    public void showRoundWinnerDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        if(Game.gameWon){
            dialogBuilder.setTitle("Player " + (Game.winningPlayer + 1) + " is the Supreme Ruler of the Universe!");
            dialogBuilder.setMessage("This lucky person was the first to reach " + Game.maxAwesomePoints + " awesome points!");
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }
            });
        }

        else {
            dialogBuilder.setTitle("Player " + (roundWinner + 1) + " is the Round Winner!");
            //dialogBuilder.setMessage("Please pass the device to the new Card Czar (Player " + (Game.currentCardCzar + 1) + ")");
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //startActivity(new Intent(getApplicationContext(), CardCzarSelect.class));
                    showCardCzarDialog();
                }
            });
        }

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

    public void showCardCzarDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Switch to Card Czar");
        dialogBuilder.setMessage("Please pass the device to the new Card Czar (Player " + (Game.currentCardCzar + 1) + ")");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                startActivity(new Intent(getApplicationContext(), CardCzarRead.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

    public void showQuitDialog()
    {
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
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

    public void onWhiteCardLongPress()
    {
        // Get every White Card inside of the HorizontalScrollView and add a long click event
        // listener that submits the selected card and disables it.
        LinearLayout ll = (LinearLayout) findViewById(R.id.LLCardCzarSelect);

        for (int i = 0; i < ll.getChildCount(); i++)
        {
            RelativeLayout rl = (RelativeLayout) ll.getChildAt(i);
            final ImageView imgWhiteCard = (ImageView) rl.getChildAt(0);
            final TextView txtWhiteCard = (TextView) rl.getChildAt(1);
            final int index = i;    // white card index

            if (imgWhiteCard != null)
            {
                imgWhiteCard.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        for (int j = 0; j < Game.submittedCards.size(); j++) {
                            for (int k = 0; k < Game.currentBlackCard.getNumPrompts(); k++) {
                                if (txtWhiteCard.getText() == Game.submittedCards.get(j).get(k).getText()) {
                                    Game.players.get(Game.submittedCards.get(j).get(k).getOwner()).incAwesomePoints();
                                    found = true;
                                    break;
                                }
                            }

                            if (found)
                                break;
                        }

                        if (Game.checkAwesomePoints()) {
                            Game.gameWon = true;
                        }

                        showRoundWinnerDialog();
                        return true;
                    }
                });
            }
        }
    }
}
