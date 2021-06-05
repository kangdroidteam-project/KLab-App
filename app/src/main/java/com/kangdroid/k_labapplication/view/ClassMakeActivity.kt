package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kangdroid.k_labapplication.data.dto.request.CommunityAddRequest
import com.kangdroid.k_labapplication.data.dto.request.GardenReservationRequest
import com.kangdroid.k_labapplication.databinding.ClassMakeBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.xml.bind.DatatypeConverter

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

                val com = CommunityAddRequest( classtitle,"NONE", contentPicture = bitstr, classcontent, classneeds, classdeadline, classrecru, garden )
                println(com.contentPicture ?: "No Content Picture")

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
                val file: File = File.createTempFile("SOME_RANDOM_IMAGE",null, this.cacheDir).apply {
                    deleteOnExit()
                }
                val fileInputStream: InputStream = this.contentResolver.openInputStream(dataUri!!)!!

                FileOutputStream(file).use { outputStream ->
                    var read: Int =-1
                    val bytes = ByteArray(1024)
                    while(fileInputStream.read(bytes).also{read = it} != -1){
                        outputStream.write(bytes,0,read)
                    }
                }
                val fileArray: ByteArray = file.readBytes()
                val bitmapTmp: Bitmap = BitmapFactory.decodeByteArray(fileArray, 0, fileArray.size)
                this.binding.classImage.setImageBitmap(bitmapTmp)

                bitstr =  DatatypeConverter.printBase64Binary(fileArray)
                println(bitstr)
            }
        }
        else{
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}