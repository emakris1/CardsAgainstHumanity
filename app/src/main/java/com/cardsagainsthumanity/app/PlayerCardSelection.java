package com.cardsagainsthumanity.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;


public class PlayerCardSelection extends Activity
{
    private ViewGroup.LayoutParams lpOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_card_selection);
        onBlackCardClick();
        setPlayerInfo();
        displayWhiteCardText();
        onWhiteCardLongPress();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.player_card_selection, menu);
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

    public void onBlackCardClick()
    {
        final ImageView imgBlackCardSmall = (ImageView) findViewById(R.id.imgPlayerBlackCardSmall);
        final TextView txtBlackCardSmall = (TextView) findViewById(R.id.txtPlayerBlackCardSmall);
        final ImageView imgBlackCardLarge = (ImageView) findViewById(R.id.imgPlayerBlackCardLarge);
        final TextView txtBlackCardLarge = (TextView) findViewById(R.id.txtPlayerBlackCardLarge);
        final TextView txtCurrentPlayer = (TextView) findViewById(R.id.txtCurrentPlayerNum);
        final TextView txtCurrentPoints = (TextView) findViewById(R.id.txtCurrentPlayerPoints);
        final TextView txtSubmitted = (TextView) findViewById(R.id.txtSubmittedCards);

        imgBlackCardSmall.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                imgBlackCardSmall.setVisibility(View.GONE);
                txtBlackCardSmall.setVisibility(View.GONE);
                txtCurrentPlayer.setVisibility(View.GONE);
                txtCurrentPoints.setVisibility(View.GONE);
                txtSubmitted.setVisibility(View.GONE);
                imgBlackCardLarge.setVisibility(View.VISIBLE);
                txtBlackCardLarge.setVisibility(View.VISIBLE);
                txtBlackCardLarge.setText(Game.currentBlackCard.getText());

            }
        });

        imgBlackCardLarge.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                imgBlackCardSmall.setVisibility(View.VISIBLE);
                txtBlackCardSmall.setVisibility(View.VISIBLE);
                txtCurrentPlayer.setVisibility(View.VISIBLE);
                txtCurrentPoints.setVisibility(View.VISIBLE);
                txtSubmitted.setVisibility(View.VISIBLE);
                imgBlackCardLarge.setVisibility(View.GONE);
                txtBlackCardLarge.setVisibility(View.GONE);

            }
        });
    }

    public void setPlayerInfo()
    {
        TextView player = (TextView) findViewById(R.id.txtCurrentPlayerNum);
        TextView points = (TextView) findViewById(R.id.txtCurrentPlayerPoints);
        TextView submitted = (TextView) findViewById(R.id.txtSubmittedCards);
        player.setText("Player: " + (Game.currentPlayer + 1));
        points.setText("Awesome Points: " + Game.players.get(Game.currentPlayer).getNumAwesomePoints());
        submitted.setText("Submit Cards: " + Game.getCurrentBlackCard().getNumPrompts());
    }

    public void displayWhiteCardText()
    {
        TextView text;

        text = (TextView) findViewById(R.id.txtWhiteCard1);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(0).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard2);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(1).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard3);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(2).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard4);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(3).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard5);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(4).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard6);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(5).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard7);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(6).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard8);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(7).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard9);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(8).getText());
        text.setTextColor(Color.BLACK);

        text = (TextView) findViewById(R.id.txtWhiteCard10);
        text.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(9).getText());
        text.setTextColor(Color.BLACK);
    }

    public void onWhiteCardLongPress()
    {
        
    }

}
