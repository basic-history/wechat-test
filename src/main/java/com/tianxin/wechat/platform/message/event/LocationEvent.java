package com.tianxin.wechat.platform.message.event;

import com.tianxin.wechat.platform.message.Event;
import com.tianxin.wechat.platform.message.Visitor;

/**
 * 地理位置事件
 *
 * @author snowway
 * @since 6/24/14 9:58
 */
public class LocationEvent extends AbstractEvent {

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 精度
     */
    private double precision;


    public LocationEvent() {
    }

    public LocationEvent(double latitude, double longitude, double precision) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }

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

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    @Override
    public String getEventType() {
        return Event.TYPE_LOCATION;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
