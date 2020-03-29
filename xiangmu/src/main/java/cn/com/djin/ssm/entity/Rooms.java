package cn.com.djin.ssm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Rooms {
    private Integer id;

    private String roomPic;

    private String roomNum;

    private String roomStatus;

    private Integer roomTypeId;

    //房间是否显示:1表示显示0不显示
    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    private RoomType roomType;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomPic() {
        return roomPic;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic == null ? null : roomPic.trim();
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus == null ? null : roomStatus.trim();
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}