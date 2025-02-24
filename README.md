# Account Fast Track

A convenient Android keyboard application that helps you quickly fill in login credentials while signing into websites or applications. The app allows you to store your usernames and passwords securely and access them through a custom keyboard interface for fast data entry.

<img src="https://github.com/user-attachments/assets/3c9e6d1a-b213-4ef3-9829-667a7d4c520a" width="400">

## Features

<img src="https://github.com/user-attachments/assets/64f62c88-2dca-422d-98d7-f1b01f500c13" width="400">

- Custom keyboard for quick username and password entry
- Secure storage of login credentials
- Fast switching between stored accounts
- Seamless integration with any app that requires login
- Modern Material Design UI

## Technical Requirements

- Android device running Android 5.0 (SDK 21) or higher
- Permission to use custom keyboard

## Setup

1. Clone the repository:
```bash
git clone https://github.com/ammarptn/AccountFastTrack.git
```

2. Open the project in Android Studio

3. Build and install the application

4. Enable the keyboard in your Android settings:
   - Go to Settings > System > Languages & input > Virtual keyboard
   - Add "Account Fast Track" keyboard
   - Enable the keyboard

## How It Works

1. Add Your Credentials:
   - Securely store your usernames and passwords
   - Organize credentials by website or application

2. Use the Keyboard:
   - When logging in anywhere, switch to the Account Fast Track keyboard
   - Select the desired stored credentials
   - The keyboard will automatically fill in your login information

## Security

- All credentials are encrypted and stored securely on your device
- No internet connection required
- No sensitive data is transmitted externally
- Secure keyboard implementation

## Project Structure

- `/app` - Main application module
- `/app/src/main/java/com/ammarptn/accountfasttrack/` - Source code
  - `/presentation` - UI components and keyboard implementation
  - `/storage` - Secure credential storage implementation

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Privacy Policy

This keyboard does not collect or transmit any personal data. All login credentials are stored locally on your device in an encrypted format.
