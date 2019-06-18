package com.qibenyu.interview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SkillListFragment : Fragment() {


    private lateinit var mSkillMap: Map<String, Class<*>>

    private lateinit var mRecyclerView: RecyclerView

    companion object {
        fun newInstance(skills: HashMap<String, Any>): SkillListFragment {
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

        mRecyclerView = view.findViewById(R.id.recycler_view)
        mRecyclerView.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSkillMap = arguments?.get("skills") as Map<String, Class<*>>

    }

    inner class ItemAdapter(view: View) : RecyclerView.ViewHolder(view) {


    }
}

