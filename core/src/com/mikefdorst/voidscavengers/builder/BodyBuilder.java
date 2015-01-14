package com.mikefdorst.voidscavengers.builder;

import com.badlogic.gdx.physics.box2d.*;
import com.mikefdorst.voidscavengers.view.shape.EquilateralTriangle;
import com.mikefdorst.voidscavengers.view.shape.Shape;

public class BodyBuilder {
  private BodyDef bodyDef;
  private FixtureDef fixtureDef;
  private PolygonShape poly;
  
  public Body build(World world) {
    if (poly == null) {
      poly = new PolygonShape();
      poly.set(new EquilateralTriangle(10).getVertices());
      fixtureDef.shape = poly;
    }
    Body body = world.createBody(bodyDef);
    body.createFixture(fixtureDef);
    poly.dispose();
    poly = null;
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
    poly = new PolygonShape();
    poly.set(shape.getVertices());
    fixtureDef.shape = poly;
    return this;
  }
  
  public BodyBuilder shape(PolygonShape shape) {
    poly = shape;
    fixtureDef.shape = poly;
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
