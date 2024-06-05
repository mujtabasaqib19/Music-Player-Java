import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class QNode {
    QNode next;
    String data;

    QNode(String data) {
        this.data = data;
        this.next = null;
    }
}

class StackNode{
    String[] playlistState;
    StackNode next;
    StackNode(String[] state){
        this.playlistState=state;
        this.next=null;
    }
}
class PlaylistStack{
    StackNode top;
    PlaylistStack(){
        this.top=null;
    }
    void push(String[] state){
        StackNode newNode=new StackNode(state);
        newNode.next=top;
        top=newNode;
    }
    String[] pop(){
        if(isEmpty()){
            return null;
        }
        String[] poppedState=top.playlistState;
        top=top.next;
        return poppedState;
    }
    boolean isEmpty(){
        return top==null;
    }
}

class Queue {
    QNode front;
    QNode rear;

//    SongStack undoStack;

    Queue() {
        this.front = null;
        this.rear = null;
//        this.undoStack=new SongStack();
    }

    void enqueue(String... songs) {
        for (String song : songs) {
            QNode temp = new QNode(song);
            if (rear == null) {
                front = rear = temp;
            } else {
                rear.next = temp;
                rear = temp;
            }
        }
        //pushing state to undo stack
//        undoStack.push(toArray());
    }

    void dequeue() {
        if (front == null) {
            return;
        }
        QNode temp = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        //pushing state to undo stack
//        undoStack.push(toArray());
    }

    void removeSong(String data) {
        QNode current = front;
        QNode prev = null;

        while (current != null && !current.data.equals(data)) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            if (prev == null) {
                front = current.next;
            } else {
                prev.next = current.next;
            }
            if (current == rear) {
                rear = prev;
            }
        }

    }



    static void playBeep() {
        try {

            String soundFilePath = "\\C:\\Users\\STMINC\\Desktop\\DS Playlist Project Final Ver\\DS Playlist Project\\Memory.wav";
                    //"\\E:\\DS Playlist Project\\DS Playlist Project\\Memory.wav\\";  // Replace with the actual path

            AudioInputStream Audio = AudioSystem.getAudioInputStream(new File(soundFilePath));

            Clip clip = AudioSystem.getClip();

            clip.open(Audio);

            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.close();
            Audio.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    void savePlaylist(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            QNode current = front;
            while (current != null) {
                writer.println(current.data);
                current = current.next;
            }
            System.out.println("Playlist saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void shuffle() {
        String[] songsArray = toArray();
        Random rand = new Random();
        for (int i = songsArray.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            String temp = songsArray[i];
            songsArray[i] = songsArray[j];
            songsArray[j] = temp;
        }
        front = rear = null;
        enqueue(songsArray);
    }

    String[] toArray() {
        QNode current = front;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }

        String[] array = new String[size];
        current = front;
        int i = 0;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }




    void display() {
        QNode current = front;
        int i = 1;
        // System.out.println("Playlist: ");
        while (current != null) {
            System.out.println(i++ + ". " + current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    static Queue loadPlaylist(String fileName) {
        Queue playlist = new Queue();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Songs> loadedPlaylist = (ArrayList<Songs>) in.readObject();
            for (Songs song : loadedPlaylist) {
                playlist.enqueue(song.name);
            }
            return playlist;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void playPlaylistOnce(Queue playlist) {
        System.out.println("Now playing: ");
        playlist.display();
        Queue.playBeep();
    }

    static void playPlaylistLoop(Queue playlist)
    {
        while (true) {
            playPlaylistOnce(playlist);
            Scanner s = new Scanner(System.in);
            System.out.println("Do you want to continue playing? (yes/no): ");
            String continueChoice = s.nextLine().toLowerCase();
            if (!continueChoice.equals("yes")) {
                break;
            }
        }
    }
    void playNext(int songNumber){
        if (songNumber<=1 || front==null || rear==null){
            return;
        }
        QNode current=front;
        QNode prev=null;
        int currSongNum=1;
        //finding selected song
        while (current!=null && currSongNum<songNumber){
            prev=current;
            current=current.next;
            currSongNum++;
        }

        prev.next=current.next;
        current.next=front;
        front=current;
        if(current==rear){
            rear=prev;
        }
    }
    void sortPlaylist() {
        front = insertionSort(front);
    }

    private QNode insertionSort(QNode head) {
        if (head == null || head.next == null) {
            return head; // Already sorted or empty list
        }

        QNode sorted = null;
        QNode current = head;

        while (current != null) {
            QNode next = current.next;

            if (sorted == null || sorted.data.compareToIgnoreCase(current.data) >= 0) {

                current.next = sorted;
                sorted = current;
            } else {

                QNode temp = sorted;
                while (temp.next != null && temp.next.data.compareToIgnoreCase(current.data) < 0) {
                    temp = temp.next;
                }


                current.next = temp.next;
                temp.next = current;
            }

            current = next;
        }

        return sorted;
    }


}

