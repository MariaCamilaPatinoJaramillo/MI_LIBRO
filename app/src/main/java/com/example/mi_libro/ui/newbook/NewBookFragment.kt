package com.example.mi_libro.ui.newbook

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mi_libro.R
import com.example.mi_libro.databinding.FragmentNewBookBinding
import com.example.mi_libro.ui.model.Book
import java.text.SimpleDateFormat
import java.util.*

class NewBookFragment : Fragment() {

    private lateinit var newBookBinding: FragmentNewBookBinding
    private lateinit var viewModel: NewBookViewModel
    private var cal = Calendar.getInstance()
    private var publicationDate = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newBookBinding= FragmentNewBookBinding.inflate(inflater, container, false)
        viewModel=ViewModelProvider(this).get(NewBookViewModel::class.java)
        return newBookBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            newBookBinding.publicationDateButton.text = publicationDate
        }

        with(newBookBinding){
            publicationDateButton.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            saveButton.setOnClickListener {

                if (nameBookEditText.text?.isEmpty() == true ||
                    nameAuthorEditText.text?.isEmpty() == true ||
                    pagesEditText.text?.isEmpty() == true
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Debe digitar nombre, autor y número de páginas",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    val nameBook: String = nameBookEditText.text.toString()
                    val author = nameAuthorEditText.text.toString()
                    val pages = pagesEditText.text.toString().toInt()
                    val abstract = abstractEditText.text.toString()

                    var genre = ""

                    if (suspenseCheckBox.isChecked) genre = "Suspenso"
                    if (terrorCheckBox.isChecked) genre += " Terror"
                    if (infantileCheckBox.isChecked) genre += "Infantil"
                    if (fictionCheckBox.isChecked) genre += " Ficción"

                    val score = when {
                        oneRadioButton.isChecked -> 1
                        twoRadioButton.isChecked -> 2
                        threeRadioButton.isChecked -> 3
                        fourRadioButton.isChecked -> 4
                        else -> 5
                    }

                    val book= Book (
                            name= nameBook,
                            author= author,
                            pages=pages,
                            abstract=abstract,
                            genre=genre,
                            score= score,
                            publicationDate= publicationDate)
                    findNavController().navigate(NewBookFragmentDirections.actionNewBookFragmentToDetalleFragment(book))

                }

            }
        }
    }
}

