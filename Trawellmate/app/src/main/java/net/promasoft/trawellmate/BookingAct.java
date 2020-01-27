package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.shawnlin.numberpicker.NumberPicker;

import net.promasoft.trawellmate.util.AlineActivityHelper;

import java.util.Locale;

public class BookingAct extends AppCompatActivity {
    String TAG = "booking Act";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        new AlineActivityHelper(BookingAct.this, true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker1);
//        numberPicker.setMaxValue(10);
//        numberPicker.setMinValue(1);
//        numberPicker.setValue(2);
//        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
//            }
//        });
//        NumberPicker numberPickerChild = (NumberPicker) findViewById(R.id.number_picker2);
//        numberPickerChild.setMaxValue(10);
//        numberPickerChild.setMinValue(0);
//        numberPickerChild.setValue(1);
//        numberPickerChild.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
//            }
//        });
    }

}
