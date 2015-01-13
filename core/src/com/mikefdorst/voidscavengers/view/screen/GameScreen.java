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
  private Body player;
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
    
    player = new BodyBuilder()
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
    font.draw(batch, "player angle: " + Float.toString(player.getAngle() / (float) Math.PI) + "pi", 20, 430);
    font.draw(batch, "sin(player angle): " + Float.toString(sin(player.getAngle())), 20, 410);
    font.draw(batch, "cos(player angle): " + Float.toString(cos(player.getAngle())), 20, 390);
    batch.end();
    
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      player.applyForceToCenter(sin(player.getAngle())*-1000, cos(player.getAngle())*1000, true);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      player.applyForceToCenter(sin(player.getAngle()) * 1000, cos(player.getAngle()) * -1000, true);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      player.applyAngularImpulse(-100, true);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      player.applyAngularImpulse(100, true);
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
    
  }
}
