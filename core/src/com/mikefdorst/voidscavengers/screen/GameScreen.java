package com.mikefdorst.voidscavengers.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mikefdorst.voidscavengers.VoidScavengers;
import com.mikefdorst.voidscavengers.model.EquilateralTriangle;
import com.mikefdorst.voidscavengers.util.reference.Ref;

public class GameScreen implements Screen {
  
  final VoidScavengers game;
  private OrthographicCamera camera;
  private ShapeRenderer renderer;
  private EquilateralTriangle triangle;
  
  public GameScreen(VoidScavengers game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Ref.window.width, Ref.window.height);
    renderer = new ShapeRenderer();
    triangle = new EquilateralTriangle(50, Color.RED);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
    renderer.setProjectionMatrix(camera.combined);
    
    renderer.begin(ShapeRenderer.ShapeType.Line);
    renderer.identity();
    renderer.translate(Ref.window.width / 2, Ref.window.height / 2, 0);
    triangle.render(renderer);
    renderer.end();
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
    renderer.dispose();
  }
}
