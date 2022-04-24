package model;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable {
    private int receiptId;
    private Customer customer;
    private User staff;
    private Room room;
    private Date checkIn;
    private Date checkOut;
    private double totalPrice;

    public Receipt() {
    }

    public Receipt(int receiptId, Customer customer, User staff, Room room, Date checkIn, Date checkOut, double totalPrice) {
        this.receiptId = receiptId;
        this.customer = customer;
        this.staff = staff;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptId=" + receiptId +
                ", customer=" + customer +
                ", staff=" + staff +
                ", room=" + room +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalPrice=" + totalPrice +
                '}'+"\n\n\n";
    }
}
