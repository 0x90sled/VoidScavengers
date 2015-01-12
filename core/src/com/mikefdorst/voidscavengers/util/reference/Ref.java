package com.mikefdorst.voidscavengers.util.reference;

public class Ref {
  public static class application {
    public static final String name = "Void Scavengers";
  }
  public static class window {
    public static final int width = 800;
    public static final int height = 450;
    public static final double aspect_ratio = (double) width / height;
  }
  public static class font {
    public static class path_to {
      public static String open_sans(String typeface) {
        return font_path + "open-sans/OpenSans-" + typeface + ".ttf";
      }
    }
    private static final String font_path = "font/";
  }
}
