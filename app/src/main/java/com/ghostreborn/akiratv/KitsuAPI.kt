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
}