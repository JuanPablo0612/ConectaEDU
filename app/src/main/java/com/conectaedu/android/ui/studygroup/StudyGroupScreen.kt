package com.conectaedu.android.ui.studygroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.conectaedu.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyGroupScreen(
    viewModel: StudyGroupViewModel = hiltViewModel(),
    areaId: String,
    courseId: String,
    studyGroupId: String,
    navController: NavController
) {
    val uiState = viewModel.uiState
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(key1 = true) {
        viewModel.init(areaId, courseId, studyGroupId)
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = uiState.studyGroup.name, fontSize = 14.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = uiState.studyGroup.description, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(
                        items = uiState.messages,
                        key = { it.id },
                        contentType = { "message" }
                    ) { message ->
                        if (message.senderId == uiState.currentUser.id) {
                            SentMessageCard(message = message)
                        } else {
                            ReceivedMessageCard(message = message)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = uiState.message,
                        onValueChange = { viewModel.onMessageTextChanged(it) },
                        label = { Text(text = stringResource(id = R.string.study_group_message)) },
                        modifier = Modifier.weight(1f)
                    )

                    Button(onClick = { viewModel.onAddMessage() }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    }
                }
            }
        }
    }
}