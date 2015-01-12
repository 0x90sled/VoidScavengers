package com.mikefdorst.voidscavengers.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mikefdorst.voidscavengers.VoidScavengers;
import com.mikefdorst.voidscavengers.util.reference.Ref;
import com.mikefdorst.voidscavengers.view.text.DefaultFont;
import com.mikefdorst.voidscavengers.view.text.Font;

public class MainMenuScreen implements Screen {
  
  final VoidScavengers game;
  private OrthographicCamera camera;
  private Font titleFont, infoFont;
  

  public MainMenuScreen(VoidScavengers game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Ref.window.width, Ref.window.height);
    
    titleFont = new DefaultFont(36);
    infoFont = new DefaultFont();
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
    
    camera.update();
    game.batch.setProjectionMatrix(camera.combined);
    
    game.batch.begin();
    titleFont.draw(game.batch, "Void Scavengers", 25, 425);
    infoFont.draw(game.batch, "Tap anywhere to begin", 25, 225);
    game.batch.end();

    if (Gdx.input.isTouched()) {
      game.setScreen(new GameScreen(game));
      dispose();
    }
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
    titleFont.dispose();
    infoFont.dispose();
  }
}
