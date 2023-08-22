
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
        import java.awt.event.*;
        import java.io.*;
 class NotepadAPP extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public NotepadAPP() {
        setTitle("Extended Notepad Demo");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new OpenActionListener());
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem print= new JMenuItem("Print");
        saveMenuItem.addActionListener(new SaveActionListener());
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
        JMenuItem copyMenuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        JMenuItem pasteMenuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        JMenuItem selectAll = new JMenuItem(DefaultEditorKit.selectAllAction);
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        menuBar.add(editMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(NotepadAPP.this, "Extended Notepad Demo\nCreated by Your Name", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();

        setVisible(true);
    }

    private class OpenActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = fileChooser.showOpenDialog(NotepadAPP.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    textArea.read(reader, null);
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(NotepadAPP.this, "Error opening the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = fileChooser.showSaveDialog(NotepadAPP.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                    textArea.write(writer);
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(NotepadAPP.this, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotepadAPP());
    }
}
