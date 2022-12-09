package view;

import controller.Actions;
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
    private Actions controller = new Actions();
    public ArrayList<InvoiceHeader> getInvoices() {
        if (invoices == null) invoices = new ArrayList<>();
        return invoices;
    }
    public MainFrame(String title) {
        super(title);
        //MenuBar
        this.setupMenuBar();
        saveBtn.addActionListener(controller);
        createInvoiceBtn.addActionListener(controller);
        deleteInvoiceBtn.addActionListener(controller);
        canselBtn.addActionListener(controller);
        this.setSize(1000, 700);
        this.setLocation(400, 150);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(InitialPanel);

    }
    private void setupMenuBar(){
        menuBar= new JMenuBar();
        loadMenuItem= new JMenuItem("Load");
        saveMenuItem= new JMenuItem("Save");
        fileMenu= new JMenu("File");
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        loadMenuItem.addActionListener(controller);
        saveMenuItem.addActionListener(controller);
    }

    public Actions getController(){return controller;}
    public int getNextInvoiceNum() {
        int num = 0;

        for (InvoiceHeader invoice : getInvoices()) {
            if (invoice.getInvoiceNumber() > num)
                num = invoice.getInvoiceNumber();
        }
        return ++num;
    }
    public InvoiceTableModel getInvoicesTableModel() {
        if (invoiceTableModel == null) {
            invoiceTableModel = new InvoiceTableModel(getInvoices());
        }
        return invoiceTableModel;
    }
    public static void main(String[] args) {
        JFrame frame = new MainFrame("Invoice Generator");
        frame.setVisible(true);
    }


}
