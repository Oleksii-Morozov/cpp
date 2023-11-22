package alerix.dev;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class UserInterface {
    private Library library = new Library(new Semaphore(0));
    private final ArrayList<Reader> readers = new ArrayList<>();

    private JTable readersTable;
    private JLabel ReadersL;
    private JTextField ReaderF;
    private JLabel BooksL;
    private JTextField BooksF;
    private JTextField BookTakenF;
    private JLabel BookTakenL;
    private JButton runSelectedButton;
    private JButton suspendSelectedButton;
    private JPanel MainPanel;
    private JScrollPane readersTableScroll;

    public UserInterface() {
        ReaderF.addActionListener(e -> updateAll());
        BooksF.addActionListener(e -> updateAll());

        runSelectedButton.addActionListener(e -> {
            var rows = readersTable.getSelectedRows();
            for (int row : rows) {
                var reader = readers.get(row);
                if (reader.isAlive()) {
                    reader.resumeReader();
                } else {
                    reader.start();
                }
            }
        });

        suspendSelectedButton.addActionListener(e -> {
            var rows = readersTable.getSelectedRows();
            for (int row : rows) {
                var reader = readers.get(row);
                reader.suspendReader();
            }
        });

        readersTable.setModel(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return readers.size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0 -> {
                        return "Name";
                    }
                    case 1 -> {
                        return "Priority";
                    }
                    case 2 -> {
                        return "Status";
                    }
                    case 3 -> {
                        return "Have book";
                    }
                    default -> {
                        return "";
                    }
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                var reader = readers.get(rowIndex);
                switch (columnIndex) {
                    case 0 -> {
                        return reader.getName();
                    }
                    case 1 -> {
                        return reader.getPriority();
                    }
                    case 2 -> {
                        return reader.getState();
                    }
                    case 3 -> {
                        return reader.isHaveBook() ? "Yes" : "No";
                    }
                    default -> {
                        return "";
                    }
                }
            }
        });


        readersTable.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
        readersTable.getTableHeader().setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        readersTable.setRowHeight(30);
        readersTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        readersTableScroll.setMinimumSize(new Dimension(600, 0));

        new Thread(() -> {
            while (true) {
                readersTable.updateUI();
                BookTakenF.setText(String.valueOf(library.getBorrowedBooks()));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException in ui updater");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserInterface");
        frame.setContentPane(new UserInterface().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void updateAll() {
        var books = BooksF.getText();
        if (books.isEmpty()) {
            books = "0";
        }
        library = new Library(new Semaphore(Integer.parseInt(books)));

        readers.forEach(r -> {
            if (r.isAlive()) r.interrupt();
        });
        readers.clear();
        var readersCountString = ReaderF.getText();
        if (readersCountString.isEmpty()) {
            readersCountString = "0";
        }
        int count = Integer.parseInt(readersCountString);
        for (int i = 0; i < count; i++) {
            var reader = new Reader(library);
            reader.setPriority(new Random().nextInt(Thread.MIN_PRIORITY, Thread.MAX_PRIORITY));
            readers.add(reader);
        }
    }
}
