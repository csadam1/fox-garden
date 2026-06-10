package com.cherry.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.database.jvm.JdbcConnection;
import liquibase.Contexts;
import liquibase.LabelExpression;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class LiquibaseRunner {

    private static final Logger logger = Logger.getLogger(LiquibaseRunner.class.getName());

    private static final String DATA_SOURCE = "java:/jdbc/FoxDS";
    private static final String LIQUIBASE_SUCCESSFULLY_EXECUTED = "Liquibase successfully executed.";
    private static final String INITIAL_CHANGELOG_PATH = "db/changelog/db.changelog-master.xml";
    private static final String ERROR_MESSAGE_LOG = "An Error happened during Liquibase initialization.";

    @Resource(lookup = DATA_SOURCE)
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        try (Connection connection = dataSource.getConnection()) {

            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase(INITIAL_CHANGELOG_PATH, new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());

            logger.log(Level.INFO, LIQUIBASE_SUCCESSFULLY_EXECUTED);

        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_MESSAGE_LOG, e);
        }
    }
}
