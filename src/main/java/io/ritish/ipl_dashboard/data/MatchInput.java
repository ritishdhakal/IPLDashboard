package io.ritish.ipl_dashboard.data;

import org.springframework.batch.item.ItemProcessor;

public class MatchInput {
    private String id;
    private String city;
    private String date;
    private String player_of_match;
    private String venue;
    private String team1;
    private String team2;
    private String toss_winner;
    private String toss_decision;
    private String match_winner;
    private String result;
    private String result_margin;
    private String umpire1;
    private String umpire2;

    // getters for all input fields
    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getPlayer_of_match() {
        return player_of_match;
    }

    public String getVenue() {
        return venue;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getToss_winner() {
        return toss_winner;
    }

    public String getToss_decision() {
        return toss_decision;
    }

    public String getMatch_winner() {
        return match_winner;
    }

    public String getResult() {
        return result;
    }

    public String getResult_margin() {
        return result_margin;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public String getUmpire2() {
        return umpire2;
    }

    // setters for all input fields
    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlayer_of_match(String player_of_match) {
        this.player_of_match = player_of_match;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setToss_winner(String toss_winner) {
        this.toss_winner = toss_winner;
    }

    public void setToss_decision(String toss_decision) {
        this.toss_decision = toss_decision;
    }

    public void setMatch_winner(String match_winner) {
        this.match_winner = match_winner;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResult_margin(String result_margin) {
        this.result_margin = result_margin;
    }

    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }

    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }

}
