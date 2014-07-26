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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class ScoreBoard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        displayScores();
        onClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayScores(){

        TextView textView = (TextView) findViewById(R.id.scoretext);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Game.numPlayers; i++) {
            if(Game.isDirty) {
                sb.append("Douchebag " + (i + 1) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
            }

            else{
                sb.append("Douchebag " + (i + 1) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
            }
        }

        textView.setText(sb);

        /*for(int i = 0; i < linearLayout.getChildCount(); i++){
            textView = (TextView) linearLayout.getChildAt(i);
            textView.setVisibility(View.VISIBLE);
        }

        for(int i = linearLayout.getChildCount() - 1; i >= Game.numPlayers; i--){
            textView = (TextView) linearLayout.getChildAt(i);
            textView.setVisibility(View.GONE);
        }*/

        /*for(int i = 0; i < Game.numPlayers; i++){
            textView = (TextView) linearLayout.getChildAt(i);
            if(Game.isDirty) {
                textView.setText("Douchebag " + (i + 1) + ": " + Game.players.get(i).getNumAwesomePoints());
            }

            else{
                textView.setText("Player " + (i + 1) + ": " + Game.players.get(i).getNumAwesomePoints());
            }
        }*/
    }

    public void onClick()
    {
        // Show a dialog to pass the device to the next player.
        final TextView textView = (TextView) findViewById(R.id.scoretext);

        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showCardCzarDialog();
            }
        });
    }

    public void showCardCzarDialog(){
        Game.switchCardCzar();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Switch to Card Czar");
        if(Game.isDirty) {
            dialogBuilder.setMessage("Please pass the device to the new Card Czar (Douchebag " + (Game.currentCardCzar + 1) + ")");
        }

        else{
            dialogBuilder.setMessage("Please pass the device to the new Card Czar (Player " + (Game.currentCardCzar + 1) + ")");
        }

        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                if(!Game.gameWon) {
                    startActivity(new Intent(getApplicationContext(), CardCzarRead.class));
                }

                else{
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

    @Override
    public void onBackPressed()
    {
        showQuitDialog();
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
}