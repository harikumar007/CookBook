package com.haruvi.cookbook.domain.entity

import com.google.gson.annotations.SerializedName


data class Categories (

    @SerializedName("idCategory"             ) var idCategory             : String? = null,
    @SerializedName("strCategory"            ) var strCategory            : String? = null,
    @SerializedName("strCategoryThumb"       ) var strCategoryThumb       : String? = null,
    @SerializedName("strCategoryDescription" ) var strCategoryDescription : String? = null

)

data class CategoriesResponse (
    @SerializedName("categories" ) var categories : ArrayList<Categories> = arrayListOf()
)