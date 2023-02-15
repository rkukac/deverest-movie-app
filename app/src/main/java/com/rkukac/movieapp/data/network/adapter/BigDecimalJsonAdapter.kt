package com.rkukac.movieapp.data.network.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException
import java.math.BigDecimal

class BigDecimalJsonAdapter : JsonAdapter<BigDecimal?>() {

    private val stringFormatter = BigDecimalStringFormatter()

    @Synchronized
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): BigDecimal? {
        return if (reader.peek() == JsonReader.Token.NULL) {
            reader.nextNull<BigDecimal>()
        } else {
            stringFormatter.parse(reader.nextString())
        }
    }

    @Synchronized
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: BigDecimal?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.value(stringFormatter.format(value))
        }
    }
}