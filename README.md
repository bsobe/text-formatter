<img src="https://raw.githubusercontent.com/bsobe/text-formatter/master/images/text-formatter-example.png" width="240"/>

[![](https://jitpack.io/v/bsobe/text-formatter.svg)](https://jitpack.io/#bsobe/text-formatter) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Text Formatter
Text Formatter is easy way of the format text.

## Motivation
* Improve UX & UI performance.
* Escape removing spaces, parsing formatted text etc..

## How it works?
**TextFormatter** use a CharacterSpan which can be draw any character without string manipulation.

# Usage
```kotlin
        val textViewCreditCardNumber = findViewById<TextView>(R.id.textViewCreditCardNumber)
        TextFormatter.Builder()
            .setPattern("#### #### #### ####")
            .attach(textViewCreditCardNumber)

        val editTextPhoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber)
        TextFormatter.Builder()
            .customizeIndex(index = 1, customizedCharacter = " (")
            .customizeIndex(index = 4, customizedCharacter = ") ")
            .addSpaceAt(index = 7)
            .addSpaceAt(index = 9)
            .attach(editTextPhoneNumber)
```

# Installation
 - To implement **Text Formatter** to your Android project via Gradle, you need to add JitPack repository to your root build.gradle.
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
 - After adding JitPack repository, you can add **Text Formatter** dependency to your app level build.gradle.
```gradle
dependencies {
    implementation "com.github.bsobe:text-formatter:$last-version"
}
```

License
--------
    Copyright 2019 bsobe / Barış Söbe

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
