### This is a simple project that uses 'View UI' and '[Jetpack Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)'.

And use [GitHub Rest API](https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#about-search)

---
</br>

## Some screen recording

<div align="left">

| Search Github User | Infinite Scroll | Like User |
| :---------------: | :---------------: | :---------------: |
| <img src="https://github.com/winter-love-dev/GithubApp/assets/26156815/38c553b6-78aa-4877-a575-ff8b96a792f5" align="center" width="250px"/> | <img src="https://github.com/winter-love-dev/GithubApp/assets/26156815/c94f6501-0989-4a44-9237-3d897c32cef1" align="center" width="250px"/> | <img src="https://github.com/winter-love-dev/GithubApp/assets/26156815/25d02f99-0268-4274-b612-0718313b398a" align="center" width="250px"/> |

</div>

## Tech Stack

<p align="left">
  <a href="https://kotlinlang.org"><img alt="Kotlin Version" src="https://img.shields.io/badge/Kotlin-1.8.0-blueviolet.svg?style=flat"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

All library info : [libs.versions.toml](gradle/libs.versions.toml)

- <b>AAC-ViewModel</b> Combined with the screen, it maintains screen state and handles UI state and screen rotation events.
- <b>Coroutine</b> Asynchronous Task. Used 'Flow' to make data flow and reflect it in the UI. 
- Jetpack
  - <b>Hilt</b> Dependency Injection
  - <b>Room</b>
    - Focused on implementing functionality by leveraging SQLite wrapped in an abstraction layer, and combined 'Flow' to implement UI interaction more easily.
  - <b>Pagning3</b> is Load large datasets efficiently
    - <b>PagingSource</b> is Load network data to make pagination
    - <b>RemoteMediator</b> - Apply Cache Process with Room
  - Network by <b>okhttp3 + retrofit2</b>
  
---
# Architecture

## [RemoteMediator](https://developer.android.com/topic/libraries/architecture/paging/v3-network-db) is use 'Paging3 + Room'. 
It helps you navigate apps smoothly even in unstable internet environments.
<img width="500" alt="remote_mediator_architrecture" src="https://github.com/winter-love-dev/GithubApp/assets/26156815/63e60a59-2003-47b2-b4b9-23980d866841">

</br>

## MVVM Design pattern with Dependency Injection, Reference by [App Arhitecture Guide](https://developer.android.com/jetpack/guide?hl=ko#mobile-app-ux)  
![mvvm_example](https://github.com/winter-love-dev/CatchBottle/assets/26156815/f61d9746-f375-4cfa-80ea-20a3cb0ceafb)

</br>

## Multi Module Architecture, Reference by [android/nowinandroid](https://github.com/android/nowinandroid)
![dep_graph_app](https://github.com/winter-love-dev/CatchBottle/assets/26156815/22cdd95a-29ee-4ea6-be8e-fe42ffeae5a2)

</br>

## Domain Layer, Reference by [Domain Layer Docs](https://developer.android.com/topic/architecture/domain-layer)

Work Focused
- Define feature specification
- Programming on the repository layer or feature layer, By domain layer specification.
- Programming in ‘UseCase’ units
- And code abstraction

![mad-arch-domain-overview](https://github.com/winter-love-dev/CatchBottle/assets/26156815/5997a25a-3d89-4314-8bb8-d7f5253c6faf)


# License
```
Copyright 2023 winter-love-dev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


