package com.gohn.nativedialog.sample;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.gohn.nativedialog.ButtonClickListener;
import com.gohn.nativedialog.ButtonType;
import com.gohn.nativedialog.CanceledListener;
import com.gohn.nativedialog.CustomViewClickListener;
import com.gohn.nativedialog.NDialog;

import java.util.List;

/**
 * Created by gohn on 2017. 12. 2..
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        final View layoutButtonCount = findViewById(R.id.layout_button_count);
        final View layoutPositive = findViewById(R.id.layout_positive);
        final View layoutNegative = findViewById(R.id.layout_negative);
        final View layoutNeutral = findViewById(R.id.layout_neutral);
        final View layoutTitle = findViewById(R.id.layout_title);
        final View layoutMessage = findViewById(R.id.layout_message);
        final View layoutCancelable = findViewById(R.id.layout_cancelable);

        final RadioGroup rgButtonCount = (RadioGroup) findViewById(R.id.rg_button_count);
        final RadioGroup rgIcon = (RadioGroup) findViewById(R.id.rg_icon);

        final EditText etPositive = (EditText) findViewById(R.id.et_positive);
        final EditText etNegative = (EditText) findViewById(R.id.et_negative);
        final EditText etNeutral = (EditText) findViewById(R.id.et_neutral);
        final EditText etTitle = (EditText) findViewById(R.id.et_title);
        final EditText etMessage = (EditText) findViewById(R.id.et_message);

        final CheckBox cbPositiveNull = (CheckBox) findViewById(R.id.cb_positive_isnull);
        final CheckBox cbPositiveOnClick = (CheckBox) findViewById(R.id.cb_positive_onclick);
        final CheckBox cbPositiveDismiss = (CheckBox) findViewById(R.id.cb_positive_dismiss);
        final CheckBox cbNegativeNull = (CheckBox) findViewById(R.id.cb_negative_isnull);
        final CheckBox cbNegativeOnClick = (CheckBox) findViewById(R.id.cb_negative_onclick);
        final CheckBox cbNegativeDismiss = (CheckBox) findViewById(R.id.cb_negative_dismiss);
        final CheckBox cbNeutralNull = (CheckBox) findViewById(R.id.cb_neutral_isnull);
        final CheckBox cbNeutralOnClick = (CheckBox) findViewById(R.id.cb_neutral_onclick);
        final CheckBox cbNeutralDismiss = (CheckBox) findViewById(R.id.cb_neutral_dismiss);
        final CheckBox cbTitleNull = (CheckBox) findViewById(R.id.cb_title_isnull);
        final CheckBox cbMessageNull = (CheckBox) findViewById(R.id.cb_message_isnull);
        final CheckBox cbCancelable = (CheckBox) findViewById(R.id.cb_cancelable_outside);
        final CheckBox cbCancelListener = (CheckBox) findViewById(R.id.cb_cancel_listener);
        final CheckBox cbCustomViewAdd = (CheckBox) findViewById(R.id.cb_customview_add);
        final CheckBox cbCustomViewOnClick = (CheckBox) findViewById(R.id.cb_customview_onclick);
        final CheckBox cbCustomViewGetChildren = (CheckBox) findViewById(R.id.cb_customview_get_childviews);

        final View colorPositive = findViewById(R.id.color_positive);
        final View colorNegative = findViewById(R.id.color_negative);
        final View colorNeutral = findViewById(R.id.color_neutral);

        rgButtonCount.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_count_0:
                        layoutPositive.setVisibility(View.GONE);
                        layoutNegative.setVisibility(View.GONE);
                        layoutNeutral.setVisibility(View.GONE);
                        break;
                    case R.id.radio_button_count_1:
                        layoutPositive.setVisibility(View.VISIBLE);
                        layoutNegative.setVisibility(View.GONE);
                        layoutNeutral.setVisibility(View.GONE);
                        break;
                    case R.id.radio_button_count_2:
                        layoutPositive.setVisibility(View.VISIBLE);
                        layoutNegative.setVisibility(View.VISIBLE);
                        layoutNeutral.setVisibility(View.GONE);
                        break;
                    case R.id.radio_button_count_3:
                        layoutPositive.setVisibility(View.VISIBLE);
                        layoutNegative.setVisibility(View.VISIBLE);
                        layoutNeutral.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        rgButtonCount.check(R.id.radio_button_count_1);
        rgIcon.check(R.id.radio_icon_0);

        colorPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(MainActivity.this)
                        .setTitle("Choose Color")
                        .initialColor(((ColorDrawable) colorPositive.getBackground()).getColor())
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setPositiveButton("OK", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                colorPositive.setBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });
        colorNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(MainActivity.this)
                        .setTitle("Choose Color")
                        .initialColor(((ColorDrawable) colorNegative.getBackground()).getColor())
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setPositiveButton("OK", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                colorNegative.setBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });
        colorNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(MainActivity.this)
                        .setTitle("Choose Color")
                        .initialColor(((ColorDrawable) colorNeutral.getBackground()).getColor())
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setPositiveButton("OK", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                colorNeutral.setBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });

        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bottom Button Type
                ButtonType buttonType = null;
                switch (rgButtonCount.getCheckedRadioButtonId()) {
                    case R.id.radio_button_count_0:
                        buttonType = ButtonType.NO_BUTTON;
                        break;
                    case R.id.radio_button_count_1:
                        buttonType = ButtonType.ONE_BUTTON;
                        break;
                    case R.id.radio_button_count_2:
                        buttonType = ButtonType.TWO_BUTTON;
                        break;
                    case R.id.radio_button_count_3:
                        buttonType = ButtonType.THREE_BUTTON;
                        break;
                }

                NDialog nDialog = new NDialog(MainActivity.this, buttonType);

                // Positive Button
                if (!cbPositiveNull.isChecked()) {
                    nDialog.setPositiveText(etPositive.getText().toString());
                }
                if (cbPositiveOnClick.isChecked()) {
                    nDialog.setPositiveClickListener(new ButtonClickListener() {
                        @Override
                        public void onClick(int button) {
                            toast("Positive Button Clicked");
                        }
                    });
                }
                nDialog.setPositiveButtonOnClickDismiss(cbPositiveDismiss.isChecked());
                nDialog.setPositiveColor(((ColorDrawable) colorPositive.getBackground()).getColor());

                // Negative Button
                if (!cbNegativeNull.isChecked()) {
                    nDialog.setNegativeText(etNegative.getText().toString());
                }
                if (cbNegativeOnClick.isChecked()) {
                    nDialog.setNegativeClickListener(new ButtonClickListener() {
                        @Override
                        public void onClick(int button) {
                            toast("Negative Button Clicked");
                        }
                    });
                }
                nDialog.setNegativeButtonOnClickDismiss(cbNegativeDismiss.isChecked());
                nDialog.setNegativeColor(((ColorDrawable) colorNegative.getBackground()).getColor());

                // Neutral Button
                if (!cbNeutralNull.isChecked()) {
                    nDialog.setNeutralText(etNeutral.getText().toString());
                }
                if (cbNeutralOnClick.isChecked()) {
                    nDialog.setNeutralClickListener(new ButtonClickListener() {
                        @Override
                        public void onClick(int button) {
                            toast("Neutral Button Clicked");
                        }
                    });
                }
                nDialog.setNeutralButtonOnClickDismiss(cbNeutralDismiss.isChecked());
                nDialog.setNeutralColor(((ColorDrawable) colorNeutral.getBackground()).getColor());

                // Icon
                switch (rgIcon.getCheckedRadioButtonId()) {
                    case R.id.radio_icon_0:
                        break;
                    case R.id.radio_icon_1:
                        nDialog.setIcon(R.drawable.help);
                        break;
                    case R.id.radio_icon_2:
                        nDialog.setIcon(R.drawable.info);
                        break;
                }

                // Title
                if (!cbTitleNull.isChecked()) {
                    String title = etTitle.getText().toString();

                    SpannableString spannablecontent = new SpannableString(title);
                    spannablecontent.setSpan(new StyleSpan(Typeface.BOLD), 0, spannablecontent.length(), 0);

                    nDialog.setTitle(spannablecontent);
                }

                // Message
                if (!cbMessageNull.isChecked()) {
                    nDialog.setMessage(etMessage.getText().toString());
                }

                // Cancelable
                nDialog.isCancelable(cbCancelable.isChecked());
                if (cbCancelListener.isChecked()) {
                    nDialog.setCanceledListener(new CanceledListener() {
                        @Override
                        public void onCanceled() {
                            toast("Dialog Canceled");
                        }
                    });
                }

                // CustomView
                if (cbCustomViewAdd.isChecked()) {
                    nDialog.setCustomView(R.layout.custom_view);
                    // OnClick Event for All Children
                    if (cbCustomViewOnClick.isChecked()) {
                        nDialog.setCustomViewClickListener(new CustomViewClickListener() {
                            @Override
                            public void onClickView(View v) {
                                if (v.getId() == R.id.custom_text) {
                                    toast("Custom View Text Clicked");
                                } else if (v.getId() == R.id.custom_btn) {
                                    toast("Custom View Button Clicked");
                                }
                            }
                        });
                    }
                    // User can customize event handler of custom view
                    if (cbCustomViewGetChildren.isChecked()) {
                        List<View> customChildren = nDialog.getCustomViewChildren();
                        if (customChildren != null) {
                            for (View child : customChildren) {
                                if (child.getId() == R.id.custom_switch) {
                                    ((Switch) child).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                            toast("Custom View Switch Checked : " + isChecked);
                                        }
                                    });
                                } else if (child.getId() == R.id.custom_checkbox) {
                                    ((CheckBox) child).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                            toast("Custom View CheckBox Checked : " + isChecked);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }

                // Show Dialog
                nDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                String url = "https://github.com/Haegon/ParallaxViewPager";
                Intent iGithub = new Intent(Intent.ACTION_VIEW);
                iGithub.setData(Uri.parse(url));
                startActivity(iGithub);
                break;
            case R.id.action_feedback:
                Intent iEmail = new Intent(Intent.ACTION_SEND);
                iEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"gohn0929@gmail.com"});
                iEmail.setType("message/rfc822");
                startActivity(Intent.createChooser(iEmail, "Select Email Application"));
                break;
            case R.id.action_instagram:
                Uri uri = Uri.parse("http://instagram.com/_u/gohn__");
                Intent iInstagram = new Intent(Intent.ACTION_VIEW, uri);

                iInstagram.setPackage("com.instagram.android");
                try {
                    startActivity(iInstagram);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/gohn__")));
                }
                break;
            case R.id.action_share:
                Intent iShare = new Intent(android.content.Intent.ACTION_SEND);
                iShare.setType("text/plain");
                iShare.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(Intent.createChooser(iShare, "Select Application for Sharing"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
