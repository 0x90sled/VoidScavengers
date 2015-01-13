package com.mikefdorst.voidscavengers.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mikefdorst.voidscavengers.VoidScavengers;
import com.mikefdorst.voidscavengers.builder.BodyBuilder;
import com.mikefdorst.voidscavengers.util.reference.Ref;

public class GameScreen implements Screen {
  
  final VoidScavengers game;
  private OrthographicCamera camera;
  private World world;
  private Box2DDebugRenderer renderer;
  private Body body;
  private float world_scale;
  
  public GameScreen(VoidScavengers game) {
    this.game = game;
    
    camera = new OrthographicCamera();
    world_scale = 1/5f;
    camera.setToOrtho(false, view_width(), view_height());
    world = new World(new Vector2(0, 0), true);
    renderer = new Box2DDebugRenderer();
    
    body = new BodyBuilder()
      .type(BodyDef.BodyType.DynamicBody)
      .position(view_width()/2, view_height()/2)
      .build(world);
    
    /*
      TODO: Create an array of triangular bodies with random position and orientation and scale 5.
      TODO: Set rotation with Body#setTransform(posX,posY,rotation)
      TODO: Then implement user control - we'll need the randomly placed triangles for reference to see how fast we move.
     */
  }
  
  private float view_height() {
    return Ref.window.height * world_scale;
  }
  
  private float view_width() {
    return Ref.window.width * world_scale;
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
