package com.qibenyu.interview

import android.content.Intent
import android.os.Bundle
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

    private lateinit var mAlgorithmMap: Map<String, Class<out IAlgorithm>>

    companion object {
        fun newInstance(map: HashMap<String, Class<out IAlgorithm>>): AlgorithmFragment {
            val bundle = Bundle()
            bundle.putSerializable("ALGORITHM", map)
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

        mAlgorithmMap = arguments?.get("ALGORITHM") as LinkedHashMap<String, Class<out IAlgorithm>>
        val data: ArrayList<String> = arrayListOf()
        data.addAll(mAlgorithmMap.keys)

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

    inner class AlgorithmAdapter(private val data: List<String>) :
        RecyclerView.Adapter<ItemViewHolder>() {

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

        fun bindItem(position: Int, algorithm: String) {

            val clazz = mAlgorithmMap[algorithm]
            with(itemView) {
                itemSkill.text = algorithm
                setOnClickListener {
                    open(clazz!!)
                }
            }

//            if (clazz == openItem) {
//                open(openItem)
//            }
        }

        private fun open(clazz: Class<out IAlgorithm>) {
            val intent = Intent(context, AlgorithmActivity::class.java)
            intent.putExtra("ALGORITHM", clazz.name)
            context?.startActivity(intent)
        }
    }

}