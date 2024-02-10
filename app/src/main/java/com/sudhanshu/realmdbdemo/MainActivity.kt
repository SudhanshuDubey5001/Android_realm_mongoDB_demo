package com.sudhanshu.realmdbdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sudhanshu.realmdbdemo.models.Course
import com.sudhanshu.realmdbdemo.ui.theme.RealmDBDemoTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealmDBDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val courses by viewModel.courses.collectAsState()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(courses) { course ->
                            CourseItem(course = course,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clickable {
                                        viewModel.showCourseDetails(course)
                                    })
                        }
                    }
                    if(viewModel.courseDetails!=null){
                        Dialog(
                            onDismissRequest = viewModel::hideCourseDetails) {
                            Column(
                                modifier = Modifier
                                    .widthIn(200.dp, 300.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .padding(20.dp)
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                viewModel.courseDetails?.teacherAssigned.let { teacher ->
                                    Text(text = "Name: " + teacher?.fullName)
                                    Text(text = "Address: " + teacher?.address?.streetName + " " + teacher?.address?.localityName)
                                    Text(text = "City: " + teacher?.address?.city)
                                    Text(text = "ZipCode: " + teacher?.address?.zipCode)
                                }
                                Button(onClick = viewModel::deleteCourse,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.errorContainer,
                                        contentColor = MaterialTheme.colorScheme.error
                                    )) {
                                    Text(text = "Delete Course")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CourseItem(
    course: Course,
    modifier: Modifier
) {
    Column(
        modifier = modifier) {
        Text(
            text = course.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "Held by ${course.teacherAssigned?.fullName}",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enrolled Students : ${course.enrolledStudents.joinToString { it.name }}",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}