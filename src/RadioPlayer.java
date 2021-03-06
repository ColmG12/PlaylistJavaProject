//RadioPlayer.java
/*Code researched and written by John Brosnan who sent it on for use with the audio aspects of the project
  It uses some of the JavaFX classes and contains its own driver to show how it might be used. The GUI contains
  2 JButtons, one plays an audio file while the other generates a random number between 1 and 1000*/

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;


/*You pass in the path to the audio file you wish to play as a String to the static method playAudio()
  and just call RadioPlayer.playAudio() as necessary from whichever class you need to use it from in your
  project*/

public class RadioPlayer extends JFrame implements ActionListener{

    JButton audioButton; //need this global for event-handling later

    static MediaPlayer mediaPlayer; //need to declare this reference globally to prevent garbage collector
    //from removing it prematurely when play() is called in the playAudio()
    //method, which would stop playing the clip after a few seconds

    List<AudioClip> clips = new List<AudioClip>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<AudioClip> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(AudioClip audioClip) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends AudioClip> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends AudioClip> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public AudioClip get(int index) {
            return null;
        }

        @Override
        public AudioClip set(int index, AudioClip element) {
            return null;
        }

        @Override
        public void add(int index, AudioClip element) {

        }

        @Override
        public AudioClip remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<AudioClip> listIterator() {
            return null;
        }

        @Override
        public ListIterator<AudioClip> listIterator(int index) {
            return null;
        }

        @Override
        public List<AudioClip> subList(int fromIndex, int toIndex) {
            return null;
        }
    };


    //some sample audio files I have in a folder called audio that I sent with this Java file  - note that the "audio"
    // folder is assumed to be in the same folder as the bytecode RadioPlayer.class here so that relative file
    // paths can be used

    String songFile1 = "Song Folder/50 Cent-In Da Club.mp3", songFile2 = "Song Folder/All Saints-Pure Shores.mp3";

    //Just a short sample driver for the RadioPlayer class to demonstrate how it can be used from other classes

    public static void main(String args[])
    {
        RadioPlayer player = new RadioPlayer(); //create the RadioPlayer GUI

        //In order to avoid an "initialization exception" it is necessary to initiate the JavaFX Runtime when the
        // application is started
        //I am initialising here by creating a "dummy" JFXPanel() object
        //You MUST add this line of code to your own main() method for your project if you are going to use this class

        JFXPanel fxPanel = new JFXPanel();
    }

    //a sample GUI constructor to demonstrate the operation of the RadioPlayer class
    //You will never need to call this though in your own applications, you simply need
    //to call playAudio() on the class as it is a static method

    public RadioPlayer()
    {
        super("RadioPlayer Tester");

        FlowLayout flowLayout = new FlowLayout();

        setLayout(flowLayout);

        audioButton = new JButton("Play");
        add(audioButton);

        JButton randomButton = new JButton("Generate Random Number");
        add(randomButton);

        audioButton.addActionListener(this);
        randomButton.addActionListener(this);

        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }


    public static void playAudio(String path)
    {
        //create a new Media object using the file path specified in the call to playAudio
        //this will become the audio clip object we wish to play

        Media audioClip = new Media(new File(path).toURI().toString());

        //create a new MediaPlayer object that will be used to play the audio clip

        mediaPlayer = new MediaPlayer(audioClip);

		/*when the clip has played to completion, the code below ensures that the JavaFX thread will terminate
		 I have commented it out though because you generally want a JavaFX thread to keep operating
		 after an audio file has been fully played, but a JavaFX thread won't terminate automatically
	     without a call to Platform.exit() or System.exit(). So, if you run this program with the code below commented out,
		 the JavaFX thread will continue after the audio clip has played meaning you can press the button again and again
		 to replay the audio - if you uncomment it, exceptions are thrown after the first few presses because the
		 audio requires JavaFX libraries which are no longer available as the thread they run on is terminated
		 The other button, for random number generation, based on the swing event-dispatch thread, continues to operate
		 as normal though, even with exceptions being thrown for the other thread*/

		/*mediaPlayer.setOnEndOfMedia(new Runnable() {
 			 public void run() {
		    	Platform.exit();
		  	}
		});*/

        /*now actually play the audio clip - some exception-handling code to attempt to play the .wav audio clip associated with audioFile1.
         *If it succeeds, you will hear the sound of a gunshot, otherwise it fails either because the audio clip could not be found, or the
         *MediaPlayer object had some difficulty in actually playing the file. If it fails, the "catch" clause executes and an error message
         *is displayed to the console*/

        try
        {
            mediaPlayer.play();
        }
        catch(Exception e)
        {
            System.out.println("The audio file " + path + " could not be played!");
        }

    }


    public void actionPerformed(ActionEvent e)
    {
		/*If the audioButton was pressed then call playAudio() to play the audio file associated with the reference audioFile1 (the gunshot)
		  or else, if the other button was pressed, display a message dialog showing a randomly generated number between 1 and 1000*/

        if(e.getSource()==audioButton) {
            //Collections.shuffle(Collections.singletonList("Song Folder"));
            RadioPlayer.playAudio(songFile2);
        }

        else
            JOptionPane.showMessageDialog(null,"The randomly generated number was " + (int)(Math.random()*1000 + 1));
    }
}
