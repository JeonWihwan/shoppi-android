package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.ServiceLocator
import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.network.ApiClient
import com.shoppi.app.repository.*
import com.shoppi.app.repository.cart.CartItemLocalDataSource
import com.shoppi.app.repository.cart.CartRepository
import com.shoppi.app.repository.productdetail.ProductDetailRemoteDataSource
import com.shoppi.app.repository.productdetail.ProductDetailRepository
import com.shoppi.app.ui.cart.CartViewModel
import com.shoppi.app.ui.category.CategoryViewModel
import com.shoppi.app.ui.categorydetail.CategoryDetailViewModel
import com.shoppi.app.ui.home.HomeViewModel
import com.shoppi.app.ui.productdetail.ProductDetailViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ServiceLocator.provideApiClient()))
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                val repository = CategoryDetailRepository(CategoryDetailRemoteDataSource(ServiceLocator.provideApiClient()))
                CategoryDetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) -> {
                // ServiceLocator 추가
                val repository = ProductDetailRepository(ProductDetailRemoteDataSource(
                    ServiceLocator.provideApiClient()))
                ProductDetailViewModel(repository, ServiceLocator.provideCartRepository(context)) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(ServiceLocator.provideCartRepository(context)) as T
            }
            else -> {
                throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}