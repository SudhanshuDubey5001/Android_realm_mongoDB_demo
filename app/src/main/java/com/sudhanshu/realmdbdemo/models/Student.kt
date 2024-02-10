package com.sudhanshu.realmdbdemo.models

import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Student : RealmObject{
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    // since we don't repetition as enrolledStudents and enrolledCourses have repeated items as for each
    // students if there are 3 subjects, then for each of those 3 subjects, there will be 3 same student
    // name in the list. So we use RealmResult and create a backlink to tell which variable i,e enrolledStudent
    val enrolledCourses: RealmResults<Course> by backlinks(Course::enrolledStudents)
}