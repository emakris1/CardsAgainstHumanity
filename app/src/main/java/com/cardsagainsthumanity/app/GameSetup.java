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
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * This class implements the screen on which users can set the number of players who will participate
 * and the maximum number of awesome points required to win a game. It contains all members and
 * methods necessary to create a new instance of the screen.
 */

public class GameSetup extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        onButtonCreateGameClick();

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.game_setup, menu);
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
     * Initializes all variables and data structures required for playing a game once the
     * Create Game button is clicked. All players and card decks are created, counters and flags
     * are initialized, and a dialog box is presented to the current user to pass the device to
     * the first player.
     */
    public void onButtonCreateGameClick(){

        Button btn = (Button) findViewById(R.id.btnCreateGame);
        final Spinner spPlayers = (Spinner) findViewById(R.id.spinnerNumPlayers);
        final Spinner spAwesomePoints = (Spinner) findViewById(R.id.spinnerMaxAwesomePoints);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Game.numPlayers = Integer.parseInt(spPlayers.getSelectedItem().toString());
                Game.maxAwesomePoints = Integer.parseInt(spAwesomePoints.getSelectedItem().toString());
                Game.gameWon = false;
                Game.deckEmpty = false;
                readInput();
                createPlayers();
                showDialog();
            }
        });

    }

    /**
     * Reads all input files, checks if the user is playing the clean or dirty game, and then loads
     * the appropriate deck into the Game class.
     */
    public void readInput(){

        InputStream inputS;
        BufferedReader buffR;
        String str;
        Game.blackDeck = new LinkedList<BlackCard>();
        Game.whiteDeck = new LinkedList<WhiteCard>();

        // Read the Black Deck
        if (Game.isDirty) {
            inputS = getResources().openRawResource(R.raw.dirtyblack);
        }

        else {
            inputS = getResources().openRawResource(R.raw.cleanblack);
        }

        buffR = new BufferedReader(new InputStreamReader(inputS));

        try{
            while ((str = buffR.readLine()) != null){
                Integer num = (Integer.valueOf(str.subSequence(0,1).charAt(0)) - '0');
                String text = str.substring(2);
                Game.blackDeck.add(new BlackCard(num, text));
            }

            buffR.close();
            inputS.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        // Read the White Deck
        if (Game.isDirty) {
            inputS = getResources().openRawResource(R.raw.dirtywhite);
        }

        else {
            inputS = getResources().openRawResource(R.raw.cleanwhite);
        }

        buffR = new BufferedReader(new InputStreamReader(inputS));

        try{
            while ((str = buffR.readLine()) != null) {
                Game.whiteDeck.add(new WhiteCard(str));
            }

            buffR.close();
            inputS.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        };

        // Shuffle the decks so that cards are in random order when removed.
        Collections.shuffle(Game.blackDeck);
        Collections.shuffle(Game.whiteDeck);
    }

    /**
     * Creates the list of players. The number of players created is determined by the
     * Game.numPlayers variable. Each player is dealt 10 cards from the white deck.
     * Finally, the Card Czar is initialized as the first player.
     */
    public static void createPlayers(){

        Game.players = new ArrayList<Player>();

        for (int i = 0; i < Game.numPlayers; i++){
            Player tmpPlayer = new Player(i);
            for (int j = 0; j < 10; j++){
                WhiteCard tmpCard = Game.whiteDeck.remove();
                tmpCard.setOwner(i);
                tmpPlayer.addPlayerCard(tmpCard);
            }

            Game.players.add(tmpPlayer);
        }

        Game.currentCardCzar = 0;
    }

    /**
     * Show a dialog to pass the device to the first player, who will server as the first Card Czar.
     */
    public void showDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Switch to Card Czar");

        if(Game.isDirty) {
            dialogBuilder.setMessage("Please pass the device to\nthe Card Czar (Douchebag " + (Game.currentCardCzar + 1) + ")");
        }

        else{
            dialogBuilder.setMessage("Please pass the device to\nthe Card Czar (Player " + (Game.currentCardCzar + 1) + ")");
        }

        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(getApplicationContext(), CardCzarRead.class));
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

}


