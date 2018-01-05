package health.fitness.workout.a5minuteworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity
{
    ImageView imgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgv = (ImageView) findViewById(R.id.imgv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "5 Minute Workout");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                Toast.makeText(getApplicationContext(), "Share with others", Toast.LENGTH_SHORT).show();
            }
        });


        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        TypeWriterTextView animateT = (TypeWriterTextView) findViewById(R.id.textInfo);
        animateT.setText("");
        animateT.setCharacterDelay(120);
        animateT.displayTextWithAnimation(getString(R.string.complete));





    }
    @Override
    public void onBackPressed()
    {
        finish();
    }

}
