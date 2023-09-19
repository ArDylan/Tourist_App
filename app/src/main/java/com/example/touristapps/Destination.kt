package com.example.touristapps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val name: String,
    val image: String,
    val overview: String,
    val description: String,
    val location: String,
    val detailImage: String,
    val rating: String
) : Parcelable
