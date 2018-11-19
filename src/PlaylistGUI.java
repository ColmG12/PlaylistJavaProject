
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JPopupMenu;
//import java.awt.FlowLayout;

public class PlaylistGUI {
    // frame
    //JFrame f;
    // Table
    //JFrame j;
    // Popup
    //JPopupMenu p;

    // Constructor
    PlaylistGUI()
    {
        // Frame initiallization
        JFrame radioframe = new JFrame();

        // Frame Title
        radioframe.setTitle("Playlist GUI");

        // Data to be displayed in the JTable
        String[][] data = {
                { "515", "In Da Club", "50 Cent", "1970s", "2003", "50 Cent-In Da Club.mp3", "225" }
                //{ "Anand Jha", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "ID", "Title", "Artist", "Genre", "Year", "Location", "Duration" };

        // Initializing the JTable
        JTable songtable= new JTable(data, columnNames);
        songtable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane scroll = new JScrollPane(songtable);
        radioframe.add(scroll);
        // Frame Size
        radioframe.setSize(1500, 600);
        // Frame Visible = true
        radioframe.setVisible(true);
        //
        //radioframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Driver  method
    public static void main(String[] args)
    {
        new PlaylistGUI();
    }
}
