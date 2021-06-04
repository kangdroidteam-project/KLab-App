package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kangdroid.k_labapplication.data.dto.request.CommunityAddRequest
import com.kangdroid.k_labapplication.data.dto.request.GardenReservationRequest
import com.kangdroid.k_labapplication.databinding.ClassMakeBinding
import java.io.ByteArrayOutputStream

class ClassMakeActivity : AppCompatActivity() {

    lateinit var binding : ClassMakeBinding
    val gallery = 0
    var bitstr : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClassMakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun init(){

        binding.apply {

            classImage.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Gallery"), gallery)
            }

            create.setOnClickListener {

                val garden = GardenReservationRequest(0L, "A")

                val classtitle = classTitle.text.toString()
                val classcontent = classContent.text.toString()
                val classneeds= classNeeds.text.toString()
                val classdeadline = classDeadline.text.toString()
                val classrecru = classrecruitment.text.toString().toInt()

                bitstr = "aaa"

                val com = CommunityAddRequest( classtitle,"NONE", bitstr, classcontent, classneeds, classdeadline, classrecru, garden )

                val intent = Intent(this@ClassMakeActivity,GardenReservationActivity::class.java)
                intent.putExtra("CommunityAddRequest",com)
                startActivity(intent)
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == gallery){
            if(resultCode== RESULT_OK){
                var dataUri = data?.data
                try{
                    var bitmap : Bitmap = MediaStore.Images.Media.getBitmap(
                        this.contentResolver,
                        dataUri
                    )
                    binding.classImage.setImageBitmap(bitmap)

                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos)
                    val bytes = baos.toByteArray()
                    bitstr =  Base64.encodeToString(bytes, Base64.DEFAULT)

                }catch (e: Exception){
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}