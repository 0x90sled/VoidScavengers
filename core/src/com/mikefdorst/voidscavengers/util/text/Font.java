package com.mikefdorst.voidscavengers.util.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public abstract class Font {
  private final FreeTypeFontGenerator generator;
  private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
  private BitmapFont font;
  private boolean generated;

  protected Font(String path) {
    generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
    parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = 12;
  }
  
  public void setSize(int size) {
    parameter.size = size;
    generated = false;
  }
  
  public void draw(Batch batch, String string, float x, float y) {
    if (!generated) {
      font = generator.generateFont(parameter);
      generated = true;
    }
    font.draw(batch, string, x, y);
  }
}
