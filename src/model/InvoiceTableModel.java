package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoiceTableModel extends AbstractTableModel {
    private ArrayList<InvoiceHeader> Invoices;
    private  String[] InvoiceColumns= {"No.", "Date", "Customer", "Total"};

    public InvoiceTableModel(ArrayList<InvoiceHeader> Invoices)
    {
        this.Invoices= Invoices;
    }
    @Override
    public int getRowCount()
    {
        return Invoices.size();
    }

    @Override
    public int getColumnCount()
    {
        return InvoiceColumns.length;
    }
    @Override
    public String getColumnName(int InvoiceColumn)
    {
        return InvoiceColumns[InvoiceColumn];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoice = Invoices.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return invoice.getInvoiceNumber();
            case 1:
                return invoice.getInvoiceDate();
            case 2:
                return invoice.getCustomerName();
            case 3:
                return invoice.getInvoiceTotal();
            default:
                return "";
        }

    }
}
