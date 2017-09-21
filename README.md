# Astro Guide #

Demonstration of Requirements understanding, proposing a solution and implementation by using latest tools and techniques. The code is based on Google Android SDK.
Developed as an assignment for Astro.

### What is this repository for? ###

* This app is built as assignment. 
* Adds different libs to enhance the application quality and performance
* Version 1.0.0


### Application Flow ###

_to be provided later_


## How do I get set up? ##

###How to set up ###
To setup you need to clone this repo, from `master` or `develop` branch or some latest `TAG`
### Configuration###
Please sync and resolve dependencies

## Requirements

- JDK 1.8
- [Android SDK](http://developer.android.com/sdk/index.html).
- Android N [(API 24) ](http://developer.android.com/tools/revisions/platforms.html).
- Latest Android SDK Tools and build tools.


## Libraries and tools included:

- Support libraries
- RecyclerViews and CardViews
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](http://google.github.io/dagger/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)
- Functional tests with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
- [Mockito](http://mockito.org/)
- [Checkstyle](http://checkstyle.sourceforge.net/), [PMD](https://pmd.github.io/) and [Findbugs](http://findbugs.sourceforge.net/) for code analysis


## Storage ##
### Local Storage ###

- [`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html) are used for session storage

### Remote Storage ###
#### SSO Auth ####
- Google Authentication API
#### Settings Persistance ####
- Using `AWS-EC2` instance with `Flask` app deployed to persist settings agaings a logged-in user. 


## External Tools:

- API-response DTOs are created by using [JSON2Schema](http://www.jsonschema2pojo.org/)


## Code Quality

Used `Android Lint` and `SonarLint`

## Distribution

The project can be distributed using [Google Play Store](https://github.com/Triple-T/gradle-play-publisher).


### Contribution guidelines ###

- forks are always appreciated

## Screenshots ##

_to be provided later_