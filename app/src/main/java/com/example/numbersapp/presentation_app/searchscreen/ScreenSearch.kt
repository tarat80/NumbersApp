package com.example.numbersapp.presentation_app.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.numbersapp.R

@Composable
fun ScreenSearch(
     vm: ScreenSearchVM = hiltViewModel()
) {

    val stt = vm.state.collectAsStateWithLifecycle()

    Box(
        Modifier.fillMaxSize(),
        Alignment.Center
    ) {
        if (stt.value.isLoading) {
            CircularProgressIndicator(
                Modifier.fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )
        }
        stt.value.errorMessage?.let { errOr ->
            AlertDialog(
                onDismissRequest = vm::errorHaveRead,
                text = { Text(text = errOr) },
                buttons = {
                    Button(
                        onClick = vm::errorHaveRead,
                        Modifier.fillMaxWidth()
                    ) {
                    }
                }
            )
        }
    }
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Top,
        Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = stt.value.searchString,
            onValueChange = vm::searchChanged,
            label = { Text(text = stringResource(R.string.enter_number)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(18.dp))

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = vm::getByNumber
        ) {
            if (stt.value.searchString.isEmpty()) {
                Text(text = stringResource(R.string.random_search))
            } else {
                Text(text = stringResource(R.string.search))
            }
        }
        Spacer(modifier = Modifier.height(18.dp))

        val lLState = rememberLazyListState()
        LaunchedEffect(key1 = stt.value.numbersAndMeans.size) {
            lLState.scrollToItem(stt.value.numbersAndMeans.size)
        }
        LazyColumn(
            reverseLayout = true,
            state = lLState,
            horizontalAlignment = Alignment.Start
        ) {
            items(stt.value.numbersAndMeans.size) { index ->
                Item(
                    numberInfo = stt.value.numbersAndMeans[index]
                )
                Spacer(modifier = Modifier.height(33.dp))
            }
        }
    }
}