package io.ritish.ipl_dashboard.data;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.ritish.ipl_dashboard.model.Match;
import io.ritish.ipl_dashboard.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final EntityManager em;

    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) { // it runs after the job is completed, and it will be used to
                                                      // calculate the total matches and total wins for each team and
                                                      // save it to the database
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            // since we have two teams in each match, we need to calculate the total matches
            // for each team by
            // counting the number of times each team appears as team1 and team2 in the
            // Match table,
            // and then we need to calculate the total wins for each team by counting
            // the number of times each team appears as matchWinner in the Match table, and
            // then we need to save the results to the Team table

            Map<String, Team> teamData = new HashMap<>();

            em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))// converting "MI" , 100 into Team ("MI", 100)

                    .forEach(team -> teamData.put(team.getTeamName(), team));

            em.createQuery("select  m.team2, count(*) as matches from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                    });

            em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        if (team != null)
                            team.setTotalWins((long) e[1]);
                    });

            // saving result to the database
            teamData.values().forEach(team -> em.merge(team));

            teamData.values().forEach(team -> System.out.println(team));

        }
    }
}