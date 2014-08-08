package com.tianxin.wechat.platform.message.item;


import com.tianxin.wechat.platform.message.Content;
import com.tianxin.wechat.platform.message.Message;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 地理位置信息
 *
 * @author snowway
 * @since 6/17/14 13:11
 */
public class LocationItem implements Content {


    /**
     * 纬度
     */
    private double latitude;

    /**
     * 经度
     */
    private double longitude;


    /**
     * 地图缩放大小
     */
    private int scale;


    /**
     * 地理位置信息
     */
    private String label;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getMessageType() {
        return Message.TYPE_LOCATION;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
