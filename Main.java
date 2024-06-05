//Mujtaba Saqib: 22K-4005
//Hafsa Salman: 22K-5161

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Songs implements Serializable
{
    String name, artist, album;
    Duration duration;
    Date releaseDate, dateAdded;

    static int i=0;
    int j=0;

    public Songs()
    {
    }

    public Songs(String name, String artist, String album, Duration duration, Date releaseDate, Date dateAdded)
    {
        this.j = i++;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.dateAdded = dateAdded;
    }

    public void displaySongDetails()
    {
        System.out.print(j + " ");
        System.out.println("Name: " + name + "\nDuration: " + duration);
        System.out.println("Album: " + album);
        System.out.println("Artist: " + artist);
        System.out.println("Release Date: " + releaseDate);
        System.out.println("Date Added: " + dateAdded);
        System.out.println("___________________________________");
    }

    public String toFileString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return (name + "," + artist + "," + album + "," + duration + "," +
                (releaseDate != null ? sdf.format(releaseDate) : "") + "," +
                (dateAdded != null ? sdf.format(dateAdded) : ""));
    }
}

class Node
{
    Songs song;
    Node next;

    public Node (Songs song)
    {
        this.song = song;
        this.next = null;
    }
}


class PlaylistQueue
{
    Node front=null;
    Node rear=null;

    public boolean isEmpty()
    {
        return rear == null;
    }

    public void enqueue(Songs song)
    {
        Node newNode = new Node(song);

        if(isEmpty())
        {
            front=newNode;
        }

        else
        {
            rear.next=newNode;
        }
        rear=newNode;
    }

    public Songs dequeue()
    {
        if(isEmpty())
        {
            System.out.println("Queue is empty. Cannot delete.");

            return null;
        }

        else
        {
            Node temp = front;

            front = front.next;

            if(front==null)
            {
                rear=null;
            }

            return temp.song;
        }
    }

    private Songs[] toArray()
    {
        int size = size();

        Songs[] array = new Songs[size];

        Node current = front;

        for (int i = 0; i < size; i++)
        {
            array[i] = current.song;
            current = current.next;
        }

        return array;
    }

    private int size() {
        int count = 0;
        Node current = front;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public void display()
    {
        if(isEmpty())
        {
            System.out.println("playlist is empty.");
        }

        else
        {
            Node temp = front;

            while(temp!=null)
            {
                System.out.print(Songs.i + " ");
                System.out.println("Name: " + temp.song.name + "\nDuration: " + temp.song.duration);
                System.out.println("Album: " + temp.song.album);
                System.out.println("Artist: " + temp.song.artist);
                System.out.println("Release Date: " + temp.song.releaseDate);
                System.out.println("Date Added: " + temp.song.dateAdded);
                System.out.println("___________________________________");

                temp = temp.next;
            }

            System.out.println("NULL");
        }
    }
}

class TreeNode
{
    Songs song;
    int height;

    TreeNode right,left;

    public TreeNode(Songs song)
    {
        this.song = song;
        this.right = null;
        this.left = null;
        this.height = 1;
    }
}

class AVLtree
{
    TreeNode root;

    public AVLtree()
    {
        this.root = null;
    }

    public int height(TreeNode n)
    {
        if(n==null)
        {
            return 0;
        }

        else
        {
            return n.height;
        }
    }

    private int  getBalance(TreeNode N)
    {
        if (N == null)
        {
            return 0;
        }

        return height(N.left) - height(N.right);
    }


    public int max(int a,int b)
    {
        return Math.max(a, b);
    }

    private TreeNode rightRotate(TreeNode root)
    {
        TreeNode newRoot=root.left;
        TreeNode subTree=newRoot.right;

        newRoot.right=root;
        root.left=subTree;

        root.height = max(height(root.left), height(root.right)) + 1;
        newRoot.height = max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    private TreeNode leftRotate(TreeNode root)
    {
        TreeNode newRoot=root.right;
        TreeNode subTree=newRoot.left;

        newRoot.left=root;
        root.right=subTree;

        root.height = max(height(root.left), height(root.right)) + 1;
        newRoot.height = max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }


    public void insert(Songs song)
    {
        root=insertRec(root,song);
    }

    public TreeNode insertRec(TreeNode root,Songs song)
    {
        if(root==null)
        {
            root=new TreeNode(song);

            return root;
        }

        if(song.name.compareTo(root.song.name)<0)
        {
            root.left = insertRec(root.left, song);
        }

        else if(song.name.compareTo(root.song.name)>0)
        {
            root.right = insertRec(root.right, song);
        }

        root.height = max(height(root.left), height(root.right)) + 1;
        //left-right
        int bal = getBalance(root);

        if(bal>1 && song.name.compareTo(root.left.song.name) < 0)
        {
            return rightRotate(root);
        }

        if(bal < -1 && song.name.compareTo(root.right.song.name) > 0)
        {
            return leftRotate(root);
        }

        if(bal > 1 && song.name.compareTo(root.left.song.name) > 0)
        {
            root.left=leftRotate(root.left);

            return rightRotate(root);
        }

        if(bal < -1 && song.name.compareTo(root.right.song.name) < 0)
        {
            root.right=rightRotate(root.right);

            return leftRotate(root);
        }

        return root;
    }

    //Inorder Traversal
    public void inorder()
    {
        System.out.println("Song list: (Alphabetical)");

        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode root)
    {
        if(root != null)
        {
            inorderRec(root.left);
            root.song.displaySongDetails();
            inorderRec(root.right);
        }
    }

    //Pre-Order Traversal
    public void preorder()
    {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode root)
    {
        if(root != null)
        {
            System.out.print(root.song.name + "  ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    //PostOrder Traversal
    public void postorder()
    {
        System.out.println("PostOrder traversal");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode root)
    {
        if(root != null)
        {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.song.name + "  ");
        }
    }


    public void delete(String songName)
    {
        root=deleteRec(root,songName);
    }

    private TreeNode deleteRec(TreeNode root,String songName)
    {
        if (root == null)
        {
            System.out.println("Playlist is empty; can not delete.");

            return root;
        }
        //find node
        if(songName.compareTo(root.song.name) < 0)
        {
            root.left=deleteRec(root.left,songName);
        }

        else if(songName.compareTo(root.song.name) > 0)
        {
            root.right=deleteRec(root.right,songName);
        }

        else
        {
            //case one child or none
            if (root.left == null)
            {
                return root.right;
            }

            else if(root.right == null)
            {
                return root.left;
            }

            //case two children
            //place inorder successor in its place
            root.song =minVal(root.right);

            //now delete inorder successor
            root.right=deleteRec(root.right,root.song.name);
        }

        root.height = max(height(root.left), height(root.right)) + 1;

        //left-right
        int bal=getBalance(root);

        if (bal > 1 && songName.compareTo(root.left.song.name) < 0)
        {
            return rightRotate(root);
        }

        if(bal < -1 && songName.compareTo(root.right.song.name) > 0)
        {
            return leftRotate(root);
        }

        if(bal > 1 && songName.compareTo(root.left.song.name) > 0)
        {
            root.left=leftRotate(root.left);

            return rightRotate(root);
        }

        if(bal < -1 && songName.compareTo(root.right.song.name) < 0)
        {
            root.right=rightRotate(root.right);

            return leftRotate(root);
        }

        return root;
    }

    public Songs minVal(TreeNode root)
    {
        Songs minval= root.song;

        while (root.left != null)
        {
            minval=root.left.song;
            root=root.left;
        }

        return minval;
    }

    public void search(String songName)
    {
        searchRec(root, songName, 0);
    }

    private boolean searchRec(TreeNode root, String songName, int level)
    {
        if (root == null)
        {
            System.out.println("Tree is empty");

            return false;
        }

        if (songName.compareTo(root.song.name) == 0)
        {
            System.out.println("Song found: ");
            root.song.displaySongDetails();

            return true;
        }

        if(songName.compareTo(root.song.name) < 0)
        {
            return searchRec(root.left, songName, level+1);
        }

        else
        {
            return searchRec(root.right, songName, level+1);
        }
    }


    public static void writeToFile(Songs song, String fileName)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
        {
            writer.write(song.toFileString() + "\n");

            System.out.println("Song details written to file successfully.");
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

public class Main
{
    static AVLtree tree = new AVLtree();

    public static void main(String[] args)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate = null;
        Date dateAdded = null;

        try
        {
            releaseDate = sdf.parse("2011-10-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //1
        Songs s1 = new Songs("Cookie", "New Jeans", "New Jeans", Duration.ofMinutes(3).plusSeconds(56), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2011-10-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //2
        Songs s2 = new Songs("Paradise", "ColdPlay", "Mylo Xyloto", Duration.ofMinutes(4).plusSeconds(38), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2011-10-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //3
        Songs s3 = new Songs("Atlantis", "Seafret", "Tell Me It's Real", Duration.ofMinutes(3).plusSeconds(49), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2016-1-29");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //4
        Songs s4 = new Songs("Fourth of July", "Sufjan Stevens", "Carrie & Lowell", Duration.ofMinutes(4).plusSeconds(39), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2015-3-31");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //5
        Songs s5 = new Songs("Somewhere Only We Know", "Keane", "Hopes And Fears", Duration.ofMinutes(3).plusSeconds(57), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2014-5-10");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //6
        Songs s6 = new Songs("Tek It", "Cafune", "Mylo Running", Duration.ofMinutes(3).plusSeconds(11), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2021-7-20");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //7
        Songs s7 = new Songs("Moral of the Story", "Ashe", "Ashlyn", Duration.ofMinutes(3).plusSeconds(21), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2021-5-7");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //8
        Songs s8 = new Songs("Water Fountain", "Alec Benjamin", "Narrated For You", Duration.ofMinutes(3).plusSeconds(38), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2018-11-16");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //9
        Songs s9 = new Songs("Heather", "Conan Gray", "Kid Krow", Duration.ofMinutes(3).plusSeconds(18), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2020-3-20");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //10
        Songs s10 = new Songs("Ocean Eyes", "Billie Eilish", "don't smile at me", Duration.ofMinutes(3).plusSeconds(20), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2017-12-22");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //11
        Songs s11 = new Songs("Duvet", "boa", "Twilight", Duration.ofMinutes(3).plusSeconds(23), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2010-4-20");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //12
        Songs s12 = new Songs("Advice", "Alex G", "Trick", Duration.ofMinutes(2).plusSeconds(37), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2012-11-5");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //13
        Songs s13 = new Songs("Anti-Hero", "Taylor Swift", "Midnights", Duration.ofMinutes(3).plusSeconds(20), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2021-10-21");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //14
        Songs s14 = new Songs("The Beach", "the Neighbourhood", "Wiped out!", Duration.ofMinutes(4).plusSeconds(15), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2015-9-30");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //15
        Songs s15 = new Songs("Another Love", "Tom Odell", "Long Way Down", Duration.ofMinutes(4).plusSeconds(4), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2013-16-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //16
        Songs s16 = new Songs("505", "Arctic Monkeys", "Favorite Worst Nightmare", Duration.ofMinutes(4).plusSeconds(13), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2007-04-22");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //17
        Songs s17 = new Songs("Night Changes", "One Direction", "FOUR", Duration.ofMinutes(3).plusSeconds(46), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2014-11-17");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //18
        Songs s18 = new Songs("Cardigan", "Taylor Swift", "folklore", Duration.ofMinutes(3).plusSeconds(59), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2020-7-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //19
        Songs s19 = new Songs("Movies", "Conan Gray", "Superache", Duration.ofMinutes(3).plusSeconds(34), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2022-16-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //20
        Songs s20 = new Songs("Afterlife", "Holding Absence", "The Greatest Mistake of My Life", Duration.ofMinutes(3).plusSeconds(46), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2021-4-16");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //21
        Songs s21 = new Songs("Breaking The Habit", "Linkin Part", "Meteora", Duration.ofMinutes(3).plusSeconds(16), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2003-3-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //22
        Songs s22 = new Songs("The Diary of Jane", "Breaking Benjamin", "Phobia", Duration.ofMinutes(3).plusSeconds(20), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2006-1-1");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //23
        Songs s23 = new Songs("On My Own", "Ashes Remail", "What I've Become", Duration.ofMinutes(2).plusSeconds(52), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2011-18-23");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //24
        Songs s24 = new Songs("Throne", "Bring Me The Horizon", "That's The Spirit", Duration.ofMinutes(3).plusSeconds(311), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2015-10-11");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //25
        Songs s25 = new Songs("Zombie", "The Cranberries", "No Need To Argue", Duration.ofMinutes(5).plusSeconds(06), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("1994-10-3");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //26
        Songs s26 = new Songs("Mind is a Prison", "Alec Benjamin", "These Two Windows", Duration.ofMinutes(2).plusSeconds(41), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2020-5-29");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //27
        Songs s27 = new Songs("Mera Musafir", "Bayaan", "Suno", Duration.ofMinutes(4).plusSeconds(51), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2020-2-7");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //28
        Songs s28 = new Songs("The Last Time", "Taylor Swift", "Red", Duration.ofMinutes(4).plusSeconds(59), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2021-11-12");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //29
        Songs s29 = new Songs("Be Somebody", "Thousand Foot Krutch", "The End Is Where We Begin", Duration.ofMinutes(3).plusSeconds(42), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2012-4-17");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //30
        Songs s30 = new Songs("Teri Tasveer", "Bayaan", "Suno", Duration.ofMinutes(4).plusSeconds(58), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2020-2-7");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //31
        Songs s31 = new Songs("Sajni", "Jal", "Boondh", Duration.ofMinutes(5).plusSeconds(06), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2008-4-1");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //32
        Songs s32 = new Songs("Paradise", "ColdPlay", "Mylo Xyloto", Duration.ofMinutes(4).plusSeconds(38), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2011-10-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //33
        Songs s33 = new Songs("Gilded Lily", "Cults", "Offering", Duration.ofMinutes(3).plusSeconds(42), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2022-10-26");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //34
        Songs s34 = new Songs("Photograph", "Ed Sheeran", "x", Duration.ofMinutes(4).plusSeconds(18), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2015-11-13");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //35
        Songs s35 = new Songs("Yellow", "ColdPlay", "Parachutes", Duration.ofMinutes(4).plusSeconds(26), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2000-17-10");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //36
        Songs s36 = new Songs("Style", "Taylor Swift", "1989", Duration.ofMinutes(3).plusSeconds(51), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2014-1-1");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //37
        Songs s37 = new Songs("Space Song", "Beach House", "Depression Cherry", Duration.ofMinutes(5).plusSeconds(20), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2015-8-28");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //38
        Songs s38 = new Songs("Willow", "Taylor Swift", "evermore", Duration.ofMinutes(3).plusSeconds(34), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2020-12-10");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //39
        Songs s39 = new Songs("Brutal", "Olivia Rodrigo", "SOUR", Duration.ofMinutes(2).plusSeconds(23), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2011-10-24");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        //40
        Songs s40 = new Songs("Stitches", "Shawn Mendes", "Handwritten", Duration.ofMinutes(3).plusSeconds(26), releaseDate, dateAdded);
        try
        {
            releaseDate = sdf.parse("2015-04-14");
            dateAdded = sdf.parse("2024-05-08");
        }

        catch (ParseException e)
        {
            e.printStackTrace();
        }

        Songs[] songsArray = {s1, s2, s3, s4, s5, s6, s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40};

        PlaylistQueue mainPlaylist = new PlaylistQueue();

        mainPlaylist.enqueue(s1);
        mainPlaylist.enqueue(s2);
        mainPlaylist.enqueue(s3);
        mainPlaylist.enqueue(s4);
        mainPlaylist.enqueue(s5);
        mainPlaylist.enqueue(s6);
        mainPlaylist.enqueue(s7);
        mainPlaylist.enqueue(s8);
        mainPlaylist.enqueue(s9);
        mainPlaylist.enqueue(s10);
        mainPlaylist.enqueue(s11);
        mainPlaylist.enqueue(s12);
        mainPlaylist.enqueue(s13);
        mainPlaylist.enqueue(s14);
        mainPlaylist.enqueue(s15);
        mainPlaylist.enqueue(s16);
        mainPlaylist.enqueue(s17);
        mainPlaylist.enqueue(s18);
        mainPlaylist.enqueue(s19);
        mainPlaylist.enqueue(s20);
        mainPlaylist.enqueue(s21);
        mainPlaylist.enqueue(s22);
        mainPlaylist.enqueue(s23);
        mainPlaylist.enqueue(s24);
        mainPlaylist.enqueue(s25);
        mainPlaylist.enqueue(s26);
        mainPlaylist.enqueue(s27);
        mainPlaylist.enqueue(s28);
        mainPlaylist.enqueue(s29);
        mainPlaylist.enqueue(s30);
        mainPlaylist.enqueue(s31);
        mainPlaylist.enqueue(s32);
        mainPlaylist.enqueue(s33);
        mainPlaylist.enqueue(s34);
        mainPlaylist.enqueue(s35);
        mainPlaylist.enqueue(s36);
        mainPlaylist.enqueue(s37);
        mainPlaylist.enqueue(s38);
        mainPlaylist.enqueue(s39);
        mainPlaylist.enqueue(s40);

        tree.insert(s1);
        tree.insert(s2);
        tree.insert(s3);
        tree.insert(s4);
        tree.insert(s5);
        tree.insert(s6);
        tree.insert(s7);
        tree.insert(s8);
        tree.insert(s9);
        tree.insert(s10);
        tree.insert(s11);
        tree.insert(s12);
        tree.insert(s13);
        tree.insert(s14);
        tree.insert(s15);
        tree.insert(s16);
        tree.insert(s17);
        tree.insert(s18);
        tree.insert(s19);
        tree.insert(s20);
        tree.insert(s21);
        tree.insert(s22);
        tree.insert(s23);
        tree.insert(s24);
        tree.insert(s25);
        tree.insert(s26);
        tree.insert(s27);
        tree.insert(s28);
        tree.insert(s29);
        tree.insert(s30);
        tree.insert(s31);
        tree.insert(s32);
        tree.insert(s33);
        tree.insert(s34);
        tree.insert(s35);
        tree.insert(s36);
        tree.insert(s37);
        tree.insert(s38);
        tree.insert(s39);
        tree.insert(s40);

        tree.inorder();

        Scanner s = new Scanner(System.in);

        System.out.println("WELCOME!\nLogin\n");

        System.out.print("USERNAME: ");
        String userName = s.next();

        System.out.print("PASSWORD: ");
        String password = s.next();

        s.nextLine();

        if ((userName.equalsIgnoreCase("Mujtaba") && password.equalsIgnoreCase("GHADA")) || (userName.equalsIgnoreCase("Hafsa") && password.equalsIgnoreCase("Salman")))
        {
            System.out.println("\nWelcome " + userName);

            int choice;

            do
            {
                System.out.println("\nWhat do you want to do?: ");
                System.out.println("1. Make/Update a playlist\n2. View your playlists\n3. Enter playlist name to play\n4. Search for a song in catalogue\n5. Sort Playlist\n6. Remove Last Song\n7. Exit");
                System.out.print("\nEnter choice: ");
                choice = s.nextInt();

                s.nextLine();

                switch (choice)
                {
                    case 1:
                        System.out.print("Enter playlist name:");
                        String playlistName = s.nextLine();

                        ArrayList<Songs> playlist = new ArrayList<>();

                        System.out.print("\nEnter the indices of the songs for your playlist. Type 'done' when finished:");

                        while (true)
                        {
                            String input = s.nextLine();

                            if (input.equalsIgnoreCase("done"))
                            {
                                break;
                            }

                            int index = Integer.parseInt(input);
                            playlist.add(songsArray[index]);
                        }

                        for (Songs song : playlist)
                        {
                            tree.insert(song);
                        }

                        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userName + "_" + playlistName + ".txt")))
                        {
                            out.writeObject(playlist);
                        }

                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        break;

                    case 2:
                        System.out.println("\nYour playlists:");
                        File userDir = new File(".");

                        File[] files = userDir.listFiles((dir, name) -> name.startsWith(userName + "_") && name.endsWith(".txt"));

                        if (files != null)
                        {
                            for (File file : files)
                            {
                                String loadedPlaylistName = file.getName().replaceFirst(userName + "_", "").replace(".txt", "");
                                System.out.println(loadedPlaylistName);

                                // Load the playlist
                                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
                                {
                                    ArrayList<Songs> loadedPlaylist = (ArrayList<Songs>) in.readObject();

                                    for (int i = 0; i < loadedPlaylist.size(); i++)
                                    {
                                        Songs song = loadedPlaylist.get(i);
                                        System.out.print(i+". ");

                                        song.displaySongDetails();
                                    }
                                }

                                catch (IOException | ClassNotFoundException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }

                        else
                        {
                            System.out.println("No playlists found.");
                        }

                        break;

                    case 3:
                        Queue Plist = new Queue();

                        System.out.print("Enter the playlist name to play:");
                        String selectedPlaylist = s.nextLine();

                        Queue selectedQueue = Queue.loadPlaylist(userName + "_" + selectedPlaylist + ".txt");

                        if (selectedQueue != null)
                        {
                            System.out.println("Options for " + selectedPlaylist + ": ");
                            System.out.println("1. Play Once");
                            System.out.println("2. Shuffle and Play Once");
                            System.out.println("3. Loop Playlist");
                            System.out.println("4. Shuffle and Loop Playlist");
                            System.out.println("5. Play next song");

                            System.out.print("\nEnter your choice: ");
                            int playChoice = s.nextInt();

                            s.nextLine();

                            switch (playChoice)
                            {
                                case 1:
                                    Queue.playPlaylistOnce(selectedQueue);

                                    break;

                                case 2:
                                    selectedQueue.shuffle();
                                    Queue.playPlaylistOnce(selectedQueue);

                                    break;

                                case 3:
                                    Queue.playPlaylistLoop(selectedQueue);

                                    break;

                                case 4:
                                    selectedQueue.shuffle();
                                    Queue.playPlaylistLoop(selectedQueue);

                                    break;

                                case 5:
                                    if (selectedQueue != null)
                                    {
                                        System.out.print("Enter the song number to play next: ");
                                        int songNumberToPlayNext = s.nextInt();

                                        s.nextLine();
                                        selectedQueue.playNext(songNumberToPlayNext);
                                        System.out.println("Playing next song...");
                                        Queue.playPlaylistOnce(selectedQueue);
                                    }

                                    else
                                    {
                                        System.out.println("Please create or load a playlist first.");
                                    }

                                    break;

                                default:
                                    System.out.println("Invalid choice. Playing the playlist without shuffling.");
                                    Queue.playPlaylistOnce(selectedQueue);
                            }
                        }

                        else
                        {
                            System.out.println("Playlist not found.");
                        }

                        break;

                    case 4:
                        if (tree.root == null)
                        {
                            System.out.println("Tree is empty. Add songs to the playlist first.");
                        }

                        else
                        {
                            System.out.print("What song do you want to search for: ");
                            String search = s.nextLine();

                            tree.search(search);
                        }

                        break;

                    case 6:
                        System.out.println("Last song removed: ");
                        File userDirr = new File(".");
                        File[] filess = userDirr.listFiles((dir, name) -> name.startsWith(userName + "_") && name.endsWith(".txt"));

                        SongStack undo = new SongStack();

                        if (filess != null)
                        {
                            for (File file : filess)
                            {
                                System.out.println(" ");
                                String loadedPlaylistName = file.getName().replaceFirst(userName + "_", "").replace(".txt", "");
                                System.out.println(loadedPlaylistName);

                                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
                                {
                                    ArrayList<Songs> loadedPlaylist = (ArrayList<Songs>) in.readObject();

                                    Queue loadedPlayList = new Queue();
//                                    SongStack undo=new SongStack();
//                                    Queue loadedPlayList2 = new Queue();

                                    for (Songs song : loadedPlaylist)
                                    {
                                        undo.push(song.name);
                                    }

                                    undo.pop();
                                    System.out.println(" ");
                                    undo.display();
                                    System.out.println();
                                }

                                catch (IOException | ClassNotFoundException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }

                        else
                        {
                            System.out.println("No playlists found.");
                        }

                        break;

                    case 5:
                        Queue sortList = new Queue();
                        System.out.println("Sorting playlist by: Song Name");

                        // Call the method to sort the playlist
                        sortList.sortPlaylist();

                        // Display the sorted playlist
                        System.out.println("Playlist sorted by: Song Name");
                        sortList.display();

                        File userDirr1 = new File(".");
                        File[] filess1 = userDirr1.listFiles((dir, name) -> name.startsWith(userName + "_") && name.endsWith(".txt"));

                        if (filess1 != null)
                        {
                            for (File file : filess1)
                            {
                                String loadedPlaylistName = file.getName().replaceFirst(userName + "_", "").replace(".txt", "");
                                System.out.println(loadedPlaylistName);

                                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
                                {
                                    ArrayList<Songs> loadedPlaylist = (ArrayList<Songs>) in.readObject();

                                    //create a new queue for each playlist
                                    Queue loadedPlayList = new Queue();

                                    //enqueue each song into the loadedPlayList queue
                                    for (Songs song : loadedPlaylist) {
                                        loadedPlayList.enqueue(song.name);
                                    }
                                    loadedPlayList.sortPlaylist();
                                    //display the songs in the loaded playlist
                                    loadedPlayList.display();
                                }

                                catch (IOException | ClassNotFoundException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                        }

                        else
                        {
                            System.out.println("No playlists found.");
                        }

                        break;

                    case 7:
                        System.out.println("Exiting the program. Goodbye!");

                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }

            }
            while (choice != 7);
        }

        else
        {
            System.out.println("Invalid username or password. Exiting the program.");
        }

        s.close();
    }
}