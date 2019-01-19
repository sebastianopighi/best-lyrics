package com.sebastianopighi.bestlyrics.presentation.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.sebastianopighi.bestlyrics.R
import com.sebastianopighi.bestlyrics.presentation.App
import com.sebastianopighi.bestlyrics.presentation.adapters.EndlessRecyclerViewScrollListener
import com.sebastianopighi.bestlyrics.presentation.adapters.TracksAdapter
import com.sebastianopighi.bestlyrics.presentation.factories.TracksViewModelFactory
import com.sebastianopighi.bestlyrics.presentation.utils.BaseActivity
import com.sebastianopighi.bestlyrics.presentation.utils.ImageLoader
import com.sebastianopighi.bestlyrics.presentation.viewmodels.TracksViewModel
import kotlinx.android.synthetic.main.activity_tracks.*
import javax.inject.Inject

class TracksActivity : BaseActivity() {

    @Inject
    lateinit var factory: TracksViewModelFactory
    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var viewModel: TracksViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var tracksAdapter: TracksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks)
        setTitle(R.string.tracks)


        (application as App).createTracksComponent().inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(TracksViewModel::class.java)

        recyclerView = tracks_recyclerview
        progressBar = tracks_progress

        if(getString(R.string.api_key).isNullOrEmpty()) {
            showNoAPIKeyDialog()
            return
        }

        tracksAdapter = TracksAdapter(imageLoader) { track, _ ->
            openLyrics(track.track_id)
        }
        val layoutManager = GridLayoutManager(this@TracksActivity, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = tracksAdapter
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Toast.makeText(this@TracksActivity, R.string.loading_new_tracks, Toast.LENGTH_SHORT).show()
                viewModel.getTracks()
            }
        })

        viewModel.viewState.observe(this, Observer {
            if (it != null) manageViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
        if (checkInternetConnection()) {
            if (savedInstanceState == null) {
                viewModel.getTracks()
            } else {
                viewModel.viewState.value?.tracks?.let { tracksAdapter.addTracks(it) }
            }
        }
    }

    private fun openLyrics(trackId: Int) {
        val lyricsIntent = Intent(this@TracksActivity, LyricsActivity::class.java)
        lyricsIntent.putExtra(TRACK_ID, trackId)
        startActivity(lyricsIntent)
    }

    private fun manageViewState(state: TracksViewState) {
        progressBar.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        state.addTracks?.let { tracksAdapter.addTracks(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as App).releaseTracksComponent()
    }

    override fun reloadData() {
        viewModel.getTracks()
    }
}
