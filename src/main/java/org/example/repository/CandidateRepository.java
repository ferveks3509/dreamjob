package org.example.repository;

import org.example.model.Candidate;

import java.util.Collection;
import java.util.Optional;

public interface CandidateRepository {
    Candidate save(Candidate candidate);

    boolean deleteById(int id);

    boolean update(Candidate candidate);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();
}
