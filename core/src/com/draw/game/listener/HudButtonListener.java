package com.draw.game.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.draw.game.Constants;
import com.draw.game.MainScreenActor;
import com.draw.game.screen.Hud;

/**
 * Created by Skronak on 22/11/2017.
 */
//TODO faire apparaitre objet en fonction du bouton cliqué
public class HudButtonListener extends InputListener {

    private Actor parentActor;
    private Hud hud;
    private float tapSquareSize = 14, touchDownX = -1, touchDownY = -1, stageTouchDownX = -1, stageTouchDownY = -1;
    private int pressedPointer = -1;
    private int button;
    private boolean dragging;
    private float deltaX, deltaY;
    private float initialPosX, initialPosy;

    public HudButtonListener(Hud hud, Actor actor){
        this.hud=hud;
        this.parentActor = actor;
        this.initialPosX = actor.getX();
        this.initialPosy = actor.getY();
    }

    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if (pressedPointer != -1) return false;
        if (pointer == 0 && this.button != -1 && button != this.button) return false;
        pressedPointer = pointer;
        touchDownX = x;
        touchDownY = y;
        stageTouchDownX = event.getStageX();
        stageTouchDownY = event.getStageY();

        event.stop();

       return true;
    }

    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        if (pointer != pressedPointer) return;
        if (!dragging && (Math.abs(touchDownX - x) > tapSquareSize || Math.abs(touchDownY - y) > tapSquareSize)) {
            dragging = true;
            dragStart(event, x, y, pointer);
            deltaX = x;
            deltaY = y;
        }
        if (dragging) {
            deltaX -= x;
            deltaY -= y;
            drag(event, x, y, pointer);
            deltaX = x;
            deltaY = y;
        }
    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        if (pointer == pressedPointer) {
            if (dragging) dragStop(event, x, y, pointer);
            cancel();
        }
        this.hud.getPlayScreen().updateTips();
        hud.getPlayScreen().getShadowImg().setVisible(false);
    }

    public void dragStart (InputEvent event, float x, float y, int pointer) {
        Gdx.app.log("parentActor", "dragStart");

        // Animation pour retirer lentement l'objet du HUD
        Actor actor = new Actor();
        actor.setWidth(parentActor.getWidth());
        actor.setHeight(parentActor.getWidth());
//        actor.debug();
        hud.getScrollContainerVG().addActor(actor);
        hud.getScrollContainerVG().swapActor(actor,parentActor);
        actor.addAction(Actions.sequence(Actions.scaleTo(0.1f,0.1f,0.5f),Actions.removeActor(actor)));

        // Ajoute acteur sur l'hud pour le deplacer sur tt l'ecran
        hud.getStage().addActor(parentActor);
        parentActor.setPosition(stageTouchDownX-(parentActor.getWidth()/2),stageTouchDownY-(parentActor.getHeight()/2));

        // Affiche le shadow pour le placement sur le stage
        hud.getPlayScreen().getShadowImg().setVisible(true);
        hud.getPlayScreen().getShadowImg().setSize(parentActor.getWidth(),parentActor.getHeight());
        hud.getPlayScreen().getShadowImg().setPosition(deltaX,Constants.GROUND_HEIGHT);
    }

    public void drag(InputEvent event, float x, float y, int pointer) {
        Gdx.app.log("parentActor", "drag");
        this.parentActor.moveBy(x - this.parentActor.getWidth() / 2, y - this.parentActor.getHeight() / 2);
        hud.getPlayScreen().getShadowImg().setPosition(parentActor.getX(), Constants.GROUND_HEIGHT);
    }

    public void dragStop (InputEvent event, float x, float y, int pointer) {
        parentActor.remove();
        MainScreenActor dadActor = new MainScreenActor(new Image( new Texture(Gdx.files.internal("bat1.png"))),hud.getPlayScreen(), Constants.OBJECT_TYPE_BUILDING);
        hud.getPlayScreen().getStage().addActor(dadActor);
        dadActor.getDragGroup().setPosition(hud.getPlayScreen().getShadowImg().getX()- dadActor.getWidth(), hud.getPlayScreen().getShadowImg().getY());
    }

    /* If a drag is in progress, no further drag methods will be called until a new drag is started. */
    public void cancel () {
        dragging = false;
        pressedPointer = -1;
    }

    /** Returns true if a touch has been dragged outside the tap square. */
    public boolean isDragging () {
        return dragging;
    }
}
