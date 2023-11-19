package com.shelter.animalshelter.model;

import liquibase.pro.packaged.S;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "take_animal_info")
public class InfoHowToTakeAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Getter
    @Column(name = "rules_for_meeting_animal")
    private String rulesForMeetingAnimal;

    @Getter
    @Column(name = "document_list")
    private String documentList;
    @Getter
    @Column(name = "recommendations_for_transport")
    private String recForTransport;
    @Getter
    @Column(name = "home_recommendations_for_small")
    private String homeRecommendForSmall;
    @Getter
    @Column(name = "home_recommendations_for_big")
    private String homeRecommendForBig;
    @Getter
    @Column(name = "home_recommendations_for_disable")
    private String homeRecommendForDisable;
    @Getter
    @Column(name = "dog_handler_tips")
    private String dogHandlerTips;
    @Getter
    @Column(name = "recommendations_for_proven_dog_handlers")
    private String recForProvenDogHandlers;
    @Getter
    @Column(name = "reasons_for_refusal")
    private String reasonsForRefusal;

    @Override
    public String toString() {
        return "InfoHowToTakeAnimal{" +
                "id=" + id +
                ", rulesForMeetingAnimal='" + rulesForMeetingAnimal + '\'' +
                ", documentList='" + documentList + '\'' +
                ", recForTransport='" + recForTransport + '\'' +
                ", homeRecommendForSmall='" + homeRecommendForSmall + '\'' +
                ", homeRecommendForBig='" + homeRecommendForBig + '\'' +
                ", homeRecommendForDisable='" + homeRecommendForDisable + '\'' +
                ", dogHandlerTips='" + dogHandlerTips + '\'' +
                ", recForProvenDogHandlers='" + recForProvenDogHandlers + '\'' +
                ", reasonsForRefusal='" + reasonsForRefusal + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoHowToTakeAnimal that = (InfoHowToTakeAnimal) o;
        return Objects.equals(id, that.id) && Objects.equals(rulesForMeetingAnimal, that.rulesForMeetingAnimal) && Objects.equals(documentList, that.documentList) && Objects.equals(recForTransport, that.recForTransport) && Objects.equals(homeRecommendForSmall, that.homeRecommendForSmall) && Objects.equals(homeRecommendForBig, that.homeRecommendForBig) && Objects.equals(homeRecommendForDisable, that.homeRecommendForDisable) && Objects.equals(dogHandlerTips, that.dogHandlerTips) && Objects.equals(recForProvenDogHandlers, that.recForProvenDogHandlers) && Objects.equals(reasonsForRefusal, that.reasonsForRefusal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rulesForMeetingAnimal, documentList, recForTransport, homeRecommendForSmall, homeRecommendForBig, homeRecommendForDisable, dogHandlerTips, recForProvenDogHandlers, reasonsForRefusal);
    }

    public void setRulesForMeetingAnimal(String rulesForMeetingAnimal) {
        this.rulesForMeetingAnimal = rulesForMeetingAnimal;
    }

    public void setDocumentList(String documentList) {
        this.documentList = documentList;
    }

    public void setRecForTransport(String recForTransport) {
        this.recForTransport = recForTransport;
    }

    public void setHomeRecommendForSmall(String homeRecommendForSmall) {
        this.homeRecommendForSmall = homeRecommendForSmall;
    }

    public void setHomeRecommendForBig(String homeRecommendForBig) {
        this.homeRecommendForBig = homeRecommendForBig;
    }

    public void setHomeRecommendForDisable(String homeRecommendForDisable) {
        this.homeRecommendForDisable = homeRecommendForDisable;
    }

    public void setDogHandlerTips(String dogHandlerTips) {
        this.dogHandlerTips = dogHandlerTips;
    }

    public void setRecForProvenDogHandlers(String recForProvenDogHandlers) {
        this.recForProvenDogHandlers = recForProvenDogHandlers;
    }

    public void setReasonsForRefusal(String reasonsForRefusal) {
        this.reasonsForRefusal = reasonsForRefusal;
    }
}
