package model;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomId;
    private double price;
    private RoomStatus roomStatus;
    private int NumberOfBedroom;
    private int NumberOfToilet;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public int getNumberOfBedroom() {
        return NumberOfBedroom;
    }

    public void setNumberOfBedroom(int numberOfBedroom) {
        NumberOfBedroom = numberOfBedroom;
    }

    public int getNumberOfToilet() {
        return NumberOfToilet;
    }

    public void setNumberOfToilet(int numberOfToilet) {
        NumberOfToilet = numberOfToilet;
    }

    public Room(int roomId, double price, RoomStatus roomStatus, int numberOfBedroom, int numberOfToilet) {
        this.roomId = roomId;
        this.price = price;
        this.roomStatus = roomStatus;
        NumberOfBedroom = numberOfBedroom;
        NumberOfToilet = numberOfToilet;
    }

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                ", NumberOfBedroom=" + NumberOfBedroom +
                ", NumberOfToilet=" + NumberOfToilet +
                '}'+"\n";
    }
}
