package com.bignerdranch.photogallery

import android.graphics.Bitmap
import android.util.LruCache

/**
 * Created by hongy_000 on 2017/10/2.
 */
class UtilsCache private constructor(){

    companion object {
        private val maxMemory = Runtime.getRuntime().maxMemory() / 1024
        private val cacheSize= (maxMemory / 8).toInt()

        private val mMemoryCache: LruCache<String, Bitmap>? = object :LruCache<String, Bitmap>(cacheSize){
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return (value?.rowBytes)!! * (value.height) / 1024
            }
        }
        fun getBitmapFromMemoryCache( key: String):Bitmap? {
            return mMemoryCache?.get(key)
        }

        fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
            mMemoryCache?.put(key, bitmap)
        }
    }

}