package org.example.repository;

import org.checkerframework.checker.units.qual.C;
import org.example.configuration.DatasourceConfiguration;
import org.example.model.Candidate;
import org.example.model.File;
import org.example.model.Vacancy;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Properties;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Sql2oCandidateRepositoryTest {
    private static Sql2oCandidateRepository sql2oCandidateRepository;
    private static Sql2oFileRepository sql2oFileRepository;
    private static File file;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oVacancyRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oCandidateRepository = new Sql2oCandidateRepository(sql2o);
        sql2oFileRepository = new Sql2oFileRepository(sql2o);

        // нужно сохранить хотя бы один файл, т.к. Vacancy от него зависит
        file = new File("test", "test");
        sql2oFileRepository.save(file);
    }

    @AfterAll
    public static void deleteFile() {
        sql2oFileRepository.deleteById(file.getId());
    }

    @AfterEach
    public void clearVacancies() {
        var vacancies = sql2oCandidateRepository.findAll();
        for (var vacancy : vacancies) {
            sql2oCandidateRepository.deleteById(vacancy.getId());
        }
    }
    /*
    @Test
    public void whenSaveThenGetSame() {
        var creationDate = now().truncatedTo(ChronoUnit.MINUTES);
        var candidate = sql2oCandidateRepository.save(
                new Candidate(0, "name", "desc", creationDate, file.getId()));
        var savedCandidate = sql2oCandidateRepository.findById(candidate.getId()).get();
        assertThat(savedCandidate).usingRecursiveComparison().isEqualTo(candidate);
    }

     */
}