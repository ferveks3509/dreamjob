package org.example.repository;

import org.example.model.Candidate;
import org.example.model.Vacancy;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oCandidateRepository implements CandidateRepository {
    private final Sql2o sql2o;

    public Sql2oCandidateRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Candidate save(Candidate candidate) {
        try (var connection = sql2o.open()) {
            var sql = ("INSERT INTO candidates(name, description, created, file_id) " +
                    "VALUES(:name, :description, :created, :fileId)");
            var query = connection.createQuery(sql, true)
                    .addParameter("name", candidate.getName())
                    .addParameter("description", candidate.getDescription())
                    .addParameter("created", candidate.getCreated())
                    .addParameter("fileId", candidate.getFileId());
            int generatedKey = query.executeUpdate().getKey(Integer.class);
            candidate.setId(generatedKey);
            return candidate;
        }
    }

    @Override
    public boolean update(Candidate candidate) {
        try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE candidates
                    SET name = :name, description = :description, created = :created, file_id = :fileId
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("name", candidate.getName())
                    .addParameter("description", candidate.getDescription())
                    .addParameter("created", candidate.getCreated())
                    .addParameter("fileId", candidate.getFileId())
                    .addParameter("id", candidate.getId());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM candidates WHERE id = :id")
                    .addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Candidate> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM candidates WHERE id = :id")
                    .addParameter("id", id);
            var candidate = query.setColumnMappings(Candidate.COLUMN_MAPPING).executeAndFetchFirst(Candidate.class);
            return Optional.ofNullable(candidate);
        }
    }

    @Override
    public Collection<Candidate> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM candidates");
            return query.setColumnMappings(Candidate.COLUMN_MAPPING).executeAndFetch(Candidate.class);
        }
    }
}
