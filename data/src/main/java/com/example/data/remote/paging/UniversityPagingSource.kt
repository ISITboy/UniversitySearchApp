package com.example.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.Constants.DEFAULT_PAGE_SIZE
import com.example.data.remote.UniversityApi
import com.example.domain.model.UniversityResponseItem
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.lang.Exception

class UniversityPagingSource(
    private val universityApi: UniversityApi,
    private val searchedName: String,
    private val searchedCountry: String
) : PagingSource<Int, UniversityResponseItem>() {


    override fun getRefreshKey(state: PagingState<Int, UniversityResponseItem>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var currentOffset = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UniversityResponseItem> {
        if (searchedName.isEmpty() && searchedCountry.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }


        return try {
            val pageIndex = params.key ?: 1
            val loadSize = params.loadSize

            val uniResponse = universityApi.getUniversitiesByNameAndCountry(
                searchedName, searchedCountry, loadSize ,currentOffset
            )

            currentOffset = loadSize * pageIndex
            LoadResult.Page(
                data = uniResponse,
                nextKey = getNextKey(
                    dataSize = uniResponse.size,
                    loadSize = loadSize,
                    pageIndex = pageIndex
                ),
                prevKey = getPrevKey(pageIndex = pageIndex)
            )

        }
        catch (e: HttpException) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
        catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    private fun getNextKey(dataSize: Int, loadSize: Int, pageIndex: Int) =
        if (dataSize == loadSize) pageIndex + (loadSize / DEFAULT_PAGE_SIZE) else null
    private fun getPrevKey(pageIndex:Int) = if (pageIndex == 1) null else pageIndex - 1

}