package `in`.palashbauri.blogfreedom.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(){
    var wfInstanceUrl by remember {
         mutableStateOf(TextFieldValue("https://"))
    }
    var wfUsername by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var wfPassword by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Surface(modifier = Modifier
        .fillMaxSize(1f)
        .padding(top = 150.dp, bottom = 150.dp)) {

        OutlinedCard(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.fillMaxSize(1f) ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {

                Text(text = "Login to WriteFreely",
                    fontSize = 30.sp ,
                    fontWeight = FontWeight.ExtraBold ,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(color = Color.Black)
                )

                OutlinedTextField(value = wfInstanceUrl ,
                                    onValueChange = { wfInstanceUrl = it },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                )
                OutlinedTextField(value = wfUsername,
                                    placeholder = { Text(text = "username") },
                                    onValueChange = {wfUsername = it},

                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                )
                OutlinedTextField(value = wfPassword,
                                    placeholder = { Text(text = "password") },
                                    onValueChange = {wfPassword = it},
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                )
                
                OutlinedButton(onClick = { /*TODO*/ }  ) {
                    Text(text = "Login")
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    LoginView()
}
