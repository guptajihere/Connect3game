package com.example.connect3udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

int player=0;    //0-yellow,  1-red,  2-empty;
   int game[]={2,2,2,2,2,2,2,2,2};
   boolean active=true;
   int winposition[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void drop(View view)
    {
        ImageView a = (ImageView) view;                       // Another way to do it
        Log.d("tag", a.getTag().toString());   // we get the tag of the particular space that we tap
        int tapped=Integer.parseInt(a.getTag().toString()); // the tag of imageview is converted to String which is further converted to an integer
        if(game[tapped]==2&&active)   // to check if the tapped space is empty and the game is over or not,if empty and not over then the turn can be played else not
        {

        game[tapped]=player;
        a.animate().alpha(0);                          //we can also use a.setTranslationY(1000);

        if (player == 0) {
            a.setImageResource(R.drawable.yellow);
            player = 1;
            a.animate().alpha(1).setDuration(1 / 100);        // a.animate().translationYBy(1000).setDuration(1/100);
        }

        else
            {
            a.setImageResource(R.drawable.red);
            player = 0;
            a.animate().alpha(1).setDuration(1 / 100);
            }
        for(int win[]:winposition)   // in every turn the loop will come to an end so if there are statements outside the loop,they will get executed
        {
            if(game[win[0]]==game[win[1]] && game[win[1]]==game[win[2]] &&game[win[1]]!=2)
            { // A player has won
                active =false;
                String winner ="";
                if(player==1)
                {
                    winner = "Yellow";
                }
            else
                {
                    winner="Red";
                }
                //Toast.makeText(this, winner+" is the winner", Toast.LENGTH_LONG).show();
                     Button b=(Button)findViewById(R.id.button);
                TextView t=(TextView)findViewById(R.id.textView);
                t.setText(winner+" is the winner");
                 b.setVisibility(View.VISIBLE);
                t.setVisibility(View.VISIBLE);
                }
             }
        // To check if it's a draw
            if(game[0]!=2&&game[1]!=2&&game[2]!=2&&game[3]!=2&&game[4]!=2&&game[5]!=2&&game[6]!=2&&game[7]!=2&&game[8]!=2&& active)
            {
                Button b=(Button)findViewById(R.id.button);
                TextView t=(TextView)findViewById(R.id.textView);
                t.setText("No winner,it's a draw");
                b.setVisibility(View.VISIBLE);
                t.setVisibility(View.VISIBLE);
            }

        }

       }
    public void play(View view)
    {
        Button b=(Button)findViewById(R.id.button);
        TextView t=(TextView)findViewById(R.id.textView);

        //t.setText(winner+" is the winner");

        b.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);

        GridLayout gridlayout=(GridLayout)findViewById(R.id.grid);
     for(int i=0;i<gridlayout.getChildCount();i++)
     {
        ImageView a=(ImageView)gridlayout.getChildAt(i);  // when we play again the images of red and yellow have to be reset so we remove it in this way
        a.setImageResource(0);
        }

        // we have to now set the original values of the gameboard i.e, reset the values before playing it again
        for(int i=0;i<game.length;i++)
        {
            game[i]=2;
        }

         player =0;
         active=true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Vaibhav's Connect Game");
    }
}