package devs2blu.hackweek.eventmanager.utils;

import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureRequest;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.entities.Treasure;

public class UpdateEntities {
    
    public static Treasure updateTreasure(TreasureRequest tRequest, Treasure t) {

        if (tRequest.getToken() != null) {
            t.setToken(tRequest.getToken());
        } 
        if (tRequest.getScore() != null) {
            t.setScore(tRequest.getScore());
        } 

        if (tRequest.getHidden() != null) {
            t.setHidden(tRequest.getHidden());
        } 
        
        return t;
    }
}
