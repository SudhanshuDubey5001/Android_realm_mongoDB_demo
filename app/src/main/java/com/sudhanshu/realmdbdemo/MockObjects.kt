package com.sudhanshu.realmdbdemo

import com.sudhanshu.realmdbdemo.models.Address
import com.sudhanshu.realmdbdemo.models.Course
import com.sudhanshu.realmdbdemo.models.Student
import com.sudhanshu.realmdbdemo.models.Teacher
import io.realm.kotlin.ext.realmListOf

object MockObjects {
    val address1 = Address().apply {
        houseNumber = 12
        localityName = "East Side"
        streetName = "Park Avenue"
        city = "Los Angeles"
        zipCode = 123456
    }

    val address2 = Address().apply {
        houseNumber = 45
        localityName = "West Side"
        streetName = "Main Street"
        city = "New York"
        zipCode = 789012
    }

    val address3 = Address().apply {
        houseNumber = 34
        localityName = "North Side"
        streetName = "Broadway"
        city = "Chicago"
        zipCode = 567890
    }

    val address4 = Address().apply {
        houseNumber = 78
        localityName = "South Side"
        streetName = "Ocean Drive"
        city = "Miami"
        zipCode = 345678
    }

    val address5 = Address().apply {
        houseNumber = 56
        localityName = "Central Area"
        streetName = "Market Street"
        city = "San Francisco"
        zipCode = 901234
    }

    val course1 = Course().apply {
        name = "Coding Made Easy"
    }

    val course2 = Course().apply {
        name = "Advanced Algorithms"
    }

    val course3 = Course().apply {
        name = "Mobile App Development"
    }

    val course4 = Course().apply {
        name = "Web Development Basics"
    }

    val course5 = Course().apply {
        name = "Data Science Fundamentals"
    }

    val course6 = Course().apply {
        name = "Data Structures and Algorithms"
    }

    val course7 = Course().apply {
        name = "Mobile App Design"
    }

    val course8 = Course().apply {
        name = "Artificial Intelligence Fundamentals"
    }

    val course9 = Course().apply {
        name = "Web Development Frameworks"
    }

    val course10 = Course().apply {
        name = "Cloud Computing Essentials"
    }

    val teacher1 = Teacher().apply {
        this.fullName = "Jane Watson"
        this.address = address1
        this.courses = realmListOf(course1, course2)
    }

    val teacher2 = Teacher().apply {
        this.fullName = "John Doe"
        this.address = address2
        this.courses = realmListOf(course3, course4)
    }

    val teacher3 = Teacher().apply {
        this.fullName = "Shaun Mendes"
        this.address = address3
        this.courses = realmListOf(course5, course6)
    }

    val teacher4 = Teacher().apply {
        this.fullName = "Grey House"
        this.address = address4
        this.courses = realmListOf(course7, course8)
    }

    val teacher5 = Teacher().apply {
        this.fullName = "Will Turner"
        this.address = address5
        this.courses = realmListOf(course9, course10)
    }

    val student1 = Student().apply {
        name = "Lulu Craig"
    }

    val student2 = Student().apply {
        name = "Oliver Smith"
    }

    val student3 = Student().apply {
        name = "Emma Johnson"
    }

    val student4 = Student().apply {
        name = "James Wilson"
    }

    val student5 = Student().apply {
        name = "Ava Davis"
    }

    val student6 = Student().apply {
        name = "Mia Thompson"
    }

    val student7 = Student().apply {
        name = "Liam Brown"
    }

    val student8 = Student().apply {
        name = "Sophia White"
    }

    val student9 = Student().apply {
        name = "Noah Martinez"
    }

    val student10 = Student().apply {
        name = "Isabella Jackson"
    }
}
