package com.catalogo.services;

import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

@Service
public interface IArchivoService {
	
	public void guardar(String archivo, InputStream bytes);
	public void eliminar(String archivo);
	public ResponseEntity<Resource> get(String archivo);
	
}
