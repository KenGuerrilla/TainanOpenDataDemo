package com.itl.kg.app.tainanopendatademo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itl.kg.app.tainanopendatademo.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        initListAdapter()

        return binding.root
    }

    private fun initListAdapter() {

        val layoutManager = LinearLayoutManager(requireContext())

        val adapter = MainListAdapter(MainListConfig.list).also {
            it.itemClickListener = object : ItemClickListener {
                override fun onItemClick(item: MainListItem, view: View) {
                    view.findNavController().navigate(item.nav)
                }
            }
        }

        binding.mMainListRv.also {
            it.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
    }

}