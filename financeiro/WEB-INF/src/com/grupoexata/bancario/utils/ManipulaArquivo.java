package com.grupoexata.bancario.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.grupoexata.bancario.utils.RemoverAcentos;

public class ManipulaArquivo {
	
	public static int MAX_SIZE = 1024*1024;
	public final static int ERROR_CRIAR_DIR = -4;
	public final static int ERROR_SIZE_EXCEDIDO = -3;
	public final static int ERROR_CRIAR_ARQ = -2;
	public final static int OK = 0;
	
	public List<File> getFiles(String path){
		List<File> list = new ArrayList<File>();
		File dir = new File(path);
		if (dir.exists()){
			File fList[] = dir.listFiles();
			for (int i = 0; i < fList.length; i++) {
				list.add(fList[i]);
			}
		}
		return list;
	}
	
	public int gravar(FormFile myFile, String diretorio) throws IOException{
		int ret = OK;
		if (createDir(diretorio)){
			if(myFile == null){
				throw new IllegalArgumentException();
			}
			String fileName = RemoverAcentos.remover(myFile.getFileName());
			String filePath = diretorio;
			if(fileName != null && !fileName.equals("") && myFile.getFileSize() < MAX_SIZE){
				File fileToCreate = new File(filePath, fileName);
				if(!fileToCreate.exists()){
					FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
					fileOutStream.write(myFile.getFileData());
					fileOutStream.flush();
					fileOutStream.close();
				}else{
					fileToCreate.delete();
					ret = ERROR_CRIAR_ARQ;
				}
			}else{
				ret = ERROR_SIZE_EXCEDIDO;
			}
		}else{
			ret = ERROR_CRIAR_DIR;
		}
		return ret;
	}
	
	public boolean createDir(String diretorio){
		File file = new File(diretorio);
		if (!file.exists()){
			file.mkdir();
		}
		return file.isDirectory();
	}
}
