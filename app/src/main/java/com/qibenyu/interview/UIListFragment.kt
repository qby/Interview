package com.qibenyu.interview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.qibenyu.componment.DividerItemDecoration
import com.qibenyu.ui.IShowable
import com.qibenyu.ui.UIShowActivity
import kotlinx.android.synthetic.main.item_skill_name.view.*


class UIListFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mUIMap: List<Class<out IShowable>>

    companion object {
        fun newInstance(uiList: ArrayList<Class<out IShowable>>): UIListFragment {
            val bundle = Bundle()
            bundle.putSerializable("UI", uiList)
            val fragment = UIListFragment()
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

        mUIMap = arguments?.get("UI") as List<Class<out IShowable>>
        val data: ArrayList<Class<out IShowable>> = arrayListOf()
        data.addAll(mUIMap)

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
            adapter = UIAdapter(data)
        }
    }

    inner class UIAdapter(private val data: List<Class<out IShowable>>) : RecyclerView.Adapter<ItemViewHolder>() {

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

    inner class ItemViewHolder(containerView: View?) : RecyclerView.ViewHolder(containerView!!) {

        fun bindItem(position: Int, clazz: Class<out IShowable>) {
            Log.d("qibenyu", "class canonicalName = ${clazz.canonicalName} , simpleName = ${clazz.simpleName} , name = ${clazz.name}")

            with(itemView) {
                itemSkill.text = clazz.simpleName
                setOnClickListener {
                    val intent = Intent(context, UIShowActivity::class.java)
                    intent.putExtra("UI_SHOW", clazz.name)
                    context?.startActivity(intent)
                }
            }

        }
    }

}