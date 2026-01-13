# MathAct

**MathAct** (also known as *MaTHech*) is an interactive educational Android application designed to help students master integer operations. By combining structured textual lessons with gamified learning experiences, MathAct makes abstract mathematical concepts tangible and engaging.

## 📖 About the Project

MathAct uses a hybrid mobile architecture. It leverages the robustness of native Android (Java) for navigation and system integration while utilizing the flexibility of web technologies (HTML5, CSS, JavaScript) to render rich, interactive educational content and games. This approach allows for dynamic animations and responsive learning tools that run completely offline.

## ✨ Key Features

*   **Structured Curriculum:** A comprehensive set of lessons (Pages 1-28) covering:
    *   Introduction to Integers
    *   Addition, Subtraction, Multiplication, and Division
    *   Real-world Word Problems
*   **Gamified Learning:** Reinforce concepts through integrated games:
    *   🐍 **Snake and Integers:** A math-twist on the classic Snake game.
    *   🫧 **Bubble Integers:** Solve problems to pop bubbles.
    *   🧩 **Interactive Quizzes:** Drag-and-drop, multiple choice, and matching exercises.
*   **Visual Tools:** Includes an **Interactive Number Line** to help students visualize positive and negative movements.
*   **Hybrid Navigation:** Seamlessly transitions between native Android menus and web-based content using a custom JavaScript interface.
*   **100% Offline:** All assets are bundled within the application, ensuring learning is accessible anywhere without an internet connection.
*   **Content Protection:** Utilizes AES encryption to secure specific game assets.

## 🛠️ Technical Stack

*   **Platform:** Android (SDK)
*   **Primary Language:** Java
*   **Content Engine:** Android WebView
*   **Web Technologies:** HTML5, CSS3, JavaScript
*   **Build System:** Gradle (Kotlin DSL)
*   **Security:** Custom AES Utils for asset decryption

## 📂 Project Structure

*   **`app/src/main/java/`**: Contains the native Android logic.
    *   `SplashActivity`, `StartActivity`, `TableOfContentsActivity`: Handle app flow and navigation.
    *   `Preface`, `PageX`: Wrappers for individual lessons.
    *   `utils/`: Helper classes for encryption and other utilities.
*   **`app/src/main/assets/`**: The heart of the educational content.
    *   `*.html`: Lesson pages and interactive tools.
    *   `encrypted/`: Protected game assets (`.enc`).
    *   `sounds/`: Audio effects for games and quizzes.

## 🚀 Getting Started

### Prerequisites
*   [Android Studio](https://developer.android.com/studio) (latest version recommended)
*   Java Development Kit (JDK) 8 or higher

### Installation
1.  **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/MathAct.git
    ```
2.  **Open in Android Studio:**
    *   Launch Android Studio.
    *   Select "Open an existing Android Studio project".
    *   Navigate to the cloned directory.
3.  **Sync Gradle:**
    *   Allow Android Studio to download necessary dependencies and sync the project.
4.  **Run the App:**
    *   Connect an Android device via USB or create an Emulator (AVD).
    *   Click the **Run** button (green play icon).

## 🤝 Contributing

Contributions are welcome! If you have ideas for new lessons, games, or optimizations:

1.  Fork the repository.
2.  Create your feature branch (`git checkout -b feature/AmazingLesson`).
3.  Commit your changes (`git commit -m 'Add AmazingLesson'`).
4.  Push to the branch (`git push origin feature/AmazingLesson`).
5.  Open a Pull Request.

## 📄 License

[Insert License Name, e.g., MIT, Apache 2.0]
