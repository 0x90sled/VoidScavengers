package com.mikefdorst.voidscavengers.view.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EquilateralTriangle {
  
  private float scale;
  private Color[] color;
  
  public EquilateralTriangle(float scale, Color color) {
    setScale(scale);
    setColor(color);
  }
  
  public EquilateralTriangle(float scale, Color left, Color top, Color right) {
    setScale(scale);
    setColor(left, top, right);
  }
  
  public EquilateralTriangle setScale(float scale) {
    this.scale = scale;
    return this;
  }
  
  public EquilateralTriangle setColor(Color color) {
    setColor(color, color, color);
    return this;
  }
  
  public EquilateralTriangle setColor(Color left, Color top, Color right) {
    this.color = new Color[] {left, top, right};
    return this;
  }
  
  public EquilateralTriangle render(ShapeRenderer renderer) {
    renderer.triangle(
      (float) (scale * Math.sin(4*Math.PI/3)),
      (float) (scale * Math.cos(4*Math.PI/3)),
      (float) (scale * Math.sin(0*Math.PI/3)),
      (float) (scale * Math.cos(0*Math.PI/3)),
      (float) (scale * Math.sin(2*Math.PI/3)),
      (float) (scale * Math.cos(2*Math.PI/3)),
      color[0], color[1], color[2]);
    return this;
  }
}
