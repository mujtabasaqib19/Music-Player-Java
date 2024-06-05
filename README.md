# Java Music Player Project

## Description

This project is a music player application implemented in Java. It leverages data structures such as linked lists, binary search trees, and AVL trees to efficiently manage and organize song playlists, ensuring optimal performance and user experience.

## Features

- **Song Management:** Add, remove, and display songs with details including name, artist, album, duration, release date, and date added.
- **Playlist Management:** Create, update, view, and play playlists.
- **Queue Implementation:** Songs are enqueued and dequeued to/from playlists using a linked list-based queue.
- **AVL Tree Implementation:** Songs are stored and managed using an AVL tree to ensure balanced and efficient song searching and sorting.
- **Song Search:** Search for songs in the AVL tree by name.
- **Playlist Playback:** Play playlists with options to shuffle, loop, and play next songs.
- **Undo Feature:** Undo the removal of songs from playlists using a stack.

## Project Structure

The project consists of several classes:

- `Songs`: Represents a song with attributes like name, artist, album, duration, release date, and date added.
- `Node`: Represents a node in the playlist queue.
- `PlaylistQueue`: Manages the playlist using a queue data structure.
- `TreeNode`: Represents a node in the AVL tree.
- `AVLtree`: Manages the songs using an AVL tree for efficient searching and sorting.
- `Main`: The main class that contains the program's entry point and user interface logic.
- `Queue`: Additional functionalities for managing and playing playlists.
- `SongStack`: Stack implementation for managing song undo operations.

## Installation and Setup

1. **Clone the Repository:**

   ```sh
   git clone https://github.com/your-username/java-music-player.git
   ```

2. **Navigate to the Project Directory:**

   ```sh
   cd java-music-player
   ```

3. **Compile the Java Files:**

   ```sh
   javac Main.java
   ```

4. **Run the Application:**

   ```sh
   java Main
   ```

## Usage

1. **Login:**
   - Enter the username and password to access the application.
   - Valid credentials:
     - Username: Mujtaba, Password: GHADA
     - Username: Hafsa, Password: Salman

2. **Menu Options:**
   - **Make/Update a Playlist:** Create or update a playlist by adding songs.
   - **View Your Playlists:** View all saved playlists.
   - **Enter Playlist Name to Play:** Play a specific playlist.
   - **Search for a Song in Catalogue:** Search for a song by name.
   - **Sort Playlist:** Sort the current playlist by song name.
   - **Remove Last Song:** Remove the last song from the current playlist.
   - **Exit:** Exit the application.

## Example Songs

The project includes an array of predefined songs that can be used for testing and demonstration purposes.

## Contributors

- Mujtaba Saqib
- Hafsa Salman

## Acknowledgments

Special thanks to my teammate Hafsa for their support and guidance throughout this project. Feel free to give feedback and collaborate on any project.

For feedback, contact me at mujtabsaqib654@gmail.com.
