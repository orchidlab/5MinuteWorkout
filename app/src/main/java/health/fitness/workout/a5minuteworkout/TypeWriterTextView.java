package health.fitness.workout.a5minuteworkout;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

/**
 * Created by Rabbi on 1/3/2018.
 */

public class TypeWriterTextView  extends android.support.v7.widget.AppCompatTextView
{
    private CharSequence sequence;
    private int mIndex;
    private  long delay = 150;
    public TypeWriterTextView(Context context)
    {
        super(context);

    }

    public TypeWriterTextView(Context context, AttributeSet attrs)
    {
        super(context , attrs);

    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            setText(sequence.subSequence(0,mIndex++));
            if (mIndex <= sequence.length())
            {
                handler.postDelayed(runnable,delay);
            }
        }
    };

    public void displayTextWithAnimation(CharSequence text)
    {
        sequence = text;
        mIndex = 0;
        setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delay);

    }

    public  void setCharacterDelay( long m)
    {
        delay = m;

    }
}
