package org.sidia.xkcdreader.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import org.sidia.xkcdreader.R
import org.sidia.xkcdreader.data.XkcdXmlFeedParser
import org.sidia.xkcdreader.model.XkcdRepository

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val repo = XkcdRepository(XkcdXmlFeedParser())
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        fetchAndLogPosts()
    }

    private fun fetchAndLogPosts() = scope.launch {
        val post = withContext(Dispatchers.IO) {
            repo.getLast()
        }
        textView.text = post.toString()
        Log.d(TAG, "My URL is: " + post.imageUrl.substring(2))
        Glide.with(applicationContext).load(post.imageUrl.substring(2)).into(imageView)
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
