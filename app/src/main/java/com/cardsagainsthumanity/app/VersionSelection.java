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

/**
 * This is the screen on which the user will select which version of the game he or she will be playing.
 */

public class VersionSelection extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_selection);
        onDirtyButtonClick();
        onCleanButtonClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.version_selection, menu);
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

    /**
     * Sets up the Dirty Deck button so that when it is clicked, a dialog prompt to confirm
     * the user's age appears. Game.isDirty is set to true or false depending on the answer,
     * and the GameSetup activity is started.
     */
    public void onDirtyButtonClick()
    {
        Button btn = (Button) findViewById(R.id.btnDirty);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog();
            }
        });
    }

    /**
     * Sets up the Clean Deck button so that when it is clicked, Game.isDirty is set to false
     * and the GameSetup activity is started.
     */
    public void onCleanButtonClick()
    {
        Button btn = (Button) findViewById(R.id.btnClean);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Game.isDirty = false;
                startActivity(new Intent(getApplicationContext(), GameSetup.class));
            }
        });
    }

    /**
     * Sets up a dialog prompt to verify that the user is at least 18 years old.
     * If they click yes, Game.isDirty is set to true; otherwise, it is set to false.
     * From here, the GameSetup activity is started.
     */
    public void showDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Age Verification");
        dialogBuilder.setMessage("Are you at least 18 years old?");

        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                Game.isDirty = false;
                startActivity(new Intent(getApplicationContext(), GameSetup.class));
            }
        });

        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                Game.isDirty = true;
                startActivity(new Intent(getApplicationContext(), GameSetup.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

}
