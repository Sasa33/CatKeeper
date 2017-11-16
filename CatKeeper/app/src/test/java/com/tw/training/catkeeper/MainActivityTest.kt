package com.tw.training.catkeeper

import android.widget.Button
import android.widget.TextView
import com.tw.training.catkeeper.activity.MainActivity
import org.junit.Ignore
import org.junit.Test
import org.robolectric.Robolectric


class MainActivityTest {
    @Ignore
    @Test
    public fun clickingButton_shouldChangeResultsViewText() {
        var activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java)

        var leftTab: Button = activity.findViewById(R.id.left_tab_btn)
        var rightTab: Button = activity.findViewById(R.id.right_tab_btn)
        var results: TextView = activity.findViewById(R.id.name)

        leftTab.performClick()
//        assert(results.text.equals("Kitty"))
        assert(leftTab.isSelected)
        assert(!leftTab.isSelected)
    }
}