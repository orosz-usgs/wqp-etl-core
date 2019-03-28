package gov.acwi.wqp.etl.summaries.orgSum.index;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuildOrgSumIndexes {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("buildOrgSumOrganizationIndex")
	private Tasklet buildOrgSumOrganizationIndex;

	@Bean
	public Step buildOrgSumOrganizationIndexStep() {
		return stepBuilderFactory.get("buildOrgSumOrganizationIndexStep")
				.tasklet(buildOrgSumOrganizationIndex)
				.build();
	}

	@Bean
	public Flow buildOrgSumIndexesFlow() {
		return new FlowBuilder<SimpleFlow>("buildOrgSumIndexesFlow")
				.start(buildOrgSumOrganizationIndexStep())
				.build();
	}

}
