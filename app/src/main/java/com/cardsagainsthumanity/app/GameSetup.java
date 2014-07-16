package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Activity Screen on which the user selects the number of players and the target number of awesome points.
 */

public class GameSetup extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        onButtonCreateGameClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.game_setup, menu);
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

    public void onButtonCreateGameClick()
    {
        Button btn = (Button) findViewById(R.id.btnCreateGame);
        final Spinner spPlayers = (Spinner) findViewById(R.id.spinnerNumPlayers);
        final Spinner spAwesomePoints = (Spinner) findViewById(R.id.spinnerMaxAwesomePoints);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Game.numPlayers = Integer.parseInt(spPlayers.getSelectedItem().toString());
                Game.maxAwesomePoints = Integer.parseInt(spAwesomePoints.getSelectedItem().toString());
                readInput();
                createPlayers();
                showDialog();
            }
        });
    }

    public void showDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Switch to Card Czar");
        dialogBuilder.setMessage("Player 1 is now the Card Czar. Please pass the device to him or her.");
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                startActivity(new Intent(getApplicationContext(), CardCzarRead.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Reads input; checks if the user is playing the
     * clean or dirty game, and then reads from the
     * appropriate deck.
     */
    public void readInput()
    {
        InputStream inputS;
        BufferedReader buffR;
        String str;
        Game.blackDeck = new LinkedList<BlackCard>();
        Game.whiteDeck = new LinkedList<WhiteCard>();

        // Read the Black Deck
        if (Game.isDirty)
            inputS = getResources().openRawResource(R.raw.dirtyblack);
        else
            inputS = getResources().openRawResource(R.raw.cleanblack);
        buffR = new BufferedReader(new InputStreamReader(inputS));

        try
        {
            while ((str = buffR.readLine()) != null)
            {
                Integer num = (Integer.valueOf(str.subSequence(0,1).charAt(0)) - '0');
                String text = str.substring(2);
                Game.blackDeck.add(new BlackCard(num, text));
            }

            buffR.close();
            inputS.close();
        }
        catch (IOException ex) {ex.printStackTrace();}

        // Read the White Deck
        if (Game.isDirty)
            inputS = getResources().openRawResource(R.raw.dirtywhite);
        else
            inputS = getResources().openRawResource(R.raw.cleanwhite);
        buffR = new BufferedReader(new InputStreamReader(inputS));

        try
        {
            while ((str = buffR.readLine()) != null)
                Game.whiteDeck.add(new WhiteCard(str));

            buffR.close();
            inputS.close();
        }
        catch (IOException ex) {ex.printStackTrace();};

        Collections.shuffle(Game.blackDeck);
        Collections.shuffle(Game.whiteDeck);
    }

    /**
     * Method to create the list of players
     * Deals the white cards
     * Sets the first player in the list to be Card Czar
     */
    public static void createPlayers()
    {
        Game.players = new ArrayList<Player>();

        for (int i = 0; i < Game.numPlayers; i++)
        {
            Player temp = new Player(i);
            for (int j = 0; j < 10; j++)
            {
                temp.addPlayerCard(Game.whiteDeck.removeFirst());
                temp.getPlayerCards().get(j).setOwner(j);
            }

            Game.players.add(temp);
            //players.get(0).toggleIsCardCzar();
            Game.cardCzar = 0;
        }
    }

}
