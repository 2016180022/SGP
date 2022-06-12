package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;


import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.BoxCollidable;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.Recyclable;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.view.GameView;

public class Scene {
    protected float frameTime, elapsedTime;

    public static Scene getInstance() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex < 0) return null;
        return sceneStack.get(lastIndex);
    }

    public static void clear() {
        sceneStack.clear();
    }

    protected ArrayList<ArrayList<GameObject>> layers;
    protected Paint collisionPaint;
    protected boolean showsBoxCollidables;

    protected static ArrayList<Scene> sceneStack = new ArrayList<>();

    public static void start(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            top.end();
            sceneStack.set(lastIndex, scene);
        }
        else {
            sceneStack.add(scene);
        }
        scene.start();
    }

    public static void push(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            top.pause();
        }
        sceneStack.add(scene);
        scene.start();
    }
    public static void popScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            top.end();
        }
        lastIndex--;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            top.resume();
        }
    }

    public void init() {
        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);

        elapsedTime = 0;
    }

    public boolean isTransparent() {return false;}

    public void start(){}
    public void pause(){}
    public void resume(){}
    public void end(){}

    protected void initLayers(int count) {
        layers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        elapsedTime += frameTime;
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.update(frameTime);
            }
        }
    }

    public void draw(Canvas canvas) {
        draw(canvas, sceneStack.size() - 1);
    }

    protected void draw(Canvas canvas, int index) {
        Scene scene = sceneStack.get(index);
        if (scene.isTransparent() && index > 0) {
            draw(canvas, index - 1);
        }
        ArrayList<ArrayList<GameObject>> layers = scene.layers;
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                gobj.draw(canvas);
            }
        }
        if (showsBoxCollidables) {
            drawBoxCollidables(canvas);
        }
    }

    public void drawBoxCollidables(Canvas canvas) {
        for (ArrayList<GameObject> gameObjects : layers) {
            for (GameObject gobj : gameObjects) {
                if (gobj instanceof BoxCollidable) {
                    RectF box = ((BoxCollidable) gobj).getBoundingRect();
                    canvas.drawRect(box, collisionPaint);
                }
            }
        }
    }

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjects = layers.get(layerIndex);
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> gameObjects : layers) {
                    boolean removed = gameObjects.remove(gameObject);
                    if (!removed) continue;
                    if (gameObject instanceof Recyclable) {
                        RecycleBin.add((Recyclable) gameObject);
                    }
                    break;
                }
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }

    public int objectCount() {
        if (layers == null) return 0;
        int count = 0;
        for (ArrayList<GameObject> gameObjects : layers) {
            count += gameObjects.size();
        }
        return count;
    }

    protected int getTouchLayerIndex() {
        return -1;
    }

    public boolean handleBackKey() {
        return false;
    }
}
