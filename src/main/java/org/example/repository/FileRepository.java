package org.example.repository;

import org.example.model.File;

import java.util.Optional;

public interface FileRepository {
    File save(File file);
    Optional<File> findById(int id);
    boolean deleteById(int id);
}
