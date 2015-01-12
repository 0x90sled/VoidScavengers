package com.mikefdorst.voidscavengers.util.application;

public class MenuBar {
  public static void setApplicationName(String name) {
    System.setProperty("com.apple.mrj.application.apple.menu.about.name", name);
  }
}
