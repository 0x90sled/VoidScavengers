package com.mikefdorst.voidscavengers.controller;

import com.badlogic.gdx.physics.box2d.Body;

import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;

public class Player {
  public Body body;
  
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
}
