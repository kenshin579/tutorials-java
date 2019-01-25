package com.java.examples;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class S3App {

	private static final AWSCredentials credentials;
	private static final String BUCKET_NAME = "advenoh-s3-test";
	private static final String BUCKET_NAME2 = "advenoh-s3-test2";
	private static final String KEY_ID = "<key_id>";
	private static final String SECRET_ACCESS_KEY = "<secret_access_key>";
	private static AmazonS3 s3client;

	static {
		credentials = new BasicAWSCredentials(
				KEY_ID,
				SECRET_ACCESS_KEY
		);
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		//1.직접 지정해서
		//s3client = createConnectionWithCredentials(credentials);

		//2.환경 설정에서
		s3client = createConnectionWithCredentials();

		AWSS3Service awsService = new AWSS3Service(s3client);

		String[] bucketList = new String[] { BUCKET_NAME, BUCKET_NAME2 };

		//creating a bucket
		for (String bucketName : bucketList) {
			if (awsService.doesBucketExist(bucketName)) {
				System.out.println("deleting existing bucket " + BUCKET_NAME);
				awsService.deleteBucket(bucketName);
			}
			awsService.createBucket(bucketName);
		}

		//list all the buckets
		for (Bucket s : awsService.listBuckets()) {
			System.out.println(s.getName());
		}

		//uploading object
		Map<String, String> uploadResourceMap = getMapFileForS3Upload();

		for (String bucketKey : uploadResourceMap.keySet()) {
			awsService.putObject(
					BUCKET_NAME,
					bucketKey,
					new File(uploadResourceMap.get(bucketKey))
			);
		}

		//listing objects
		ObjectListing objectListing = awsService.listObjects(BUCKET_NAME);
		for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
			System.out.println(os.getKey());
		}

		//downloading an object
		S3Object s3object = awsService.getObject(BUCKET_NAME, "text/text.txt");
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		FileUtils.copyInputStreamToFile(inputStream, new File("amazon-s3/new_text.txt"));

		//copying an object
		awsService.copyObject(
				BUCKET_NAME,
				"text/text.txt",
				BUCKET_NAME2,
				"text/text2.txt"
		);

		//deleting an object
		awsService.deleteObject(BUCKET_NAME, "text/text.txt");

		//deleting multiple objects
		String objkeyArr[] = {
				"image/image.png",
				"video/video.mov"
		};

		DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(BUCKET_NAME)
				.withKeys(objkeyArr);
		awsService.deleteObjects(delObjReq);

		//deleting bucket
		for (String bucketName : bucketList) {
			deleteAllObjectsAndBucket(awsService, bucketName);
		}
	}

	private static Map<String, String> getMapFileForS3Upload() throws URISyntaxException {
		Map<String, String> result = new HashMap<>();

		File dir = new File(S3App.class.getClassLoader().getResource(".").toURI());
		File[] directoryList = dir.listFiles();
		if (directoryList != null) {
			String bucketKey;

			for (File file : directoryList) {
				if (file.isFile() && !file.isDirectory()) {
					bucketKey = file.getName().split("\\.")[0] + "/" + file.getName();
					log.info("file: {} {}", bucketKey, file.getAbsoluteFile());
					result.put(bucketKey, file.getAbsoluteFile().toString());
				}
			}
		}
		return result;
	}

	private static void deleteAllObjectsAndBucket(AWSS3Service awsService, final String bucketName) {
		log.info("deleteAllObjectsAndBucket : bucketName: {}", bucketName);
		try {
			ObjectListing objectListing = awsService.listObjects(bucketName);

			while (true) {
				Iterator<S3ObjectSummary> objIter = objectListing.getObjectSummaries().iterator();
				while (objIter.hasNext()) {
					awsService.deleteObject(bucketName, objIter.next().getKey());
				}

				// If the bucket contains many objects, the listObjects() call
				// might not return all of the objects in the first listing. Check to
				// see whether the listing was truncated. If so, retrieve the next page of objects
				// and delete them.
				if (objectListing.isTruncated()) {
					objectListing = awsService.listNextBatchOfObjects(objectListing);
				} else {
					break;
				}
			}

			// Delete all object versions (required for versioned buckets).
			VersionListing versionList = awsService.listVersions(new ListVersionsRequest().withBucketName(bucketName));
			while (true) {
				Iterator<S3VersionSummary> versionIter = versionList.getVersionSummaries().iterator();
				while (versionIter.hasNext()) {
					S3VersionSummary vs = versionIter.next();
					awsService.deleteVersion(bucketName, vs.getKey(), vs.getVersionId());
				}

				if (versionList.isTruncated()) {
					versionList = awsService.listNextBatchOfVersions(versionList);
				} else {
					break;
				}
			}

			// After all objects and object versions are deleted, delete the bucket.
			awsService.deleteBucket(bucketName);
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client couldn't
			// parse the response from Amazon S3.
			e.printStackTrace();
		}
	}

	private static AmazonS3 createConnectionWithCredentials(AWSCredentials credentials) {
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}

	private static AmazonS3 createConnectionWithCredentials() {
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new DefaultAWSCredentialsProviderChain())
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}
}