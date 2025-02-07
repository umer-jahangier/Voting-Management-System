# Voting Management System

## Overview

Semester Project for showing comprehensive knowledge and understanding of the concepts of OOP in JAVA. This is a Java-based application that facilitates electronic voting. It allows voters to securely cast their votes and enables administrators to manage elections efficiently. The system ensures data security, user authentication, and accurate vote tallying.

## Features

- **User Authentication**: Secure login for voters and administrators.
- **Voting System**: Allows eligible voters to cast their votes.
- **Admin Panel**: Manage voters, view election results, and oversee the voting process.
- **Result Display**: Shows election results in real-time.
- **Data Management**: Stores voter credentials and voting records securely.

## Technologies Used

- **Programming Language**: Java
- **GUI Framework**: JavaFX
- **IDE**: IntelliJ IDEA (Project files included)
- **File Handling**: Stores data using text files

## Project Structure

```
Voting Management System/
│── src/
│   ├── MainEntry.java  # Main application entry point
|   │── Resources/          # Image assets
│   ├── sample/
│   │   ├── FirstPage.java
│   │   ├── Main.java
│   │   ├── Person.java
│   │   ├── admin/
│   │   │   ├── AdminLoginController.java
│   │   │   ├── AdminPanel.java
│   │   ├── result/
│   │   │   ├── Result.java
│   │   ├── voter/
│   │   │   ├── VoterLogin.java
│   │   │   ├── VoterPanel.java
│── Credentials/        # Stores credential and voting data
│── Voting Management System.iml  # IntelliJ module file
```

## Setup & Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/Voting-Management-System.git
   ```
2. Open the project in **IntelliJ IDEA** or any Java IDE.
3. Make sure JavaFX is installed and configured in your IDE.
4. Edit Configurations of the MainEntry.java File.  (Open Main Entry.java File, GOTO Run tab in Option menu, Click Edit Configurations.
5. If Application exists Good, Otherwise add new for Application.\
   Name: MainEntry, Run On Local Machine, In Build and Run Select available JRE package, preferabbly JAVA 23.\
   Click Modify Options->Environment Variables->Add "--module-path "C:\Program Files\Java\javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml.\
   Replace "C:\Program Files\Java\javafx-sdk-23.0.2\lib" with your javafx lib path. Click Ok. Boom you have successfully setup JavaFx in your IDE.
6. Run `MainEntry.java` to launch the application.

## How to Use

### For Voters:

- Log in using your assigned credentials.
- Cast your vote.
- View the confirmation message after successful voting.

### For Administrators:

- Log in with admin credentials, Username: Master & Password: Oogway
- Manage voter lists and oversee the voting process.

## Future Enhancements

- Implement a database for better data management.
- Enhance security with encryption.
- Improve UI with a modern design.
- Add multi-election support.

## Contributors

- Muhammad Umer

## License

This project is licensed under the MIT License. Feel free to use and modify it as needed.
