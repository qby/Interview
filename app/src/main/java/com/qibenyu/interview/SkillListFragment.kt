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
import com.qibenyu.componment.DividerItemDecoration
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_skill_name.view.*


class SkillListFragment : androidx.fragment.app.Fragment() {

    lateinit var mRecyclerView: androidx.recyclerview.widget.RecyclerView

    companion object {
        fun newInstance(skills: HashMap<String, Class<out Activity>>): SkillListFragment {
            val bundle = Bundle()
            bundle.putSerializable("skills", skills)
            val fragment = SkillListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_skill_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSkillMap = arguments?.get("skills") as Map<String, Class<out Activity>>
        val data: ArrayList<String> = arrayListOf<String>()
        data.addAll(mSkillMap.keys)

        mRecyclerView = view.findViewById(R.id.recycler_view)
        with(mRecyclerView) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                context,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.DIVIDERITEM_HORIZONTAL,
                    R.color.divider_view_bg
                )
            )
            animation = null
            adapter = SkillAdapter(data)
        }

    }

    private lateinit var mSkillMap: Map<String, Class<out Activity>>

    inner class SkillAdapter(val data: List<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<ItemViewHolder>() {

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

    inner class ItemViewHolder(override val containerView: View?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView!!),
        LayoutContainer {

        fun bindItem(position: Int, skill: String) {

            with(itemView) {
                itemSkill.text = skill
                setOnClickListener {
                    val clazz: Class<out Activity>? = mSkillMap[skill]
                    context?.startActivity(Intent(context, clazz))
                }
            }

        }
    }

}

