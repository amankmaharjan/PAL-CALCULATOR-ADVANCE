
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

public class Mystery extends JFrame implements ActionListener {

    public Mystery() {
        super("Welcome to Mystery Program");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu toolsMenu = new JMenu("Tools");
        menuBar.add(toolsMenu);
        JMenuItem openMenuItem = new JMenuItem("Open File");
        openMenuItem.setActionCommand("Open File");
        openMenuItem.addActionListener(this);
        JMenuItem saveMenuItem = new JMenuItem("Save File");
        saveMenuItem.setActionCommand("Save File");
        saveMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String clickedString = e.getActionCommand();
        if (clickedString.compareTo("Open File") == 0) {
            JOptionPane.showMessageDialog(null, "Open an existing file.");
        }
        if (clickedString.compareTo("Save File") == 0) {
            JOptionPane.showMessageDialog(null, "Save data to an existing file.");
        }
    }

    public static void main(String[] args) {
        Mystery myProg = new Mystery();
    }
}
