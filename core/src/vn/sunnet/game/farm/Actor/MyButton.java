package vn.sunnet.game.farm.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.nature.F;
//import com.javavip.game.controller.ResourceManager;
//import com.javavip.game.edit.Actions;
//import com.javavip.game.edit.ClickListener;
//import com.javavip.game.edit.Group;
//import com.javavip.game.edit.Image;
//import com.javavip.game.edit.InputEvent;
//import com.javavip.game.edit.Label;
//import com.javavip.game.edit.Label.LabelStyle;
//import com.javavip.game.edit.ScaleToAction;
//import com.javavip.game.info.SoundManager;

public abstract class MyButton extends Group {

	MyButtonListener clickedListener;
	private Image image, img_disable;
	private Label label;
	private String name;// ten button
	private Color fontColor, colorImage;
	public int id = -1;
	public int type = -1;

	public MyButton(TextureRegion region) {
		name = "";
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			this.addActor(image);
		}
		image.addListener(clickedListener);
	}

	public MyButton(TextureRegion region, String name, float padding, boolean isEmotion) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth() + padding, image.getHeight() + padding);
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			this.addActor(image);
		}
		image.setPosition(getWidth() / 2 - image.getWidth() / 2, getHeight() / 2 - image.getHeight() / 2);
		this.addListener(clickedListener);
	}

	public MyButton(TextureRegionDrawable region) {
		name = "";
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			this.addActor(image);
		}
		image.addListener(clickedListener);
	}

	public MyButton(String region) {
//		name = "";
//		clickedListener = new MyButtonListener();
//		{
//			image = new Image(ResourceManager.shared().atlasMain.findRegion(region));
//			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
//		}
//		{
//			this.setSize(image.getWidth(), image.getHeight());
//			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
//		}
//		{
//			this.addActor(image);
//		}
//		image.addListener(clickedListener);
	}

	public MyButton(String region, boolean isPlayNow) {
//		name = "";
//		clickedListener = new MyButtonListener();
//		{
//			image = new Image(ResourceManager.shared().atlasMain.findRegion(region));
//			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
//		}
//		{
//			this.setSize(image.getWidth(), image.getHeight());
//			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
//		}
//		{
//			this.addActor(image);
//		}
//		image.addListener(clickedListener);
//		Image tay = new Image("tayDealer");
//		addActor(tay);
//		tay.setPosition(getWidth() - 40, 175);
//		tay.setOrigin(35, 6);
//		tay.addAction(Actions
//				.forever(Actions.sequence(Actions.rotateBy(-10, 0.8f), Actions.rotateBy(10, 0.8f), Actions.delay(3))));
//
//		Image mat = new Image("matDealer");
//		addActor(mat);
//		mat.setPosition(88, 315);
//		mat.addAction(
//				Actions.forever(Actions.sequence(Actions.alpha(1, 0.15f), Actions.alpha(0, 0.15f), Actions.delay(2f))));

	}

	public MyButton(TextureRegion region, String name) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);

			label = new Label(name, lblStyle);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(TextureRegion region, String name, float posXTitle, float posYTitle) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);

			label = new Label(name, lblStyle);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setPosition(posXTitle, posYTitle);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(TextureRegion region, String name, String fontPath) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			BitmapFont font;
			if(fontPath.compareTo("") == 0){
				font = new BitmapFont();
			}else{
				font = new BitmapFont(Gdx.files.internal(fontPath));
			}
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);

			label = new Label(name, lblStyle);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(TextureRegion region, String name, String fontPath, float posXTitle, float posYTitle) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			BitmapFont font;// = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			if(fontPath.compareTo("") == 0){
				font = new BitmapFont();
			}else{
				font = new BitmapFont(Gdx.files.internal(fontPath));
			}
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);

			label = new Label(name, lblStyle);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setPosition(posXTitle, posYTitle);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(TextureRegionDrawable region, String name, float y) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{

			BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
			label = new Label(name, lblStyle);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setY(getHeight() / 2 - label.getPrefHeight() / 2 - y);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(TextureRegion region, String name, float paddingtop) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{

			BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
			label = new Label(name, lblStyle);
//			label = new Label(name, ResourceManager.shared().lblStyleTahoma14);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setY(getHeight() / 2 - label.getPrefHeight() / 2 - paddingtop);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(TextureRegion region, String name, Label.LabelStyle style) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			label = new Label(name, style);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(String region, String name) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(Assets.manager.get(region, TextureRegion.class));
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
//			label = new Label(name, ResourceManager.shared().lblStyleTahoma14);

			BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
			label = new Label(name, lblStyle);

			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setY(label.getY() + 1);
		}
		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public MyButton(String region, String name, boolean notifi) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(Assets.manager.get(region, TextureRegion.class));
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
//			label = new Label(name, ResourceManager.shared().lblStyleTahoma14);

			BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
			label = new Label(name, lblStyle);

			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setY(label.getY() + 1);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
//		status = new Image(ResourceManager.shared().iconRed);
//		addActor(status);
//		status.setPosition(0, getHeight() - status.getHeight());
//		status.setVisible(false);
	}

	public MyButton(TextureRegion region, String name, Label.LabelStyle labelStyle, float scalefont, float y) {
		this.name = name;
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}
		{
			label = new Label(name, labelStyle);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setY(y);
			label.setFontScale(scalefont);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		image.addListener(clickedListener);
	}

	public void setText(final String text) {
		Gdx.app.postRunnable(new Runnable() {

			@Override
			public void run() {
				label.setText(text);
			}
		});
	}

	public String getText() {
		return label.getText().toString();
	}

	public MyButton(String text, TextureRegion region, BitmapFont font, Color fontColor) {
		name = "";
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}

		{
			Label.LabelStyle style = new Label.LabelStyle(font, fontColor);
			this.fontColor = fontColor;
			label = new Label(text, style);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setY(label.getY() + 1);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		this.addListener(clickedListener);
	}

	public MyButton(String text, TextureRegion region, BitmapFont font, Color fontColor, float y) {
		name = "";
		clickedListener = new MyButtonListener();
		{
			image = new Image(region);
			image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		}
		{
			this.setSize(image.getWidth(), image.getHeight());
			this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}

		{
			Label.LabelStyle style = new Label.LabelStyle(font, fontColor);
			this.fontColor = fontColor;
			label = new Label(text, style);
			label.setSize(this.getWidth(), this.getHeight());
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.setTouchable(Touchable.disabled);
			label.setY(y);

		}

		{
			this.addActor(image);
			if (label != null) {
				this.addActor(label);
			}
		}
		this.addListener(clickedListener);
	}

	public MyButton(String text, TextureRegion region) {
		clickedListener = new MyButtonListener();
		image = new Image(region);
		image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		this.setSize(image.getWidth(), image.getHeight());
		this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);

//		label = new Label(text, ResourceManager.shared().lblStyleTahoma14);
			BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
			Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
			label = new Label(name, lblStyle);
		label.setSize(image.getWidth(), this.getHeight());
		label.setAlignment(Align.center);
		label.setWrap(true);
		label.setTouchable(Touchable.disabled);
		label.setY(label.getY());
		this.addActor(image);
		this.addActor(label);
		this.addListener(clickedListener);
	}

	public MyButton(String text, TextureRegion region, float padLeft, float padTop) {
		clickedListener = new MyButtonListener();
		image = new Image(region);
		image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		this.setSize(image.getWidth(), image.getHeight());
		this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);

//		label = new Label(text, ResourceManager.shared().lblStyleTahoma14);

        BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
        Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
        label = new Label(name, lblStyle);
		label.setSize(image.getWidth(), this.getHeight());
		label.setPosition(image.getX() + padLeft, label.getY());
		label.setAlignment(Align.center);
		this.addActor(image);
		this.addActor(label);
		this.addListener(clickedListener);
	}

	public MyButton(String text, TextureRegion region, float height) {
		clickedListener = new MyButtonListener();
		image = new Image(region);
		image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		this.setSize(image.getWidth(), image.getHeight());
		this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);

//		label = new Label(text, ResourceManager.shared().lblStyleTahoma14);

        BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
        Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
        label = new Label(name, lblStyle);
		label.setSize(this.getWidth(), this.getHeight());
		label.setAlignment(Align.center);
		// label.setWrap(true);
		label.setTouchable(Touchable.disabled);
		label.setY(label.getY() + height);
		this.addActor(image);
		if (label != null) {
			this.addActor(label);
		}
		this.addListener(clickedListener);
	}

	public MyButton(String text, TextureRegion region, Color color) {
		clickedListener = new MyButtonListener();
		image = new Image(region);
		image.setOrigin(image.getWidth() / 2, image.getWidth() / 2);
		this.setSize(image.getWidth(), image.getHeight());
		this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);

//		label = new Label(text, ResourceManager.shared().lblStyleTahoma14);

        BitmapFont font = new BitmapFont(Gdx.files.internal(F.strFontNormal));
        Label.LabelStyle lblStyle = new Label.LabelStyle(font, Color.WHITE);
        label = new Label(name, lblStyle);
		label.setSize(this.getWidth(), this.getHeight());
		label.setAlignment(Align.center);
		label.setWrap(true);
		label.setTouchable(Touchable.disabled);
		label.setColor(color);
		this.fontColor = color;
		label.setY(label.getY() + 2);
		this.addActor(image);
		if (label != null) {
			this.addActor(label);
		}
		this.addListener(clickedListener);
	}

	class MyButtonListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			super.clicked(event, x, y);
//			SoundManager.shared().play_click();
			precessClicked();
		}

		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			if (!isDisable) {
				MyButton.this.addAction(Actions.alpha(0.5f));
			}

			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			if (!isDisable) {
				MyButton.this.addAction(Actions.alpha(1));
			}

			super.touchUp(event, x, y, pointer, button);
		}
	}

	public abstract void precessClicked();

	public void addActionClicked() {
		ScaleToAction action = Actions.scaleTo(1f, 1f, 0.2f);
		action.setInterpolation(Interpolation.pow3Out);
		this.addAction(Actions.sequence(Actions.scaleTo(1.1f, 1.1f, 0.2f), action));
	}

	/**
	 *
	 * @param drawable
	 */
	public void setButtonState(TextureRegionDrawable drawable) {
		image.setDrawable(drawable);
	}

	private boolean isDisable = false;

	public void setImgDisable(TextureRegionDrawable drawable) {
		if (img_disable != null) {
			img_disable.setDrawable(drawable);
		} else {
			img_disable = new Image(drawable);
			this.addActor(img_disable);
			img_disable.toBack();
		}
		img_disable.setVisible(false);

	}

	public void setDisabled(boolean disable) {
		isDisable = disable;
		if (disable) {
			if (label != null)
				label.setColor(Color.valueOf("2c0404"));
			if (img_disable != null) {
				image.setVisible(false);
				img_disable.setVisible(true);
			} else {
				image.setVisible(true);
			}

		} else {
			if (label != null) {
				if (fontColor != null) {
					label.setColor(fontColor);
				} else {
					label.setColor(Color.WHITE);
				}
			}
			if (img_disable != null) {
				image.setVisible(true);
				img_disable.setVisible(false);
			} else {
				image.setVisible(true);
			}
		}
	}

	public void setDisabled(boolean disable, boolean setColor) {
		isDisable = disable;
		if (disable) {
			if (label != null && setColor) {
				label.setColor(Color.WHITE);
				// label.setColor(Color.valueOf("961919"));
				// label.setColor(Color.valueOf("f5f2f2"));
			}
			if (img_disable != null) {
				image.setVisible(false);
				img_disable.setVisible(true);
			} else {
				image.setVisible(true);
			}

		} else {
			if (label != null) {
				if (fontColor != null) {
					label.setColor(fontColor);
				} else {
					label.setColor(Color.WHITE);
				}
			}
			if (img_disable != null) {
				image.setVisible(true);
				img_disable.setVisible(false);
			} else {
				image.setVisible(true);
			}
		}

	}

	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		image.setWidth(width);
		if (label != null) {
			label.setWidth(width);
		}
	}

	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		image.setHeight(height);
		if (label != null) {
			label.setHeight(height);
		}
	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		image.setWidth(width);
		image.setHeight(height);
		if (img_disable != null) {
			img_disable.setWidth(width);
			img_disable.setHeight(height);
		}
		if (label != null) {
			label.setWidth(width);
			label.setHeight(height);
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public void setColor(Color color){
		this.colorImage = color;
		if(image != null){
			image.setColor(color);
		}
	}

	@Override
	public void setColor(float r, float g, float b, float a){
		this.colorImage = new Color(r, g, b, a);
		if(image != null){
			image.setColor(r, g, b, a);
		}
	}

	public void setColorText(Color color) {
		fontColor = color;
		if (label != null)
			label.setColor(color);
	}

	public void setColorText(float r, float g, float b, float a) {
		fontColor = new Color(r, g, b, a);
		if (label != null)
			label.setColor(r, g, b, a);
	}

	public void setAlign(int alignment) {
		if (label != null)
			label.setAlignment(alignment);
	}

	public void rotateImage(float x) {
		if (image != null) {
			image.setRotation(x);
		}
	}

	public void setImage(TextureRegion region) {
		image.setDrawable(new TextureRegionDrawable(region));
	}

	public Image getImage() {
		return image;
	}

	public void setScaleFont(float scale) {
		if (label != null) {
			label.setFontScale(scale);
		}
	}

	public Label getLabel() {
		return label;
	}
}
