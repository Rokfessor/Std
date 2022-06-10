package com.mvarlamov.lab10.utils;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    public void init();
    public String save(MultipartFile file, String name);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
}