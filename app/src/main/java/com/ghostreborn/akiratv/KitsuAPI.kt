package com.ghostreborn.akiratv

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KitsuAPI {
    suspend fun login(userName: String, pass: String) = withContext(Dispatchers.IO) {
        return@withContext Constants.api
            .login(username = userName, password = pass)
            .execute()
            .body()
    }

    suspend fun user(token: String) = withContext(Dispatchers.IO) {
        return@withContext Constants.api
            .user("Bearer $token")
            .execute()
            .body()
    }

    suspend fun ids(userID: String, offset: Int) = withContext(Dispatchers.IO) {
        return@withContext Constants.api
            .ids(userID, offset)
            .execute()
            .body()
    }

    suspend fun anime(entryID: String) = withContext(Dispatchers.IO) {
        return@withContext Constants.api
            .anime(entryID)
            .execute()
            .body()
    }
}