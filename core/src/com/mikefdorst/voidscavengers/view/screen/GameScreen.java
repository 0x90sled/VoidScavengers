package com.mikefdorst.voidscavengers.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mikefdorst.voidscavengers.VoidScavengers;
import com.mikefdorst.voidscavengers.builder.BodyBuilder;
import com.mikefdorst.voidscavengers.util.reference.Ref;
import com.mikefdorst.voidscavengers.view.shape.EquilateralTriangle;

import static com.badlogic.gdx.math.MathUtils.random;

public class GameScreen implements Screen {
  
  final VoidScavengers game;
  private OrthographicCamera camera;
  private World world;
  private Box2DDebugRenderer renderer;
  private Body body;
  private float world_scale;
  
  private Body[] triangles;
  
  public GameScreen(VoidScavengers game) {
    this.game = game;
    
    camera = new OrthographicCamera();
    world_scale = 1/5f;
    camera.setToOrtho(false, view_width(), view_height());
    world = new World(new Vector2(0, 0), true);
    renderer = new Box2DDebugRenderer();
    
    triangles = new Body[100];
    
    for (int i = 0; i < 100; i++) {
      triangles[i] = new BodyBuilder()
        .type(BodyDef.BodyType.DynamicBody)
        .shape(new EquilateralTriangle(5))
        .build(world);
      triangles[i].setTransform(random(Ref.window.width), random(Ref.window.height), random((float) (2 * Math.PI)));
    }
    
    body = new BodyBuilder()
      .type(BodyDef.BodyType.DynamicBody)
      .position(view_width()/2, view_height()/2)
      .build(world);
    
    /*
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
