package com.application.codelab2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.application.codelab2.ui.theme.CodeLab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val elements = alignBodySectionData()
            val collectionsData = collectionsData()
            val viewModel = ViewModelProvider(this)[SharedViewModel::class.java]
            CodeLab2Theme {
                Scaffold(bottomBar = { BottomBarComposable(viewModel = viewModel) }) { padding ->
                    if (viewModel.screenName.value == "Home") {
                        MyScreen(
                            elements = elements,
                            collectionsData,
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(padding)
                        )
                    }
                }
            }
        }
    }

    private fun alignBodySectionData(): ArrayList<AlignBody> {
        val alignBodyData = ArrayList<AlignBody>()
        val titles = arrayOf(
            "Yoga Master",
            "Aerobics",
            "Cardio",
            "Cooling Down",
            "Exercise",
            "Warm Up",
            "Biceps"
        )
        val images: Array<Int> = arrayOf(
            R.drawable.fitness_1,
            R.drawable.fitness_2,
            R.drawable.fitness_3,
            R.drawable.fitness_4,
            R.drawable.fitness_5,
            R.drawable.fitness_6,
            R.drawable.fitness_7
        )
        for ((image, title) in images.zip(titles)) {
            alignBodyData.add(AlignBody(image, title))
        }
        return alignBodyData
    }

    private fun collectionsData(): ArrayList<Collections> {
        val collectionsData = ArrayList<Collections>()
        val images: Array<Int> = arrayOf(
            R.drawable.nature1,
            R.drawable.nature2,
            R.drawable.nature3,
            R.drawable.nature4,
            R.drawable.nature5,
            R.drawable.nature6,
            R.drawable.nature7,
            R.drawable.nature8,
            R.drawable.nature9,
            R.drawable.nature10,
            R.drawable.nature11,
            R.drawable.nature12,
            R.drawable.nature13,
            R.drawable.nature14,
        )
        val titles: Array<String> = arrayOf(
            "Ecosystems",
            "Landscapes",
            "Forests",
            "Oceans",
            "Geology",
            "Flora",
            "Climate Patterns",
            "Architects",
            "Ornithology",
            "Nature",
            "Outdoors",
            "Heroes",
            "Sustainable",
            "Woods"
        )
        for ((image, title) in images.zip(titles)) {
            collectionsData.add(Collections(image, title))
        }
        return collectionsData
    }
}

//Data class for Align Your body elements
data class AlignBody(val image: Int, val title: String)

//Data class for Collections
data class Collections(val image: Int, val title: String)

@Preview(showSystemUi = true)
@Composable
private fun PreviewFunction() {
    MyScreen(ArrayList(), ArrayList())
}

@Composable
fun MyScreen(
    elements: ArrayList<AlignBody>,
    collections: ArrayList<Collections>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SearchBarComposable()
        Text(
            text = "Align your Body",
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        LazyRowComposable(
            elements = elements,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp)
        )
        Text(
            text = "Favorite Collections",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp, start = 10.dp)
        )
        LazyRowGridComposable(
            collections = collections, modifier = Modifier
                .height(168.dp)
                .fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun CollectionItemsPreview() {
    CollectionItems()
}

@Composable
fun CollectionItems(image: Int = R.drawable.nature1, title: String = "Nature") {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .width(200.dp)
            .wrapContentHeight()

    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = image), contentDescription = "",
                Modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = title, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }

    }
}

@Composable
fun LazyRowGridComposable(collections: ArrayList<Collections>, modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(collections) { data ->
            CollectionItems(data.image, data.title)
        }
    }
}


@Preview()
@Composable
fun NavigationBarPreview() {
    BottomBarComposable(SharedViewModel())
}


@Composable
fun BottomBarComposable(viewModel: SharedViewModel) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NavigationBarItem(
            selected =
            viewModel.screenName.value == "Home", onClick = { viewModel.screenName.value = "Home" },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_filled_24),
                    contentDescription = "Home Icon"
                )
            },
            label = {
                Text(text = "Home")
            }
        )
        NavigationBarItem(selected = viewModel.screenName.value == "EmptyScreen",
            onClick = { viewModel.screenName.value = "EmptyScreen" },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_hourglass_empty_24),
                    contentDescription = "EmptyScreen"
                )
            },
            label = { Text(text = "Empty Screen") })
    }
}

@Composable
fun LazyRowComposable(elements: ArrayList<AlignBody>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier) {
        items(elements) { element ->
            AlignYourBodyElement(
                element.image,
                element.title,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ElementPreview() {
    AlignYourBodyElement(R.drawable.fitness_4, "Yoga ")
}

@Composable
fun AlignYourBodyElement(image: Int, title: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Text(text = title)

    }
}

@Composable
fun SearchBarComposable(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
) {
    var textFieldText by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = textFieldText, onValueChange = { textFieldText = it },
        placeholder = {
            Text(text = "Search")
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Search Icon",
            )
        },
        modifier = modifier
    )
}
