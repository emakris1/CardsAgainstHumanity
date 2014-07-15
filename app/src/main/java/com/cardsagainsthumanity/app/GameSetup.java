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
                //Game.readInput();
                //Game.createPlayers();
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

}
