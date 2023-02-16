package com.rkukac.movieapp.data.network.adapter

import java.math.BigDecimal

class BigDecimalStringFormatter {

    fun parse(valueString: String): BigDecimal {
        return valueString.toBigDecimal()
    }

    fun format(value: BigDecimal): String {
        return value.toString()
    }
}