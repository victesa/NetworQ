package com.victorkirui.resources

enum class CardOptions(val resId: Int){
    OPTION_1(com.victorkirui.resources.R.drawable.red_phoenix_card),
    OPTION_2(com.victorkirui.resources.R.drawable.blue_construction_card),
    OPTION_3(com.victorkirui.resources.R.drawable.blue_phoenix_card),
    OPTION_4(com.victorkirui.resources.R.drawable.green_leaf_card),
    OPTION_5(com.victorkirui.resources.R.drawable.plain_green_card);

    companion object{
        fun getAll(): List<Int> = CardOptions.entries.map { it.resId }
    }

}