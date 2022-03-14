package com.bracamod.geo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bracamod.geo.service.MinioS3Service;

@RestController
@RequestMapping(value = "/s3", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class MinioController {

	@Autowired
	private MinioS3Service minioService;

	@GetMapping(value = "/cities", produces = MediaType.IMAGE_PNG_VALUE)
	// @RequestMapping(value = "/cities", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getBuckets() {
		minioService.getObjectUrl();
		byte[] image = minioService.getFile("test");
		minioService.listBuckets();

		return new ResponseEntity<>(image,HttpStatus.OK);
	}

}
