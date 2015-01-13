package com.mikefdorst.voidscavengers.builder;

import com.badlogic.gdx.physics.box2d.*;
import com.mikefdorst.voidscavengers.view.shape.Shape;

public class BodyBuilder {
  private BodyDef bodyDef;
  private FixtureDef fixtureDef;
  
  public Body build(World world) {
    Body body = world.createBody(bodyDef);
    body.createFixture(fixtureDef);
    return body;
  }
  
  public BodyBuilder() {
    bodyDef = new BodyDef();
    fixtureDef = new FixtureDef();
  }
  
  public BodyBuilder type(BodyDef.BodyType type) {
    bodyDef.type = type;
    return this;
  }
  
  public BodyBuilder position(float x, float y) {
    bodyDef.position.set(x, y);
    return this;
  }
  
  public BodyBuilder shape(Shape shape) {
    PolygonShape poly = new PolygonShape();
    poly.set(shape.getVertices());
    fixtureDef.shape = poly;
    poly.dispose();
    return this;
  }
  
  public BodyBuilder density(float density) {
    fixtureDef.density = density;
    return this;
  }
  
  public BodyBuilder friction(float friction) {
    fixtureDef.friction = friction;
    return this;
  }
  
  public BodyBuilder restitution(float restitution) {
    fixtureDef.restitution = restitution;
    return this;
  }
}
