package com.mikefdorst.voidscavengers.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mikefdorst.voidscavengers.VoidScavengers;
import com.mikefdorst.voidscavengers.util.reference.Ref;
import com.mikefdorst.voidscavengers.view.shape.EquilateralTriangle;

public class GameScreen implements Screen {
  
  final VoidScavengers game;
  private OrthographicCamera camera;
  private World world;
  private Box2DDebugRenderer renderer;
  private Body body;
  
  public GameScreen(VoidScavengers game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Ref.window.width/5, Ref.window.height/5);
    world = new World(new Vector2(0, -100), true);
    renderer = new Box2DDebugRenderer();
    
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(50, 50);
    body = world.createBody(bodyDef);
    PolygonShape triangle = new PolygonShape();
    EquilateralTriangle triangleShape = new EquilateralTriangle(10);
    triangle.set(triangleShape.getVertices());
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = triangle;
    fixtureDef.density = 1f;
    fixtureDef.friction = 0.4f;
    fixtureDef.restitution = 0.6f;
    Fixture fixture = body.createFixture(fixtureDef);
    triangle.dispose();
    
    bodyDef = new BodyDef();
    bodyDef.position.set(0, 10);
    Body groundBody = world.createBody(bodyDef);
    PolygonShape groundBox = new PolygonShape();
    groundBox.setAsBox(camera.viewportWidth, 10);
    groundBody.createFixture(groundBox, 0);
    groundBox.dispose();
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
    renderer.render(world, camera.combined);
    world.step(1/60f, 6, 2);
  }

  @Override
  public void show() {

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    
  }
}
