package com.tw.training.catkeeper.fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.adapter.NearbyCatAdapter


class NearbyCatFragment : Fragment(), AdapterView.OnItemClickListener {
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(activity, "item clicked:" + p2, Toast.LENGTH_SHORT).show()
    }

    private lateinit var mListView: ListView

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mListView = view!!.findViewById(R.id.listView)

        setupListView()
    }

    private fun setupListView() {
        var data = listOf("cat 1", "cat 2", "cat 3", "cat 4", "cat 5", "cat 6", "cat 7", "cat 8", "cat 9", "cat 10", "cat 11", "cat 12", "cat 13", "cat 14", "cat 15", "cat 16")
        mListView.adapter = NearbyCatAdapter(activity, data)

//        mListView.onItemClickListener = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("cat_keeper", "fragment oncreate")
    }

    override fun onResume() {
        super.onResume()
        Log.i("cat_keeper", "fragment onresume")
    }


}