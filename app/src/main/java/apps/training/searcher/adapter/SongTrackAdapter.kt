package apps.training.searcher.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import apps.training.searcher.R
import apps.training.searcher.data.Results
import apps.training.searcher.databinding.LayoutTrackDataItemBinding
import apps.training.searcher.utils.Logger.debug

class SongTrackAdapter : RecyclerView.Adapter<SongTrackAdapter.SongHolder>() {

    class SongHolder(val binding : LayoutTrackDataItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var trackData : List<Results> = listOf()

    fun loadTracks(data : List<Results>){
        this.trackData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_track_data_item,parent,false))
    }

    override fun getItemCount(): Int {
        return trackData.size
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.binding.track = trackData[position]
        debug(tag = "SongAdapter",msg = "${trackData[position].trackId}")
    }
}