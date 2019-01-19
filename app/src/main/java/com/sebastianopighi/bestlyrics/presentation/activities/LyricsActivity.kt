package com.sebastianopighi.bestlyrics.presentation.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.sebastianopighi.bestlyrics.R
import com.sebastianopighi.bestlyrics.presentation.App
import com.sebastianopighi.bestlyrics.presentation.factories.LyricsViewModelFactory
import com.sebastianopighi.bestlyrics.presentation.utils.BaseActivity
import com.sebastianopighi.bestlyrics.presentation.utils.ImageLoader
import com.sebastianopighi.bestlyrics.presentation.viewmodels.LyricsViewModel
import kotlinx.android.synthetic.main.activity_lyrics.*
import javax.inject.Inject

class LyricsActivity : BaseActivity() {

    @Inject
    lateinit var factory: LyricsViewModelFactory
    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var viewModel: LyricsViewModel
    private lateinit var pixel_tracking: ImageView
    private lateinit var cover: ImageView
    private lateinit var title: TextView
    private lateinit var artist: TextView
    private lateinit var album_name: TextView
    private lateinit var album_release: TextView
    private lateinit var lyrics: TextView
    private lateinit var copyright: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (application as App).createLyricsComponent().inject(this)
        factory.trackId = intent.getIntExtra(TRACK_ID, 0)
        viewModel = ViewModelProviders.of(this, factory).get(LyricsViewModel::class.java)

        pixel_tracking = pixel_tracking_image
        cover = track_album_image
        title = track_title
        artist = track_artist
        album_name = album_title
        album_release = album_release_date
        lyrics = track_lyrics
        copyright = lyrics_copyright
        progressBar = lyrics_progress

        viewModel.viewState.observe(this, Observer {
            it?.let { state -> manageViewState(state) }
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                lyrics.text = "No lyrics found for this song :("
                Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
        if (checkInternetConnection() && savedInstanceState == null) {
            viewModel.getLyrics()
        }
    }

    private fun manageViewState(state: LyricsViewState) {
        progressBar.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        state.track?.let {
            setTitle("${it.track_name} - ${it.artist_name}")
            title.text = it.track_name
            artist.text = it.artist_name
            album_name.text = "Album: ${it.album_name}"
            album_release.text = "Release date: ${it.album_release_date}"
            if (it.album_cover_url.isNotEmpty())
                imageLoader.load(it.album_cover_url, cover)
        }
        state.lyrics?.let {
            imageLoader.load(it.pixel_tracking_url, pixel_tracking)
            lyrics.text = it.lyrics_body
            copyright.text = it.lyrics_copyright
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        (application as App).releaseLyricsComponent()
    }

    override fun reloadData() {
        viewModel.getLyrics()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
