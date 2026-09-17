package com.tim.document.web1.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/files")
public class FileController {
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	private static final Set<String>ALLOWED_EXTENSIONS=
			Set.of("pdf","doc","docx","png","jpg","jpeg");
	
	@PostMapping("upload")
	public ResponseEntity<String>upload(
			@RequestParam("file")MultipartFile file){
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("請選擇檔案");
		}
		String fileName=file.getOriginalFilename();
		if (fileName==null || !fileName.contains(".")) {
			return ResponseEntity.badRequest().body("檔案格式不正確");
		}
		String extension=fileName
				.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		if (!ALLOWED_EXTENSIONS.contains(extension)) {
			return ResponseEntity.badRequest().body("只允許PDF、Word、PNG、JPG 檔案");
		}
		try {
			String datatime =LocalDateTime.now()
					.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			String savedFileName=datatime+"_"+fileName;
			File target=new File(uploadDir,savedFileName);
			file.transferTo(target);
			return ResponseEntity.ok("檔案上傳成功"+fileName);
		} 
		catch (IOException e) {
			return ResponseEntity.internalServerError().body("檔案儲存失敗");
		}
	}
}
