package com.gohn.nativedialog.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.gohn.nativedialog.ButtonClickListener;
import com.gohn.nativedialog.ButtonType;
import com.gohn.nativedialog.CanceledListener;
import com.gohn.nativedialog.CustomViewClickListener;
import com.gohn.nativedialog.NDialog;

import java.util.List;

public class SimpleActivity extends AppCompatActivity {
    String TAG = SimpleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        // Constructor with Bottom Button Count
        NDialog nDialog = new NDialog(SimpleActivity.this, ButtonType.THREE_BUTTON);

        // Title Icon
        nDialog.setIcon(R.drawable.help);

        // Title Text
        nDialog.setTitle("This is title");

        // Content Message
        nDialog.setMessage(R.string.dialog_message);


        // Bottom Button OnClick Event Handler
        ButtonClickListener buttonClickListener = new ButtonClickListener() {
            @Override
            public void onClick(int button) {
                switch (button) {
                    case NDialog.BUTTON_POSITIVE:
                        Log.d(TAG, "Positive Button Clicked");
                        break;
                    case NDialog.BUTTON_NEGATIVE:
                        Log.d(TAG, "Negative Button Clicked");
                        break;
                    case NDialog.BUTTON_NEUTRAL:
                        Log.d(TAG, "Neutral Button Clicked");
                        break;
                }
            }
        };

        // Positive Button
        nDialog.setPositiveText("OKAY");
        nDialog.setPositiveColor(Color.BLUE);
        nDialog.setPositiveButtonOnClickDismiss(false); // default : true
        nDialog.setPositiveClickListener(buttonClickListener);

        // Negative Button
        nDialog.setNegativeText("NOPE");
        nDialog.setNegativeColor(Color.parseColor("#FF0000"));
        nDialog.setNegativeButtonOnClickDismiss(true); // default : true
        nDialog.setNegativeClickListener(buttonClickListener);

        // Neutral Button
        nDialog.setNeutralText(R.string.neutral);
        nDialog.setNeutralColor(R.color.neutral);
        nDialog.setNeutralButtonOnClickDismiss(false); // default : true
        nDialog.setNeutralClickListener(buttonClickListener);

        // Cancelable
        nDialog.isCancelable(false); // default : true
        nDialog.setCanceledListener(new CanceledListener() {
            @Override
            public void onCanceled() {
                Log.d(TAG, "Dialog Canceled");
            }
        });


        // Custom View Set up (View or resourceId)
        nDialog.setCustomView(R.layout.custom_view);
        // Handle Only 'OnClick Event' On Custom View
        nDialog.setCustomViewClickListener(new CustomViewClickListener() {
            @Override
            public void onClickView(View v) {
                switch (v.getId()) {
                    case R.id.custom_text:
                        Log.d(TAG, "Custom View Text Clicked");
                        break;
                    case R.id.custom_btn:
                        Log.d(TAG, "Custom View Button Clicked");
                        break;
                }
            }
        });
        // Handle View Event by User
        List<View> childViews = nDialog.getCustomViewChildren();
        for (View childView : childViews) {
            switch (childView.getId()) {
                case R.id.custom_switch:
                    ((Switch) childView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            Log.d(TAG, "Custom View Switch Checked : " + isChecked);
                        }
                    });
                    break;
                case R.id.custom_checkbox:
                    ((CheckBox) childView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            Log.d(TAG, "Custom View CheckBox Checked : " + isChecked);
                        }
                    });
                    break;
            }
        }


        // SHOW DIALOG
        nDialog.show();
    }
}
