package com.sudhanshu.realmdbdemo.models

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject

//Relations
// Teacher 1-to-1 with Address    ---> one teacher can have only one address
// Teacher 1-to-many with courses  ---> one teacher can teach many courses but one course can only be taught by one teacher
// Students many-to-many courses   ---> one students can join multiple courses and one course can be studied by many students
//class Address: RealmObject{
class Address :
    EmbeddedRealmObject {     // we are using embedded one bcz we are not going to store the address object alone but with combination with Teacher object
    //we are not creating _id var here bcz we will do it in Teacher
    var houseNumber: Int = 0
    var streetName: String = ""
    var localityName: String = ""
    var city: String = ""
    var zipCode: Int = 0
    var teacher: Teacher? = null
}