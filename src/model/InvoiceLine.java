package model;

public class InvoiceLine {
    // variables declaration
    private String ItemName;
    private double ItemPrice;
    private int ItemCount;
    private InvoiceHeader ItemHeader;
    public InvoiceLine(){}
    public InvoiceLine(String ItemName, double ItemPrice, int ItemCount,  InvoiceHeader ItemHeader )
    {
        this.ItemName= ItemName;
        this.ItemPrice= ItemPrice;
        this.ItemCount= ItemCount;
        this.ItemHeader= ItemHeader;
    }
    /// setters
    public void setItemHeader(InvoiceHeader ItemHeader)
    {
        this.ItemHeader= ItemHeader;
    }
    public void setItemName(String ItemName)
    {
        this.ItemName = ItemName;
    }
    public void setItemPrice(double ItemPrice)
    {
        this.ItemPrice = ItemPrice;
    }
    public void setItemCount(int ItemCount)
    {
        this.ItemCount = ItemCount;
    }
    // getters
    public InvoiceHeader getItemHeader() {
        return ItemHeader;
    }
    public String getItemName()
    {
        return ItemName;
    }
    public double getItemPrice()
    {
        return ItemPrice;
    }
    public  int getItemCount()
    {
        return ItemCount;
    }
    public double getLineTotal()
    {
        return ItemCount * ItemPrice;
    }

    @Override
    public String toString() {
        return "Line{" + "num=" + ItemHeader.getInvoiceNumber() + ", item=" + ItemName+ ", price=" + ItemPrice + ", count=" + ItemCount + '}';
    }

    public InvoiceHeader getInvoice() {
        return ItemHeader;
    }

    public String getAsCSV() {
        return ItemHeader.getInvoiceNumber() + "," + ItemName + "," + ItemPrice + "," + ItemCount;
    }
}
