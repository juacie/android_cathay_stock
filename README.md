Cathay Stock
===
![platform](https://img.shields.io/badge/platform-android-green)
![version](https://img.shields.io/badge/app-v1.0.0(1)-blue)
![date](https://img.shields.io/badge/date-2024.12.5-red)

## Contents

[TOC]

## Package Constructor

```
.
├── app
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src
│       ├── androidTest
│       │   └── java
│       │       └── com
│       │           └── juacie
│       │               └── cathay
│       │                   └── stock
│       │                       └── ExampleInstrumentedTest.kt
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── com
│       │   │       └── juacie
│       │   │           └── cathay
│       │   │               └── stock
│       │   │                   ├── app
│       │   │                   │   ├── base
│       │   │                   │   │   ├── BaseActivity.kt
│       │   │                   │   │   ├── BaseApplication.kt
│       │   │                   │   │   ├── BaseViewModel.kt
│       │   │                   │   │   └── IViewModel.kt
│       │   │                   │   ├── kit
│       │   │                   │   │   ├── function
│       │   │                   │   │   │   ├── FuncObj.kt
│       │   │                   │   │   │   └── LogObj.kt
│       │   │                   │   │   └── network
│       │   │                   │   │       ├── ApiService.kt
│       │   │                   │   │       ├── BaseResponse.kt
│       │   │                   │   │       ├── Client.kt
│       │   │                   │   │       ├── ErrorEntity.kt
│       │   │                   │   │       └── LoggingInterceptor.kt
│       │   │                   │   └── main
│       │   │                   │       ├── model
│       │   │                   │       │   ├── StockDay.kt
│       │   │                   │       │   ├── StockDayAverage.kt
│       │   │                   │       │   ├── StockInfo.kt
│       │   │                   │       │   └── StockRatio.kt
│       │   │                   │       ├── view
│       │   │                   │       │   ├── StockActivity.kt
│       │   │                   │       │   ├── StockListScreen.kt
│       │   │                   │       │   └── component
│       │   │                   │       │       ├── ScrollableStockList.kt
│       │   │                   │       │       ├── SearchAndMenuBar.kt
│       │   │                   │       │       └── StockCard.kt
│       │   │                   │       └── viewModel
│       │   │                   │           └── StockViewModel.kt
│       │   │                   └── ui
│       │   │                       └── theme
│       │   │                           ├── Color.kt
│       │   │                           ├── Theme.kt
│       │   │                           └── Type.kt
│       │   └── res
│       │       ├── drawable
│       │       │   ├── ic_launcher_background.xml
│       │       │   ├── ic_launcher_foreground.xml
│       │       │   └── ic_menu.xml
│       │       ├── font
│       │       │   ├── roboto_black.ttf
│       │       │   ├── roboto_black_italic.ttf
│       │       │   ├── roboto_bold.ttf
│       │       │   ├── roboto_bold_italic.ttf
│       │       │   ├── roboto_italic.ttf
│       │       │   ├── roboto_light.ttf
│       │       │   ├── roboto_light_italic.ttf
│       │       │   ├── roboto_medium.ttf
│       │       │   ├── roboto_medium_italic.ttf
│       │       │   ├── roboto_regular.ttf
│       │       │   ├── roboto_thin.ttf
│       │       │   └── roboto_thin_italic.ttf
│       │       ├── mipmap-anydpi
│       │       │   ├── ic_launcher.xml
│       │       │   └── ic_launcher_round.xml
│       │       ├── mipmap-hdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-mdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-xhdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-xxhdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── mipmap-xxxhdpi
│       │       │   ├── ic_launcher.webp
│       │       │   └── ic_launcher_round.webp
│       │       ├── values
│       │       │   ├── colors.xml
│       │       │   ├── strings.xml
│       │       │   └── themes.xml
│       │       └── xml
│       │           ├── backup_rules.xml
│       │           └── data_extraction_rules.xml
│       └── test
│           └── java
│               └── com
│                   └── juacie
│                       └── cathay
│                           └── stock
│                               └── ExampleUnitTest.kt
├── build.gradle.kts
├── gradle
│   ├── libs.versions.toml
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
└── settings.gradle.kts
```



## 題目

根據臺灣證券交易所服務API開發頁面
### 規範
1. Kotlin 開發
2. 程式架構請用MVP/MVVM
3. 會檢視Coding Style、程式品質、資料處理邏輯
4. 畫面加入您的UI/UX經驗，自由發揮
5. 將專案上傳至GitHub 並設為公開分享
6. 螢幕錄製基本操作畫面，上傳[Youtube影片](https://youtube.com/shorts/MXlJvG7mcxk?feature=share)，設為非公開分享
### 規範
1. 使用Compose、新的程式架構
2. 加入合理滑動動畫、畫面特效或展現Material Design掌握程度
3. 單元測試/UI測試
4. 支援螢幕轉向or夜晚模式