package com.essie.version_comparator

import androidx.core.text.isDigitsOnly

object VersionComparator {
    const val LESS_THAN = -1
    const val EQUALS = 0
    const val GREATER_THAN = 1

    fun compareTo(v1: String, v2: String, comparator: Comparator<String>? = null): Int {
        if (v1 == v2) {
            return EQUALS
        }
        if (v1.isEmpty()) {
            return LESS_THAN
        }
        if (v2.isEmpty()) {
            return GREATER_THAN
        }

        val splits1 = v1.split(".")
        val splits2 = v2.split(".")
        val indexLimit = splits1.size.coerceAtMost(splits2.size) - 1

        for (i in 0..indexLimit) {
            val s1 = splits1[i]
            val s2 = splits2[i]

            if (s1.isDigitsOnly() && s2.isDigitsOnly()) {
                val compareTo = s1.toInt().compareTo(s2.toInt())
                if (compareTo != EQUALS) {
                    return if (compareTo < 0) LESS_THAN else GREATER_THAN
                }
            } else {
                val compareTo = comparator?.compare(s1, s2) ?: s1.compareTo(s2)
                if (compareTo != EQUALS) {
                    return if (compareTo < 0) LESS_THAN else GREATER_THAN
                }
            }
        }

        if (splits1.size > splits2.size) {
            return GREATER_THAN
        }
        return LESS_THAN
    }
}
