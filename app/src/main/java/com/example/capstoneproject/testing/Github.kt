package com.example.github.ui.resource

import android.os.Parcel
import android.os.Parcelable

data class Github(
    var username: String? = "",
    var name: String? = "",
    var avatar: Int? = 0,
    var company: String? = "",
    var location: String? = "",
    var repository: String? = "",
    var follower: String? = "",
    var following: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeValue(avatar)
        parcel.writeString(company)
        parcel.writeString(location)
        parcel.writeString(repository)
        parcel.writeString(follower)
        parcel.writeString(following)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Github> {
        override fun createFromParcel(parcel: Parcel): Github {
            return Github(parcel)
        }

        override fun newArray(size: Int): Array<Github?> {
            return arrayOfNulls(size)
        }
    }

}