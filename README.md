# Steps

* create a visual display
* create the forms
* create a display for pros and cons
* create a 1-10 weighting for each pro and con
* display the overall equation and suggest action
* provide a reset button at any time


 
# Decide
Decide is an Android application built in Kotlin that helps users organize their thoughts when 
facing difficult decisions.

Create a decision, list its pros and cons, and revisit your thoughts whenever you need to. 
Decide provides a simple, distraction-free way to evaluate the advantages and disadvantages 
of your choices.

## Instructions for Build and Use

Steps to build and/or run the software:

1. Open the project in Android Studio and let Gradle sync.
2. Build with `./gradlew assembleDebug`.
3. Run on an API 24+ emulator or device using **Run** or `./gradlew installDebug`.
4. Test with `./gradlew test`.

Instructions for using the software:

1. Enter a decision title and press **Done**.
2. Add pros and cons, assigning each a significance score from 1–10.
3. Review the totals and recommendation. Use **Reset** to start over or the back arrow to return.

## Development Environment

To recreate the development environment, you need the following software and/or libraries with the specified versions:

* Android Studio with Android SDK 35
* Android Gradle Plugin 8.7.3 and Kotlin 2.0.21
* Jetpack Compose BOM 2024.12.01 with Java 11 compatibility

## Useful Websites to Learn More

I found these websites useful in developing this software:

* [YouTube](https://youtu.be/Q4uud_0hppw?si=PtVWtwsBr8OpbqCi)
* [YouTube](https://youtu.be/GhuiNcOEv1A?si=u7VogboFC2XptbW_)
* [YouTube](https://youtu.be/XLt_moCoauw?si=bOkWxM9aPYfEdF6J)

## Future Work

The following items I plan to fix, improve, and/or add to this project in the future:

* [ ] Save decisions permanently across app restarts
* [x] Allow users to edit and delete saved decisions
* [ ] Allow users to edit and delete individual pros and cons
* [ ] Add charts or graphs to visualize pros and cons
* [x] Add dark mode and accessibility improvements
* [ ] Add decision sharing and export options
* [ ] Add more automated tests
* [ ] Add undo for recently added items
* [ ] Display the score equation and difference
* [ ] Add notes to each decision
* [ ] Add categories or tags for decisions
* [ ] Support neutral or customizable recommendation thresholds
* [ ] Add search for saved decisions
* [ ] Add confirmation before resetting a decision
* [ ] Add decision history and timestamps
* [ ] Add customizable scoring weights
* [ ] Add onboarding and help text
