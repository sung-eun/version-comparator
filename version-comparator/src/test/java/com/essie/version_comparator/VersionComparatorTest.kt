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
    }

    @Test
    fun greater() {
        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.1", "1.0.0"))
        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.1.1", "1.0.0"))
        assertEquals(GREATER_THAN, VersionComparator.compareTo("2.0.1", "1.0.0"))
        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.0_2", "1.0.0"))
    }

    @Test
    fun equals_CustomComparator() {
        val comparator = createCustomComparator()
        assertEquals(EQUALS, VersionComparator.compareTo("1.0.0.alpha", "1.0.0.alpha", comparator))
        assertEquals(EQUALS, VersionComparator.compareTo("1.0.0.alpha_1", "1.0.0.alpha_1", comparator))
    }

    @Test
    fun lessThan_CustomComparator() {
        val comparator = createCustomComparator()
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0.alpha", "1.0.0.beta", comparator))
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0.alpha_1", "1.0.0.beta_1", comparator))
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0.alpha_1", "1.0.0.alpha_2", comparator))
        assertEquals(LESS_THAN, VersionComparator.compareTo("1.0.0.dev_1", "1.0.0.release_2", comparator))
    }

    @Test
    fun greater_CustomComparator() {
        val comparator = createCustomComparator()
        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.0.beta", "1.0.0.dev", comparator))
        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.0.release", "1.0.0.beta", comparator))
        assertEquals(GREATER_THAN, VersionComparator.compareTo("1.0.0.beta_4", "1.0.0.beta_1", comparator))
    }

    private fun createCustomComparator(): Comparator<String> {
        return Comparator { o1, o2 ->
            val ordinal1 = getOrdinalOfVersion(o1)
            val ordinal2 = getOrdinalOfVersion(o2)

            if (ordinal1 == ordinal2) {
                return@Comparator o1.compareTo(o2)
            }
            return@Comparator ordinal1.compareTo(ordinal2)
        }
    }

    private fun getOrdinalOfVersion(s: String): Int {
        val lowerCase = s.toLowerCase()
        return when {
            lowerCase.startsWith("dev") -> TestBuildVariant.DEV.ordinal
            lowerCase.startsWith("alpha") -> TestBuildVariant.ALPHA.ordinal
            lowerCase.startsWith("beta") -> TestBuildVariant.BETA.ordinal
            lowerCase.startsWith("release") -> return TestBuildVariant.RELEASE.ordinal
            else -> -1
        }
    }

    private enum class TestBuildVariant {
        DEV, ALPHA, BETA, RELEASE
    }
}