package com.draw.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.draw.game.listener.GameObjectGestureListener;
import com.draw.game.screen.MainScreen;

import java.util.Random;

//TODO: clique long = drag & drop
// TODO tap = suppression avec bouton
public class MainScreenActor extends Group {

    // Sprite
	protected Actor dragActor;
    protected Group dragGroup;
    private float touchDown_x;
    private float touchDown_y;
    private float dx;
    private float dy;
    protected MainScreen mainScreen;
    protected boolean playAnimation;
    private ImageButton deleteButton;

    // Type d'objet (pour la logique)
    int type;

	public MainScreenActor(final Actor actor, final MainScreen mainScreen, int type) {
        this.mainScreen=mainScreen;
        this.type=type;
		dragActor = actor;
        playAnimation=true;


        if (actor instanceof Image) {
            debugRandomBuilding();
        }
		dragActor.setOrigin( actor.getWidth()/2, actor.getHeight()/2 );

		dragGroup = new Group();
		dragActor.setWidth( actor.getWidth()/2 );
		dragActor.setHeight( actor.getHeight()/2 );
		dragGroup.addActor(dragActor );
        dragGroup.setTransform(true);

        deleteButton = constructDeleteButton();
        GameObjectGestureListener gameObjectGestureListener = new GameObjectGestureListener(this);
        dragGroup.addListener(gameObjectGestureListener);

        dragGroup.addListener(new InputListener() {
			final float h = dragActor.getHeight() / 2;

			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
             Gdx.app.log("MainScreenActor", String.valueOf(x)+"/"+String.valueOf(y));
             playAnimation=false;
             deleteButton.setVisible(false);

             //Change taille du shadow et on le rend visible
             dx = dragGroup.getX() - touchDown_x;
             mainScreen.getShadowImg().setSize(dragActor.getWidth(),dragActor.getHeight());
             mainScreen.getShadowImg().setPosition(dragGroup.getX()-dragGroup.getWidth()/2, Constants.GROUND_HEIGHT);
             mainScreen.getShadowImg().setVisible(true);

            //Change aspect visuel
             dragGroup.clearActions();
             dragGroup.addAction(Actions.parallel(
                     Actions.scaleTo(1.5f, 1.5f, 0.25f, Interpolation.fade),
                     Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.5f), 0.25f, Interpolation.fade) )
             );

             touchDown_x = x;
             touchDown_y = h - y;
             return true;
            }

            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Gdx.app.log("touchDragged", String.valueOf(x)+"/"+String.valueOf(y));
                dx = dragGroup.getX() - touchDown_x;
            	dy = dragGroup.getY() - h + touchDown_y;
                // Position shadow
                mainScreen.getShadowImg().setPosition(x + dx, Constants.GROUND_HEIGHT);
                // Position objet
                dragGroup.setPosition(x + dx, y + dy);
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("up", String.valueOf(x)+"/"+String.valueOf(y));
                mainScreen.getShadowImg().setVisible(false);

                // Restaure l'aspect visuel au drop
                dragGroup.clearActions();
                dragGroup.addAction(Actions.parallel(
                    Actions.scaleTo(1.0f, 1.0f, 0.25f, Interpolation.fade),
                    Actions.color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0.25f, Interpolation.fade))
                );
                dragGroup.addAction(Actions.moveTo(mainScreen.getShadowImg().getX(),mainScreen.getShadowImg().getY(),0.2f));

               dx = dragGroup.getX() - touchDown_x;                 // x & y = position du curseur relative a l'acteur

               addActorToStage();
               playAnimation=true;
            }

		});

        this.addActor(dragGroup);
        this.addActor(deleteButton);
    }

    private ImageButton constructDeleteButton() {
        Drawable deleteDrawableUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("icon/delete_up.png"))));
        Drawable deleteDrawableDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("icon/delete_down.png"))));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = deleteDrawableUp;
        style.down = deleteDrawableDown;
        final ImageButton deleteButton = new ImageButton(style);

        deleteButton.setSize(100,100);
        deleteButton.setVisible(false);
        deleteButton.addListener(new InputListener() {
           @Override
           public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
               return true;
           }
           @Override
           public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               deleteButton.addAction(Actions.run(new Runnable() {
                   @Override
                   public void run() {
                       dragGroup.getParent().remove();
                   }
               }));
           }});

        return deleteButton;
    }

    // Affiche le menu
    public void showMenu() {
        deleteButton.setPosition(dragGroup.getX()+dragActor.getWidth(), dragGroup.getY()+dragActor.getHeight());
        deleteButton.setVisible(true);
    }

    // Depose l'actor dans le stage
    public void addActorToStage() {
        this.remove();
//        mainScreen.getStage().addActor(this);
        Gdx.app.log("actorList", String.valueOf(mainScreen.getStage().getActors().size));

        switch (type){
            case Constants.OBJECT_TYPE_BUILDING: mainScreen.getBuildingGroup().addActor(this);
            break;
            case Constants.OBJECT_TYPE_BACKGROUND: mainScreen.getBackgroundGroup().addActor(this);
            break;
            case Constants.OBJECT_TYPE_VEHICULE: mainScreen.getVehiculeGroup().addActor(this);
            break;
            default: mainScreen.getForegroundGroup().addActor(this);
            break;
        }
    }

    @Override
    public void act(float deltaTime)
    {
        if (playAnimation) {
            super.act(deltaTime);
        }
    }

    void dispose() {
		dragGroup.clear();
		dragActor = null;
		dragGroup = null;
	}

	public void debugRandomBuilding() {
        Random rand = new Random();
        int index = rand.nextInt(9-1) + 1;
        dragActor = new Image( new Texture(Gdx.files.internal("building/build"+index+".png")));
    }

    public Group getDragGroup() {
        return dragGroup;
    }
}
