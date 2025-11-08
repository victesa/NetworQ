# Contributing to NetworQ

First off, thank you for considering contributing to NetworQ! 🚀

We are building a context-aware networking app to help people remember *who* they met and *where*. We welcome contributions from everyone, whether you are fixing a typo, fixing a bug, or adding a new feature.

## 🛠 Getting Started

### Prerequisites
* **Android Studio:** Latest stable version (Ladybug/Koala).
* **JDK:** Version 17.
* **Knowledge:** Basic understanding of Kotlin, Jetpack Compose, and MVVM architecture.

### Setting up the Project
1.  **Fork** the repository on GitHub.
2.  **Clone** your fork locally:
    ```bash
    git clone [https://github.com/YOUR_USERNAME/NetworQ.git](https://github.com/victesa/NetworQ.git)
    ```
3.  **Sync** the project with Gradle files in Android Studio.
4.  **Run** the app on an emulator or physical device.

---

## 🏗 Development Workflow

We follow a standard **Feature Branch** workflow.

1.  **Create a Branch:** Always work on a new branch for your changes.
    ```bash
    git checkout -b feature/my-new-feature
    # or
    git checkout -b fix/login-crash
    ```
2.  **Make Changes:** Write your code.
3.  **Test:** Ensure the app builds and runs without crashing.
4.  **Commit:** Use descriptive commit messages.
    ```bash
    git commit -m "feat: Add floating window overlay UI"
    ```
5.  **Push:** Push your branch to your fork.
    ```bash
    git push origin feature/my-new-feature
    ```
6.  **Pull Request:** Go to the original NetworQ repository and open a Pull Request (PR) from your branch.

---

## 📐 Coding Guidelines

To keep the codebase clean and modular, please adhere to the following:

### Architecture
* **MVVM:** We use Model-View-ViewModel. Logic should reside in the ViewModel, not the UI (Composable).
* **Modularization:** If you are adding a large feature, consider if it belongs in a new module in `:features`.
* **Dependency Injection:** We use **Koin**. Please declare new modules in the `di` package.

### Jetpack Compose Style
* Use `Modifier` as the first optional parameter in your Composable functions.
* Keep Composables small and reusable.
* Preview your Composables using `@Preview`.

### Database
* We use **Room**. All database interactions should go through a Repository pattern, never accessed directly by the ViewModel or UI.

---

## 🧪 Current Focus Areas

If you are looking for something to work on, check out the "Upcoming Roadmap" in our README, specifically:
1.  **Floating Window:** Handling the `SYSTEM_ALERT_WINDOW` permission and UI.
2.  **UI Polish:** improving transitions between the Home screen and Event Details.

---

## ⚖️ License

By contributing, you agree that your contributions will be licensed under the MIT License defined in the `LICENSE` file.