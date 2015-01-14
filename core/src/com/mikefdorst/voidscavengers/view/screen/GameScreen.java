package com.mikefdorst.voidscavengers.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mikefdorst.voidscavengers.VoidScavengers;
import com.mikefdorst.voidscavengers.builder.BodyBuilder;
import com.mikefdorst.voidscavengers.controller.Player;
import com.mikefdorst.voidscavengers.util.reference.Ref;
import com.mikefdorst.voidscavengers.view.shape.EquilateralTriangle;
import com.mikefdorst.voidscavengers.view.text.DefaultFont;
import com.mikefdorst.voidscavengers.view.text.Font;

import static com.badlogic.gdx.math.MathUtils.*;

public class GameScreen implements Screen {
  
  final VoidScavengers game;
  private OrthographicCamera camera;
  private World world;
  private Box2DDebugRenderer renderer;
  private Player player;
  private float world_scale;
  private Font font;
  private SpriteBatch batch;
  
  private Body[] triangles;
  
  public GameScreen(VoidScavengers game) {
    this.game = game;
    
    camera = new OrthographicCamera();
    world_scale = 1/5f;
    camera.setToOrtho(false, view_width(), view_height());
    world = new World(new Vector2(0, 0), true);
    renderer = new Box2DDebugRenderer();
    batch = new SpriteBatch();
    
    triangles = new Body[100];

    font = new DefaultFont();
    
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
    
    batch.begin();
    font.draw(batch, "body angle: " + Float.toString(player.body.getAngle() / (float) Math.PI) + "pi", 20, 430);
    font.draw(batch, "sin(body angle): " + Float.toString(sin(player.body.getAngle())), 20, 410);
    font.draw(batch, "cos(body angle): " + Float.toString(cos(player.body.getAngle())), 20, 390);
    font.draw(batch, "player heading: " + player.getHeading(), 20, 370);
    batch.end();
    
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
    font.dispose();
    batch.dispose();
    renderer.dispose();
    world.dispose();
    game.dispose();
  }
}
