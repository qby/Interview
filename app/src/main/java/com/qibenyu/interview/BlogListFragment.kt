package com.qibenyu.interview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qibenyu.blog.BlogActivity
import com.qibenyu.componment.DividerItemDecoration
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_skill_name.view.*


class BlogListFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView

    companion object {
        fun newInstance(blogs: HashMap<String, Int>): BlogListFragment {
            val bundle = Bundle()
            bundle.putSerializable("blogs", blogs)
            val fragment = BlogListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBlogMap = arguments?.get("blogs") as Map<String, Int>
        val data: ArrayList<String> = arrayListOf()
        data.addAll(mBlogMap.keys)

        mRecyclerView = view.findViewById(R.id.recycler_view)
        with(mRecyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.DIVIDERITEM_HORIZONTAL,
                    R.color.divider_view_bg
                )
            )
            animation = null
            adapter = BlogAdapter(data)
        }

    }

    private lateinit var mBlogMap: Map<String, Int>

    inner class BlogAdapter(val data: List<String>) : RecyclerView.Adapter<ItemViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ItemViewHolder {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.item_skill_name, viewGroup, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bindItem(position, data[position])
        }
    }

    inner class ItemViewHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView!!),
        LayoutContainer {

        fun bindItem(position: Int, blog: String) {

            with(itemView) {
                itemSkill.text = blog
                setOnClickListener {
                    val id = mBlogMap[blog]
                    val intent = Intent(context, BlogActivity::class.java)
                    intent.putExtra("blogId", id)
                    context?.startActivity(intent)
                }
            }

        }
    }

}

