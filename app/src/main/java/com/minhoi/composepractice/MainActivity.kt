package com.minhoi.composepractice

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.minhoi.composepractice.screen.Graph1
import com.minhoi.composepractice.screen.Graph2
import com.minhoi.composepractice.screen.Graph3
import com.minhoi.composepractice.ui.theme.ComposePracticeTheme
import kotlin.random.Random

private val TAG = MainActivity::class.simpleName

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePracticeTheme {
                CoupangEx()
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
fun TestPreview1() {
    ComposePracticeTheme {
        CoupangEx()
    }
}

@Composable
fun CoupangEx() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            TopLogoArea()
            TopSearchBarArea()
            TopBanner()
            CategoryList()
            CenterBannerArea()
        }
    }
}

@Composable
fun TopLogoArea() {
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Text("C", fontSize = 30.sp)
                Text("O", fontSize = 30.sp)
                Text("U", fontSize = 30.sp)
                Text("P", fontSize = 30.sp)
                Text("A", fontSize = 30.sp)
                Text("N", fontSize = 30.sp)
                Text("G", fontSize = 30.sp)
            }
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "cart",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
            )
        }
    }
}

@Composable
fun TopSearchBarArea() {
    var inputText by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
    ) {
        TextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            },
            placeholder = {
                Text(text = "쿠팡에서 검색하세요")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopBanner() {

    val pageState = rememberPagerState()
    val pageCount = 5

    val textList = listOf(
        "광고문구 1",
        "광고문구 2",
        "광고문구 3",
        "광고문구 4",
        "광고문구 5"
    )

    Box(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        HorizontalPager(
            count = pageCount,
            state = pageState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Gray)
        ) { page ->
            Text(
                text = textList[page],
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(15.dp)
        )
    }
}

@Composable
fun CategoryList() {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(10.dp)
    ) {
        val itemList = listOf(
            "Item1",
            "Item2",
            "Item3",
            "Item4",
            "Item5"
        )

        val iconList = listOf(
            Icons.Default.Favorite,
            Icons.Default.ArrowBack,
            Icons.Default.ShoppingCart,
            Icons.Default.List,
            Icons.Default.Phone
        )
        itemList.forEachIndexed { index, item ->
            Column(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .width(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = iconList[index], contentDescription = "categoryIcon")
                Text(text = item)
                Spacer(modifier = Modifier.size(10.dp))
                Icon(imageVector = iconList[index], contentDescription = "categoryIcon")
                Text(text = item)
            }
        }
    }
}

@Composable
fun CenterBannerArea() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(20.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "배너 영역",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
@Composable
fun RadiusTest2() {
    Column(

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(Color.Blue)
        )
        {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.large)
                .background(Color.Blue)
        )
        {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        )
        {

        }
    }
}


@Composable
fun RadiusTest1() {
    Column(

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(Color.Red)
        )
        {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.large)
                .background(Color.Red)
        )
        {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Red)
        )
        {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.small)
                .background(Color.Red)
        )
        {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .background(Color.Red)
        )
        {

        }
    }
}

@Composable
fun Test1() {
    Column {
        Text(
            text = "여기는 제목",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(30.dp)
        )
        Text(
            text = "여기는 내용",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(30.dp)
        )
        Text(
            text = "여기는 하단글",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(30.dp)
        )
    }
}

@Composable
fun Test2() {
    Column {
        Text(
            text = "여기는 제목",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(30.dp)
        )
        Text(
            text = "여기는 내용",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(30.dp)
        )
        Text(
            text = "여기는 하단글",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(30.dp)
        )
    }
}

@Composable
fun ThemeTest1() {
    ComposePracticeTheme(
//        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(text = "HELLO")
        }
    }
}

@Composable
fun GraphAppMain() {

    val navController = rememberNavController()

    val navItems = listOf(
        NavigationItem("그래프1", Icons.Default.AddCircle, "tab1"),
        NavigationItem("그래프2", Icons.Default.AddCircle, "tab2"),
        NavigationItem("그래프3", Icons.Default.AddCircle, "tab3")
    )

    Scaffold(
        bottomBar = {
            BottomAppBar(

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    navItems.forEach {
                        val currentRoute =
                            navController.currentBackStackEntryAsState().value?.destination?.route
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                navController.navigate(it.route)
                            }
                        ) {
                            val isCurrentRoute = it.route == currentRoute
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.name,
                                tint = if (isCurrentRoute) Color.Red else Color.Black
                            )
                            Text(
                                text = it.name,
                                color = if (isCurrentRoute) Color.Red else Color.Black
                            )
                        }
                    }
                }
            }
        }

    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = navItems.first().route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = "tab1") {
                Graph1()
            }
            composable(route = "tab2") {
                Graph2()
            }
            composable(route = "tab3") {
                Graph3()
            }
        }
    }
}

data class NavigationItem(
    val name: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun MyDiceApp() {
    var diceNumber by remember {
        mutableStateOf(5)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .border(3.dp, Color.Black)
                .background(Color.White)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            when (diceNumber) {
                1 -> DiceNumberCircle1()
                2 -> DiceNumberCircle2()
                3 -> DiceNumberCircle3()
                4 -> DiceNumberCircle4()
                5 -> DiceNumberCircle5()
                6 -> DiceNumberCircle6()
            }
        }

        Button(
            onClick = {
                diceNumber = Random.nextInt(1, 7)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "주사위 굴리기",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }

        Text(
            text = diceNumber.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
fun DiceNumberCircle1() {
    Box(
        modifier = Modifier.size(50.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(Color.Black, radius = size.minDimension / 10)
        }
    }
}

@Composable
fun DiceNumberCircle2() {
    Row {
        DiceNumberCircle1()
        Spacer(modifier = Modifier.size(50.dp))
        DiceNumberCircle1()
    }
}

@Composable
fun DiceNumberCircle3() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DiceNumberCircle1()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DiceNumberCircle1()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DiceNumberCircle1()
        }
    }
}

@Composable
fun DiceNumberCircle4() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        DiceNumberCircle2()
        DiceNumberCircle2()
    }
}

@Composable
fun DiceNumberCircle5() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiceNumberCircle2()
        DiceNumberCircle1()
        DiceNumberCircle2()
    }
}

@Composable
fun DiceNumberCircle6() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiceNumberCircle2()
        DiceNumberCircle2()
        DiceNumberCircle2()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyResume() {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Android Dev Resume") })
        },
    ) { paddingValues ->
        MyResumeContent(paddingValues = paddingValues)
    }
}

@Composable
fun MyResumeContent(paddingValues: PaddingValues) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.eximage1),
                contentDescription = "resume",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "자기소개",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "안녕하세요 안드로이드 개발자 지망생입니다.",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(text = "휴대폰 번호 : 010-1234-5678", modifier = Modifier.padding(10.dp))
            Text(text = "이메일 : example@example.com", modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"))
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "전화 걸기")
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:abc@naver.com")
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "이메일 보내기")
            }
        }
    }
}

@Composable
fun MyCanvas() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(Color.Green)
    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        ) {
            drawCircle(Color.Black, radius = size.minDimension / 2)
        }
    }
}

@Composable
fun MyDialog() {

    var dialogFlag by remember {
        mutableStateOf(false)
    }
    var inputText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            dialogFlag = true
        }) {
            Text(text = "나와라 Dialog")
        }
    }
}

@Composable
fun MyGridScreen(navHostController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(20.dp)
    ) {

        items(15) { number ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(1.dp, Color.Black)
                    .clickable {
                        navHostController.navigate("myNumberScreen/$number")
                    }
            ) {
                Text(text = number.toString(), fontSize = 30.sp)
            }
        }
    }
}

@Composable
fun MyNumberScreen(number: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = number.toString(), fontSize = 70.sp)
    }
}

@Composable
fun MyNav2(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "myGridScreen") {
        composable("myGridScreen") {
            MyGridScreen(navHostController)
        }
        composable("myNumberScreen/{number}") { navBackStackEntry ->
            MyNumberScreen(number = navBackStackEntry.arguments?.getString("number"))
        }
    }
}

@Composable
fun MyNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "myScreen1"
    ) {
        composable("myScreen1") {
            MyScreen1(navController = navController)
        }
        composable("myScreen2") {
            MyScreen2(navController = navController)
        }
        composable("myScreen3") {
            MyScreen3(navController = navController)
        }
    }
}

@Composable
fun MyScreen1(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "화면1", fontSize = 50.sp)
        Button(onClick = {
            navController.navigate("myScreen2")
        }) {
            Text(text = "2번 화면으로 가기", fontSize = 30.sp)
        }
    }
}

@Composable
fun MyScreen2(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "화면2", fontSize = 50.sp)
        Button(onClick = {
            navController.navigate("myScreen3")

        }) {
            Text(text = "3번 화면으로 가기", fontSize = 30.sp)
        }
    }
}

@Composable
fun MyScreen3(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "화면3", fontSize = 50.sp)
        Button(onClick = {
            navController.navigate("myScreen1")

        }) {
            Text(text = "1번 화면으로 가기", fontSize = 30.sp)
        }
    }
}

@Composable
fun MyShowHideEx2() {
    var switchState by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Switch(checked = switchState, onCheckedChange = {
            switchState = it
        })

        Text(
            text = if (switchState) "ON" else "OFF"
        )

        if (switchState) {
            Button(
                onClick = {

                }
            ) {
                Text(text = "얍얍")
            }
        }
    }
}

@Composable
fun MyShowHideEx1() {
    var isButtonVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                isButtonVisible = !isButtonVisible
            }
        ) {
            if (isButtonVisible) {
                Text(text = "숨기기", fontSize = 50.sp)
            } else {
                Text(text = "보이기", fontSize = 50.sp)
            }
        }
    }
}

@Composable
fun MyTextArea3() {
    MyTextFormat2 {
        Text(text = "안녕", fontSize = 100.sp, color = Color.Red)
    }
}

@Composable
fun MyTextFormat2(content: @Composable () -> Unit) {
    Column {
        content()
        content()
        content()
        content()
        content()
    }
}

@Composable
fun MyTextArea2() {
    Column {
        MyTextFormat1("안녕", 100.sp, Color.Red)
        MyTextFormat1("안녕", 100.sp, Color.Green)
        MyTextFormat1("안녕", 100.sp, Color.Gray)
    }
}

@Composable
fun MyTextFormat1(text: String, fontSize: TextUnit, color: Color) {
    Text(text = text, fontSize = fontSize, color = color)
}

@Composable
fun MyTextArea1() {
    Column {
        Text(
            text = "안녕",
            fontSize = 100.sp,
            color = Color.Red
        )

        Text(
            text = "안녕",
            fontSize = 100.sp,
            color = Color.Gray
        )

        Text(
            text = "안녕",
            fontSize = 100.sp,
            color = Color.Green
        )
    }
}

@Composable
fun MyProgressIndicator() {
    var progress by remember {
        mutableStateOf(0.0f)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            if (progress < 1.0f) {
                progress += 0.1f
            }
        }) {
            Text(text = "행복게이지", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.size(30.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.height(10.dp),
            color = Color.Red,
            trackColor = Color.Cyan
        )

        CircularProgressIndicator(
            progress = progress,
            color = Color.Red
        )
    }
}

@Composable
fun MyLazyRow() {
    val textList = listOf(
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
    )

    LazyRow {
        items(textList) {
            Text(
                text = it,
                fontSize = 100.sp,
                modifier = Modifier.clickable {
                    println("ClickedItem : $it")
                }
            )
        }
    }
}

@Composable
fun MyLazyColumnEx() {
    val textList = listOf(
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "A",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
        "B",
    )

    LazyColumn {
        items(textList) { item ->
            Text(text = item, fontSize = 60.sp, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun MyScaffoldEx() {

    Scaffold(
        topBar = { MyTopBar() },
        floatingActionButton = {
            MyFloatingActionButton()
        },
        bottomBar = {
            MyBottomBar()
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(text = "gdgdgd", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun MyBottomBar() {

    BottomAppBar(
        containerColor = Color.Red
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Home, contentDescription = "home")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Favorite, contentDescription = "fav")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Settings, contentDescription = "setting")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        title = {
            Text(text = "Main")
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        },
        actions = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Btn")
            }
        },
    )
}

@Composable
fun MyFloatingActionButton() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Default.Menu, contentDescription = "menu")
    }
}

@Composable
fun MySurface2() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray,
        border = BorderStroke(2.dp, Color.Red),
        contentColor = Color.Blue
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.size(200.dp),
                color = Color.Red
            ) {
                Text(text = "hello jetpack compose", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "hello")
        }
    }
}

@Composable
fun MySurface1() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = Color.Red,
        shape = RoundedCornerShape(20.dp)
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Green
            ),

            ) {
            Text(text = "클릭해보세요")
        }
    }
}

@Composable
fun CardTest(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ),
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center

        ) {
            Text(text = text, fontSize = 30.sp)
        }
    }
}

@Composable
fun ColumnRowTest2() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Cyan)
            .border(
                border = BorderStroke(5.dp, color = Color.Blue)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.eximage1),
                contentDescription = "ex",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
        }
        Text(
            text = "명함 만들기",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp)
        )
        Text(
            text = "Developer",
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "이메일",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "example@example.com",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = Color.Blue
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "연락처",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "010-1234-5678",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun ColumnRowTest1() {
    Column(
        // padding 먼저 주고 background 설정 (순서 주의)
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Gray)
    ) {
        Text(text = "안녕하세요")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "왼쪽")
            Text(text = "중앙")
            Text(text = "오른쪽")
        }
        Text(text = "반갑습니다")

    }
}

@Composable
fun RowTest() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalArrangement = Arrangement.SpaceEvenly, // 가로로 동일한 간격 정렬
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "item1",
            style = TextStyle(background = Color.Blue),
            fontSize = 30.sp
        )
        Text(
            text = "item2",
            style = TextStyle(background = Color.Red),
            fontSize = 30.sp
        )
        Text(
            text = "item3",
            style = TextStyle(background = Color.Green),
            fontSize = 30.sp
        )
    }
}

@Composable
fun BoxExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Blue)
                .padding(15.dp)
                .align(Alignment.TopStart)
        ) {
            Text(text = "왼쪽 위")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Green)
                .padding(15.dp)
                .align(Alignment.TopCenter)
        ) {
            Text(text = "중앙 위")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Gray)
                .padding(15.dp)
                .align(Alignment.TopEnd)
        ) {
            Text(text = "오른쪽 위")
        }

        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(text = "중앙 왼쪽")
        }

        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.Center)
        )
        {
            Text(text = "중앙 중앙")
        }

        Button(onClick = {}, modifier = Modifier.align(Alignment.CenterEnd)) {
            Text(text = "중앙 오른쪽")
        }

    }
}

@Composable
fun MyImageTest2() {
    AsyncImage(
        model = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.nationalgeographic.com%2Fanimals%2Fmammals%2Ffacts%2Fdomestic-dog&psig=AOvVaw32nsjHF_jsshLg-w9TTQ0_&ust=1719426007331000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCKDGzJSv94YDFQAAAAAdAAAAABAE",
        contentDescription = "ex",
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun MyImageTest1() {
    Image(
        painter = painterResource(id = R.drawable.eximage1),
        contentDescription = "ex"
    )
}


@Composable
fun MyTextField3() {
    // 입력한 부분
    var textState by remember {
        mutableStateOf("")
    }

    // 결과값 부분
    var enteredText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = textState,
            onValueChange = {
                textState = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                enteredText = textState
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "입력하기")
        }

        Text(text = "결과값 텍스트 : ${enteredText}")
    }
}

@Composable
fun MyTextField2() {
    var textState by remember {
        mutableStateOf("hello")
    }

    OutlinedTextField(
        value = textState,
        onValueChange = {
            textState = it
        },
        label = {
            Text(text = "입력하는 공간")
        }
    )
}

@Composable
fun MyTextField1() {
    var textState by remember {
        mutableStateOf("hello")
    }

    TextField(
        value = textState,
        onValueChange = {
            textState = it
        },
        label = {
            Text(text = "입력하는 공간")
        }
    )
}

@Composable
fun ColumnTest2() {
    Column(
        modifier = Modifier.padding(30.dp)
    ) {
        Text(text = "안녕하세요1", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(30.dp))
        Divider(thickness = 4.dp, color = Color.Blue)
        Text(text = "반갑습니다1", fontSize = 30.sp)
    }
}

@Composable
fun ColumnTest1() {
    Text(text = "안녕하세요1", fontSize = 30.sp)
    Text(text = "반갑습니다1", fontSize = 30.sp)
}

@Composable
fun SimpleCounterBtn() {
    var count by remember {
        mutableStateOf(0)
    }

    Button(
        onClick = {
            count++
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Count : $count",
            fontSize = 50.sp
        )
    }
}

@Composable
fun MyBtn() {

    val context = LocalContext.current

    Button(
        onClick = {
            Log.d("Main", "OnClick")
            Toast.makeText(context, "클릭완료", Toast.LENGTH_LONG).show()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Yellow,
            contentColor = Color.Blue
        ),
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
    ) {
        Text(
            text = "버튼입니다. 버튼버튼버튼버튼버튼",
            lineHeight = 30.sp,
            fontSize = 30.sp,
            color = Color.Red
        )
    }
}

@Composable
fun MyTextEx() {
    Text(
        text = "안녕하세요 텍스트 예제입니다.",
        fontSize = 30.sp,
        color = Color.Red,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(30.dp),
    )
}



