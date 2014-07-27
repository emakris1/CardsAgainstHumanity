package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CardCzarRead extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_czar_read);
        Game.submittedCards = new ArrayList<ArrayList<WhiteCard>>();
        try {
            displayBlackCardText();
        }catch(Exception e){
            Game.deckEmpty = true;
            Game.gameWon = true;
            showQuitDialog();
        }

        onBlackCardClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.card_czar_read, menu);
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
        TextView text = (TextView) findViewById(R.id.txtCZBlackCardLarge);
        Game.setCurrentBlackCard();
        text.setText(Game.getCurrentBlackCard().getText());
    }

    public void onBlackCardClick()
    {
        //Set the next player to follow the Card Czar
        Game.setNewRoundPlayer();

        // Show a dialog to pass the device to the next player.
        final ImageView blackCard = (ImageView) findViewById(R.id.imgCZBlackCardLarge);

        blackCard.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                showPlayerDialog();
            }
        });
    }

    public void showPlayerDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);



            if(Game.isDirty) {
                dialogBuilder.setTitle("Switch to Douchebag " + (Game.currentPlayer + 1));
                dialogBuilder.setMessage("Please pass the device to Douchebag " + (Game.currentPlayer + 1));
            }

            else{
                dialogBuilder.setTitle("Switch to Player " + (Game.currentPlayer + 1));
                dialogBuilder.setMessage("Please pass the device to Player " + (Game.currentPlayer + 1));
            }

            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), PlayerCardSelection.class));
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

        if(Game.deckEmpty){
            dialogBuilder.setTitle("Game Over");

            if(Game.isDirty) {
                dialogBuilder.setMessage("The deck has been depleted. Fuck you.");
            }

            else{
                dialogBuilder.setMessage("The deck has been depleted.");
            }

            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    startActivity(new Intent(getApplicationContext(), ScoreBoard.class));
                }
            });
        }

        else {
            dialogBuilder.setTitle("Quit to Main Menu");
            dialogBuilder.setMessage("If you back out now, your game will be aborted. Are you sure you want to quit?");
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }
            });

            dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

}


