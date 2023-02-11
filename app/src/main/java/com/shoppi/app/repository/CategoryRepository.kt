package com.shoppi.app.repository

import com.shoppi.app.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository (private val remoteDataSource: CategoryRemoteDataSource
) {


    suspend fun getCategories(): List<Category> {

        // Thread 전환 과정 Retrofit이 있으면 대신 해줌
        /*withContext(Dispatchers.IO) {
            remoteDataSource.getCategories()
        }*/

        return remoteDataSource.getCategories()
    }


}