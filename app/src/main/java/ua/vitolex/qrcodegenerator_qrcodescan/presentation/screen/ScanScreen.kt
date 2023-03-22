package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen


import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.common.InputImage
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.ScannerViewModel
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.BarCodeAnalyser
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.components.BannerAdView
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.components.ResponsiveText
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*
import ua.vitolex.qrcodegenerator_qrcodescan.utils.ShareUtils.Companion.shareTextOrLink
import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScanScreen(navController: NavHostController, viewModel: ScannerViewModel = hiltViewModel()) {
    val context = LocalContext.current
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    val textResult = viewModel.textResult
    val typeResult = viewModel.typeResult

    //для картинки
    val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                try {
                    val image = InputImage.fromFilePath(context, uri)
                    viewModel.scanner.process(image)
                        .addOnSuccessListener { barcodes ->
                            if (barcodes.isEmpty()) showToast("QR code or barcode is not found.")
                            viewModel.extractBarcodeQrCodeInfo(barcodes)
                        }
                        .addOnFailureListener { e ->
                            showToast("Failed scanning due to ${e.message}")
                        }
                } catch (e: IOException) {
                    e.printStackTrace()
                    showToast("Failed due to ${e.message}")
                }
            }
        }
    )
    //для камери
    val cameraOpen = remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<Preview?>(null) }


    //для функціоналу кнопок
    val uriHandler = LocalUriHandler.current

    //для копіювання тексту
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                backgroundColor = Black333
            ) {
                Column(
                    Modifier
                        .padding(top = 10.dp, bottom = 0.dp, start = 5.dp)
                ) {
                    Text(text = "CODERATOR", color = WhiteGrey)
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(Silver)
                    )
                    Row(
                        Modifier
                            .height(32.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .height(20.dp)
                                .padding(bottom = 0.dp, top = 0.dp)
                                .background(androidx.compose.ui.graphics.Color.Transparent),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Canvas(modifier = Modifier
                                .width(40.dp),
                                onDraw = {
                                    val trianglePath = Path().apply {
                                        moveTo(0.dp.toPx(), 11.dp.toPx())
                                        lineTo(30.dp.toPx(), 7.dp.toPx())
                                        lineTo(30.dp.toPx(), 15.dp.toPx())
                                    }
                                    drawPath(
                                        color = DirtyRed,
                                        path = trianglePath
                                    )
                                })
                        }
                        Text(
                            text = "back",
                            fontSize = 10.sp,
                            fontFamily = presstart,
                            color = WhiteGrey,
                        )
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Black333)
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Black333),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .border(3.dp, Silver, shape = RoundedCornerShape(24.dp))
                            .padding(16.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(Black333),
                    ) {

                        if (cameraOpen.value) {
                            Text(
                                text = "Camera",
                                style = MaterialTheme.typography.body2,
                                color = WhiteGrey,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            if (cameraPermissionState.hasPermission) {
                                AndroidView(
                                    factory = { AndroidViewContext ->
                                        PreviewView(AndroidViewContext).apply {
                                            this.scaleType =
                                                PreviewView.ScaleType.FILL_CENTER
                                            layoutParams = ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                            )
                                            implementationMode =
                                                PreviewView.ImplementationMode.COMPATIBLE
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = RoundedCornerShape(16.dp)),
                                    update = { previewView ->
                                        val cameraSelector: CameraSelector =
                                            CameraSelector.Builder()
                                                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                                .build()
                                        val cameraExecutor: ExecutorService =
                                            Executors.newSingleThreadExecutor()
                                        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                                            ProcessCameraProvider.getInstance(context)

                                        cameraProviderFuture.addListener({
                                            preview = Preview.Builder().build().also {
                                                it.setSurfaceProvider(previewView.surfaceProvider)
                                            }
                                            val cameraProvider: ProcessCameraProvider =
                                                cameraProviderFuture.get()
                                            val barcodeAnalyser = BarCodeAnalyser(
                                            ) { barcodes ->
                                                viewModel.extractBarcodeQrCodeInfo(barcodes)
                                                    .also {
                                                        cameraOpen.value = false
                                                        cameraProvider.unbindAll()
                                                    }
                                            }
                                            val imageAnalysis: ImageAnalysis =
                                                ImageAnalysis.Builder()
                                                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                                    .build()
                                                    .also {
                                                        it.setAnalyzer(
                                                            cameraExecutor,
                                                            barcodeAnalyser
                                                        )
                                                    }

                                            try {
                                                cameraProvider.unbindAll()
                                                cameraProvider.bindToLifecycle(
                                                    lifecycleOwner,
                                                    cameraSelector,
                                                    preview,
                                                    imageAnalysis
                                                )
                                            } catch (e: Exception) {
                                                Log.d(
                                                    "TAG",
                                                    "CameraPreview: ${e.localizedMessage}"
                                                )
                                            }
                                        }, ContextCompat.getMainExecutor(context))
                                    }
                                )
                            }
                            if (cameraPermissionState.shouldShowRationale) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                                        .clip(shape = RoundedCornerShape(14.dp)),
                                    backgroundColor = Color.Black.copy(0.5f),
                                    contentColor = Dark,
                                    elevation = 5.dp
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(
                                                start = 20.dp,
                                                top = 20.dp,
                                                end = 20.dp
                                            ),
                                        text = "Camera permission is needed",
                                        style = MaterialTheme.typography.body1.copy(
                                            lineHeight = 14.sp,
                                            fontSize = 10.sp
                                        ),
                                        color = DirtyRed,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            if (!cameraPermissionState.hasPermission && !cameraPermissionState.shouldShowRationale) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                                        .clip(shape = RoundedCornerShape(14.dp)),
                                    backgroundColor = Color.Black.copy(0.5f),
                                    contentColor = Dark,
                                    elevation = 5.dp
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(
                                                start = 20.dp,
                                                top = 20.dp,
                                                end = 20.dp
                                            ),
                                        text = "Navigate to settings and enable the Camera permission",
                                        style = MaterialTheme.typography.body1.copy(
                                            lineHeight = 14.sp,
                                            fontSize = 10.sp
                                        ),
                                        color = DirtyRed,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            } else {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                                        .clip(shape = RoundedCornerShape(14.dp)),
                                    backgroundColor = Color.Black.copy(0.5f),
                                    contentColor = Dark,
                                    elevation = 5.dp
                                ) {
                                }
                            }
                        } else {
                            Text(
                                text = "Display",
                                style = MaterialTheme.typography.body2,
                                color = WhiteGrey,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                                    .padding(8.dp)
                                    .clip(shape = RoundedCornerShape(10.dp)),
                                backgroundColor = PrimaryGray,
                                contentColor = Dark,
                                elevation = 5.dp
                            ) {
                                LazyColumn() {
                                    item {
                                        Text(
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                top = 20.dp,
                                                end = 20.dp
                                            ),
                                            text = typeResult.value,
                                            style = MaterialTheme.typography.body1,
                                            fontSize = 14.sp,
                                        )
                                    }
                                    item {
                                        Text(
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                top = 10.dp,
                                                end = 20.dp
                                            ),
                                            text = textResult.value,
                                            style = MaterialTheme.typography.body2.copy(lineHeight = 18.sp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(3.dp, Silver, shape = RoundedCornerShape(14.dp))
                            .padding(6.dp)
                            .clip(shape = RoundedCornerShape(12.dp))

                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .heightIn(min = 36.dp)
                                    .background(
                                        PrimaryGray
                                    )
                                    .border(1.dp, Black333)
                                    .clickable {
                                        if (textResult.value.isNotBlank()  && cameraOpen.value == false) {
                                            clipboardManager.setText(AnnotatedString((textResult.value)))
                                            showToast("Copied")
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                ResponsiveText(
                                    modifier = Modifier.padding(8.dp),
                                    text = "Copy",
                                    color = Black27,
                                    textStyle = MaterialTheme.typography.body1,
                                    targetTextSizeHeight = 10.sp,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .heightIn(min = 36.dp)
                                    .background(
                                        PrimaryGray
                                    )
                                    .border(1.dp, Black333)
                                    .clickable {
                                        if (textResult.value.isNotBlank()  && cameraOpen.value == false) {
                                            shareTextOrLink(context, textResult.value)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                ResponsiveText(
                                    modifier = Modifier.padding(8.dp),
                                    text = "Share",
                                    color = Black27,
                                    textStyle = MaterialTheme.typography.body1,
                                    targetTextSizeHeight = 10.sp,
                                )
                            }
                            if (typeResult.value == "WebSite"
                                || typeResult.value == "Phone"
                                || typeResult.value == "Email"
                                || typeResult.value == "Text"
                                || typeResult.value == "Location"
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .heightIn(min = 36.dp)
                                        .background(
                                            PrimaryGray
                                        )
                                        .border(1.dp, Color(0xFF030406))
                                        .clickable {
                                            if (typeResult.value == "WebSite" && cameraOpen.value == false) {
                                                uriHandler.openUri("${textResult.value}")
                                            }
                                            if (typeResult.value == "Phone" && cameraOpen.value == false) {
                                                uriHandler.openUri("tel:${textResult.value}")
                                            }
                                            if (typeResult.value == "Email" && cameraOpen.value == false) {
                                                uriHandler.openUri("mailto:${textResult.value}")
                                            }
                                            if (typeResult.value == "Text" && cameraOpen.value == false) {
                                                uriHandler.openUri("https://www.google.com.ua/search?q=${textResult.value}")
                                            }
                                            if (typeResult.value == "Location" && cameraOpen.value == false) {
                                                if (textResult.value.contains("geo:")) {
                                                    uriHandler.openUri("${textResult.value}")
                                                } else {
                                                    uriHandler.openUri("geo:${textResult.value}")
                                                }

                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (typeResult.value == "Phone") {
                                        ResponsiveText(
                                            modifier = Modifier.padding(8.dp),
                                            text = "Call",
                                            color = Black27,
                                            textStyle = MaterialTheme.typography.body1,
                                            targetTextSizeHeight = 10.sp,
                                        )
                                    }
                                    if (typeResult.value == "WebSite") {
                                        ResponsiveText(
                                            modifier = Modifier.padding(8.dp),
                                            text = "Open",
                                            color = Black27,
                                            textStyle = MaterialTheme.typography.body1,
                                            targetTextSizeHeight = 10.sp,
                                        )
                                    }
                                    if (typeResult.value == "Email") {
                                        ResponsiveText(
                                            modifier = Modifier.padding(8.dp),
                                            text = "Send",
                                            color = Black27,
                                            textStyle = MaterialTheme.typography.body1,
                                            targetTextSizeHeight = 10.sp,
                                        )
                                    }
                                    if (typeResult.value == "Text") {
                                        ResponsiveText(
                                            modifier = Modifier.padding(8.dp),
                                            text = "Search",
                                            color = Black27,
                                            textStyle = MaterialTheme.typography.body1,
                                            targetTextSizeHeight = 10.sp,
                                        )
                                    }
                                    if (typeResult.value == "Location") {
                                        ResponsiveText(
                                            modifier = Modifier.padding(8.dp),
                                            text = "Maps",
                                            color = Black27,
                                            textStyle = MaterialTheme.typography.body1,
                                            targetTextSizeHeight = 10.sp,
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Start scanning from image or using camera",
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(20.dp),
                        textAlign = TextAlign.Center,
                        color = WhiteGrey
                    )
                    Row(
                        modifier = Modifier
                            .width(180.dp)
                            .height(83.dp)
                            .background(Black333)
                            .border(3.dp, Silver, shape = RoundedCornerShape(12.dp))
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(PrimaryGray)
                                .widthIn(min = 80.dp)
                                .heightIn(min = 80.dp)
                                .clickable {
                                    getImageRequest.launch(arrayOf("image/*"))
                                    cameraOpen.value = false
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.gallery_icon),
                                contentDescription = "Gallery",
                                modifier = Modifier
                                    .scale(1.5f)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .background(if (cameraOpen.value) SecondaryGray else PrimaryGray)
                                .widthIn(min = 80.dp)
                                .heightIn(min = 80.dp)
                                .clickable {
                                    if (cameraOpen.value == false) {
                                        cameraPermissionState.launchPermissionRequest()
                                    }
                                    cameraOpen.value = !cameraOpen.value
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.camera_icon),
                                contentDescription = "Camera",
                                modifier = Modifier
                                    .scale(1.5f)
                            )
                        }
                    }

                }
            }
            BannerAdView(id = stringResource(id = R.string.banner_ad_unit_id_scan))
        }
    }
}
