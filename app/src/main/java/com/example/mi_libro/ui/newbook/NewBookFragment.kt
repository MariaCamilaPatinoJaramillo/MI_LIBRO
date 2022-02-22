package com.example.mi_libro.ui.newbook

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mi_libro.R

class NewBookFragment : Fragment() {

    companion object {
        fun newInstance() = NewBookFragment()
    }

    private lateinit var viewModel: NewBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewBookViewModel::class.java)
        // TODO: Use the ViewModel
    }

}