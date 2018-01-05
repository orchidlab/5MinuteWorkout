package health.fitness.workout.a5minuteworkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public ProgressBar pr1;
    private int pStatus = 0;
    public TextView tvm;
    ImageView imgv;
    private static int SPLASH_TIME_OUT = 151880;

    // int status;
    private Handler handler = new Handler();
    // boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvm = (TextView) findViewById(R.id.tvmain);

        imgv = (ImageView) findViewById(R.id.imgv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TypeWriterTextView animateT = (TypeWriterTextView) findViewById(R.id.textInfo);
        animateT.setText("");
        animateT.setCharacterDelay(120);
        animateT.displayTextWithAnimation(getString(R.string.pushup));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "5 Minute Workout");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                Toast.makeText(getApplicationContext(), "Share with others", Toast.LENGTH_SHORT).show();
            }
        });

        tvm.setOnClickListener(new View.OnClickListener()
        {
            public  void onClick(View v)
            {
                if (v==tvm)
                {
                    progress1();
                    glideimg();

                    TypeWriterTextView animateT = (TypeWriterTextView) findViewById(R.id.textInfo);
                    animateT.setText("");
                    animateT.setCharacterDelay(120);
                    animateT.displayTextWithAnimation(getString(R.string.go));

                    new Handler().postDelayed(new Runnable()
                    {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                        @Override
                        public void run()
                        {

                            // Start your app main activity after time is over
                            Intent i = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(i);

                            // close this activity
                            finish();
                        }
                    }, SPLASH_TIME_OUT);

                }


                }





        });

    }
    @Override
    public void onBackPressed()
    {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void glideimg()
    {

        ImageView imageView = (ImageView) findViewById(R.id.imgv);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.drawable.pushup).into(imageViewTarget);
    }


    public void progress1()
    {
       pr1 = (ProgressBar) findViewById(R.id.progressBar);

        pStatus= 0;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (pStatus<20)
                {
                    pStatus+=1;
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            pr1.setProgress(pStatus);
                            tvm.setText(+pStatus + "/" + pr1.getMax());

                            if (pStatus==20)
                            {
                                vibrating();
                                tvm.setText("Completed");
                                imgv.setImageResource(R.drawable.frontsquat);

                                TypeWriterTextView animateT = (TypeWriterTextView) findViewById(R.id.textInfo);
                                animateT.setText("");
                                animateT.setCharacterDelay(120);
                                animateT.displayTextWithAnimation(getString(R.string.front));
                            }

                        }
                    });
                    try
                    {
                        Thread.sleep(6000);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }


        }).start();

    }



    public void vibrating()
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 400 milliseconds
        v.vibrate(400);

        //settext here
    }
}

