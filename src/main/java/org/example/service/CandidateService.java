package org.example.service;

import org.example.dto.FileDto;
import org.example.model.Candidate;
import org.example.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface CandidateService {
    Candidate save(Candidate vacancy, FileDto image);

    boolean deleteById(int id);

    boolean update(Candidate candidate, FileDto image);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();
}
