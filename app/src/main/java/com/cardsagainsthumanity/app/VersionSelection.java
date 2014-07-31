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
import android.widget.TextView;

/**
 * This class implements the screen where users can select which version of the game they want to
 * play. It contains all members and methods necessary to create a new instance of the screen.
 */

public class VersionSelection extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_selection);
        onDirtyButtonClick();
        onCleanButtonClick();

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.version_selection, menu);
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
     * Show a dialog box when the "Dirty" Deck button is clicked to confirm if the user is over
     * 18 years old before continuing to Game Setup with the dirty card decks marked for use.
     */
    public void onDirtyButtonClick(){

        Button btn = (Button) findViewById(R.id.btnDirty);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showDialog();
            }
        });

    }

    /**
     * Launches the Game Setup screen when the "Clean" Deck button is clicked. The clean decks will
     * be marked for use.
     */
    public void onCleanButtonClick(){

        Button btn = (Button) findViewById(R.id.btnClean);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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
    public void showDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Age Verification");
        dialogBuilder.setMessage("Are you at least 18 years old?");

        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Game.isDirty = false;
                startActivity(new Intent(getApplicationContext(), GameSetup.class));
            }
        });

        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Game.isDirty = true;
                startActivity(new Intent(getApplicationContext(), GameSetup.class));
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
