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
    private String roomNumber;
    //定义关联实体类Bedcost
    @OneToOne(targetEntity = Roomcost.class)
    //映射外键名字
    @JoinColumn(name = "rcId")
    private Roomcost roomcost;
    //定义关联实体类Elder
    @OneToOne(targetEntity = Elder.class)
    //映射外键名字
    @JoinColumn(name = "eId")
    private Elder elder;

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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
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
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (roomNumber != null ? !roomNumber.equals(that.roomNumber) : that.roomNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rId;
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        return result;
    }
}
