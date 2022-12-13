package controller;

import model.InvoiceHeader;
import model.InvoiceLine;
import model.InvoiceTableModel;
import model.LineTableModel;
import view.InvoiceDialog;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Actions implements ActionListener {
    private MainFrame frame;
    private InvoiceDialog invoiceDialog;
    public Actions (MainFrame frame){
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ActionCommand = e.getActionCommand();
        System.out.println("Action: "+ ActionCommand);

        switch (ActionCommand){
            case "Load file":
                loadFile();
                break;
            case "Save file":
            case "Save":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "createInvoiceOK":
                createInvoiceOK();
                break;
            case "createInvoiceCancel":
                createInvoiceCancel();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Cancel":
                cancel();
                break;
            default:
                break;
        }

    }
    String str1;
    String str2;
    String str3;
    String str4;
    private void loadFile(){
        JFileChooser fileChooser = new JFileChooser();
        try {

            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] arr = headerLine.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                    Date invoiceDate = MainFrame.dateFormat.parse(str2);
                    InvoiceHeader header = new InvoiceHeader(code, str3, invoiceDate);
                    invoiceHeaders.add(header);
                }
                frame.setInvoicesArray(invoiceHeaders);
                JOptionPane.showMessageDialog(frame, "Select Line File");
                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
                    for (String lineLine : lineLines) {
                        String[] arr = lineLine.split(",");
                        str1 = arr[0];    // invoice num (int)
                        str2 = arr[1];    // item name   (String)
                        str3 = arr[2];    // price       (double)
                        str4 = arr[3];    // count       (int)
                        int invCode = Integer.parseInt(str1);
                        double price = Double.parseDouble(str3);
                        int count = Integer.parseInt(str4);
                        InvoiceHeader inv = frame.getInvObject(invCode);
                        InvoiceLine line = new InvoiceLine(str2, price, count, inv);
                        inv.getInvoiceLines().add(line);
                    }
                }
                InvoiceTableModel headerTable = new InvoiceTableModel(invoiceHeaders);
                frame.setInvoiceTableModel(headerTable);
                frame.getheaderTable().setModel(headerTable);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile(){
        ArrayList<InvoiceHeader> invoicesArray = frame.getInvoicesArray();
        JFileChooser fcc = new JFileChooser();
        try {
            int result = fcc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fcc.getSelectedFile();
                FileWriter headercsv = new FileWriter(headerFile+".csv");
                String headers = "";
                for (InvoiceHeader invoice : invoicesArray) {
                    headers += invoice.getAsCSV();
                    headers += "\n";
                }

                headers = headers.substring(0, headers.length()-1);
                try
                { headercsv.write(headers);
                    headercsv.close();
                    JOptionPane.showMessageDialog(frame, "File Saved Successfully!");
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void createNewInvoice(){
        if(str1 == null){
            JOptionPane.showMessageDialog(frame, "Select file in correct format!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            invoiceDialog = new InvoiceDialog(frame);
            invoiceDialog.setVisible(true);
        }
    }
    private void createInvoiceCancel() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
    }
    private void createInvoiceOK() {
        invoiceDialog.setVisible(false);

        String custName = invoiceDialog.getCustNameField().getText();
        String str = invoiceDialog.getInvDateField().getText();
        Date d = new Date();
        try {
            d = MainFrame.dateFormat.parse(str);
        }
        catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Date isn't correct, resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

        int invNum = 0;
        for (InvoiceHeader inv : frame.getInvoicesArray()) {
            if (inv.getInvoiceNumber() > invNum) {
                invNum = inv.getInvoiceNumber();
            }
        }
        invNum++;
        InvoiceHeader newInv = new InvoiceHeader(invNum, custName, d);
        frame.getInvoicesArray().add(newInv);
        frame.getInvoiceTableModel().fireTableDataChanged();
        invoiceDialog.dispose();
        invoiceDialog = null;

    }
    private void deleteInvoice(){
        int selectedInvoiceIndex = frame.getheaderTable().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            frame.getInvoicesArray().remove(selectedInvoiceIndex);
            frame.getInvoiceTableModel().fireTableDataChanged();
            frame.getlineTable().setModel(new LineTableModel(null));
            frame.setLinesArray(null);
            frame.getCustNameLbl().setText("");
            frame.getInvNumLbl().setText("");
            frame.getInvTotalIbl().setText("");
            frame.getDateLbl().setText("");
        }
    }
    private void cancel(){
        frame.setVisible(false);
        frame.dispose();
        frame = null;
    }
}
