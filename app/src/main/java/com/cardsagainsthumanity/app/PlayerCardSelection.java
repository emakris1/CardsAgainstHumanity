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

/**
 * This class represents the screen on which players choose the cards they wish to submit for the
 * current round. It contains all members and methods necessary to create a new instance of the screen.
 */

public class PlayerCardSelection extends Activity{

    ArrayList<WhiteCard> cardsToSubmit;
    ArrayList<Integer> removeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_card_selection);
        onBlackCardClick();
        setPlayerInfo();
        displayWhiteCardText();
        onWhiteCardClick();

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.player_card_selection, menu);
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

    /**
     * Allows the user to expand the black card to view its prompt. Two black card images are used,
     * one large and one small. When the small card is clicked, it is made invisible and unclickable
     * to the user, and the large card becomes visible and clickable with its text displayed to the user.
     * When the black card is clicked, it is made invisible and unclickable to the user and the
     * small card becomes visible and clickable again.
     */
    public void onBlackCardClick(){

        // All UI elements pertaining to the small black card
        final ImageView imgBlackCardSmall = (ImageView) findViewById(R.id.imgPlayerBlackCardSmall);
        final TextView txtBlackCardSmall = (TextView) findViewById(R.id.txtPlayerBlackCardSmall);
        final ImageView imgBlackCardLarge = (ImageView) findViewById(R.id.imgPlayerBlackCardLarge);
        final TextView txtBlackCardLarge = (TextView) findViewById(R.id.txtPlayerBlackCardLarge);

        // All UI elements pertaining to the large black card
        final TextView txtCurrentPlayer = (TextView) findViewById(R.id.txtCurrentPlayerNum);
        final TextView txtCurrentPoints = (TextView) findViewById(R.id.txtCurrentPlayerPoints);
        final TextView txtSubmitted = (TextView) findViewById(R.id.txtSubmittedCards);

        imgBlackCardSmall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Make the small black card image and text disappear
                imgBlackCardSmall.setVisibility(View.INVISIBLE);
                txtBlackCardSmall.setVisibility(View.INVISIBLE);

                // Make the current player's information disappear
                txtCurrentPlayer.setVisibility(View.GONE);
                txtCurrentPoints.setVisibility(View.GONE);
                txtSubmitted.setVisibility(View.GONE);

                // Make the large black card image and text appear
                imgBlackCardLarge.setVisibility(View.VISIBLE);
                txtBlackCardLarge.setVisibility(View.VISIBLE);
                txtBlackCardLarge.setText(Game.currentBlackCard.getText());
            }
        });

        imgBlackCardLarge.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Make the small black card image and text appear
                imgBlackCardSmall.setVisibility(View.VISIBLE);
                txtBlackCardSmall.setVisibility(View.VISIBLE);

                // Make the current player's information appear
                txtCurrentPlayer.setVisibility(View.VISIBLE);
                txtCurrentPoints.setVisibility(View.VISIBLE);

                // Make the large black card image and text disappear
                txtSubmitted.setVisibility(View.VISIBLE);
                imgBlackCardLarge.setVisibility(View.GONE);
                txtBlackCardLarge.setVisibility(View.GONE);
            }
        });

    }

    // Initializes the current player's information at the start of their turn.
    public void setPlayerInfo(){

        TextView player = (TextView) findViewById(R.id.txtCurrentPlayerNum);
        TextView points = (TextView) findViewById(R.id.txtCurrentPlayerPoints);
        TextView submitted = (TextView) findViewById(R.id.txtSubmittedCards);
        player.setText("Player: " + (Game.currentPlayer + 1));
        points.setText("Awesome Points: " + Game.players.get(Game.currentPlayer).getNumAwesomePoints());
        submitted.setText("Submit Cards: " + "0" + "/" + Game.getCurrentBlackCard().getNumPrompts());
        cardsToSubmit = new ArrayList<WhiteCard>();
        removeIndex = new ArrayList<Integer>();

    }

    // Get every White Card inside of the HorizontalScrollView, make then visible, set
    // their text to correspond to the current player's cards, and make the text black.
    public void displayWhiteCardText(){

        LinearLayout ll = (LinearLayout) findViewById(R.id.LLPlayerCards);
        for (int i = 0; i < ll.getChildCount(); i++){
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

    // Get every White Card inside of the HorizontalScrollView and add a click event
    // listener that submits the selected card and disables it.
    public void onWhiteCardClick(){

        LinearLayout ll = (LinearLayout) findViewById(R.id.LLPlayerCards);
        final TextView submitted = (TextView) findViewById(R.id.txtSubmittedCards);
        for (int i = 0; i < ll.getChildCount(); i++){
            RelativeLayout rl = (RelativeLayout) ll.getChildAt(i);
            final ImageView imgWhiteCard = (ImageView) rl.getChildAt(0);
            final TextView txtWhiteCard = (TextView) rl.getChildAt(1);
            final int index = i;    // white card index
            if (imgWhiteCard != null){
                imgWhiteCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cardsToSubmit.add(Game.players.get(Game.currentPlayer).getPlayerCard(index));
                        removeIndex.add(index);

                        imgWhiteCard.setEnabled(false);
                        imgWhiteCard.setImageAlpha(63);

                        txtWhiteCard.setEnabled(false);
                        txtWhiteCard.setTextColor(Color.GRAY);

                        showToast("Card Submitted!");
                        submitted.setText("Submit Cards: " + cardsToSubmit.size() + "/" + Game.getCurrentBlackCard().getNumPrompts());

                        // Check whether or not more cards need to be submitted.
                        if (cardsToSubmit.size() == Game.currentBlackCard.getNumPrompts()) {
                            for (int i = 0; i < removeIndex.size(); i++) {
                                Game.players.get(Game.currentPlayer).removePlayerCard(removeIndex.get(i));
                                // Make sure there are cards left in the deck to refill the player's hand.
                                try {
                                    WhiteCard tmpCard = Game.whiteDeck.remove();
                                    tmpCard.setOwner(Game.currentPlayer);
                                    Game.players.get(Game.currentPlayer).addPlayerCard(tmpCard);
                                } catch (Exception e) {
                                    Game.gameWon = true;
                                    Game.deckEmpty = true;
                                }
                            }

                            // Add the player's hand to the submitted cards list.
                            Game.submittedCards.add(cardsToSubmit);

                            // Check if every player has submitted a hand to determine if the next
                            // user should be another player or the Card Czar.
                            if (Game.submittedCards.size() == Game.numPlayers - 1) {
                                showCardCzarDialog();
                            } else {
                                Game.switchPlayer();
                                showPlayerDialog();
                            }
                        }
                    }
                });
            }
        }

    }

    /**
     * Shows a toast to the user for confirmation that their card has been submitted.
     * @param text
     */
    public void showToast(CharSequence text){

        Context context = getApplicationContext();
        CharSequence cs = text;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, cs, duration);
        toast.show();

    }

    public void showPlayerDialog(){

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
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(getApplicationContext(), PlayerCardSelection.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;                                                                                   // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

    }

    public void showCardCzarDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Switch to Card Czar");

        if(Game.isDirty) {
            dialogBuilder.setMessage("Please pass the device to\nthe Card Czar (Douchebag " + (Game.currentCardCzar + 1) + ")");
        }

        else{
            dialogBuilder.setMessage("Please pass the device to\nthe Card Czar (Player " + (Game.currentCardCzar + 1) + ")");
        }

        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(getApplicationContext(), CardCzarSelect.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.show();
        TextView txtMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;                                                                               // Dim level. 0.0 - no dim, 1.0 - completely opaque
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
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

    }

}
