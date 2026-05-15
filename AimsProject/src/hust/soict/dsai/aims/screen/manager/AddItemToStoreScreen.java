package hust.soict.dsai.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Map<String, JTextField> inputFields = new HashMap<>();

    public AddItemToStoreScreen(Store store, String screenTitle) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(screenTitle), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.add(createSouth(), BorderLayout.SOUTH);

        setTitle(screenTitle);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createNorth(String screenTitle) {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        north.add(createMenuBar());
        north.add(createHeader(screenTitle));

        return north;
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            new StoreManagerScreen(store);
            dispose();
        });

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> {
            new AddBookToStoreScreen(store);
            dispose();
        });

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store);
            dispose();
        });

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store);
            dispose();
        });

        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);

        menu.add(viewStoreItem);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createHeader(String screenTitle) {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel(screenTitle);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    private JPanel createCenter() {
        JPanel wrapper = new JPanel(new BorderLayout());

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(0, 2, 10, 10));

        buildForm(form);

        wrapper.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.NORTH);
        wrapper.add(form, BorderLayout.CENTER);
        wrapper.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.SOUTH);

        return wrapper;
    }

    private JPanel createSouth() {
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Add to store");
        addButton.addActionListener(e -> {
            try {
                Media media = createMedia();
                store.addMedia(media);

                JOptionPane.showMessageDialog(
                        this,
                        "Added successfully: " + media.getTitle(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                new StoreManagerScreen(store);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cannot add media: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            new StoreManagerScreen(store);
            dispose();
        });

        south.add(addButton);
        south.add(cancelButton);

        return south;
    }

    protected void addInputField(JPanel form, String key, String label) {
        JLabel jLabel = new JLabel(label);
        JTextField textField = new JTextField();

        inputFields.put(key, textField);

        form.add(jLabel);
        form.add(textField);
    }

    protected String getText(String key) {
        return inputFields.get(key).getText().trim();
    }

    protected float getFloat(String key) {
        return Float.parseFloat(getText(key));
    }

    protected int getInt(String key) {
        return Integer.parseInt(getText(key));
    }

    protected abstract void buildForm(JPanel form);

    protected abstract Media createMedia();
}
