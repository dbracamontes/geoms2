package com.bracamod.geo.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import io.minio.messages.Bucket;

@Service
public class MinioS3Service {
	// @Value("${minio.url}")
	private static String url = "http://127.0.0.1:9001";

	// @Value("${minio.user}")
	private static String user = "minio";

	// @Value("${minio.accesskey}")
	private static String accessKey = "minio123";

	private static MinioClient minioClient;

	static {
		minioClient = MinioClient.builder().endpoint(url).credentials(user, accessKey).build();
	}

	public void listBuckets() {
		try {
			List<Bucket> buckets = minioClient.listBuckets();
			for (Bucket bucket : buckets) {
				System.out.println(bucket.name());
			}
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getObjectUrl() {
		// Get presigned URL string to download 'my-objectname' in 'my-bucketname' and
		// its life time
		// is 2 hours.
		String url;
		try {
			url = minioClient
					.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket("states")
							.object("aguascalientes/Aguascalientes_in_Mexico.png").expiry(2, TimeUnit.HOURS).build());
			System.out.println(url);
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
				| IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] getFile(String key) {
		// get object given the bucket and object name
		byte[] object = null;
		try (InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket("states")
				.object("aguascalientes/Aguascalientes_in_Mexico.png").build())) {

			object = IOUtils.toByteArray(stream);

		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

}
