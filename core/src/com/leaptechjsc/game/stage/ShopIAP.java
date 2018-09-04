package com.leaptechjsc.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.leaptechjsc.game.happyfarm.Actor.MyButton;
import com.leaptechjsc.game.happyfarm.assets.Audio;
import com.leaptechjsc.game.happyfarm.assets.Data;
import com.leaptechjsc.game.happyfarm.assets.Language;
import com.leaptechjsc.game.happyfarm.nature.F;

public class ShopIAP {
        private Texture background;
        private Button btnclose;
        private Stage stage;
        private boolean gc = false;
        private BitmapFont fontTitle;
        private BitmapFont font;

        private MyButton[] arrayBtnIAP;

        public ShopIAP(Stage sta) {
            stage = new Stage();
            background = new Texture(Gdx.files.internal("data/menu/cai-dat-2.png"));

            fontTitle = new BitmapFont(Gdx.files.internal(F.strFontNormal));
            fontTitle.setColor(Color.RED);
            fontTitle.getData().setScale(1.75f);

            font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
            font.setColor(Color.BLACK);
            font.getData().setScale(1.2f);

            btnclose = createButton("data/texture/close.png");
            btnclose.setPosition(1090, 575);

            stage.setViewport(sta.getViewport());

            arrayBtnIAP = new MyButton[6];
            for(int i = 0; i < 6; i++){
                Texture textureTemp = new Texture(Gdx.files.internal("data/shopiap/chip0" + (i + 1) + ".png"));
                TextureRegion textureRegion = new TextureRegion(textureTemp);
                MyButton btnTemp = new MyButton(textureRegion) {
                    @Override
                    public void precessClicked() {

                    }
                };
            }

            stage.addActor(btnclose);
            Gdx.input.setInputProcessor(stage);
        }

        public Button createButton(String path) {

            Texture texture = new Texture(Gdx.files.internal(path));
            TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
            return new Button(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]),
                    new TextureRegionDrawable(region[1]));
        }

        public void render(SpriteBatch batch) {
            batch.begin();
            batch.draw(background, 133, 83);
            fontTitle.draw(batch, Language.General.NAP_XU.getStr(), 550, 600);
//            font.draw(batch, Language.General.MUSIC.getStr(), 220, 415);
//            font.draw(batch, Language.General.SOUND.getStr(), 580, 310);
            batch.end();
            stage.draw();
            updateButton();
        }

        public void updateButton() {
            if(btnclose.isChecked() || Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                btnclose.setChecked(false);
                gc = true;
            }
        }

        public boolean gc() {
            return gc;
        }
}
