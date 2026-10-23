package me.cristianpena.facebook.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

val FeedBorder = Color(0xFFE4E6EB)

data class Post(
    val id: String = "1",
    val author: String = "Cristian Peña",
    val time: String = "Hace 2 horas",
    val text: String = "Por fin terminé la pantalla de Login 🚀",
    val imageUrl: String? = "https://picsum.photos/600/400",
    val imageEmoji: String? = null,
    val gradient: List<Color>? = null,
    val likes: Int = 24,
    val comments: Int = 3,
)

val samplePosts = listOf(
    Post(
        id = "1",
        author = "Cristian Peña",
        time = "Hace 2 horas",
        text = "Por fin terminé la pantalla de Login 🚀",
        imageUrl = "https://picsum.photos/600/400",
        likes = 24,
        comments = 3,
    ),
    Post(
        id = "2",
        author = "María García",
        time = "Hace 5 horas",
        text = "¡Disfrutando del fin de semana! ☀️",
        imageUrl = "https://picsum.photos/600/400?random=2",
        likes = 42,
        comments = 7,
    ),
)

@Composable
fun PostContent(
    text: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    @Suppress("UNUSED_PARAMETER") imageEmoji: String? = null,
    @Suppress("UNUSED_PARAMETER") gradient: List<Color>? = null,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (text.isNotEmpty()) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            )
        }
        if (!imageUrl.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            AsyncImage(
                model = imageUrl,
                contentDescription = "Imagen de la publicación",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
            )
        }
    }
}

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier,
) {
    var liked by rememberSaveable(post.id) { mutableStateOf(false) }

    Surface(
        color = Color.White,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column {
            PostHeader(
                name = post.author,
                time = post.time,
            )

            PostContent(
                text = post.text,
                imageUrl = post.imageUrl,
                imageEmoji = post.imageEmoji,
                gradient = post.gradient,
            )

            PostStats(
                likes = post.likes + if (liked) 1 else 0,
                comments = post.comments,
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 12.dp),
                color = FeedBorder,
            )

            PostActions(
                liked = liked,
                onLikeClick = { liked = !liked },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostCardPreview() {
    PostCard(post = samplePosts.first())
}
