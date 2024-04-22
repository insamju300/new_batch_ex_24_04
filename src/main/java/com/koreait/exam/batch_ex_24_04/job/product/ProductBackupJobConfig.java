package com.koreait.exam.batch_ex_24_04.job.product;

import com.koreait.exam.batch_ex_24_04.app.product.entity.Product;
import com.koreait.exam.batch_ex_24_04.app.product.repository.BackupedProductRepository;
import com.koreait.exam.batch_ex_24_04.app.product.repository.ProductRepository;
import com.koreait.exam.batch_ex_24_04.app.product.service.BackupedProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ProductBackupJobConfig {
  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  private final ProductRepository productRepository;
  private final BackupedProductRepository backupedProductRepository;
  private final BackupedProductService backupedProductService;

  @Bean
  public Job productBackupJap(Step productBackupStep1, CommandLineRunner initData) throws Exception {
    initData.run();


    return jobBuilderFactory.get("productBackupJob")
        .start(productBackupStep1)
        .build();
  }
  @Bean
  @JobScope
  public Step productBackupStep1(Tasklet productBackupStep1Tasklet) {
    return stepBuilderFactory.get("productBackupStep1")
        .tasklet(productBackupStep1Tasklet)
        .build();

  }
  @Bean
  @StepScope
  public Tasklet productBackupStep1Tasklet() {

    return (stepContribution, chunkContext) -> {
//            log.debug("name : {}, age : {}", name, age);

      List<Product> products = productRepository.findAll();
      log.info("머시 중헌디");
      log.info("프로덕트사이즈 " + products.size());

      backupedProductService.backupAll(products);

      return RepeatStatus.FINISHED;
    };
  }
}
