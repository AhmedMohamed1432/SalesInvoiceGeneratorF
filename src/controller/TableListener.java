package controller;

import model.InvoiceHeader;
import model.InvoiceLine;
import model.InvoiceTableModel;
import model.LineTableModel;
import view.MainFrame;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

public class TableListener implements ListSelectionListener {
    private MainFrame frame;
    public TableListener(MainFrame frame){
        this.frame = frame;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvIndex = frame.getheaderTable().getSelectedRow();
        System.out.println("Invoice :" + selectedInvIndex);
        if (selectedInvIndex != -1) {
            InvoiceHeader selectedInv = frame.getInvoicesArray().get(selectedInvIndex);
            ArrayList<InvoiceLine> lines = selectedInv.getInvoiceLines();
            LineTableModel lineTable = new LineTableModel(lines);
            frame.setLinesArray(lines);
            frame.getlineTable().setModel(lineTable);
            frame.getCustNameLbl().setText(selectedInv.getCustomerName());
            frame.getInvNumLbl().setText("" + selectedInv.getInvoiceNumber());
            frame.getInvTotalIbl().setText("" + selectedInv.getInvoiceTotal());
            frame.getDateLbl().setText(MainFrame.dateFormat.format(selectedInv.getInvoiceDate()));
        }
    }
}
