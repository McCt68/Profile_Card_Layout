package eu.example.profilecardlayout

// Branch 4
// Video 57
// Making more cards, and extract users to an entoty
// Themes are stored in package UI
// Here I am doing a custom theme - I overwrote the theme in UI package theme.kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun MainScreen() {
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
            Column() {
                ProfileCard(userProfileList[0]) // get index zero with brackets method
                ProfileCard(userProfileList.get(1)) // get index with .set method
                ProfileCard(userProfileList.component2()) // get index with .component2() method
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
        title = { Text("Messaging Application users")}
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
            ProfilePicture(userProfile.drawableId, userProfile.status )
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfilePicture(drawableId: Int, onlineStatus: Boolean){
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus == true)
                { lightGreen200 } // Custom color from UI Color.kt
            else { notOnline}
            ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = drawableId), // argument passed to function when its called
            modifier = Modifier.size(96.dp),
            contentScale = ContentScale.Crop, // make the picture fit to the container ??
            contentDescription = "Content description"
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "$userName", // given as argument when function is called
            style = MaterialTheme.typography.h5
        )

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCustomTheme() {
        MainScreen()
    }
}