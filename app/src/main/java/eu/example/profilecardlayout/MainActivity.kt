package eu.example.profilecardlayout

// Video 62
// Using LazyColumns for long list from Url's
// Pictures dont laod in emulator, but runs fine on real device

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import eu.example.profilecardlayout.ui.theme.MyCustomTheme
import eu.example.profilecardlayout.ui.theme.lightGreen200
import eu.example.profilecardlayout.ui.theme.notOnline

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // from package UI themes
            MyCustomTheme() {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {
// Scaffold
// A container that allows us to pass an application bar to it
// It should be added to the parent @composable
    Scaffold(topBar = {AppBar()}) {

// What is shown below the topBar
        Surface(
            modifier = Modifier.fillMaxSize(),
            // We can delete the next line, since we changed the default surface color in themes
            // So it now defaults to LightGray
            // color = Color.LightGray
        ) {

//      LazyColumn ( kinda New way of recyclerView  )
//      About avoiding to load all items in a list, even they are not all visible to the user.
//      with LazyColumn we only load those that are visible
//      We can set it to maybe 1-2 to each end below and above the actual visual screen -
//      to have them ready for view when the user scrolls into them.
            LazyColumn() {
                items(userProfiles) {userProfile ->
                    ProfileCard(userProfile = userProfile)
                }
            }
        }
    }
}

@Composable
fun AppBar(){
    TopAppBar(
        navigationIcon = { Icon(
            Icons.Default.Home,
            "content description",
            Modifier.padding(horizontal = 12.dp)
        ) },
        title = { Text(
            "Messaging Application use",
            fontSize = DpToSp(dp = 22.dp)

            )}
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile){
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White // set background to white, so it don't use default from theme
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status )
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean){
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 4.dp,
            color = if (onlineStatus == true)
                { lightGreen200 } // Custom color from UI Color.kt
            else { notOnline}
            ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        // getting the image
        Image(
            // get from URL
            painter = rememberImagePainter(
                data = pictureUrl,
                builder = {
                    transformations(CircleCropTransformation())
                },),
            modifier = Modifier.size(96.dp),
            contentDescription = "profile picture"
                //data = "https://picsum.photos/300/300",


                )
//            Old Way
//            painter = painterResource(id = drawableId), // argument passed to function when its called
//            modifier = Modifier.size(96.dp),
//            contentScale = ContentScale.Crop, // make the picture fit to the container ??
//            contentDescription = "Content description"
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        // Changes the transparency, so its a little greyed out with medium
        CompositionLocalProvider(LocalContentAlpha provides (
                if (onlineStatus == true)
                    1f else ContentAlpha.medium)
                ){
            Text(
                text = "$userName", // given as argument when function is called
                style = MaterialTheme.typography.h5
            )
        }

        // MAYBE ERRORS HERE HERE
        // Changes the transparency, so its a little greyed out with medium
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if(onlineStatus == true){
                    "Active now" // not specifying parameter type, it will just use String
                } else {
                    "Offline"
                },
                style = MaterialTheme.typography.body2
            )
        }
    }
}

// convert density pixel, to scalable pixel
@Composable
fun DpToSp(dp: Dp) = with(LocalDensity.current) {dp.toSp()}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCustomTheme() {
        MainScreen()
    }
}