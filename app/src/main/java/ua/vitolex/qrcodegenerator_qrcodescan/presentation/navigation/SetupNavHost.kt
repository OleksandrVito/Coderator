package ua.vitolex.qrcodegenerator_qrcodescan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.GeneratorViewModel
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen.*

sealed class Screens(val rout: String) {
    object SplashScreen : Screens(rout = "splash_screen")
    object MainScreen : Screens(rout = "main_screen")
    object ScanScreen : Screens(rout = "scan_screen")
    object InputScreen : Screens(rout = "input_screen")
    object CodeScreen : Screens(rout = "code_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    val viewModel: GeneratorViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screens.MainScreen.rout) {

        composable(route = Screens.SplashScreen.rout) {
//            SplashScreen(navController = navController)
        }
        composable(route = Screens.MainScreen.rout) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.ScanScreen.rout) {
            ScanScreen(navController = navController)
        }
        composable(route = Screens.InputScreen.rout) {
            InputScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.CodeScreen.rout) {
            CodeScreen(navController = navController, viewModel = viewModel)
        }
    }
}