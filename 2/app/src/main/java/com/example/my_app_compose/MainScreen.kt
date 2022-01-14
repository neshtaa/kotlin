package com.example.my_app_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.my_app_compose.ProfileScreen
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable


import com.example.my_app_compose.ui.theme.My_app_composeTheme


data class Item(
    val id: Int,
    val title: String,
    val sex: String,
    val image: Int = R.drawable.ic_launcher_background,
    val description: String,
    val age: Int
)

val ItemsList = listOf<Item>(
    Item(
        id = 0,
        title = "Monty",
        sex = "Male",
        image = R.drawable.cat1,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        age = 2
    ),
    Item(
        id = 1,
        title = "Chloe",
        sex = "Female",
        image = R.drawable.cat2,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        age = 3
    ),
    Item(
        id = 3,
        title = "Lucy",
        sex = "Female",
        image = R.drawable.cat3,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        age = 1
    ),
    Item(
        id = 4,
        title = "Robert",
        sex = "Male",
        image = R.drawable.cat4,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        age = 2
    ),
    Item(
        id = 5,
        title = "Winky",
        sex = "Male",
        image = R.drawable.cat5,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        age = 3
    ),
    Item(
        id = 6,
        title = "Tomas",
        sex = "Male",
        image = R.drawable.cat6,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        age = 4
    ),
    Item(
        id = 7,
        title = "Casia",
        sex = "Female",
        image = R.drawable.cat7,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        age = 5
    ),
)

class ItemsViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    fun getById(id: Int) = items.value.first { it.id == id }

    init {
        _items.value = ItemsList;
    }
}

@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)
        .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(5.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .absolutePadding(right = 10.dp, left = 10.dp, top = 10.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(15.dp)),
            )
            Column(
                modifier = Modifier.absolutePadding(left = 5.dp)
            ) {
                Text(item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary)
                Text(item.sex,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onPrimary)
            }
        }
    }

}

@Composable
fun ItemsComponent(data: List<Item>, onSelected: (Item) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ){
        items(data) {
                item -> ItemRow(item = item, onClick = { onSelected(item) })
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    ItemsComponent(
        data = ItemsList,
        onSelected = { }
    )
}
//@Preview(showBackground = true, device = Devices.PIXEL_2)


@Composable
fun MainScreen() {
    MyAppTheme{
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.surface
        ) {
            var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
            val viewModel = ItemsViewModel()
            Crossfade(targetState = selectedId) { id ->
                if (id == null) {
                    ItemsComponent(
                        data = ItemsList,
                        onSelected = { selectedId = it.id }
                    )
                } else {
                    ProfileScreen(item = viewModel.getById(id))
                    BackHandler {
                        selectedId = null
                    }
                }
            }
            //ProfileScreen()
        }
    }
}

