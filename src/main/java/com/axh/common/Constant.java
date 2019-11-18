package com.axh.common;

public class Constant {
  public  static final String  CURRENT_USER="current_user";
  public  static final String  EMAIL="email";
  public  static final String  USERNAME="username";
  public  interface  Role{
    int ORDINARY_USER=0;
    int ADMIN_USER=1;
  }
  public  interface  ProductStatus{
    int NOW=1;
    int LAST=2;
    int DELETE=3;
  }

}
