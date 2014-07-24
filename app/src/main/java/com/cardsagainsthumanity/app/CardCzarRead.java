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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class CardCzarRead extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_czar_read);
        displayBlackCardText();
        onBlackCardClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.card_czar_read, menu);
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

    public void displayBlackCardText()
    {
        TextView text = (TextView) findViewById(R.id.txtCZBlackCardLarge);
        Game.setCurrentBlackCard();
        text.setText(Game.getCurrentBlackCard().getText());
    }

    public void onBlackCardClick()
    {
        final ImageView blackCard = (ImageView) findViewById(R.id.imgCZBlackCardLarge);

        blackCard.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                showDialog();
            }
        });
    }

    public void showDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Switch to Player " + (Game.currentPlayer + 1));
        dialogBuilder.setMessage("Please pass the device to Player " + (Game.currentPlayer + 1));
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                startActivity(new Intent(getApplicationContext(), PlayerCardSelection.class));
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 1;     // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
    }

}
