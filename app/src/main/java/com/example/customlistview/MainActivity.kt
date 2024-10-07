package com.example.customlistview

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.customlistview.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val GALLERY_REQUEST = 302
    var bitmap: Bitmap? = null
    val persons: MutableList<Person> = mutableListOf()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.mainActivityImageIV.setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent,GALLERY_REQUEST)

        }

        binding.savePersonButtonBTN.setOnClickListener{
            val personName = binding.mainActivityNameEditTextET.text.toString()
            val personAge = binding.mainActivityAgeEditTextET.text.toString()
            val personPhone = binding.mainActivityPhoneEditTextET.text.toString()
            val personImage = bitmap
            val person = Person(personName,personAge,personPhone,personImage)
            persons.add(person)

            val listAdapter = ListAdapter(this, persons)
            binding.mainActivityPersonListViewLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()

            binding.mainActivityNameEditTextET.text.clear()
            binding.mainActivityAgeEditTextET.text.clear()
            binding.mainActivityPhoneEditTextET.text.clear()
            binding.mainActivityImageIV.setImageResource(R.drawable.baseline_person_24)
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            GALLERY_REQUEST -> if (resultCode == RESULT_OK){
                val selectedImage: Uri? = data?.data
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedImage)
                } catch (e: IOException){
                    e.printStackTrace()
                }
                binding.mainActivityImageIV.setImageBitmap(bitmap)
            }
        }
    }
}