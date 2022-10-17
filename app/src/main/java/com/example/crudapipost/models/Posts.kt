package com.example.crudapipost.models

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

class Posts (
        @SerializedName("userId")
        var userId: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("body")
        var body: String
        )