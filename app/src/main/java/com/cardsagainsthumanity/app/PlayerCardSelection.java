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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        // Get every White Card inside of the HorizontalScrollView, make then visible, set
        // their text to correspond to the current player's cards, and make the text black.
        LinearLayout ll = (LinearLayout) findViewById(R.id.LLPlayerCards);
        for (int i = 0; i < ll.getChildCount(); i++)
        {
            RelativeLayout rl = (RelativeLayout) ll.getChildAt(i);
            rl.setVisibility(View.VISIBLE);
            TextView tv = (TextView) rl.getChildAt(1);
            tv.setText(Game.players.get(Game.currentPlayer).getPlayerCards().get(i).getText());
            tv.setTextColor(Color.BLACK);
        }
    }

    public void onWhiteCardLongPress()
    {
        LinearLayout ll = (LinearLayout) findViewById(R.id.LLPlayerCards);
        for (int i = 0; i < ll.getChildCount(); i++)
        {
            RelativeLayout rl = (RelativeLayout) ll.getChildAt(i);
            ImageView iv = (ImageView) rl.getChildAt(0);
            if (iv != null)
            {
                iv.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View view)
                    {
                        showToast("Card Submitted!");
                        return true;
                    }
                });
            }
        }
    }

    public void showToast(CharSequence text)
    {
        Context context = getApplicationContext();
        CharSequence cs = text;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, cs, duration);
        toast.show();
    }

}
