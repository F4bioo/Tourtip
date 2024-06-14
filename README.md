# Tourtip
Tourtip is a guided tour library for Android applications, built using Jetpack Compose. This library allows you to create customizable tooltips that guide users through various features of your app.

## Features
- **Customizable Tooltips:** Easily create tooltips with custom styles, positions, and animations.
- **Step-by-Step Guidance:** Provide users with step-by-step instructions to enhance their onboarding experience.
- **Compose Integration:** Built with Jetpack Compose for seamless integration into your Compose UI.

<!-- start dependency -->
## Installation

### Groovy
Add the following dependency to your `build.gradle` file on app module:

```gradle
dependencies {
    implementation 'com.fappslab.tourtip:tourtip:1.06.11'
}
```

### Kotlin
Add the following dependency to your `build.gradle.kts` file on app module:

```kotlin
dependencies {
    implementation("com.fappslab.tourtip:tourtip:1.06.11")
}
```
<!-- end dependency -->

## Usage

### Basic Setup
To start using Tourtip, initialize the library in your Compose setup.

```kotlin
import com.fappslab.tourtip.compose.TourtipLayout

// Initialize Tourtip in your Composable
TourtipLayout(
    onBack = { currentStep ->
        // Optional: Handle back action for event tracking $currentStep
    },
    onNext = { currentStep ->
        // Optional: Handle next action for event tracking $currentStep
    },
    onClose = { currentStep ->
        // Optional: Handle close action for event tracking $currentStep
    },
    onClickOut = { currentStep ->
        // Optional: Handle click outside action for event tracking $currentStep
    }
) { controller ->

    // Screen content
    // ...
    // ...
    Button(onClick = { controller.startTourtip() }) {
        Text("Start Tour")
    }
}
```

### Creating a BubbleDetail
Create a bubble to guide users through specific features of your app.

```kotlin
import com.fappslab.tourtip.compose.extension.bubbleAnchor
import com.fappslab.tourtip.model.BubbleDetail
import com.fappslab.tourtip.model.HighlightType

// Example how to attach a bubble to a Text composable
Text(
    modifier = Modifier
        .fillMaxWidth()
        .bubbleAnchor { // to attach the bubble to any composable component
            BubbleDetail(
                index = 0,
                title = { Text("Step 1") },
                message = { Text("This is the first step of the tour.") },
                highlightType = HighlightType.Rounded
            )
        },
    text = "Let's start the Tourtip Sample!"
)
```

### Customizing Tourtip
Customize the appearance and behavior of bubbles to fit your app's design.

```kotlin
import androidx.compose.ui.graphics.Color
import com.fappslab.tourtip.model.TourtipAnimType
import com.fappslab.tourtip.theme.defaults.TourtipDefaults

TourtipLayout(
    animType = TourtipAnimType.Bouncy,
    scrimColor = TourtipDefaults.scrimColor,
    backgroundColor = Color.Red
) { controller ->

    // Screen content
    // ...
    // ...
    Button(onClick = { controller.startTourtip() }) {
        Text("Start Tour")
    }
}

```

## Contributing
We welcome contributions! If you'd like to contribute to Tourtip, please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/my-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Changed: my feature'`).
5. Push to the branch (`git push origin feature/my-feature`).
6. Create a new Pull Request.

## License
Tourtip is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.
