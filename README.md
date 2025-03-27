# Hash Table Visualization and Benchmarking – Java

This project is a comprehensive demonstration of various hash table implementations in Java, focusing on open addressing techniques such as **linear probing**, **quadratic probing**, and **double hashing**. It includes both backend logic and a JavaFX-based GUI for interactive exploration and testing.

---

## 🔧 Features

- 🧮 **Custom HashMap Implementations**
  - `HashMapOa`: Supports configurable open addressing strategies.
  - `HashMap`: Classic hash table with chaining.
- ⚙️ **Open Addressing Techniques**
  - Linear Probing
  - Quadratic Probing
  - Double Hashing
- 📊 **GUI-based Interface (JavaFX)**
  - Perform operations like insert, delete, find.
  - Visual feedback of the hash table structure.
  - Dynamic interaction through menu controls.
- 🚗 **Car Object Management**
  - Random car data generation.
  - Insert and manage car data into hash tables.
- 🧪 **Benchmarking Utilities**
  - Test hash table performance with varying load factors and sizes.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your_username/hash-tables-visualization.git
cd hash-tables-visualization
```

### 2. Build and Run

#### Option A: Run via Terminal (Main Method)

```bash
javac -d bin src/**/*.java
java -cp bin edu.ktu.ds.lab3.demo.DemoExecution
```

#### Option B: Run GUI (JavaFX)

Make sure JavaFX is installed and properly linked. Run the main GUI window:

```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp bin edu.ktu.ds.lab3.gui.MainWindow
```

---

## 📈 Sample Output

Visual feedback and benchmark logs are printed in the terminal and GUI window depending on the selected actions.

---

## 🧠 Educational Use

This project was developed for educational purposes, particularly in data structure coursework to demonstrate:
- Hash table behavior under different configurations
- Performance implications of collision handling
- GUI-based data structure visualization
