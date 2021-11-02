![Version badge](https://img.shields.io/badge/version-1.0.0-green.svg)
# version-comparator
간단한 Android 버전 비교 라이브러리 입니다.
입력받은 버전1(`v1`)과 버전2(`v2`)를 비교하여 아래 의미에 따라  `0`, `-1`,  `1` 중 하나를 반환합니다.
*  `0 (EQUALS)` : `v1`과 `v2`가 같을 경우
* `-1 (LESS_THAN)` : `v1`이 `v2`보다 낮은 버전일 경우
* `1 (GREATER_THAN)` : `v1`이 `v2`보다 높은 버전일 경우

`MAJOR.MINOR.PATCH`를 기준으로 하여 MAJOR -> MINOR -> PATCH 순으로 비교합니다. 

## 프로젝트에 추가하기
### Gradle
```Gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation 'com.essie-cho:version-comparator:1.1.0'
}
```

## 사용하기
### 기본 사용법
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

### Custom comparator 사용법
원하는 결과를 반환하는 `Comparator<String>`를 정의하여 `VersionComparator.compareTo(..)` 호출 시 전달합니다.
Custom comparator를 전달하지 않은 경우, 숫자만 포함된 경우를 제외하고`String.compareTo(..)`를 사용합니다.
```kotlin
val comparator = Comparator<String> { o1, o2 ->
		//...
	}

val result = VersionComparator.compareTo("1.0.0.beta", "1.0.0.dev", comparator)
```


