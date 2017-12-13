package com.draw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.draw.game.screen.MainScreen;

import java.util.Random;

//TODO: clique long = drag & drop
// TODO tap = suppression avec bouton
public class DragAndDropActor extends Group {

    // Sprite
	private Actor dragActor;
    private Group dragGroup;
    private float touchDown_x;
    private float touchDown_y;
    private float dx;
    private float dy;
    private MainScreen playScreen;
    private Animation animation;
    private float deltatime;
    private TextureRegion currentFrame;
    private boolean playAnimation = true;
    private boolean isMoveable = false;
    private Animation idleAnimation;

    // Type d'objet (pour la logique)
    int type;

	public DragAndDropActor(final Actor actor, final MainScreen playScreen, int type) {
        this.playScreen=playScreen;
        this.type=type;
		dragActor = actor;
        debugRandomBuilding();
		dragActor.setOrigin( actor.getWidth()/2, actor.getHeight()/2 );
        deltatime = 0;

        // actor portent les animation
		dragActor.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //Change taille du shadow et on le rend visible
                playScreen.getShadowImg().setSize(dragActor.getWidth(),dragActor.getHeight());
                playScreen.getShadowImg().setVisible(true);

                dragActor.clearActions();
                currentFrame=null;
            	dragActor.addAction(Actions.parallel(
            			Actions.scaleTo(1.5f, 1.5f, 0.25f, Interpolation.fade),
            			Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.5f), 0.25f, Interpolation.fade) )
            			);
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                playScreen.getShadowImg().setVisible(false);
                // Retrecit acteur lors du drop
                dragActor.clearActions();
                dragActor.addAction(Actions.parallel(
            			Actions.scaleTo(1.0f, 1.0f, 0.25f, Interpolation.fade),
            			Actions.color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0.25f, Interpolation.fade)
            			)
                );
                dragGroup.addAction(Actions.moveTo(playScreen.getShadowImg().getX(),playScreen.getShadowImg().getY(),0.2f));
            }
		});

		dragGroup = new Group();
		dragActor.setWidth( actor.getWidth()/2 );
		dragActor.setHeight( actor.getHeight()/2 );
		dragGroup.addActor(dragActor );

        // groupe gere les positions
		dragGroup.addListener(new InputListener() {
			final float h = dragActor.getHeight() / 2;

			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            Gdx.app.log("down", String.valueOf(x)+"/"+String.valueOf(y));
            playScreen.getShadowImg().setVisible(true);
            touchDown_x = x;
           	touchDown_y = h - y;
               return true;
            }

            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Gdx.app.log("touchDragged", String.valueOf(x)+"/"+String.valueOf(y));
                dx = dragGroup.getX() - touchDown_x;
            	dy = dragGroup.getY() - h + touchDown_y;
                // Position shadow
               playScreen.getShadowImg().setPosition(x + dx, Constants.GROUND_HEIGHT);
                // Position objet
                dragGroup.setPosition(x + dx, y + dy);
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("up", String.valueOf(x)+"/"+String.valueOf(y));
                playScreen.getShadowImg().setVisible(false);
                // x & y = position du curseur relative a l'acteur
               dx = dragGroup.getX() - touchDown_x;
                addActorToStage();
            }
		});
		this.addActor(dragGroup);
    }

    public void addIdleAnimation( float animSpeed, Array<TextureRegion> frames, Animation.PlayMode playMode) {
        animation = new Animation(animSpeed, frames);
        animation.setPlayMode(playMode);
    }
    // Depose l'actor par dessus systematiquement
    public void addActorToStage() {
        this.remove();
        playScreen.getStage().addActor(this);
        Gdx.app.log("actorList", String.valueOf(playScreen.getStage().getActors().size));
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        currentFrame = (TextureRegion) animation.getKeyFrame(deltatime, true);
        batch.draw(currentFrame,dragGroup.getX(),dragGroup.getY(),dragActor.getWidth(),dragActor.getHeight());
    }

    @Override
    public void act(float deltaTime)
    {
        if (playAnimation) {
            super.act(deltaTime);
            deltatime += deltaTime;
        }
    }

	void dispose() {
		dragGroup.clear();
		dragActor = null;
		dragGroup = null;
	}

	public Group getDragGroup() {
		return dragGroup;
	}

	public void setDragGroup(Group dragGroup) {
		this.dragGroup = dragGroup;
	}

	public void debugRandomBuilding() {
        Random rand = new Random();
        int index = rand.nextInt(9-1) + 1;
        dragActor = new Image( new Texture(Gdx.files.internal("building/build"+index+".png")));
    }

    public Actor getDragActor() {
        return dragActor;
    }

    public void setDragActor(Actor dragActor) {
        this.dragActor = dragActor;
    }

    public float getTouchDown_x() {
        return touchDown_x;
    }

    public void setTouchDown_x(float touchDown_x) {
        this.touchDown_x = touchDown_x;
    }

    public float getTouchDown_y() {
        return touchDown_y;
    }

    public void setTouchDown_y(float touchDown_y) {
        this.touchDown_y = touchDown_y;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public MainScreen getPlayScreen() {
        return playScreen;
    }

    public void setPlayScreen(MainScreen playScreen) {
        this.playScreen = playScreen;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public float getDeltatime() {
        return deltatime;
    }

    public void setDeltatime(float deltatime) {
        this.deltatime = deltatime;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public boolean isPlayAnimation() {
        return playAnimation;
    }

    public void setPlayAnimation(boolean playAnimation) {
        this.playAnimation = playAnimation;
    }

    public boolean isMoveable() {
        return isMoveable;
    }

    public void setMoveable(boolean moveable) {
        isMoveable = moveable;
    }

    public Animation getIdleAnimation() {
        return idleAnimation;
    }

}
