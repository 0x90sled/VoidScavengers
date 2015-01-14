package com.mikefdorst.voidscavengers.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mikefdorst.voidscavengers.builder.BodyBuilder;
import com.mikefdorst.voidscavengers.controller.Player;
import com.mikefdorst.voidscavengers.game.VoidScavengers;
import com.mikefdorst.voidscavengers.util.debug.DebugTextView;
import com.mikefdorst.voidscavengers.util.reference.Ref;
import com.mikefdorst.voidscavengers.view.shape.EquilateralTriangle;

import static com.badlogic.gdx.math.MathUtils.random;

public class GameScreen implements Screen {
  
  final VoidScavengers game;
  private OrthographicCamera camera;
  private World world;
  private Box2DDebugRenderer renderer;
  private Player player;
  private float world_scale;
  private DebugTextView debugTextView;
  private float angle_last_frame;
  
  private Body[] triangles;
  
  public GameScreen(VoidScavengers game) {
    this.game = game;
    
    camera = new OrthographicCamera();
    world_scale = 1/5f;
    camera.setToOrtho(false, view_width(), view_height());
    world = new World(new Vector2(0, 0), true);
    renderer = new Box2DDebugRenderer();
    debugTextView = new DebugTextView(10);
    
    triangles = new Body[100];
    
    for (int i = 0; i < 100; i++) {
      triangles[i] = new BodyBuilder()
        .type(BodyDef.BodyType.DynamicBody)
        .shape(new EquilateralTriangle(5))
        .build(world);
      triangles[i].setTransform(random(Ref.window.width), random(Ref.window.height), random((float) (2 * Math.PI)));
    }

    player = new Player();
    player.body = new BodyBuilder()
      .type(BodyDef.BodyType.DynamicBody)
      .position(view_width()/2, view_height()/2)
      .density(1)
      .build(world);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
    renderer.render(world, camera.combined);
    world.step(1/60f, 6, 2);
    
    debugTextView.setLine(0, "player heading: " + player.getHeading());
    debugTextView.setLine(1, "player torque: " +
      Float.toString((player.body.getAngle() - angle_last_frame) * 30/(float)Math.PI));
    angle_last_frame = player.body.getAngle();
    debugTextView.setLine(2, "player position: " + player.body.getPosition().toString());
    debugTextView.draw();
    
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      player.moveForward(1000);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      player.moveBackward(1000);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      player.turnRight(100);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      player.turnLeft(100);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.EQUALS)) {
      world_scale = MathUtils.clamp(world_scale - 0.005f, 0.15f, 1);
      camera.setToOrtho(false, view_width(), view_height());
    }
    if (Gdx.input.isKeyPressed(Input.Keys.MINUS)) {
      world_scale = MathUtils.clamp(world_scale + 0.005f, 0.15f, 1);
      camera.setToOrtho(false, view_width(), view_height());
    }
  }

  private float view_height() {
    return Ref.window.height * world_scale;
  }

  private float view_width() {
    return Ref.window.width * world_scale;
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
    debugTextView.dispose();
    renderer.dispose();
    world.dispose();
    game.dispose();
  }
}
