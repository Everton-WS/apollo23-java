package devs2blu.hackweek.eventmanager.utils;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityRequest;
import devs2blu.hackweek.eventmanager.dtos.event.EventRequest;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionRequest;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerRequest;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureRequest;
import devs2blu.hackweek.eventmanager.dtos.user.UserRequest;
import devs2blu.hackweek.eventmanager.entities.Activity;
import devs2blu.hackweek.eventmanager.entities.Event;
import devs2blu.hackweek.eventmanager.entities.Question;
import devs2blu.hackweek.eventmanager.entities.Speaker;
import devs2blu.hackweek.eventmanager.entities.Treasure;
import devs2blu.hackweek.eventmanager.entities.User;

public class UpdateEntities {
    
    public static User updateUser(UserRequest uRequest, User u) {
        if (uRequest.getName() != null) u.setName(uRequest.getName());
        if (uRequest.getMobile() != null) u.setMobile(uRequest.getMobile());
        if (uRequest.getName() != null) u.setName(uRequest.getName());
        if (uRequest.getPassword() != null) u.setPassword(uRequest.getPassword());

        return u;
    }

    public static Event updateEvent(EventRequest eRequest, Event e) {
        if (eRequest.getName() != null) e.setName(eRequest.getName());
        if (eRequest.getWebsite() != null) e.setWebsite(eRequest.getWebsite());
        if (eRequest.getCity() != null) e.setCity(eRequest.getCity());
        if (eRequest.getState() != null) e.setState(eRequest.getState());
        if (eRequest.getStartDate() != null) e.setStartDate(eRequest.getStartDate());
        if (eRequest.getEndDate() != null) e.setEndDate(eRequest.getEndDate());

        return e;
    }

    public static Activity updateActivity(ActivityRequest aRequest, Activity a) {
        if (aRequest.getName() != null) a.setName(aRequest.getName());
        if (aRequest.getDescription() != null) a.setDescription(aRequest.getDescription());
        if (aRequest.getLocation() != null) a.setLocation(aRequest.getLocation());
        if (aRequest.getType() != null) a.setType(aRequest.getType());

        return a;
    }

    public static Speaker updateSpeaker(SpeakerRequest sRequest, Speaker s) {
        if (sRequest.getName() != null) s.setName(sRequest.getName());
        if (sRequest.getMiniBio() != null) s.setMiniBio(sRequest.getMiniBio());
        if (sRequest.getSocialMedia() != null) s.setSocialMedia(sRequest.getSocialMedia());

        return s;
    }

    

    public static Treasure updateTreasure(TreasureRequest tRequest, Treasure t) {
        if (tRequest.getToken() != null) t.setToken(tRequest.getToken());
        if (tRequest.getScore() != null) t.setScore(tRequest.getScore());
        if (tRequest.getHidden() != null) t.setHidden(tRequest.getHidden());
        
        return t;
    }

    public static Question updateQuestion(QuestionRequest qRequest, Question q) {
        if (qRequest.getQuestionText() != null) q.setQuestionText(qRequest.getQuestionText());
        if (qRequest.getApproved() != null) q.setApproved(qRequest.getApproved());
        if (qRequest.getExcluded() != null) q.setExcluded(qRequest.getExcluded());

        return q;
    }
}
