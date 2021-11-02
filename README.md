![Version badge](https://img.shields.io/badge/version-1.0.0-green.svg)
----
[한국어](/README-ko.md)

# version-comparator
Android version name comparator library.
Compare the input `v1` to `v2`, and return one of `0`, `-1`, or `1` according to the meaning below.
*  `0 (EQUALS)` : `v1` equals `v2`
* `-1 (LESS_THAN)` : `v1` is less than `v2`
* `1 (GREATER_THAN)` : `v1` is greater than `v2`

Based on `MAJOR.MINOR.PATCH`, compare in the order MAJOR -> MINOR -> PATCH.

## Setup
### Gradle
```Gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation 'com.essie-cho:version-comparator:1.1.0'
}
```

## Usage
### Basic
```kotiln
val result = VersionComparator.compareTo("v1", "v2")
when (result) {
    EQUALS:
        //...
    LESS_THAN:
        //...
    GREATER_THAN:
        //...
}
```

### Custom comparator
If you are using specific non-numeric strings such as "alpha", "snapshot", 
Define a `Comparator<String>` that returns the desired result and pass it when calling `VersionComparator.compareTo(..)`.

If the custom comparator is empty, VersionComparator use `String.compareTo(..)` except for digit only cases.

```kotlin
val comparator = Comparator<String> { o1, o2 ->
		//...
	}

val result = VersionComparator.compareTo("1.0.0.beta", "1.0.0.dev", comparator)
```


