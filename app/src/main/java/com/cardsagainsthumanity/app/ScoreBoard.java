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


public class ScoreBoard extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        displayScores();
        onScoreboardClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.score_board, menu);
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

    public void displayScores()
    {
        TextView txtScoreboard = (TextView) findViewById(R.id.txtScoreboardCard);
        TextView txtPrompt = (TextView) findViewById(R.id.txtScoreboardPrompt);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Game.numPlayers; i++) {
            if(Game.isDirty) {
                sb.append("Douchebag " + (i + 1) + ": " + Game.players.get(i).getNumAwesomePoints());
                if (Game.players.get(i).getNumAwesomePoints() != 1)
                    sb.append(" Awesome Points\n");
                else
                    sb.append(" Awesome Point\n");
            }
            else{
                sb.append("Player " + (i + 1) + ": " + Game.players.get(i).getNumAwesomePoints());
                if (Game.players.get(i).getNumAwesomePoints() != 1)
                    sb.append(" Awesome Points\n");
                else
                    sb.append(" Awesome Point\n");
            }
        }
        txtScoreboard.setText(sb);

        if (Game.gameWon)
            txtPrompt.setText("Tap the scoreboard to return to the main menu");
        else
            txtPrompt.setText("Tap the scoreboard to continue to the next round");
    }

    public void onScoreboardClick()
    {
        // Show a dialog to pass the device to the next player.
        final ImageView iv = (ImageView) findViewById(R.id.imgScoreboardCard);

        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(Game.gameWon) {
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }

                else{
                    showCardCzarDialog();
                }
            }
        });
    }

    public void showCardCzarDialog()
    {
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
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                    startActivity(new Intent(getApplicationContext(), CardCzarRead.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
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

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

}
