package com.example.pantrypal.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pantrypal.data.apihelper.ApiService
import com.example.pantrypal.data.response.AllMaterialResponse

class MaterialPagingSource(private val apiService: ApiService): PagingSource<Int, AllMaterialResponse.ListMaterial>() {

    private val PAGE_INDEX = 1

    override fun getRefreshKey(state: PagingState<Int, AllMaterialResponse.ListMaterial>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllMaterialResponse.ListMaterial> {
        return try {
            val position = params.key ?: PAGE_INDEX
            val param = params.loadSize
            val response = apiService.getAllMaterial(position, param).data.toList()

            LoadResult.Page(
                data = response,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = position.plus(1)
            )
        } catch (exception : Exception){
            return LoadResult.Error(exception)
        }
    }


}