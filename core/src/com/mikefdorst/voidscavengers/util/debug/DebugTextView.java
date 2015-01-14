package com.mikefdorst.voidscavengers.util.debug;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikefdorst.voidscavengers.view.text.DefaultFont;
import com.mikefdorst.voidscavengers.view.text.Font;

import java.util.ArrayList;
import java.util.List;

public class DebugTextView {
  private SpriteBatch batch;
  private Font font;
  
  private List<String> debugLines;
  
  public DebugTextView(int number_of_lines) {
    batch = new SpriteBatch();
    font = new DefaultFont();
    debugLines = new ArrayList<String>(number_of_lines);
    for (int i = 0; i < number_of_lines; i++) {
      debugLines.add("");
    }
  }
  
  public void setLine(int line, String text) {
    debugLines.set(line, text);
  }
  
  public void draw() {
    batch.begin();
    for (int i = 0; i < debugLines.size(); i++) {
      font.draw(batch, debugLines.get(i), 20, 430 - i * 20);
    }
    batch.end();
  }
  
  public void dispose() {
    batch.dispose();
    font.dispose();
  }
}
