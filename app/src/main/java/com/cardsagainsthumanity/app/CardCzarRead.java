package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class implements the screen on which the Card Czar will read the black card to all other
 * users. It contains all members and methods necessary to create a new instance of the screen.
 */

public class CardCzarRead extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_czar_read);
        Game.submittedCards = new ArrayList<ArrayList<WhiteCard>>();

        // Initialize the black card image and text
        try {
            displayBlackCardText();
        }catch(Exception e){
            Game.deckEmpty = true;
            Game.gameWon = true;
            showQuitDialog();
        }

        // Initialize the black card's onClick() listener
        onBlackCardClick();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.card_czar_read, menu);
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
     * Prevents the user from backing out to Game Setup once a game has been initiated. This prevents
     * users from breaking the game algorithm, and also serves as a safety mechanism for preventing
     * users from accidentally backing out of a game while passing the device to another user.
     */
    @Override
    public void onBackPressed(){

        showQuitDialog();

    }

    /**
     * Pulls a black card from the black card deck and displays it to the Card Czar
     */
    public void displayBlackCardText(){

        TextView text = (TextView) findViewById(R.id.txtCZBlackCardLarge);
        Game.setCurrentBlackCard();
        text.setText(Game.getCurrentBlackCard().getText());

    }

    /**
     * Sets the next player to follow the current Card Czar and shows a dialog
     * to pass the device to the next player when the black card is clicked.
     */
    public void onBlackCardClick(){

        Game.setNewRoundPlayer();
        final ImageView blackCard = (ImageView) findViewById(R.id.imgCZBlackCardLarge);
        blackCard.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                showPlayerDialog();
            }
        });

    }

    /**
     * Shows a dialog box to the CardCzar with the screen behind blacked out so that the Card Czar
     * doesn't accidentally see the player's cards while passing them the device.
     */
    public void showPlayerDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        if(Game.isDirty) {
            dialogBuilder.setTitle("Switch to Next Douchebag");
            dialogBuilder.setMessage("Please pass the device to\nDouchebag " + (Game.currentPlayer + 1));
        }

        else{
            dialogBuilder.setTitle("Switch to Next Player");
            dialogBuilder.setMessage("Please pass the device to\nPlayer " + (Game.currentPlayer + 1));
        }

        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), PlayerCardSelection.class));
                }
        });

        // Center the text inside the dialog box's message area
        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        // Blackout the screen behind the dialog box
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;   // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

    }

    /**
     * Show a dialog box to the user when the back button is pressed confirming if they want to
     * quit the current game in progress.
     */
    public void showQuitDialog(){

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
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
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

        // Center the text inside the dialog box's message area
        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        // Blackout the screen behind the dialog box
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;   // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

}


