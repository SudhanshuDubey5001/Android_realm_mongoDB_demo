package com.sudhanshu.realmdbdemo.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Course : RealmObject{
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    var teacherAssigned: Teacher? = null
    var enrolledStudents: RealmList<Student> = realmListOf()
}