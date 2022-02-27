package com.example.mi_libro.ui.detalle

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mi_libro.R
import com.example.mi_libro.databinding.FragmentDetalleBinding

class DetalleFragment : Fragment() {
    private lateinit var detalleBinding: FragmentDetalleBinding
    private lateinit var detalleViewModel: DetalleViewModel
    private val args: DetalleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detalleBinding= FragmentDetalleBinding.inflate(inflater, container, false)
        detalleViewModel=ViewModelProvider(this,)[DetalleViewModel::class.java]
        return detalleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book=args.book

        with (detalleBinding){
            nameBookTextView.text=book.name
            authorTextView.text=book.author
        }


        }


}