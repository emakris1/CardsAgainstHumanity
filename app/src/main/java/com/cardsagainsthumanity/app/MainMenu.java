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
 * This class implements the Main Menu screen, where users can elect to start a new game or read
 * the How To Play reference. It contains all members and methods necessary to create a new
 * instance of the screen.
 */

public class MainMenu extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Initialize the Play Game button's onClick() listener
        onPlayGameButtonClick();

        // Initialize the How To Play button's onClick() listener
        onHowToPlayButtonClick();

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.main_menu, menu);
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
     * Prevents the user from backing out to screens of a previous game, and from accidentally
     * quitting the game once it has been opened.
     */
    @Override
    public void onBackPressed(){

        showQuitDialog();

    }

    /**
     * Launches the Version Selection screen when a user clicks the Play Game button.
     */
    public void onPlayGameButtonClick(){

        Button btn = (Button) findViewById(R.id.btnPlayGame);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), VersionSelection.class));
            }
        });
    }

    /**
     * Launches the How To Play screen when a user clicks the How To Play button.
     */
    private void onHowToPlayButtonClick(){

        Button btn = (Button) findViewById(R.id.btnHowToPlay);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), HowToPlay.class));
            }
        });
    }

    /**
     * Show a dialog box to the user when the back button is pressed confirming if they want to
     * quit the application.
     */
    public void showQuitDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Exit Game");
        dialogBuilder.setMessage("Are you sure you want to exit Cards Against Humanity?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
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

