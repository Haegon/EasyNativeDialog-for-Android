package com.gohn.nativedialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gohn on 2017. 11. 28..
 */

public class NDialog {
    public static final int BUTTON_POSITIVE = 1;
    public static final int BUTTON_NEGATIVE = 2;
    public static final int BUTTON_NEUTRAL = 4;

    private Context context;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private ButtonType buttonType;

    private View customView;
    private List<View> customViewChildren;
    private Drawable icon;
    private CharSequence title;
    private CharSequence message;
    private CharSequence positiveText;
    private CharSequence negativeText;
    private CharSequence neutralText;
    private int positiveColor;
    private int negativeColor;
    private int neutralColor;
    private ButtonClickListener positiveClickListener;
    private ButtonClickListener negativeClickListener;
    private ButtonClickListener neutralClickListener;
    private CanceledListener canceledListener;
    private CustomViewClickListener customViewClickListener;
    private boolean isPositiveButtonOnClickDismiss = true;
    private boolean isNegativeButtonOnClickDismiss = true;
    private boolean isNeutralButtonOnClickDismiss = true;
    private boolean cancelable = true;


    public NDialog(Context context, ButtonType buttonType) {
        this.context = context;
        this.builder = new AlertDialog.Builder(context);
        this.buttonType = buttonType;
    }

    public void dismiss() {
        if (alertDialog != null) alertDialog.dismiss();
    }

    public NDialog show() {
        if (builder != null) {

            // 커스텀 뷰
            if (customView != null) {
                builder.setView(customView);

                if (customViewClickListener != null && customViewChildren != null) {
                    View.OnClickListener listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customViewClickListener.onClickView(v);
                        }
                    };

                    for (View child : getAllChildViews(customView))
                        child.setOnClickListener(listener);
                }
            }

            // 타이틀 아이콘
            if (icon != null)
                builder.setIcon(icon);
            // 타이틀
            if (title != null)
                builder.setTitle(title);
            // 메세지
            if (message != null)
                builder.setMessage(message);
            // 취소 가능?
            builder.setCancelable(cancelable);
            // 취소 리스너
            if (canceledListener != null) {
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        canceledListener.onCanceled();
                    }
                });
            }

            // 확인 버튼 표시 여부. 확인 버튼 온클릭 리스너
            if ((buttonType.getValue() & BUTTON_POSITIVE) == BUTTON_POSITIVE) {
                builder.setPositiveButton(R.string.btn_ok, null);
            }
            // 취소 버튼 표시 여부. 취소 버튼 온클릭 리스너
            if ((buttonType.getValue() & BUTTON_NEGATIVE) == BUTTON_NEGATIVE) {
                builder.setNegativeButton(R.string.btn_cancel, null);
            }
            // 중립 버튼 표시 여부. 중립 버튼 온클릭 리스너
            if ((buttonType.getValue() & BUTTON_NEUTRAL) == BUTTON_NEUTRAL) {
                builder.setNeutralButton(R.string.btn_option, null);
            }

            // 버튼에 커스텀 글씨, 컬러 표현
            alertDialog = builder.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(final DialogInterface dialog) {
                    if ((buttonType.getValue() & BUTTON_POSITIVE) == BUTTON_POSITIVE) {
                        Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        if (positiveText != null) {
                            button.setText(positiveText);
                        }
                        if (positiveColor != 0) {
                            button.setTextColor(positiveColor);
                        }
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isPositiveButtonOnClickDismiss) {
                                    dismiss();
                                }
                                if (positiveClickListener != null) {
                                    positiveClickListener.onClick(BUTTON_POSITIVE);
                                }
                            }
                        });
                    }
                    if ((buttonType.getValue() & BUTTON_NEGATIVE) == BUTTON_NEGATIVE) {
                        Button button = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        if (negativeText != null) {
                            button.setText(negativeText);
                        }
                        if (negativeColor != 0) {
                            button.setTextColor(negativeColor);
                        }
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isNegativeButtonOnClickDismiss) {
                                    dismiss();
                                }
                                if (negativeClickListener != null) {
                                    negativeClickListener.onClick(BUTTON_NEGATIVE);
                                }
                            }
                        });
                    }
                    if ((buttonType.getValue() & BUTTON_NEUTRAL) == BUTTON_NEUTRAL) {
                        Button button = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                        if (neutralText != null) {
                            button.setText(neutralText);
                        }
                        if (neutralColor != 0) {
                            button.setTextColor(neutralColor);
                        }
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isNeutralButtonOnClickDismiss) {
                                    dismiss();
                                }
                                if (neutralClickListener != null) {
                                    neutralClickListener.onClick(BUTTON_NEUTRAL);
                                }
                            }
                        });
                    }
                }
            });

            alertDialog.show();

            return this;
        }
        return null;
    }

    private List<View> getAllChildViews(View view) {
        List<View> allChildren = new ArrayList<>();

        if (view instanceof ViewGroup) {
            allChildren.addAll(getChildViews((ViewGroup) view));
        }

        return allChildren;
    }

    private List<View> getChildViews(ViewGroup viewGroup) {
        List<View> children = new ArrayList<>();

        for (int index = 0; index < viewGroup.getChildCount(); ++index) {
            View chlid = viewGroup.getChildAt(index);
            children.add(chlid);

            if (chlid instanceof ViewGroup) {
                children.addAll(getChildViews((ViewGroup) chlid));
            }
        }

        return children;
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public List<View> getCustomViewChildren() {
        return customViewChildren;
    }

    public NDialog setCustomView(View customView) {
        this.customView = customView;
        this.customViewChildren = getAllChildViews(this.customView);
        return this;
    }

    public NDialog setCustomView(int resourceId) {
        if (context instanceof Activity) {
            this.customView = ((Activity) context).getLayoutInflater().inflate(resourceId, null);
            this.customViewChildren = getAllChildViews(this.customView);
        }
        return this;
    }

    public NDialog setIcon(Drawable icon) {
        this.icon = icon;
        return this;
    }

    public NDialog setIcon(int resourceId) {
        this.icon = context.getResources().getDrawable(resourceId);
        return this;
    }


    public NDialog setTitle(CharSequence title) {
        this.title = title;
        return this;
    }

    public NDialog setTitle(int resourceId) {
        setTitle(context.getResources().getString(resourceId));
        return this;
    }


    public NDialog setMessage(CharSequence message) {
        this.message = message;
        return this;
    }

    public NDialog setMessage(int resourceId) {
        setMessage(context.getResources().getString(resourceId));
        return this;
    }


    public NDialog setPositiveText(CharSequence positiveText) {
        this.positiveText = positiveText;
        return this;
    }

    public NDialog setPositiveText(int resourceId) {
        setPositiveText(context.getResources().getString(resourceId));
        return this;
    }


    public NDialog setNegativeText(CharSequence negativeText) {
        this.negativeText = negativeText;
        return this;
    }

    public NDialog setNegativeText(int resourceId) {
        setNegativeText(context.getResources().getString(resourceId));
        return this;
    }


    public NDialog setNeutralText(CharSequence neutralText) {
        this.neutralText = neutralText;
        return this;
    }

    public NDialog setNeutralText(int resourceId) {
        setNeutralText(context.getResources().getString(resourceId));
        return this;
    }


    public NDialog setPositiveColor(int positiveColor) {
        this.positiveColor = positiveColor;
        return this;
    }

    public NDialog setPositiveColorResource(int positiveColorResource) {
        this.positiveColor = context.getResources().getColor(positiveColorResource);
        return this;
    }

    public NDialog setNegativeColor(int negativeColor) {
        this.negativeColor = negativeColor;
        return this;
    }

    public NDialog setNegativeColorResource(int negativeColorResource) {
        this.negativeColor = context.getResources().getColor(negativeColorResource);
        return this;
    }

    public NDialog setNeutralColor(int neutralColor) {
        this.neutralColor = neutralColor;
        return this;
    }

    public NDialog setNeutralColorResource(int neutralColorResource) {
        this.neutralColor = context.getResources().getColor(neutralColorResource);
        return this;
    }

    public NDialog setPositiveClickListener(ButtonClickListener positiveClickListener) {
        this.positiveClickListener = positiveClickListener;
        return this;
    }

    public NDialog setNegativeClickListener(ButtonClickListener negativeClickListener) {
        this.negativeClickListener = negativeClickListener;
        return this;
    }

    public NDialog setNeutralClickListener(ButtonClickListener neutralClickListener) {
        this.neutralClickListener = neutralClickListener;
        return this;
    }

    public NDialog setCanceledListener(CanceledListener canceledListener) {
        this.canceledListener = canceledListener;
        return this;
    }

    public NDialog isCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public NDialog setPositiveButtonOnClickDismiss(boolean positiveButtonOnClickDismiss) {
        this.isPositiveButtonOnClickDismiss = positiveButtonOnClickDismiss;
        return this;
    }

    public NDialog setNegativeButtonOnClickDismiss(boolean negativeButtonOnClickDismiss) {
        this.isNegativeButtonOnClickDismiss = negativeButtonOnClickDismiss;
        return this;
    }

    public NDialog setNeutralButtonOnClickDismiss(boolean neutralButtonOnClickDismiss) {
        this.isNeutralButtonOnClickDismiss = neutralButtonOnClickDismiss;
        return this;
    }

    public NDialog setCustomViewClickListener(CustomViewClickListener customViewClickListener) {
        this.customViewClickListener = customViewClickListener;
        return this;
    }
}
