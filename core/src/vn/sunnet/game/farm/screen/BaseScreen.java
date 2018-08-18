package vn.sunnet.game.farm.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import vn.sunnet.game.farm.nature.F;

public class BaseScreen implements Screen {
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;
    protected Stage stage;

    public BaseScreen(){

        camera = new OrthographicCamera(F.viewportWidth, F.viewportHeight);
        camera.position.set(F.viewportWidth/2, F.viewportHeight/2, 0);
        viewport = new StretchViewport(F.viewportWidth, F.viewportHeight, camera);

        batch = new SpriteBatch();
        stage = new Stage();
        stage.setViewport(viewport);
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show() {

    }

    protected void draw(float delta){

    }

    protected  void update(float delta){

    }

    protected  void stageDraw(float delta){
        stage.draw();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw(delta);
        batch.end();
        stageDraw(delta);
        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
        viewport.setWorldSize(F.viewportWidth, F.viewportHeight);
        viewport.update(width, height, true);
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
