package com.tw.training.catkeeper.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.presenter.CatDetailContract
import com.tw.training.catkeeper.presenter.CatDetailPresenter
import com.bumptech.glide.Glide


class DetailsActivity : AppCompatActivity(), CatDetailContract.View {
    private lateinit var mName: TextView
    private lateinit var mBanner: ImageView

    private lateinit var mMessage: TextView
    private lateinit var mAvatar: ImageView
    private lateinit var mIntro: TextView
    private lateinit var mStar: TextView
    private lateinit var mFollows: TextView
    private lateinit var mFishes: TextView
    private lateinit var mAdopted: TextView
    private lateinit var mBack: View

    private val presenter = CatDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nearby_cat_list_item_details)

        mName = findViewById(R.id.cat_name)
        mBanner = findViewById(R.id.banner)
        mMessage = findViewById(R.id.message)
        mAvatar = findViewById(R.id.avatar_big)
        mIntro = findViewById(R.id.intro)
        mStar = findViewById(R.id.stars)
        mFollows = findViewById(R.id.follows)
        mFishes = findViewById(R.id.fishes)
        mAdopted = findViewById(R.id.adopted)

        mBack = findViewById(R.id.back)

        mBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val catId = intent.getStringExtra("catId")
        presenter.start(catId)
    }

    @SuppressLint("SetTextI18n")
    override fun showCatDetails(cat: Cat?) {
        val baseUrl = "http://10.0.2.2:8080/catnip"
        Glide.with(this).load(baseUrl + cat!!.banner.imageUrl).into(mBanner)
        Glide.with(this).load(baseUrl + cat.avatar.imageUrl).into(mAvatar)

        mName.text = cat.cat
        mMessage.text = cat.message
        val age = cat.age
        val years = if (age > 1) " years" else " year"
        mIntro.text = """$age $years old | ${cat.kind}"""
        mStar.text = """${cat.stars} Stars"""
        mFollows.text = """${cat.follows} follows"""
        mFishes.text = """${cat.fishes} fishes"""
        mAdopted.text = """${cat.adopted} adopted"""
    }

    override fun onDownloadFailed(msg: String) {
        Toast.makeText(this, "get data failed", Toast.LENGTH_SHORT).show()
    }
}
