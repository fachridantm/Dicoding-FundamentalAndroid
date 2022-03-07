package com.dicoding.fundamental.mytablayout.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dicoding.fundamental.mytablayout.R
import com.dicoding.fundamental.mytablayout.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvLabel: TextView = homeBinding.sectionLabel
        val name = arguments?.getString(ARG_NAME)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        tvLabel.text = getString(R.string.content_tab_text, index)
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_NAME = "app_name"
    }
}