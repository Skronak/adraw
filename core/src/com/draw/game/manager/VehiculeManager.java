package com.draw.game.manager;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.draw.game.MainScreenActor;

import java.util.ArrayList;

/**
 * Created by Skronak on 17/12/2017.
 */

public class VehiculeManager {
    ArrayList<MainScreenActor> vehiculesList;

    public void registerVehicule(MainScreenActor actor) {
        if(vehiculesList==null){
            vehiculesList=new ArrayList<MainScreenActor>();
        }
        vehiculesList.add(actor);
    }


}
