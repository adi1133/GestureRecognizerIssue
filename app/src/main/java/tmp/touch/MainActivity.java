package tmp.touch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import it.sephiroth.android.library.uigestures.UIGestureRecognizer;
import it.sephiroth.android.library.uigestures.UIGestureRecognizerDelegate;
import it.sephiroth.android.library.uigestures.UIPanGestureRecognizer;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        final TextView textView = (TextView) findViewById(R.id.text);
        final UIGestureRecognizerDelegate delegate = new UIGestureRecognizerDelegate(new UIGestureRecognizerDelegate.Callback() {
            @Override
            public boolean shouldBegin(UIGestureRecognizer uiGestureRecognizer) {
                return true;
            }

            @Override
            public boolean shouldRecognizeSimultaneouslyWithGestureRecognizer(UIGestureRecognizer uiGestureRecognizer, UIGestureRecognizer uiGestureRecognizer1) {
                return true;
            }

            @Override
            public boolean shouldReceiveTouch(UIGestureRecognizer uiGestureRecognizer) {
                return true;
            }
        });

        final UIPanGestureRecognizer recognizer = new UIPanGestureRecognizer(this);
        recognizer.setMinimumNumberOfTouches(1);
        recognizer.setMaximumNumberOfTouches(1);
        recognizer.setActionListener(new UIGestureRecognizer.OnActionListener() {
            @Override
            public void onGestureRecognized(@NonNull UIGestureRecognizer unused) {
                Log.d("onGestureRecognized", recognizer.getState().toString());
                textView.setText("State: " + recognizer.getState().toString());
            }
        });

        delegate.addGestureRecognizer(recognizer);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return delegate.onTouchEvent(textView, event);
            }
        });
    }
}
