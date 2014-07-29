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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class ScoreBoard extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        displayScores();
        onScoreboardClick();

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.score_board, menu);
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

    @Override
    public void onBackPressed(){

        showQuitDialog();

    }

    public void displayScores(){

        RelativeLayout rlScoreboard1 = (RelativeLayout) findViewById(R.id.rlScoreboardCard1);
        RelativeLayout rlScoreboard2 = (RelativeLayout) findViewById(R.id.rlScoreboardCard2);
        TextView txtScoreboard1 = (TextView) findViewById(R.id.txtScoreboardCard1);
        TextView txtScoreboard2 = (TextView) findViewById(R.id.txtScoreboardCard2);
        TextView txtPrompt = (TextView) findViewById(R.id.txtScoreboardPrompt);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        if (Game.numPlayers <= 10){
            rlScoreboard1.setVisibility(View.VISIBLE);
            rlScoreboard2.setVisibility(View.GONE);

            for (int i = 0; i < Game.numPlayers; i++) {
                if (Game.isDirty) {
                    sb1.append("Douchebag " + String.format("%02d", (i + 1)) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
                }

                else {
                    sb1.append("Player " + String.format("%02d", (i + 1)) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
                }
            }

            txtScoreboard1.setText(sb1);


        }

        else{
            rlScoreboard1.setVisibility(View.VISIBLE);
            rlScoreboard2.setVisibility(View.VISIBLE);

            for (int i = 0; i < 10; i++) {
                if (Game.isDirty) {
                    sb1.append("Douchebag " + String.format("%02d", (i + 1)) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
                }

                else {
                    sb1.append("Player " + String.format("%02d", (i + 1)) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
                }
            }

            for (int i = 10; i < Game.numPlayers; i++) {
                if (Game.isDirty) {
                    sb2.append("Douchebag " + String.format("%02d", (i + 1)) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
                }

                else {
                    sb2.append("Player " + String.format("%02d", (i + 1)) + ": " + Game.players.get(i).getNumAwesomePoints() + "\n");
                }
            }

            txtScoreboard1.setText(sb1);
            txtScoreboard2.setText(sb2);
        }

        if (Game.gameWon) {
            txtPrompt.setText("Tap the scoreboard to return to the main menu");
        }

        else {
            txtPrompt.setText("Tap the scoreboard to continue to the next round");
        }

    }

    // Show a dialog to pass the device to the next player.
    public void onScoreboardClick(){

        final ImageView iv1 = (ImageView) findViewById(R.id.imgScoreboardCard1);
        final ImageView iv2 = (ImageView) findViewById(R.id.imgScoreboardCard2);

        iv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Game.gameWon) {
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }

                else{
                    showCardCzarDialog();
                }
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Game.gameWon) {
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }

                else {
                    showCardCzarDialog();
                }
            }
        });

    }

    public void showCardCzarDialog(){

        Game.switchCardCzar();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("New Round");

        if(Game.isDirty) {
            dialogBuilder.setMessage("Please pass the device to\nthe new Card Czar (Douchebag " + (Game.currentCardCzar + 1) + ")");
        }

        else{
            dialogBuilder.setMessage("Please pass the device to\nthe new Card Czar (Player " + (Game.currentCardCzar + 1) + ")");
        }

        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                    startActivity(new Intent(getApplicationContext(), CardCzarRead.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;                                                                       // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

    }

    public void showQuitDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Quit to Main Menu");
        dialogBuilder.setMessage("If you back out now, your game will be aborted. Are you sure you want to quit?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
            }
        });

        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;                                                                       // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

    }

}
