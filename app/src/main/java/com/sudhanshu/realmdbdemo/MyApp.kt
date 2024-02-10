package com.sudhanshu.realmdbdemo

import android.app.Application
import com.sudhanshu.realmdbdemo.models.Address
import com.sudhanshu.realmdbdemo.models.Course
import com.sudhanshu.realmdbdemo.models.Student
import com.sudhanshu.realmdbdemo.models.Teacher
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApp: Application() {

    companion object{
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Address::class,
                    Teacher::class,
                    Student::class,
                    Course::class,
                )
            )
        )
    }
}