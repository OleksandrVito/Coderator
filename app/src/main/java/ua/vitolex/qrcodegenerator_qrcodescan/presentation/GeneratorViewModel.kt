package ua.vitolex.qrcodegenerator_qrcodescan.presentation

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.google.zxing.WriterException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneratorViewModel @Inject constructor() : ViewModel() {

    private val _mapBitmap = mutableStateOf<Bitmap?>(null)
    val mapBitmap: State<Bitmap?> = _mapBitmap

    fun startGeneration(inputValue: String,  type: String, bundle: Bundle? = null,) {
        val qrGenerator = QRGEncoder(inputValue, bundle, type, 900)
        try {
            val bitmap = qrGenerator.getBitmap(1)
            _mapBitmap.value = bitmap
        } catch (e: WriterException) {
            Log.v(ContentValues.TAG, e.toString())
        }
    }
}