package org.example.service;

import org.example.dto.FileDto;
import org.example.model.File;

import java.util.Optional;

public interface FileService {
    File save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    boolean deleteById(int id);
}
