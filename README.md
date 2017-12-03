# Easy Native Dialog

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/Haegon/EasyNativeDialog.svg)](https://jitpack.io/#Haegon/EasyNativeDialog)


## Demo

<img src="https://raw.githubusercontent.com/Haegon/EasyNativeDialog/master/extra/Screenshot1.png" width="270px" height="480px"/><br/>
<img src="https://raw.githubusercontent.com/Haegon/EasyNativeDialog/master/extra/Screenshot2.png" width="270px" height="480px"/><br/>
<img src="https://raw.githubusercontent.com/Haegon/EasyNativeDialog/master/extra/Screenshot3.png" width="270px" height="480px"/>


### Download from Google Play

<a href="https://play.google.com/store/apps/details?id=com.gohn.nativedialog.sample"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height="50px"/></a>



## Add to your project

Gradle

* Add it in your root build.gradle at the end of repositories:
```Gradle
allprojects {
    repositories {
    	...
    	maven { url 'https://jitpack.io' }
    }
}
```

* Add the dependency
```Gradle
dependencies {
    compile 'com.github.Haegon:EasyNativeDialog:0.1.0'
}
```


Maven

* Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

* Add the dependency
```xml
<dependency>
    <groupId>com.github.Haegon</groupId>
    <artifactId>EasyNativeDialog</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

### Make Dialog in Java Code
```Java
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


// Custom View Setup (View or resourceId)
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
```

License
=======

    Copyright 2017 Haegon Koh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
