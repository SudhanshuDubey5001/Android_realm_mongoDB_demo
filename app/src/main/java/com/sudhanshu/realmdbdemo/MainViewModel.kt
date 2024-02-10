package com.sudhanshu.realmdbdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhanshu.realmdbdemo.models.Address
import com.sudhanshu.realmdbdemo.models.Course
import com.sudhanshu.realmdbdemo.models.Teacher
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.sudhanshu.realmdbdemo.MockObjects
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

//Relations
// Teacher 1-to-1 with Address    ---> one teacher can have only one address
// Teacher 1-to-many with courses  ---> one teacher can teach many courses but one course can only be taught by one teacher
// Students many-to-many courses   ---> one students can join multiple courses and one course can be studied by many students

class MainViewModel : ViewModel() {

    private var realm = MyApp.realm

    init {
        createDatabaseEntry()
    }

    //we made a query object then converted it to flow and then converted the results to a normal
    // list from RealmList and then listen to that list using StateFlow by providing couroutine
    // scope "viewModelScope"
    val courses = realm.query<Course>(
        //write your queries here
//        "name == $0", "Mobile App Design"       //how to filter specific fields
//        "enrolledStudents.@count > 3"
//        "teacherAssigned.fullName CONTAINS $0", "Watson"
    ).asFlow().map { results ->
        results.list.toList()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    var courseDetails: Course? by mutableStateOf(null)
        private set

    fun showCourseDetails(course: Course){
        courseDetails = course
    }
    fun hideCourseDetails(){
        courseDetails = null
    }

    fun deleteCourse(){
        viewModelScope.launch {
            realm.writeBlocking {
                //first check if the courseDetails is null or not
                val course = courseDetails ?: return@writeBlocking
                //if its not null find the latest course object to avoid race conditions and apply thread safety
                val latestCourse = findLatest(course) ?:return@writeBlocking
                //not that we have the object we can delete it
                delete(latestCourse)

                courseDetails = null
            }
        }
    }

    private fun createDatabaseEntry() {
        viewModelScope.launch {
            realm.write {
                MockObjects.course1.teacherAssigned = MockObjects.teacher1
                MockObjects.course2.teacherAssigned = MockObjects.teacher1
                MockObjects.course3.teacherAssigned = MockObjects.teacher2
                MockObjects.course4.teacherAssigned = MockObjects.teacher2
                MockObjects.course5.teacherAssigned = MockObjects.teacher3
                MockObjects.course6.teacherAssigned = MockObjects.teacher3
                MockObjects.course7.teacherAssigned = MockObjects.teacher4
                MockObjects.course8.teacherAssigned = MockObjects.teacher4
                MockObjects.course9.teacherAssigned = MockObjects.teacher5
                MockObjects.course10.teacherAssigned = MockObjects.teacher5

                MockObjects.course1.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student1,
                        MockObjects.student2
                    )
                )
                MockObjects.course2.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student3,
                        MockObjects.student4,
                        MockObjects.student5
                    )
                )
                MockObjects.course3.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student6,
                        MockObjects.student7
                    )
                )
                MockObjects.course4.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student8,
                        MockObjects.student9,
                        MockObjects.student10
                    )
                )
                MockObjects.course5.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student1,
                        MockObjects.student2,
                        MockObjects.student3
                    )
                )
                MockObjects.course6.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student4,
                        MockObjects.student5,
                        MockObjects.student6
                    )
                )
                MockObjects.course7.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student7,
                        MockObjects.student8,
                        MockObjects.student9
                    )
                )
                MockObjects.course8.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student10,
                        MockObjects.student1
                    )
                )
                MockObjects.course9.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student2,
                        MockObjects.student3,
                        MockObjects.student4
                    )
                )
                MockObjects.course10.enrolledStudents.addAll(
                    listOf(
                        MockObjects.student5,
                        MockObjects.student6,
                        MockObjects.student7,
                        MockObjects.student8
                    )
                )

                // now we will copy this to realm. Update policy ALL - meaning if try to insert it with existing
                // primary key, it will update it with new object
                copyToRealm(MockObjects.teacher1, UpdatePolicy.ALL)
                copyToRealm(MockObjects.teacher2, UpdatePolicy.ALL)
                copyToRealm(MockObjects.teacher3, UpdatePolicy.ALL)
                copyToRealm(MockObjects.teacher4, UpdatePolicy.ALL)
                copyToRealm(MockObjects.teacher5, UpdatePolicy.ALL)

                copyToRealm(MockObjects.course1, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course2, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course3, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course4, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course5, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course6, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course7, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course8, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course9, UpdatePolicy.ALL)
                copyToRealm(MockObjects.course10, UpdatePolicy.ALL)

                copyToRealm(MockObjects.student1, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student2, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student3, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student4, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student5, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student6, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student7, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student8, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student9, UpdatePolicy.ALL)
                copyToRealm(MockObjects.student10, UpdatePolicy.ALL)
            }
        }
    }
}