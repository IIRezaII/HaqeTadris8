package com.example.haqetadris8.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.example.haqetadris8.R
import com.example.haqetadris8.StartFunction
import kotlinx.android.synthetic.main.fragment_calculate.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CalculateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculate, container, false)



        view.button.setOnClickListener {

            if (view.tvStuNumber.text.isNullOrEmpty() || view.tvUnit.text.isNullOrEmpty()) {

                Toast.makeText(activity, R.string.emptyUnitText, Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }


            val stuNumText = view.tvStuNumber.text.toString().toInt()
            val unitText = view.tvUnit.text.toString().toInt()


            if (view.proRadio.isChecked) {

                val finalValue: Double = StartFunction().associatesStart(stuNumText, unitText)
                view.findViewById<TextView>(R.id.tvResult).text = finalValue.toString()

            }

            if (view.genRadio.isChecked) {

                val finalValue: Double = StartFunction().generalStart(stuNumText, unitText)
                view.findViewById<TextView>(R.id.tvResult).text = finalValue.toString()

            }

            val pdfFrag = PdfFragment()

            val bundle = Bundle()
            bundle.putString("courseName", view.findViewById<EditText>(R.id.tvCourseName).text.toString())
            pdfFrag.arguments = bundle
            val bundle2 = Bundle()
            bundle2.putString("stuNum", view.findViewById<EditText>(R.id.tvStuNumber).text.toString())
            pdfFrag.arguments = bundle
            val bundle3 = Bundle()
            bundle3.putString("unit", view.findViewById<EditText>(R.id.tvUnit).text.toString())
            pdfFrag.arguments = bundle
            val bundle4 = Bundle()
            bundle4.putString("result", view.findViewById<TextView>(R.id.tvResult).text.toString())
            pdfFrag.arguments = bundle


        }

        return view
    }
}





















