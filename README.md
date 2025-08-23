# CLI Expense Tracker

## Overview

**CLI Expense Tracker** is a lightweight, user-friendly command-line application designed to help you manage your personal finances with ease. Effortlessly add, view, and organize your expenses—all from your terminal.

---

## ✨ Features

- **Quick Expense Entry:** Add new expenses with amount, category, and date in seconds.
- **Expense Overview:** Instantly view a list of all your recorded expenses.
- **Spendbook Management:** Save and load your expense records for future reference.
- **Intuitive CLI:** Simple, clear, and interactive command-line interface.

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/cli-expense-tracker.git
   ```
2. **Build the application:**
   ```bash
   cd cli-expense-tracker
   mvn compile
   ```
3. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="org.expensetracker.Main" -Dexec.args="/path/to/your/spendbooks-directory"
   ```
   Replace `/path/to/your/spendbooks-directory` with the directory where you want your spendbooks to be stored.

---

