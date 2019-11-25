package com.qibenyu.interview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qibenyu.algorithm.AlgorithmActivity
import com.qibenyu.algorithm.IAlgorithm
import com.qibenyu.componment.DividerItemDecoration
import kotlinx.android.synthetic.main.item_skill_name.view.*


class AlgorithmFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mAlgorithmMap: List<Class<out IAlgorithm>>

    companion object {
        fun newInstance(list: ArrayList<Class<out IAlgorithm>>): AlgorithmFragment {
            val bundle = Bundle()
            bundle.putSerializable("ALGORITHM", list)
            val fragment = AlgorithmFragment()
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

        mAlgorithmMap = arguments?.get("ALGORITHM") as List<Class<out IAlgorithm>>
        val data: ArrayList<Class<out IAlgorithm>> = arrayListOf()
        data.addAll(mAlgorithmMap)

        mRecyclerView = view.findViewById(R.id.recycler_view)
        with(mRecyclerView) {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
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
            adapter = AlgorithmAdapter(data)
        }
    }

    inner class AlgorithmAdapter(private val data: List<Class<out IAlgorithm>>) : RecyclerView.Adapter<ItemViewHolder>() {

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

        fun bindItem(position: Int, clazz: Class<out IAlgorithm>) {
            Log.d("qibenyu", "class canonicalName = ${clazz.canonicalName} , simpleName = ${clazz.simpleName} , name = ${clazz.name}")

            with(itemView) {
                itemSkill.text = clazz.simpleName
                setOnClickListener {
                    val intent = Intent(context, AlgorithmActivity::class.java)
                    intent.putExtra("ALGORITHM", clazz.name)
                    context?.startActivity(intent)
                }
            }

        }
    }

}