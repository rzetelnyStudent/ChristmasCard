package com.michalkapuscinski.christmascard

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.michalkapuscinski.christmascard.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var cameraLauncher: ActivityResultLauncher<Uri>
    //private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var photoPath: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateButton.setOnClickListener {
            val intent = Intent(this, GeneratedCardActivity::class.java)
            intent.putExtra("name", binding.nameEditText.text.toString())
            intent.putExtra("uri", photoPath)
            intent.putExtra("gift_list", binding.giftsEditText.text.toString())
            startActivity(intent)
        }

        photoPath = initTempUri()

//        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
//            if (isGranted) {
//                takePicture()
//            }
//        }

        binding.editPhoto.setOnClickListener {
            //if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                takePicture()
            //}
            //else {
             //   if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
             //      requestPermissionLauncher.launch(Manifest.permission.CAMERA)
             //   }
            //}
        }

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isTaken ->
            //if (isTaken) {
                binding.profilePhoto.setImageURI(null)
                binding.profilePhoto.setImageURI(photoPath)
            //}
        }
    }

    private fun takePicture() {
        cameraLauncher.launch(photoPath)
    }

    private fun Context.resourceUri(resourceId: Int): Uri = with(resources) {
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(getResourcePackageName(resourceId))
            .appendPath(getResourceTypeName(resourceId))
            .appendPath(getResourceEntryName(resourceId))
            .build()
    }

//    private fun getPhotoUri(): Uri {
//        val directory = File(filesDir, "camera_images")
//        if(!directory.exists()){
//            directory.mkdirs()
//        }
//        val file = File(directory,"${Calendar.getInstance().timeInMillis}.png")
//        return FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", file)
//    }

    private fun initTempUri(): Uri {
        val tempImagesDir = File(
            applicationContext.filesDir, //this function gets the external cache dir
            getString(R.string.temp_images_dir)) //gets the directory for the temporary images dir
        tempImagesDir.mkdir()
        val tempImage = File(
            tempImagesDir, //prefix the new abstract path with the temporary images dir path
            getString(R.string.temp_image)) //gets the abstract temp_image file name
        return FileProvider.getUriForFile(
            applicationContext,
            getString(R.string.authorities),
            tempImage)
    }

}