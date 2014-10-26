package modernartui.neolpar.com.modernartui;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;


public class mainActivity extends Activity {

    private static String LOGGER = "L";
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<View> boxList = new ArrayList<View>();

        TypedArray boxIdArray = getResources().obtainTypedArray(R.array.boxIdArray);
        for(int i = 0; i < boxIdArray.length(); i++){
            boxList.add((View) findViewById(boxIdArray.getResourceId(i,0)));
        }

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int[] colorBoxArray = getResources().getIntArray(R.array.colorBoxArray);
                int i = 0;
                for(View box: boxList){
                    box.setBackgroundColor(recalculateColor(colorBoxArray[i], progress));
                    i++;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private int recalculateColor(int color, int progress){
        int green = Color.green(color) + progress;

        return Color.argb(
                Color.alpha(color),
                Color.red(color),
                green > 255 ? 255 : green,
                Color.blue(color));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
