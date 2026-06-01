# CodeAlpha - Premium Student Analytics Dashboard

A robust, enterprise-grade Java Graphical User Interface (GUI) application developed as part of the CodeAlpha Internship program. This software serves as an interactive academic performance dashboard allowing educators to input, manage, and track student marks with real-time statistics and persistent file database records.

## 🚀 Features
* **Premium Dashboard UI:** Modern dark-mode aesthetic with custom slate cards, clean typography, responsive grids, and vibrant mint accents.
* **Persistent Local Database:** Automatically saves records into a local text file (`student_records.txt`) and dynamically reloads existing database history upon application startup.
* **Smart Data Table:** Displays information inside a beautifully padded, read-only data grid tracking unique generated Database IDs.
* **Input Validation & Secure Logging:** Strictly controls grade boundaries between `0` and `100` and displays warning dialogs for format or blank entry errors.
* **Automated Status Evaluation:** Instantly reviews individual grades to evaluate and display a student's status (e.g., *Distinction*, *Passed*, or *Remedial Needed*).
* **Live Analytics Summary:** Dynamic metric cards pinned to the footer tracking total registered students, highest score, lowest score, and class average updates.

## 🛠️ Technologies Used
* **Language:** Java (JDK 8 or higher)
* **GUI Engine:** Java Swing Framework, Layout Managers (`BorderLayout`, `BoxLayout`, `GridLayout`), and Advanced Table Models (`JTable`).
* **Core Concepts:** Persistent File I/O Streams (`BufferedReader`, `PrintWriter`), Core Collections Architecture (`ArrayList`), Custom UI Painters (`UIManager`), and Look & Feel Cross-Platform Standardization.

## 🖥️ User Interface Overview
The application automatically launches in **Maximized Full-Screen Mode** (`JFrame.MAXIMIZED_BOTH`) to scale and adapt beautifully to any desktop monitor workspace. 

### Core Sections:
1. **Registration Portal (Left panel):** Padded form capturing full student names and numeric grades securely.
2. **Saved Student Database (Center table):** Grid alignment displaying generated database IDs, tracked student profiles, numeric grades, and evaluated academic outcomes.
3. **Summary Analytics Report (Bottom layout):** High-visibility dashboard counters pulling live calculations for quick administrative reference.
