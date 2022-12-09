package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {

    private int InvoiceNumber;
    private String CustomerName;
    private String InvoiceDate;
    private ArrayList<InvoiceLine> InvoiceLines;
    private java.text.DateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public InvoiceHeader(){}

    public InvoiceHeader(int InvoiceNumber, String CustomerName, String InvoiceDate)
    {
        this.InvoiceNumber= InvoiceNumber;
        this.CustomerName= CustomerName;
        this.InvoiceDate= InvoiceDate;
    }


    public int getInvoiceNumber()
    {
        return InvoiceNumber;
    }
    public void setInvoiceNumber(int InvoiceNumber)
    {
        this.InvoiceNumber= InvoiceNumber;
    }

    public String getCustomerName()
    {
        return CustomerName;
    }
    public void setCustomerName(String CustomerName)
    {
        this.CustomerName= CustomerName;
    }

    public String getInvoiceDate()
    {
        return InvoiceDate;
    }

    public void setInvoiceDate(String InvoiceDate)
    {
        this.InvoiceDate=InvoiceDate;
    }

    public ArrayList<InvoiceLine> getInvoiceLines()
    {
        if(InvoiceLines == null){
            InvoiceLines= new ArrayList<>();
        }
        return InvoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> InvoiceLines)
    {
        this.InvoiceLines= InvoiceLines;
    }
    public double getInvoiceTotal()
    {
        double InvoiceTotal = 0.0;
        for (int i = 0; i< getInvoiceLines().size(); i++)
        {
            InvoiceTotal += getInvoiceLines().get(i).getLineTotal();
        }
        return InvoiceTotal;
    }

    @Override
    public String toString() {
        return "Invoice{" + "num=" + InvoiceNumber + ", date=" + InvoiceDate + ", customer=" + CustomerName + '}';
    }

    public String getAsCSV() {
        return InvoiceNumber + "," + InvoiceDate + "," + CustomerName;
    }


}

