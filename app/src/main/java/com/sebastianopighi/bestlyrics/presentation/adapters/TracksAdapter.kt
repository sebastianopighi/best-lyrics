package com.sebastianopighi.bestlyrics.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianopighi.bestlyrics.R
import com.sebastianopighi.bestlyrics.presentation.models.Track
import com.sebastianopighi.bestlyrics.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.tracks_adapter_cell.view.*

class TracksAdapter constructor(private val imageLoader: ImageLoader,
                                private val onTrackSelected: (Track, View) -> Unit) : RecyclerView.Adapter<TracksAdapter.TrackCellViewHolder>() {

    private val tracks: MutableList<Track> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.tracks_adapter_cell,
            parent,
            false)
        return TrackCellViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: TrackCellViewHolder, position: Int) {
        val track = tracks[position]
        holder.bind(track, imageLoader, onTrackSelected)
    }

    fun addTracks(tracks: List<Track>) {
        this.tracks.addAll(tracks)
        notifyDataSetChanged()
    }

    class TrackCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(track: Track, imageLoader: ImageLoader, listener: (Track, View) -> Unit) = with(itemView) {
            track_title.text = track.track_name
            track_artist.text = track.artist_name
            imageLoader.load(track.album_cover_url, track_album_image)
            setOnClickListener { listener(track, itemView) }
        }

    }
}