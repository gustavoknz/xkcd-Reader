package org.sidia.xkcdreader.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.sidia.xkcdreader.R
import org.sidia.xkcdreader.TAG
import org.sidia.xkcdreader.model.XkcdPost
import org.sidia.xkcdreader.utils.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(applicationContext, SecondActivity::class.java))
        }

        RetrofitInitializer().xkcdService().fetchLastComic().enqueue(object : Callback<XkcdPost?> {
            override fun onResponse(call: Call<XkcdPost?>, response: Response<XkcdPost?>) {
                val post = response.body()
                Log.d(TAG, "Server response: $post")
                post?.let {
                    Log.d(TAG, "My URL is: " + post.title)
                    Log.d(TAG, "My description is: " + post.alt)
                    Log.d(TAG, "My id is: " + post.img)
                    mainTitle.text = post.title
                    Glide.with(applicationContext).load(post.img).into(mainImage)
                    mainSubtitle.text = post.alt
                }
            }

            override fun onFailure(call: Call<XkcdPost?>, t: Throwable) {
                Log.e(TAG, "Error fetching last post", t)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Log.d(TAG, "Settings tapped")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
