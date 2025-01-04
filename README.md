
# NFC Reader App

A simple Android application to read RFID tags using the NFC reader of an Android smartphone.

---

## 📱 Features

- Detect and read RFID tags via NFC.
- Display the ID of the detected tag on the screen.
- Check and display the current NFC status (enabled, disabled, or not supported).

---

## 🛠️ Requirements

- **Android Device**: NFC-enabled smartphone.
- **Minimum SDK**: Android 5.0 (API 21).
- **Compile SDK**: Android 14 (API 35).

---

## 🚀 Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/<your-username>/NFCReader.git
   cd NFCReader
   ```

2. **Open the project**:
   - Open the project in **Android Studio**.

3. **Sync Gradle**:
   - Sync the project with Gradle files.
   - Ensure you have the correct Android SDK installed (API 35).

4. **Run the app**:
   - Connect an NFC-enabled Android device to your computer.
   - Select the device in Android Studio and click "Run".

---

## 🧑‍💻 Usage

1. **Enable NFC** on your Android device:
   - Go to **Settings > Connections > NFC** and enable it.

2. **Open the app**:
   - The app will show the current NFC status:
     - **Enabled**: Ready to detect NFC tags.
     - **Disabled**: Prompts the user to enable NFC.
     - **Not supported**: Informs the user that the device does not support NFC.

3. **Scan an RFID tag**:
   - Place an RFID tag near the NFC sensor of the phone.
   - The app will display the tag ID if successfully detected.

---

## 📝 License

This project is licensed under the MIT License.

---

## 👤 Author

- **Fidel Brea**  
  - GitHub: [@fidelbrea](https://github.com/fidelbrea)  
  - Email: fidelbrea@gmail.com  

Just in case... feel free to contribute to this project by submitting issues or pull requests. 😊
