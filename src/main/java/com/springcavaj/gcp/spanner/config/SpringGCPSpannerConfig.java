/**
 * 
 */
package com.springcavaj.gcp.spanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

/**
 * @author c86am
 *
 */
@Configuration
public class SpringGCPSpannerConfig {
	
	@Value("${spanner.projectId}")
    private String projectId;

    @Value("${spanner.instanceId}")
    private String instanceId;

    @Value("${spanner.databaseId}")
    private String databaseId;
    
    @Bean
    public Spanner spanner() {
        SpannerOptions options = SpannerOptions.newBuilder().setProjectId(projectId).build();
        return options.getService();
    }

    @Bean
    public DatabaseClient databaseClient(Spanner spanner) {
        return spanner.getDatabaseClient(DatabaseId.of(projectId, instanceId, databaseId));
    }

}
