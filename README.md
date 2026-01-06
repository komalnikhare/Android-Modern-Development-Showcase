# Android Modern Development Showcase

This repository is a showcase project demonstrating modern Android development practices. It serves as a practical guide and learning resource for developers looking to explore key components of the Android Jetpack library, with a special focus on `Jetpack Compose` for UI and `Kotlin Flows` for asynchronous programming.
The application is structured into several modules, each designed to demonstrate a specific concept, from basic UI components to advanced, lifecycle-aware data streams.

## 🚀 What You Can Explore in This Project

This project is a hands-on lab for the following concepts:

* ### UI with Jetpack Compose:
    * Building dynamic, responsive UIs using composable functions.
    * Creating reusable UI components (ListItem, ElevatedCard).
    * Handling user interactions and state management in Compose.
    * Implementing list-based UIs with LazyVerticalGrid.
    * Navigating between screens and passing data to new activities.

* ### Asynchronous Programming with Kotlin Flows:
    * Use Case 1: `Lifecycle-Aware Data Streams`: Learn how to create a data stream that polls an API and automatically starts/stops based on the UI lifecycle. This is achieved using the stateIn operator with SharingStarted.WhileSubscribed, a crucial pattern for saving battery and network resources.
    * Use Case 2: `Advanced Flow Operators`: Dive deep into the power of functional programming with a complex chain of Flow operators. This example demonstrates how to filter, map, and transform a data stream to meet specific business requirements, all while running on a background thread using flowOn.
    * Use Case 3: `Error Handling & Retry Logic`: See how to build resilient data sources. This use case implements a robust retry mechanism (retryWhen) with exponential backoff to handle temporary network failures gracefully.

* ### Dependency Injection with Koin:
    * Understand how to set up Koin modules to provide dependencies like ViewModels, Repositories, and DataSources.
    * Learn how to use named qualifiers to inject different implementations of the same interface (FlowMockApi) into different parts of the application.

* ### Clean Architecture Principles:
    * The project follows a simplified clean architecture pattern, separating concerns into UI (Compose), ViewModel, Repository, and DataSource layers.

* ### Mocking and Testing:
    * The project uses a mocked API (FlowMockApi) to simulate real-world data fetching, allowing the focus to remain on the client-side logic without needing a live backend.

## 📂 Project Structure
The app is organized by feature, with a clear separation of concerns.
* `app/src/main/java/com/example/composeapplication/`
    * `MainActivity.kt`: The main entry point of the application, displaying a grid of use cases.
    * `di/`: Contains the Koin dependency injection modules (flowModule, flowModule2, flowModule3).
    * `compose/`: Holds simple Jetpack Compose examples.
    * `flow/`: The core of the project, containing the Flow use cases.
      * `usecase1/`: Demonstrates lifecycle-aware flows with stateIn.
      * `usecase2/`: Showcases advanced flow operators for data transformation.
      * `usecase3/`: Implements error handling with retryWhen.
      * `mock/`: Contains the mock API implementations (MockApiBehaviour.kt).
      * `datasource/`: The StockPriceDataSource responsible for "fetching" data.
      * `repository/`: The StockRepository that connects the ViewModel to the data source.

## 🛠️ How to Run and Explore

1. Clone the repository:
  `git clone https://github.com/komalnikhare/Android-Modern-Development-Showcase.git`
  
2. **Open in Android Studio:** Open the project in Android Studio (Jellyfish or newer recommended).
3. **Build and Run:** Let Gradle sync, then build and run the app on an emulator or a physical device.
4. **Explore the Use Cases:**
   - Click on **"Flow UseCase 1"** to see a list of stocks that updates in real-time. Put the app in the background and notice in Logcat that the API polling stops after 5 seconds. Bring it back to the foreground to see it resume.
   - Click on **"Flow UseCase 2"** to see the result of a complex data transformation pipeline. Check the code in `FlowUseCase2ViewModel.kt` to see the chain of operators in action.
   - Click on **"Flow UseCase 3"** and check Logcat. The mock API is designed to fail occasionally. You will see log messages indicating that the flow is retrying with an increasing delay.

This project is designed to be a living document. Feel free to experiment with the code, modify the flow operators, and see how the application behavior changes. Happy coding