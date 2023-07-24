package com.example.tipcalculator.util

fun formatDouble(value: Double): String {
    return "%.2f".format(value)
}

fun formatSliderPosition(position: Float): String{
    return ((position * 100).toInt()).toString()
}

fun calculateTip(tipPercentage: Int, totalBill:Double): Double {
    if(totalBill > 0 && totalBill.toString().isNotEmpty())
        return (totalBill/100)*tipPercentage
    else
        return  0.00
}

fun calculatePerPersonBill(totalBill: Double, totalSplit: Int):Double{

    if(totalBill>0)
        return totalBill/totalSplit

    else
        return 0.0
}