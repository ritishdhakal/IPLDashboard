package io.ritish.ipl_dashboard;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IplDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(IplDashboardApplication.class, args);
	}

	@Bean
	public CommandLineRunner runBatchJob(JobLauncher jobLauncher, Job job) {
		return args -> {
			try {
				// Adding a tiny delay or checking if the table exists can help,
				// but usually 'spring.batch.jdbc.initialize-schema=always' is enough.
				JobParameters params = new JobParametersBuilder()
						.addLong("startAt", System.currentTimeMillis())
						.toJobParameters();
				jobLauncher.run(job, params);
				System.out.println("Job started successfully!");
			} catch (Exception e) {
				System.err.println("Failed to start the batch job: " + e.getMessage());
				e.printStackTrace();
			}
		};
	}
}
