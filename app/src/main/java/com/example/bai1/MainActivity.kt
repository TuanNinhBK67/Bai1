package com.example.bai1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    lateinit var editText: EditText
    lateinit var sochan: RadioButton
    lateinit var sole: RadioButton
    lateinit var sochinhphuong: RadioButton
    lateinit var show: Button
    lateinit var textView: TextView
    lateinit var listView: ListView
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Khởi tạo các thành phần giao diện
        editText = findViewById(R.id.editText)
        sochan = findViewById(R.id.sochan)
        sole = findViewById(R.id.sole)
        sochinhphuong = findViewById(R.id.sochinhphuong)
        show = findViewById(R.id.submit)
        textView = findViewById(R.id.notification)
        radioGroup = findViewById(R.id.radioGroup)
        listView = findViewById(R.id.listView)

        // Thiết lập sự kiện click cho nút show
        show.setOnClickListener {
            // Lấy và kiểm tra đầu vào từ EditText
            val checkText = editText.text.toString()
            if (!isNumeric(checkText) || checkText.toInt() <= 0) {
                textView.text = "Vui lòng nhập lại số nguyên dương"
                return@setOnClickListener
            }

            // Lấy giá trị n từ EditText sau khi đã xác thực
            val n = checkText.toInt()
            val items = mutableListOf<Int>()
            val selectedId = radioGroup.checkedRadioButtonId

            // Kiểm tra RadioButton được chọn và thêm giá trị tương ứng vào danh sách
            when (selectedId) {
                sochan.id -> {
                    for (i in 0..n) {
                        if (i % 2 == 0) items.add(i)
                    }
                }
                sole.id -> {
                    for (i in 0..n) {
                        if (i % 2 != 0) items.add(i)
                    }
                }
                sochinhphuong.id -> {
                    var i = 0
                    while (i * i <= n) {
                        items.add(i * i)
                        i++
                    }
                }
                else -> {
                    textView.text = "Vui lòng chọn một tùy chọn"
                    return@setOnClickListener
                }
            }

            // Thiết lập adapter cho ListView để hiển thị kết quả
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
            listView.adapter = adapter
            listView.setOnItemClickListener { _, _, i, _ ->
                textView.text = "Vị trí: $i\nGiá trị: ${items[i]}"
            }
        }
    }

    // Hàm kiểm tra chuỗi có phải là số nguyên hay không
    fun isNumeric(input: String): Boolean {
        return input.toIntOrNull() != null
    }
}
