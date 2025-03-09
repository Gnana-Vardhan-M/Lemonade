package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                MakingLemonade()
            }
        }
    }
}

@Composable
fun MakingLemonade(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {
    var squeezeCount by remember { mutableIntStateOf(0)}
    var currentStep by remember {mutableIntStateOf(1) }
    var clickBuffer by remember {mutableIntStateOf(2) }
    when (currentStep) {
        1 -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.lemon_tree),
                contentDescription = stringResource(id = R.string.lemonTree),
                modifier = Modifier.clickable {
                    squeezeCount = (15..20).random()
                    currentStep = 2 })
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.tap))
        }

        2 -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.lemon_squeeze),
                contentDescription = stringResource(id = R.string.lemon),
                modifier = Modifier.clickable {
                    clickBuffer = 5
                    squeezeCount--
                    if(squeezeCount == 0)
                        currentStep = 3 })
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.keep))
        }

        3 -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.lemon_drink),
                contentDescription = stringResource(id = R.string.glass),
                modifier = Modifier.clickable {
                    clickBuffer--
                    if (clickBuffer == 0)
                        currentStep = 4 })
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.drink))
            Text(text = stringResource(id = R.string.clickbuffer))
        }

        4 -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.lemon_restart),
                contentDescription = stringResource(id = R.string.empty),
                modifier = Modifier.clickable { currentStep = 1 })
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.start))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        MakingLemonade()
    }
}