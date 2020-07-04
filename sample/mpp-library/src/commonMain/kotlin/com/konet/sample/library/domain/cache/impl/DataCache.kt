package com.konet.sample.library.domain.cache.impl

import com.konet.sample.library.domain.cache.IDataCache

class DataCache: IDataCache {

    override var authToken: String? = null
}