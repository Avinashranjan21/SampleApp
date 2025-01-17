# Payment App

This project is a sample payment application built using **Jetpack Compose**, **MVVM architecture**, and **Hilt** for dependency injection. The app provides the following features:

## Features

1. **Home Screen**:
   - Displays the current balance.
   - Toggle option to show/hide the balance.
   - Buttons for "Add Money" and "Make Payment".
   - Displays the last 5 transactions with options to view the full transaction history.

2. **Add Money**:
   - Allows users to add money to their account.
   - Displays a success animation on successful addition of funds.

3. **Make Payment**:
   - Allows users to subtract money from their account.
   - Displays an error if the balance is insufficient.
   - Shows a success animation for successful transactions.

4. **Transaction History**:
   - Displays a list of all transactions with dates, amounts, and types (Add or Subtract).
   - Additions are shown in **green**, and subtractions in **red**.
   - Option to filter the transactions by type (Add or Subtract).

## Technologies Used

- **Jetpack Compose**: For building declarative UI.
- **Hilt**: For dependency injection.
- **MVVM Architecture**: For clean separation of concerns.
- **Kotlin Coroutines & StateFlow**: For managing asynchronous tasks and state updates.

## Project Structure

```
├── home
│   ├── HomeScreen.kt
│   ├── AddMoneyScreen.kt
│   ├── MakePaymentScreen.kt
│   ├── PaymentViewModel.kt
├── transaction
│   ├── TransactionHistoryScreen.kt
│   ├── Transaction.kt
│   ├── TransactionType.kt
│   ├── PaymentRepository.kt
├── repository
│   ├── PaymentRepository.kt
├── navigation
│   ├── AppNavigation.kt
```

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd payment-app
   ```

2. Open the project in **Android Studio**.

3. Sync Gradle to install dependencies.

4. Run the app on an emulator or a physical device.

## Screenshots

### Home Screen
- Displays the current balance.
- Shows recent transactions.

### Add Money
- Simple UI for adding funds with animations.

### Make Payment
- Error handling for insufficient balance.
- Smooth UI and animations.

### Transaction History
- Detailed list of all transactions with filters.

## Dependencies

- **Compose Navigation**: For navigation between screens.
- **Material3**: For modern UI components.
- **Hilt**: For dependency injection.

## Note

Use any non empty username and password to login as empty filed is not allowed.
