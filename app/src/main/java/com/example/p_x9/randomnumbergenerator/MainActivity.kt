package com.example.p_x9.randomnumbergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.generateButton)
        var numbers:ArrayList<Int> = arrayListOf<Int>()
        button.setOnClickListener {
            //ボタンの処理
            //var isValid = true
            val minfield = findViewById<TextView>(R.id.minField)
            val maxfield = findViewById<TextView>(R.id.maxField)
            val numberLabel = findViewById<TextView>(R.id.numberTextView)
            var min = minField.text.toString()
            var max = maxField.text.toString()

            if(min.isEmpty()||max.isEmpty()){
            maxfield.error = "入力してください"
            minfield.error = "入力してください"
            }
            else if (Integer.parseInt(min) >= Integer.parseInt(max)) {
                maxfield.error = "最大値は最小値より大きくしてください"
            }
            else{
                var randomNumber:Int
                val range = (Integer.parseInt(min)..Integer.parseInt(max))

                if (range.count() == numbers.count() && uniqueSwitch.isChecked){
                    numberLabel.text = "全て生成しました。"
                }
                else {
                    do {
                        randomNumber = Random().nextInt(Integer.parseInt(max) + 1 - Integer.parseInt(min)) + Integer.parseInt(min)

                    } while (uniqueSwitch.isChecked && numbers.contains(randomNumber))
                    numbers.add(randomNumber)
                    print(numbers)
                    numberLabel.text = randomNumber.toString()
                    val adaptor = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1,numbers)
                    numberTable.adapter = adaptor
                   // numberTable.scrollTo(0,numbers.count()*20)

                }
            }

        }
        uniqueSwitch.isChecked = false

        uniqueSwitch.setOnClickListener {
            val check = uniqueSwitch.isChecked
            if (!check){
                numbers = arrayListOf<Int>()
                val adaptor = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1,numbers)
                numberTable.adapter = adaptor
            }
        }
    }
}
