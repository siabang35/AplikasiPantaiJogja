package com.example.slideimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slideimage.ui.theme.SlideImageTheme

data class Art(
    val imageResId: Int,
    val title: String,
    val description: String,
    val additionalInfo: AnnotatedString
)

val artList = listOf(
    Art(
        R.drawable.kesirat,
        "Pantai Kesirat",
        "Pantai Kesirat adalah salah satu pantai tersembunyi di Kabupaten Gunung Kidul, Daerah Istimewa Yogyakarta. Meski lokasinya terpencil, pantai ini memiliki keindahan pemandangan yang luar biasa.",
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("Baca artikel detikjogja, Pantai Kesirat Jogja: Kelebihan, Kekurangan, Fasilitas, Harga, dan Rute, selengkapnya ")
            }
            addStringAnnotation(
                tag = "URL",
                annotation = "https://www.detik.com/jogja/plesir/d-6882830/pantai-kesirat-jogja-kelebihan-kekurangan-fasilitas-harga-dan-rute",
                start = length - 1,
                end = length
            )
        }
    ),
    Art(
        R.drawable.slili,
        "Pantai Slili",
        "Pantai Slili merupakan pantai yang berada di Gunungkidul, Yogyakarta. Pantai ini istimewa karena memiliki pasir putih dengan berbatuan. Nama Slili berasal dari kata ‘mili’, dalam bahasa Jawa, artinya mengalir.",
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("Baca Informasi selengkapnya ")
            }
            addStringAnnotation(
                tag = "URL",
                annotation = "https://napaktilas.net/pantai/jogja/gunungkidul/slili/",
                start = length - 1,
                end = length
            )
        }
    ),
    Art(
        R.drawable.ngrumput,
        "Pantai Ngrumput",
        "Pantai Ngrumput adalah surga tersembunyi di antara semua pantai di Indonesia. Terletak di Kabupaten Gunung Kidul, Yogyakarta. Pantai ini menawarkan kombinasi sempurna antara pasir putih yang lembut, air laut yang jernih, dan tebing-tebing karst yang menjulang tinggi. Keindahan alam yang belum terjamah dan suasana tenang membuat Pantai Ngrumput menjadi destinasi yang sempurna untuk pelancong yang mencari ketenangan dan keindahan alam.",
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("Baca Informasi Selengkapnya ")
            }
            addStringAnnotation(
                tag = "URL",
                annotation = "https://pantai.or.id/pantai-ngrumput",
                start = length - 1,
                end = length
            )
        }
    )
)



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SlideImageTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        SlideImageScreen(artList)
                    }
                }
            }
        }
    }
}
//fungsi untuk menampilkan slide gambar dengan artList
@Composable
fun SlideImageScreen(artList: List<Art>) {
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(MaterialTheme.shapes.medium)
                .shadow(16.dp, CircleShape) // Add shadow effect to the image
        ) {
            Image(
                painter = painterResource(id = artList[currentIndex].imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = artList[currentIndex].title,
            style = MaterialTheme.typography.bodyMedium
        )

        // Split description into paragraphs
        val descriptionLines = artList[currentIndex].description.split("\n")

        // Display description in multiple paragraphs
        descriptionLines.forEach { line ->
            Text(
                text = line,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        // Tombol navigasi untuk menampilkan image previous dan next dengan menerapkan Row dan iconButton
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val isPreviousButtonHovered by remember { mutableStateOf(false) }
            val isNextButtonHovered by remember { mutableStateOf(false) }

            IconButton(
                onClick = {
                    currentIndex = (currentIndex - 1).coerceIn(0, artList.size - 1)
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (isPreviousButtonHovered) MaterialTheme.colorScheme.primary.copy(alpha = 0.8f) else MaterialTheme.colorScheme.primary)
                    .padding(12.dp)
                    .clickable { /* Handle click here */ }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = {
                    currentIndex = (currentIndex + 1).coerceIn(0, artList.size - 1)
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (isNextButtonHovered) MaterialTheme.colorScheme.primary.copy(alpha = 0.8f) else MaterialTheme.colorScheme.primary)
                    .padding(12.dp)
                    .clickable { /* Handle click here */ }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SlideImagePreview() {
    SlideImageTheme {
        SlideImageScreen(artList)
    }
}
