package com.sudhanshu.realmdbdemo.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

// we will use RealmObject here as we want to insert this Teacher object in the DB
class Teacher : RealmObject{
    @PrimaryKey var _id: ObjectId = ObjectId()  //now since we will insert this record, we need a primary key
    var fullName: String = ""
    var address: Address? = null
    var courses: RealmList<Course> = realmListOf()  //we cannot use normal List<Course> as this will be inserted to DB
}