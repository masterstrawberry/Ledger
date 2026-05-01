# Transaction Tracker Application

This is a command line interface for managing and tracking personal financial transactions.
---
# Author
Joffre villacis

## Features

* **Deposits**: Record desposits with a date, timestamp, description, vendor name, and amount.
* **Payments**: Log debits/expenses.
* **Persistent Storage**: All transactions are saved to and loaded from a `transactions.csv` file separated by a pipe-delimiter (`|`).
* **All Entries**: View every transaction.
* **Deposits Only**: Filter the ledger to show only positive transactions (Deposits).
* **Payments Only**: Filter the ledger to show only negative transactions (Payments).

* **Timeframe Filters**: Show transactions for Month To Date, Previous Month, Year To Date, or the Previous Year.
* **Vendor Search**: Quickly find all transactions associated with a specific vendor.
* **Custom Search**: Perform custom filtering by providing a date range, description, vendor, or specific amount.

---

## Technical Details:

### Class Structure
* **`Main.java`**: Handles the main application loop and I/O operations for saving and initializing data.
* **`Ledger.java`**: Manages the ledger menu for viewing different categories of transaction history.
* **`Report.java`**: Contains the logic for filtering transactions through to print out specific transaction records.
* **`Transaction.java`**: The data model representing an individual transaction entry with fields for date, time, description, vendor, and amount.

### File Format
All the transactions are stored in `transactions.csv` using the following structure:
`YYYY-MM-DD|HH:MM:SS|Description|Vendor|Amount`

---

## How to Run

2.  **Clone Repo**: Clone this repository. 
3.  **Data File**: Ensure a `transactions.csv` file exists in the root directory.
4.  **Execution**: Run the `Main.java` class to start the command-line interface.
