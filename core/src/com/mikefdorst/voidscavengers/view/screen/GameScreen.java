package com.mikefdorst.voidscavengers.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
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
  
  private Body[] triangles, boundingBox;
  
  public GameScreen(VoidScavengers game) {
    this.game = game;
    
    camera = new OrthographicCamera();
    world_scale = 1/5f;
    camera.setToOrtho(false, view_width(), view_height());
    world = new World(new Vector2(0, 0), true);
    renderer = new Box2DDebugRenderer();
    debugTextView = new DebugTextView(10);
    
    boundingBox = new Body[4];
    triangles = new Body[100];
    
    for (int i = 0; i < 100; i++) {
      triangles[i] = new BodyBuilder()
        .type(BodyDef.BodyType.DynamicBody)
        .restitution(0.5f)
        .build(world);
      triangles[i].setTransform(random(Ref.window.width), random(Ref.window.height), random((float) (2 * Math.PI)));
    }
    
    {
      PolygonShape box = new PolygonShape();
      for (int i = 0; i < 4; i++) {
        float width =  i % 2 == 0 ? Ref.window.width / 2  : 0;
        float height = i % 2 == 1 ? Ref.window.height / 2 : 0;
        float x = i == 3 ? Ref.window.width : width;
        float y = i == 2 ? Ref.window.height : height;
        box.setAsBox(width, height);
        boundingBox[0] = new BodyBuilder()
          .position(x, y)
          .shape(box)
          .build(world);
      }
    }
    
    player = new Player();
    player.body = new BodyBuilder()
      .type(BodyDef.BodyType.DynamicBody)
      .shape(new EquilateralTriangle(15))
      .position(view_width() / 2, view_height() / 2)
      .density(1)
      .restitution(0.5f)
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
      player.moveForward(25000);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      player.moveBackward(12500);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      player.turnRight(1000);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      player.turnLeft(1000);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.EQUALS)) {
      world_scale = MathUtils.clamp(world_scale - 0.005f, 0.15f, 1);
      camera.setToOrtho(false, view_width(), view_height());
    }
    if (Gdx.input.isKeyPressed(Input.Keys.MINUS)) {
      world_scale = MathUtils.clamp(world_scale + 0.005f, 0.15f, 1);
      camera.setToOrtho(false, view_width(), view_height());
    }
    camera.position.set(computeCameraPosition());
    camera.update();
  }

  private Vector3 computeCameraPosition() {
    Vector3 pos = new Vector3(player.body.getPosition(), 0);
    pos.x = MathUtils.clamp(pos.x, view_width() / 2, Ref.window.width - view_width() / 2);
    pos.y = MathUtils.clamp(pos.y, view_height() / 2, Ref.window.height - view_height() / 2);
    return pos;
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
