package com.example.customlistview

import android.graphics.Bitmap
import android.provider.ContactsContract.CommonDataKinds.Phone

class Person(
    val name: String,
    val age: String,
    val phone: String,
    val image: Bitmap?
){
}