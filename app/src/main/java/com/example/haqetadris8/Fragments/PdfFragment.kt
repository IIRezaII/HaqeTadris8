package com.example.haqetadris8.Fragments


import android.Manifest
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.haqetadris8.R
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.fragment_calculate.*
import kotlinx.android.synthetic.main.fragment_pdf.view.*
import java.io.FileOutputStream
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PdfFragment : Fragment() {



    val STORAGE_CODE : Int = 100


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_pdf, container, false)


        view.btnSave.setOnClickListener {

            //handle runtime premession for devices with marshmallow and above

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
            {
                //system os >= Marshmallow (6.0) ,check permission is enabled or not

                if (checkSelfPermission(activity!! , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {

                    //permission was not granted , request it
                    val permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, STORAGE_CODE)

                }
                else
                {
                    //permission already granted , call savePDF() method
                    savePDF ()
                }


            }
            else
            {
                // system OS < Marshmallow, call savePDF() method
                savePDF ()

            }

        }


        //handle permission result
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){

            when(requestCode)
            {
                STORAGE_CODE->
                {
                    if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    {
                        //permisson from popup wa granted, call savePDF() method
                        savePDF()

                    }
                    else
                    {

                        //permisson from popup wa dewnied, show error message
                        Toast.makeText(activity, "لطفا دسترسی را مجاز کنید", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }


        return view
    }

    private fun savePDF() {

        //create object of Document class
        val mDoc = Document ()

        //pdf file name
        val mFileName = SimpleDateFormat("yyyymmdd_hhmmss", Locale.getDefault()).format(System.currentTimeMillis())

        //pdf fle path
        val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".pdf"
        try {

            //create instance of pdf writer class
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))

            //open the document for wirting
            mDoc.open()

            //get text from edit text and text view


            if(arguments!!.getString("courseName") != null
                && arguments!!.getString("stuNum") != null
                && arguments!!.getString("unit") != null
                && arguments!!.getString("result") != null) {

                val courseName = arguments!!.getString("courseName")
                val stuNum = arguments!!.getString("stuNum")
                val unit = arguments!!.getString("unit")
                val result = arguments!!.getString("result")

                val mText =

                    ": نام درس" + courseName +  "\n" +
                            ": تعداد دانشجو" + stuNum + "\n" +
                            ": تعداد واحد" + unit +     "\n" +
                            ": ضریب درس" + result +     "\n"

                //create Paragraph
                mDoc.add(Paragraph(mText))
                //close Document
                mDoc.close()
                //show file saved message with file and path
                Toast.makeText(activity, "$mFileName.pdf\nis saved to\n$mFilePath", Toast.LENGTH_SHORT).show()
            }


        }
        catch (e : Exception){
            //if anything goes wrong
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }

    }
}
