package com.ddk.ramenlab.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ddk.ramenlab.R
import com.ddk.ramenlab.data.RecipeBoardData

class RecipeBoardListAdapter(
    private val fragment: Fragment
) : RecyclerView.Adapter<RecipeBoardListAdapter.BoardListViewHolder>() {

    private val dataset = RecipeBoardData.newBoard

    /**
     * Initialize view elements
     */
    class BoardListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val boardTitle: TextView = view.findViewById(R.id.board_title)
        val boardContent: TextView = view.findViewById(R.id.board_content)
        val chatCount: TextView? = view.findViewById(R.id.chat_count)
    }

    /**
     * Create new views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardListViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.board_list_item, parent, false)

        return BoardListViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view
     */

    override fun onBindViewHolder(holder: BoardListViewHolder, position: Int) {
        val item = dataset[position]

        holder.boardTitle.text = item.title         // 글 제목
        holder.boardContent.text = item.content     // 글 내용
        holder.chatCount?.text = item.chatCount.toString() ?: "0"

        holder.itemView.setOnClickListener {
            fragment.setFragmentResult("result", bundleOf("title" to item.title, "content" to item.content))
            fragment.findNavController().navigate(R.id.action_fragment_board_to_boardReadFragment)
        }
    }

    override fun getItemCount() = dataset.size
}