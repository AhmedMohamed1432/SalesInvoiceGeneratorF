package view;

import model.InvoiceHeader;
import model.InvoiceTableModel;

import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private JPanel MainPanel;
    private JPanel SecondaryPanel;
    private JPanel InitialPanel;
    private JLabel InvoiceLabel;
    private JButton createInvoiceBtn;
    private JButton deleteInvoiceBtn;
    private JTable invoicesTable;
    private JLabel invNumber;
    private JLabel invNumberVal;
    private JScrollPane Scrollpanel;
    private JLabel invoiceDate;
    private JLabel custName;
    private JTextField customerNameTextField;
    private JTextField dateTextfield;
    private JLabel invoiceToatal;
    private JLabel InvoiceItems;
    private JTable lineTable;
    private JButton saveBtn;
    private JLabel intotalval;
    private JButton canselBtn;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;

    private InvoiceTableModel invoiceTableModel;
    private ArrayList<InvoiceHeader> invoices;
    //private Cntrolller controller = new controller (this);

    public MainFrame(String title) {
        super(title);
        //MenuBar
        menuBar= new JMenuBar();
        loadMenuItem= new JMenuItem("Load");
        saveMenuItem= new JMenuItem("Save");
        fileMenu= new JMenu("File");
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        this.setSize(1000, 700);
        this.setLocation(400, 150);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(InitialPanel);

    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Invoice Generator");
        frame.setVisible(true);
    }


}
