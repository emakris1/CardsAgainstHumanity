package com.cardsagainsthumanity.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import java.util.ArrayList;


public class PlayerCardSelection extends Activity
{

    ArrayList<WhiteCard> cardsToSubmit;
    ArrayList<Integer> removeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_card_selection);
        onBlackCardClick();
        setPlayerInfo();
        displayWhiteCardText();
        onWhiteCardClick();
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

    @Override
    public void onBackPressed()
    {
        showQuitDialog();
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
                imgBlackCardSmall.setVisibility(View.INVISIBLE);
                txtBlackCardSmall.setVisibility(View.INVISIBLE);
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
        submitted.setText("Submit Cards: " + "0" + "/" + Game.getCurrentBlackCard().getNumPrompts());
        cardsToSubmit = new ArrayList<WhiteCard>();
        removeIndex = new ArrayList<Integer>();
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

            ImageView imgWhiteCard = (ImageView) rl.getChildAt(0);
            imgWhiteCard.setEnabled(true);
            System.out.println(imgWhiteCard.getImageAlpha());
            imgWhiteCard.setImageAlpha(255);

            TextView txtWhiteCard = (TextView) rl.getChildAt(1);
            txtWhiteCard.setEnabled(true);
            txtWhiteCard.setTextColor(Color.BLACK);
            txtWhiteCard.setText(Game.players.get(Game.currentPlayer).getPlayerCard(i).getText());
        }
    }

    public void onWhiteCardClick()
    {
        // Get every White Card inside of the HorizontalScrollView and add a long click event
        // listener that submits the selected card and disables it.
        LinearLayout ll = (LinearLayout) findViewById(R.id.LLPlayerCards);
        final TextView submitted = (TextView) findViewById(R.id.txtSubmittedCards);
        for (int i = 0; i < ll.getChildCount(); i++)
        {
            RelativeLayout rl = (RelativeLayout) ll.getChildAt(i);
            final ImageView imgWhiteCard = (ImageView) rl.getChildAt(0);
            final TextView txtWhiteCard = (TextView) rl.getChildAt(1);
            final int index = i;    // white card index
            if (imgWhiteCard != null)
            {
                imgWhiteCard.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        cardsToSubmit.add(Game.players.get(Game.currentPlayer).getPlayerCard(index));
                        removeIndex.add(index);

                        imgWhiteCard.setEnabled(false);
                        imgWhiteCard.setImageAlpha(63);

                        txtWhiteCard.setEnabled(false);
                        txtWhiteCard.setTextColor(Color.GRAY);

                        showToast("Card Submitted!");
                        submitted.setText("Submit Cards: " + cardsToSubmit.size() + "/" + Game.getCurrentBlackCard().getNumPrompts());

                        if (cardsToSubmit.size() == Game.currentBlackCard.getNumPrompts())
                        {
                            for(int i = 0; i < removeIndex.size(); i++) {
                                Game.players.get(Game.currentPlayer).removePlayerCard(removeIndex.get(i));
                                try {
                                    WhiteCard tmpCard = Game.whiteDeck.remove();
                                    tmpCard.setOwner(Game.currentPlayer);
                                    Game.players.get(Game.currentPlayer).addPlayerCard(tmpCard);
                                }catch(Exception e){
                                    Game.gameWon = true;
                                    Game.deckEmpty = true;
                                }
                            }

                            Game.submittedCards.add(cardsToSubmit);

                            if(Game.submittedCards.size() == Game.numPlayers - 1) {
                                showCardCzarDialog();
                            }

                            else{
                               Game.switchPlayer();
                               showPlayerDialog();
                            }
                        }
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

    public void showPlayerDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        if(Game.isDirty) {
            dialogBuilder.setTitle("Switch to Next Douchebag");
            dialogBuilder.setMessage("Please pass the device to\nDouchebag " + (Game.currentPlayer + 1));
        }

        else{
            dialogBuilder.setTitle("Switch to Next Player");
            dialogBuilder.setMessage("Please pass the device to\nPlayer " + (Game.currentPlayer + 1));
        }

        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                startActivity(new Intent(getApplicationContext(), PlayerCardSelection.class));
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

    public void showCardCzarDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Switch to Card Czar");
        if(Game.isDirty) {
            dialogBuilder.setMessage("Please pass the device to\nthe Card Czar (Douchebag " + (Game.currentCardCzar + 1) + ")");
        }

        else{
            dialogBuilder.setMessage("Please pass the device to\nthe Card Czar (Player " + (Game.currentCardCzar + 1) + ")");
        }

        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                startActivity(new Intent(getApplicationContext(), CardCzarSelect.class));
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
