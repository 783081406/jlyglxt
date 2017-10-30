package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/10/29.
 * 房间信息
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Roominformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rId;
    private String floor;
    private int roomNumber;
    //定义关联实体类Bedcost
    @ManyToOne(targetEntity = Roomcost.class)
    //映射外键名字
    @JoinColumn(name = "rcId")
    private Roomcost roomcost;
    //定义关联实体类Elder
    @OneToOne(targetEntity = Elder.class)
    //映射外键名字
    @JoinColumn(name = "eId")
    private Elder elder;

    //无参构造函数
    public Roominformation() {
    }

    public Roominformation(String floor, int roomNumber) {
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public Roominformation(String floor, int roomNumber, Roomcost roomcost, Elder elder) {
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.roomcost = roomcost;
        this.elder = elder;
    }

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Roomcost getRoomcost() {
        return roomcost;
    }

    public void setRoomcost(Roomcost roomcost) {
        this.roomcost = roomcost;
    }

    public Elder getElder() {
        return elder;
    }

    public void setElder(Elder elder) {
        this.elder = elder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roominformation that = (Roominformation) o;

        if (rId != that.rId) return false;
        if (roomNumber != that.roomNumber) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (roomcost != null ? !roomcost.equals(that.roomcost) : that.roomcost != null) return false;
        return elder != null ? elder.equals(that.elder) : that.elder == null;
    }

    @Override
    public int hashCode() {
        int result = rId;
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + roomNumber;
        result = 31 * result + (roomcost != null ? roomcost.hashCode() : 0);
        result = 31 * result + (elder != null ? elder.hashCode() : 0);
        return result;
    }
}
