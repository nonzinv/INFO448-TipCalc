package edu.uw.ischool.nonzinv.tipcalc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var BillAmount: EditText
    private lateinit var TipButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BillAmount = findViewById(R.id.BillAmount)
        TipButton = findViewById(R.id.TipButton)
        TipButton.isEnabled = false
        BillAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                enableButton(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        TipButton.setOnClickListener {
            calculate()
        }
    }

    private fun enableButton(amount: String) {
        TipButton.isEnabled = amount.isNotEmpty()
    }

    private fun calculate() {
        val amount = BillAmount.text.toString().replace("$", "").toDoubleOrNull()
        val tip = amount?.times(0.15)
        val total = DecimalFormat("$#.##").format(tip)
        Toast.makeText(this, "Tip: $total", Toast.LENGTH_SHORT).show()
    }
}