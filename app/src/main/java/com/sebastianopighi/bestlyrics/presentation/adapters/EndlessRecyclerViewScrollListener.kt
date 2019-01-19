package com.sebastianopighi.bestlyrics.presentation.adapters

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewScrollListener(private val layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 4
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true
    private val startingPageIndex = 0

    init {
        if(layoutManager is GridLayoutManager)
            visibleThreshold *= layoutManager.spanCount
        else if (layoutManager is StaggeredGridLayoutManager)
            visibleThreshold *= layoutManager.spanCount
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager.itemCount

        if (layoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null)
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
        } else if (layoutManager is GridLayoutManager) {
            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, view)
            loading = true
        }
    }

    fun resetState() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

}