package com.rahuls.ta_hiring_internshala.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Workshop::class], version = 1)
abstract class WorkshopRoomDB : RoomDatabase() {
    abstract fun workshopDao() : WorkshopDAO
}