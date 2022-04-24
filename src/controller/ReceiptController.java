package controller;

import model.Receipt;
import service.receiptService.ReceiptServiceIMPL;

import java.util.Date;
import java.util.List;

public class ReceiptController {
    public List<Receipt> showList(){
        return new ReceiptServiceIMPL().findAll();
    }

    public Receipt findById(int id){
        return new ReceiptServiceIMPL().findById(id);
    }

    public void addReceipt(Receipt receipt){
        new ReceiptServiceIMPL().save(receipt);
    }

    public void setRoomStt(){
        new ReceiptServiceIMPL().setRoomStt();
    }

    public void deleteById(int id){
        new ReceiptServiceIMPL().deleteById(id);
    }

    public void editById(Receipt receipt){
        new ReceiptServiceIMPL().editById(receipt);
    }

    public double calculateIncone(Date date1, Date date2){
        return new ReceiptServiceIMPL().calculateIncome(date1, date2);
    }

}
