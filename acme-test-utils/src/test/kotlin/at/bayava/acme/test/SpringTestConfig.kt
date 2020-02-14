package at.bayava.acme.test

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.testcontainers.containers.GenericContainer
import javax.sql.DataSource


@TestConfiguration
@EnableTransactionManagement
@ComponentScan("at.bayava.acme")
@ActiveProfiles("test")
class SpringTestConfig {

    @Bean(destroyMethod = "stop")
    fun postgresContainer(): GenericContainer<Nothing> {
        val container = GenericContainer<Nothing>("postgres:12.1")
        container.withEnv("POSTGRES_PASSWORD", "pass")
        container.withEnv("POSTGRES_DB", "productinventory")
        container.withExposedPorts(5432)
        container.start()
        return container
    }

    @Bean
    fun datasource(@Qualifier("postgresContainer") container: GenericContainer<Nothing>): DataSource? {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.url("jdbc:postgresql://localhost:${container.getMappedPort(5432)}/productinventory")
        dataSourceBuilder.username("postgres")
        dataSourceBuilder.password("pass")
        return dataSourceBuilder.build()
    }

}