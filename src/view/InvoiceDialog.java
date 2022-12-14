package view;


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvoiceDialog extends JDialog {
    private JTextField custNameField;
    private JTextField invDateField;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public InvoiceDialog(MainFrame frame) {
        custNameLbl = new JLabel("Customer Name:");
        custNameField = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");

        okBtn.setActionCommand("createInvoiceOK");
        cancelBtn.setActionCommand("createInvoiceCancel");

        okBtn.addActionListener(frame.getActions());
        cancelBtn.addActionListener(frame.getActions());
        setTitle("Create New Invoice:");
        setLayout(new GridLayout(3, 2));

        add(custNameLbl);
        add(custNameField);
        add(invDateLbl);
        add(invDateField);
        add(okBtn);
        add(cancelBtn);
        pack();

    }
    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getInvDateField() {
        return invDateField;
    }

}
