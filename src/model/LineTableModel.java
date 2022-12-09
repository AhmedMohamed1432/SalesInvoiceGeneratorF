package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LineTableModel extends AbstractTableModel {
    private ArrayList<InvoiceLine> Lines;
    private String[] LineColumns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};
    public LineTableModel(ArrayList<InvoiceLine> Lines)
    {
        this.Lines= Lines;
    }

    public ArrayList<InvoiceLine> getLines()
    {
        return Lines;
    }
    @Override
    public int getRowCount() {
        return Lines.size();
    }

    @Override
    public int getColumnCount() {
        return LineColumns.length;
    }
    @Override
    public String getColumnName( int column)
    {
        return LineColumns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line= Lines.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return line.getItemHeader().getInvoiceNumber();
            case 1:
                return line.getItemName();
            case 2:
                return line.getItemPrice();
            case 3:
                return line.getItemCount();
            case 4:
                return line.getLineTotal();
            default:
                return "";
        }
    }
}
