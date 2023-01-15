package `in`.palashbauri.blogfreedom.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PostListView(){
    //for (i in 0..10){
        PostCard()
    //}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCard(){
    OutlinedCard( modifier = Modifier.fillMaxWidth(1f) ) {
        Text(text = "10-01-2023")
        Text(text = "Hello world")
        Text(text = "Lorem Ipsum")
    }
}

@Preview(showBackground = true)
@Composable
fun PostListViewPreview(){
    PostListView()
}