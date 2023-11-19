package com.example.appuniversitysearch.presentation.mainscreen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appuniversitysearch.ui.theme.AppUniversitySearchTheme
import com.example.appuniversitysearch.R
import com.example.appuniversitysearch.presentation.bookmarkscreen.BookmarkScreen
import com.example.appuniversitysearch.presentation.bookmarkscreen.BookmarkViewModel
import com.example.appuniversitysearch.presentation.detailsscreen.DetailsScreen
import com.example.appuniversitysearch.presentation.detailsscreen.DetailsViewModel
import com.example.appuniversitysearch.presentation.mainscreen.components.NewsBottomNavigation
import com.example.appuniversitysearch.presentation.mainscreen.models.BottomNavigationItem
import com.example.appuniversitysearch.presentation.searchscreen.SearchScreen
import com.example.appuniversitysearch.presentation.searchscreen.SearchViewModel
import com.example.domain.model.UniversityResponseItem
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppUniversitySearchTheme {

                val bottomNavigationItems = remember {
                    listOf(
                        BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
                        BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
                    )
                }
                Log.d("MyLog", ".")

                val navController = rememberNavController()
                Log.d("MyLog", "..")
                val backStackState = navController.currentBackStackEntryAsState().value
                var selectedItem by rememberSaveable {
                    mutableStateOf(0)
                }
                selectedItem = when (backStackState?.destination?.route) {
                    Route.SearchScreen.route -> 0
                    Route.BookmarkScreen.route -> 1
                    else -> 0
                }

                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    NewsBottomNavigation(
                        items = bottomNavigationItems,
                        selectedItem = selectedItem,
                        onItemClick = { index ->
                            when (index) {
                                0 -> navigateToTab(
                                    navController = navController,
                                    route = Route.SearchScreen.route
                                )

                                1 -> navigateToTab(
                                    navController = navController,
                                    route = Route.BookmarkScreen.route
                                )
                            }
                        }
                    )
                })
                {
                    val bottomPadding = it.calculateBottomPadding()
                    NavHost(
                        navController = navController,
                        startDestination = Route.SearchScreen.route,
                        modifier = Modifier.padding(bottom = bottomPadding)
                    ) {
                        composable(route = Route.SearchScreen.route) {
                            Log.d("MyLog", "composable - Route.SearchScreen.route")
                            val viewModel: SearchViewModel = hiltViewModel()
                            SearchScreen(
                                viewModel,
                                navigateToDetails = { university ->
                                    navigateToDetails(
                                        navController = navController,
                                        university = university
                                    )
                                }
                                )
                        }
                        composable(route = Route.BookmarkScreen.route) {
                            val viewModel:BookmarkViewModel= hiltViewModel()
                            BookmarkScreen(
                                state = viewModel.state.value,
                                navigateToDetails = { university ->
                                    navigateToDetails(
                                        navController = navController,
                                        university = university
                                    )
                                }
                            )
                        }
                        composable(route = Route.DetailsScreen.route) {
                            val viewModel: DetailsViewModel = hiltViewModel()
                            navController.previousBackStackEntry?.savedStateHandle?.get<UniversityResponseItem?>("university")
                                ?.let { university ->
                                    DetailsScreen(
                                        university = university,
                                        event = viewModel::onEvent,
                                        navigateUp = { navController.navigateUp() },
                                        sideEffect = viewModel.sideEffect
                                    )
                                }

                        }
                    }
                }


            }
        }
    }


    private fun navigateToTab(navController: NavController, route: String) {

        Log.d("MyLog", "nodes = ${navController.graph.nodes}")
        navController.navigate(route) {
            Log.d("MyLog", "-${navController.graph}")
            Log.d("MyLog", "--${navController.graph.startDestinationRoute}")
            navController.graph.startDestinationRoute?.let { screen_route ->
                popUpTo(screen_route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
private fun navigateToDetails(navController: NavController, university: UniversityResponseItem) {
    navController.currentBackStackEntry?.savedStateHandle?.set("university", university)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}