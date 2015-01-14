package com.mikefdorst.voidscavengers.controller;

import com.badlogic.gdx.physics.box2d.Body;

import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;

public class Player {
  public Body body;
  private String last_recorded_heading;
  
  public void moveForward(float force) {
    body.applyForceToCenter(sin(body.getAngle()) * -force, cos(body.getAngle()) * force, true);
  }
  
  public void moveBackward(float force) {
    body.applyForceToCenter(sin(body.getAngle()) * force, cos(body.getAngle()) * -force, true);
  }
  
  public void turnRight(float force) {
    body.applyAngularImpulse(-force, true);
  }
  
  public void turnLeft(float force) {
    body.applyAngularImpulse(force, true);
  }
  
  public String getHeading() {
    final float root2over2 = (float) Math.sqrt(2)/2;
    
    if (!body.isAwake()) {
      return last_recorded_heading;
    }
    if (cos(body.getAngle()) > root2over2) {
      return last_recorded_heading = "N";
    }
    if (sin(body.getAngle()) > root2over2) {
      return last_recorded_heading = "W";
    }
    if (sin(body.getAngle()) < -root2over2) {
      return last_recorded_heading = "E";
    }
    return last_recorded_heading = "S";
  }
}
