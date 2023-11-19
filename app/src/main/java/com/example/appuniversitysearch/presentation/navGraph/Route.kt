import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
) {

    object SearchScreen : Route(route = "searchScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

}