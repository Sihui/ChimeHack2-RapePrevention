package hackmate.rapeprevention.Widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import hackmate.rapeprevention.Models.Observable;

public class NestTimePicker extends View {
  float minRadius = 0.8f;
  float maxRadius = 1.0f;
  float ignoreAngel = 30f;
  float centerX;
  float centerY;
  int maxSelector = 120;
  int unSelectedColor;
  int selectedColor;
  int barColor;
  int backgroundColor;
  boolean touchEventCaptured = false;
  Paint paint;

  public Observable<Integer> selectedMinute = Observable.from(60);

  public NestTimePicker(Context context, AttributeSet attrs) {
    super(context, attrs);
    int color = 0xFF;
    backgroundColor = Color.argb(64, color, color, color);
    unSelectedColor = Color.argb(100, color, color, color);
    selectedColor = Color.argb(172, color, color, color);
    barColor = Color.argb(255, color, color, color);
    paint = new Paint();
    paint.setFlags(Paint.ANTI_ALIAS_FLAG);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    centerX = 1.0f * getWidth() / 2;
    centerY = 1.0f * getHeight() / 2;
    paint.setColor(backgroundColor);
    canvas.drawCircle(centerX, centerY, centerX, paint);
    float radius = centerX * 0.9f;
    float[] angels = new float[maxSelector];
    for (int i = 0; i != maxSelector; ++i) {
      angels[i] =
          (float) ((1.0 * (360 - ignoreAngel * 2) / maxSelector * i + ignoreAngel) * Math.PI / 180);
    }
    paint.setStrokeWidth(5);
    minRadius = radius * 0.8f;
    maxRadius = radius;
    int selected = selectedMinute.get();

    paint.setColor(selectedColor);
    for (int i = 0; i != selected; ++i) {
      drawLineOnRing(canvas, minRadius, maxRadius, angels[i], paint);
    }

    paint.setStrokeWidth(4);
    paint.setColor(unSelectedColor);
    for (int i = selected; i != maxSelector; ++i) {
      drawLineOnRing(canvas, minRadius, maxRadius, angels[i], paint);
    }

    paint.setColor(barColor);
    paint.setStrokeWidth(10);

    drawLineOnRing(canvas, minRadius, maxRadius, angels[0], paint);
    drawLineOnRing(canvas, minRadius * 0.95f, maxRadius, angels[selected], paint);

    paint.setTextSize(160);
    paint.setTextAlign(Paint.Align.CENTER);
    canvas.drawText(Integer.toString(selected), centerX, centerY + 60, paint);
    paint.setTextSize(70);
    canvas.drawText("mins", centerX, getHeight() - 35, paint);
  }

  void drawLineOnRing(Canvas canvas, float minRadius, float maxRadius, float angel, Paint paint) {
    canvas.drawLine(centerX - minRadius * (float) Math.sin(angel),
        centerY + minRadius * (float) Math.cos(angel),
        centerX - maxRadius * (float) Math.sin(angel),
        centerY + maxRadius * (float) Math.cos(angel), paint);
  }

  float getAngel(float x, float y) {
    double distance = getDistance(x, y);
    float angel = (float) Math.asin((centerX - x) / distance);
    if (x < centerX && y > centerY) {
      return angel;
    }
    if (x > centerX && y > centerY) {
      return (float) (Math.PI * 2 + angel);
    } else {
      return (float) (Math.PI - angel);
    }
  }

  int getSelectFromAngel(float angel) {
    double angelDiff = 1.0 * (360 - 2 * ignoreAngel) / 360 * 2 * Math.PI / maxSelector;
    return (int) ((angel - 1.0 * ignoreAngel / 180 * Math.PI) / angelDiff);
  }

  private float getDistance(float x, float y) {
    return (float) Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY));
  }

  public void setSelected(int selected) {
    if (selected < 0 || selected >= maxSelector) {
      return;
    }

    if (this.selectedMinute.get() == selected) {
      return;
    }

    this.selectedMinute.set(selected);
    invalidate();
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    float distance = getDistance(event.getX(), event.getY());
    float angel = getAngel(event.getX(), event.getY());

    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      if (distance < maxRadius * 1.1 && distance > minRadius * 0.9) {
        int select = getSelectFromAngel(angel);
        if (select >= 0 && select <= maxSelector) {
          touchEventCaptured = true;
          setSelected(Math.max(1, select));
          return true;
        }
      }
    }

    if (event.getAction() == MotionEvent.ACTION_UP) {
      touchEventCaptured = false;
      return true;
    }
    if (event.getAction() == MotionEvent.ACTION_MOVE && touchEventCaptured) {
      int select = getSelectFromAngel(angel);
      Log.d("Clock", "Selected " + Integer.toString(select));
      setSelected(Math.max(1, select));
      return true;
    }
    return false;
  }
}
