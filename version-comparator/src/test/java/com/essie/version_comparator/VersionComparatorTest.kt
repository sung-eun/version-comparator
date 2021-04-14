package com.essie.version_comparator

import com.essie.version_comparator.VersionComparator.EQUALS
import com.essie.version_comparator.VersionComparator.GREATER_THAN
import com.essie.version_comparator.VersionComparator.LESS_THAN
import org.junit.Assert.assertEquals
import org.junit.Test

class VersionComparatorTest {
    @Test
    fun equals() {
        assertEquals(EQUALS, VersionComparator.compareTo("1.0.0", "1.0.0"))
        assertEquals(EQUALS, VersionComparator.compareTo("1.0.0.alpha", "1.0.0.alpha"))
        assertEquals(EQUALS, VersionComparator.compareTo("1.0.0_1", "1.0.0_1"))
    }

    @Test
    fun lessThan() {
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0", "1.0.1"))
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0", "1.0.1.1"))
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0", "2.0.1"))
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0", "1.0.0_2"))
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0.alpha", "1.0.0.beta"))
    }

    @Test
    fun greater() {
//        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.1", "1.0.0"))
//        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.1.1", "1.0.0"))
//        assertEquals(GREATER_THAN, VersionComparator.compareTo("2.0.1", "1.0.0"))
//        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.0_2", "1.0.0"))
        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.0.beta", "1.0.0.alpha"))
    }
}