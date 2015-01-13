package com.mikefdorst.voidscavengers.view.shape;

public class EquilateralTriangle extends Shape {
  
  private float scale;
  
  public EquilateralTriangle(float scale) {
    this.scale = scale;
  }
  
  public float[] getVertices() {
    return new float[] {
      (float) (scale * Math.sin(4 * Math.PI / 3)),
      (float) (scale * Math.cos(4 * Math.PI / 3)),
      (float) (scale * Math.sin(0 * Math.PI / 3)),
      (float) (scale * Math.cos(0 * Math.PI / 3)),
      (float) (scale * Math.sin(2 * Math.PI / 3)),
      (float) (scale * Math.cos(2 * Math.PI / 3))
    };
  }
}
