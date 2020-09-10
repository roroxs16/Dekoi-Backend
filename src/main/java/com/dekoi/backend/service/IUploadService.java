package com.dekoi.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadService {

	public Resource cargar(String fileName) throws MalformedURLException;

	public String copiar(MultipartFile file) throws IOException;

	public boolean eliminar(String fileName);

	public Path getPath(String fileName);
}
