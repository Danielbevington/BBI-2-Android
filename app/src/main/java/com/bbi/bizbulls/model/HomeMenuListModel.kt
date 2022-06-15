package com.bbi.bizbulls.model

import androidx.annotation.DrawableRes
import com.bbi.bizbulls.utils.Globals

class HomeMenuListModel {
    var title: String? = null

    @DrawableRes
    var icon = 0
    var itemType = Globals.Item_Type

    constructor(title: String?, icon: Int) {
        this.title = title
        this.icon = icon
    }

    constructor() {}
}