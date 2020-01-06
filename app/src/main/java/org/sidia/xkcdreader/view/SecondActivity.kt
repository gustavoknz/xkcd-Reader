package org.sidia.xkcdreader.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*
import org.sidia.xkcdreader.R
import org.sidia.xkcdreader.TAG
import org.sidia.xkcdreader.model.XkcdPost
import org.sidia.xkcdreader.utils.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.e(TAG, "Fetching data...")
        for (i in 100..105) {
            RetrofitInitializer().xkcdService().fetchComic(i).enqueue(object : Callback<XkcdPost?> {
                override fun onResponse(call: Call<XkcdPost?>, response: Response<XkcdPost?>) {
                    val post = response.body()
                    Log.d(TAG, "Server response ($i): $post")
                    post?.let {
                        Log.d(TAG, "My URL is ($i): " + post.title)
                        Log.d(TAG, "My description is ($i): " + post.alt)
                        Log.d(TAG, "My id is ($i): " + post.img)
                        secondTitle.text = String.format("Got $i, ${post.title}")
                        Glide.with(applicationContext).load(post.img).into(secondImage)
                        secondSubtitle.text = post.alt
                    }
                }

                override fun onFailure(call: Call<XkcdPost?>, t: Throwable) {
                    Log.e(TAG, "Error fetching last post", t)
                }
            })
        }
    }
}
