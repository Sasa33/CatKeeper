package com.tw.training.catkeeper.fragment

import android.content.Intent
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
import com.tw.training.catkeeper.activity.DetailsActivity
import com.tw.training.catkeeper.adapter.NearbyCatAdapter
import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import com.tw.training.catkeeper.presenter.CatsNearbyContract
import com.tw.training.catkeeper.presenter.CatsNearbyPresenter


class NearbyCatFragment : Fragment(), CatsNearbyContract.View, AdapterView.OnItemClickListener {
    private lateinit var catsData: List<GetNearbyCatResponse.MomentsBean>

    override fun onGetDataSucceed(data: List<GetNearbyCatResponse.MomentsBean>?) {
        catsData = data!!
        mListView.adapter = NearbyCatAdapter(activity, data)
    }

    override fun onGetDataFail(msg: String) {
        Toast.makeText(context, "get data failed", Toast.LENGTH_SHORT).show()
    }

    private val mPresenter = CatsNearbyPresenter(this)

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        Toast.makeText(activity, "item clicked:" + p2, Toast.LENGTH_SHORT).show()
        Log.i("cats_nearby", "Item clicked!")
        val intent = Intent(super.getContext(), DetailsActivity::class.java)
        intent.putExtra("catId", catsData[p2].id!!)
        startActivity(intent)
    }

    private lateinit var mListView: ListView

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mListView = view!!.findViewById(R.id.listView)
        mListView.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS

        mListView.onItemClickListener = this

        mPresenter.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("cat_keeper", "fragment oncreate")
    }

    override fun onResume() {
        super.onResume()
        Log.i("cat_keeper", "fragment onresume")
        mPresenter.start()
    }


}