package com.gohn.nativedialog.sample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gohn.nativedialog.ButtonType
import com.gohn.nativedialog.NDialog
import kotlinx.android.synthetic.main.activity_main_kotlin.*

class MainKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)

        btn01.setOnClickListener {
            NDialog(this, ButtonType.ONE_BUTTON)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("title")
                    .setMessage("message")
                    .setPositiveText("긍정")
                    .setNegativeText("부정")
                    .setNeutralText("중립")
                    .setPositiveColor(Color.BLUE)
                    .setNegativeColor(Color.RED)
                    .setNeutralColor(Color.GRAY)
                    .show()
        }
        btn02.setOnClickListener {
            NDialog(this, ButtonType.TWO_BUTTON)
                    .setTitle("title")
                    .setMessage("message")
                    .setPositiveText("")
                    .setNegativeText("부정")
                    .setNeutralText("중립")
                    .setPositiveColor(Color.BLUE)
                    .setNegativeColor(Color.RED)
                    .setNeutralColor(Color.GRAY)
                    .setCanceledListener {
                        toast("canceled")
                    }
                    .show()
        }
        btn03.setOnClickListener {
            NDialog(this, ButtonType.THREE_BUTTON)
                    .setTitle("title")
                    .setMessage("message")
                    .setPositiveColor(Color.RED)
                    .setNegativeColor(Color.BLACK)
                    .setNeutralColor(Color.GRAY)
                    .setNeutralClickListener({

                    })
                    .setNeutralButtonOnClickDismiss(false)
                    .setCanceledListener {
                        toast("canceled")
                    }
                    .show()
        }
        btn04.setOnClickListener {
            NDialog(this, ButtonType.THREE_BUTTON)
                    .setMessage("message")
                    .isCancelable(false)
                    .setPositiveText("긍정")
                    .setPositiveClickListener({
                        toast("positive")
                    })
                    .show()
        }
        btn05.setOnClickListener {
            var ndialog = NDialog(this, ButtonType.NO_BUTTON)
                    .setTitle("title")
                    .setMessage("message")
                    .isCancelable(false)
                    .setPositiveText("긍정")
                    .setPositiveClickListener({
                        toast("positive")
                    })

            ndialog.setCustomView(R.layout.custom_view)
            ndialog.setCustomViewClickListener({
                when (it.id) {
//                    R.id.custom_text -> {
//                        toast("Click TextView")
//                    }
//                    R.id.custom_btn -> {
//                        toast("Click Button")
//                    }
//                    R.id.custom_btn01 -> {
//                        toast("Click Button01")
//                    }
//                    R.id.custom_btn02 -> {
//                        toast("Click Button02")
//                    }
//                    R.id.custom_btn03 -> {
//                        toast("Click Button03")
//                    }
//                    R.id.custom_btn04 -> {
//                        toast("Click Button04")
//                    }
//                    R.id.custom_btn05 -> {
//                        toast("Click Button05")
//                        ndialog.dismiss()
//                        finish()
//                    }
                }
            })

            ndialog.show()
        }
    }

    fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
