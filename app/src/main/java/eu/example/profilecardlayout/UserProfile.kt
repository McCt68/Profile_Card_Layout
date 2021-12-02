package eu.example.profilecardlayout

// data class to hold user values

// UserProfile acts like a container that holds values of each user
// we pass a UserProfile to @Composable ProfileCard
// constructor takes 3 parameters, name, status, drawableId
data class UserProfile constructor (
    val name: String,
    val status: Boolean, // passed to @Composable ProfilePicture
    val drawableId: Int // passed to @Composable ProfilePicture
    )

// making a list of UserProfiles
// this is just for simplicity
// in real this could come from a server or other source
val userProfileList = arrayListOf<UserProfile>(
    UserProfile("Bettine", true, R.drawable.bettine_tenerife),
    UserProfile("Fætter BR", false, R.drawable.peter_br),
    UserProfile(name = "Michael", status = true, R.drawable.michael_thailand),
    UserProfile("Bettine", true, R.drawable.bettine_tenerife),
    UserProfile("Fætter BR", false, R.drawable.peter_br),
    UserProfile(name = "Michael", status = true, R.drawable.michael_thailand),
    UserProfile("Bettine", true, R.drawable.bettine_tenerife),
    UserProfile("Fætter BR", false, R.drawable.peter_br),
    UserProfile(name = "Michael", status = true, R.drawable.michael_thailand),
    UserProfile("Bettine", true, R.drawable.bettine_tenerife),
    UserProfile("Fætter BR", false, R.drawable.peter_br),
    UserProfile(name = "Michael", status = true, R.drawable.michael_thailand)

)