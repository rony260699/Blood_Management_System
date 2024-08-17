package com.pr.myapplication;

public class Userinfo {
    String fulName, phoneNo, address, bloodgroup;

    public Userinfo(String fulName, String phoneNo, String address, String bloodgroup, String email_adrs) {
        this.fulName = fulName;
        this.phoneNo = phoneNo;
        this.address = address;
        this.bloodgroup = bloodgroup;
    }

    public Userinfo() {

    }

    public String getFulName() {
        return fulName;
    }

    public void setFulName(String fulName) {
        this.fulName = fulName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }
}
