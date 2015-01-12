package com.mikefdorst.voidscavengers.view.text;

import com.mikefdorst.voidscavengers.util.reference.Ref;

public class DefaultFont extends Font {
  public DefaultFont() {
    super(Ref.font.path_to.open_sans("Regular"));
  }
  public DefaultFont(int size) {
    this();
    parameter.size = size;
  }
}
