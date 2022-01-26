package com.loneoaktech.apps.instantexplorer.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loneoaktech.apps.instant.common.HelloRepo
import com.loneoaktech.apps.instant.common.utils.lazyViewBinding
import com.loneoaktech.apps.instant.common.utils.withViews
import com.loneoaktech.apps.instantexplorer.R
import com.loneoaktech.apps.instantexplorer.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val vbHolder = lazyViewBinding { MainFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return vbHolder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vbHolder.withViews {
            message.text = HelloRepo().getHello(requireContext())
        }
    }
}