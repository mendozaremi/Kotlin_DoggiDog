package com.remi.doggiedog.network

import com.squareup.moshi.Json

// each dog image gets own url
data class DogImage(@Json(name = "message") val imageSrcUrl: String)