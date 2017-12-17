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
public class MainScreenActor extends Group {

    // Sprite
	protected Actor dragActor;
    protected Group dragGroup;
    private float touchDown_x;
    private float touchDown_y;
    private float dx;
    private float dy;
    protected MainScreen playScreen;
    protected boolean playAnimation;

    // Type d'objet (pour la logique)
    int type;

	public MainScreenActor(final Actor actor, final MainScreen playScreen, int type) {
        this.playScreen=playScreen;
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

		dragGroup.addListener(new InputListener() {
			final float h = dragActor.getHeight() / 2;

			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
             Gdx.app.log("down", String.valueOf(x)+"/"+String.valueOf(y));
             playAnimation=false;

             //Change taille du shadow et on le rend visible
             playScreen.getShadowImg().setSize(dragActor.getWidth(),dragActor.getHeight());
             playScreen.getShadowImg().setPosition(x + dx, Constants.GROUND_HEIGHT);
             playScreen.getShadowImg().setVisible(true);

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
               playScreen.getShadowImg().setPosition(x + dx, Constants.GROUND_HEIGHT);
                // Position objet
                dragGroup.setPosition(x + dx, y + dy);
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("up", String.valueOf(x)+"/"+String.valueOf(y));
                playScreen.getShadowImg().setVisible(false);

                // Restaure l'aspect visuel au drop
                dragGroup.clearActions();
                dragGroup.addAction(Actions.parallel(
                    Actions.scaleTo(1.0f, 1.0f, 0.25f, Interpolation.fade),
                    Actions.color(new Color(1.0f, 1.0f, 1.0f, 1.0f), 0.25f, Interpolation.fade))
                );
                dragGroup.addAction(Actions.moveTo(playScreen.getShadowImg().getX(),playScreen.getShadowImg().getY(),0.2f));

               dx = dragGroup.getX() - touchDown_x;                 // x & y = position du curseur relative a l'acteur

               addActorToStage();
               playAnimation=true;
            }

		});
		this.addActor(dragGroup);
    }

    // Depose l'actor par dessus systematiquement
    public void addActorToStage() {
        this.remove();
        playScreen.getStage().addActor(this);
        Gdx.app.log("actorList", String.valueOf(playScreen.getStage().getActors().size));
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
