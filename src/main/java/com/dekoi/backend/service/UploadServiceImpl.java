package com.dekoi.backend.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadService {

	private final static String DIRECTORIO_UPLOAD = "uploads";

	@Override
	public Resource cargar(String fileName) throws MalformedURLException {
		Path pathFile = getPath(fileName);

		
		Resource recurso = new UrlResource(pathFile.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			pathFile = Paths.get("src/main/resources/static/img").resolve("no-img.png").toAbsolutePath();

			recurso = new UrlResource(pathFile.toUri());

			throw new RuntimeException("Error no se pudo cargar la imagen: " + fileName);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
		Path pathFile = getPath(fileName);
		

		Files.copy(file.getInputStream(), pathFile);

		return fileName;
	}

	@Override
	public boolean eliminar(String fileName) {
		if (fileName != null && fileName.length() > 0) {
			Path beforePathFile = Paths.get("uploads").resolve(fileName).toAbsolutePath();
			File beforeFile = beforePathFile.toFile();
			if (beforeFile.exists() && beforeFile.canRead()) {
				beforeFile.delete();
				return true;
			}
		}

		return false;
	}

	@Override
	public Path getPath(String fileName) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(fileName).toAbsolutePath();
	}



}
