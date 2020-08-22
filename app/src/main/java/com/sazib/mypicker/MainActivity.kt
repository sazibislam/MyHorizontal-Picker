package com.sazib.mypicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sazib.mypicker.picker.CustomSliderAdapter
import com.sazib.mypicker.picker.ScreenUtils
import com.sazib.mypicker.picker.SliderLayoutManager
import kotlinx.android.synthetic.main.activity_main.numberPicker

class MainActivity : AppCompatActivity() {

  private val data = (1..20).toList()
      .map { it.toString() } as ArrayList<String>
  private lateinit var rvHorizontalPicker: RecyclerView
  private lateinit var sliderAdapter: CustomSliderAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setHorizontalPicker()

/*
* for the below picker
* */
    numberPicker.value = 12
    numberPicker.minValue = 0
    numberPicker.setMaxValue(24)
  }

  private fun setHorizontalPicker() {
    rvHorizontalPicker = findViewById(R.id.rv_horizontal_picker)

    // Setting the padding such that the items will appear in the middle of the screen
    val padding: Int = ScreenUtils.getScreenWidth(this) / 2 - ScreenUtils.dpToPx(this, 40)
    rvHorizontalPicker.setPadding(padding, 0, padding, 0)

    // Setting layout manager
    rvHorizontalPicker.layoutManager = SliderLayoutManager(this).apply {
      callback = object : SliderLayoutManager.OnItemSelectedListener {
        override fun onItemSelected(layoutPosition: Int) {
          sliderAdapter.setSelectedItem(layoutPosition)
          Log.d("selected text", data[layoutPosition])
          Toast.makeText(this@MainActivity, data[layoutPosition], Toast.LENGTH_SHORT).show()
        }
      }
    }

    // Setting Adapter
    sliderAdapter = CustomSliderAdapter()
    rvHorizontalPicker.adapter = sliderAdapter.apply {
      setData(data)
      callback = object : CustomSliderAdapter.Callback {
        override fun onItemClicked(view: View) {
          rvHorizontalPicker.smoothScrollToPosition(
              rvHorizontalPicker.getChildLayoutPosition(
                  view
              )
          )
        }
      }
    }
  }
}