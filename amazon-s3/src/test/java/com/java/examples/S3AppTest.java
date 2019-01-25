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
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 블로그에 작성하기 위해 개별로 작성한 유니테스트라 개별로 돌려보시면 됩니다.
 * http://advenoh.tistory.com/
 */
@Slf4j
public class S3AppTest {
	private static final AWSCredentials credentials;
	private static final String BUCKET_NAME = "advenoh-s3-test";
	private static final String BUCKET_NAME2 = "advenoh-s3-test2";
	private static final String KEY_ID = "<key_id>";
	private static final String SECRET_ACCESS_KEY = "<secret_access_key>";

	static {
		credentials = new BasicAWSCredentials(
				KEY_ID,
				SECRET_ACCESS_KEY
		);
	}

	AmazonS3 s3client;

	private static void deleteAllObjectsAndBucket(AmazonS3 s3client, final String bucketName) {
		log.info("deleteAllObjectsAndBucket : bucketName: {}", bucketName);
		try {
			ObjectListing objectListing = s3client.listObjects(bucketName);

			while (true) {
				Iterator<S3ObjectSummary> objIter = objectListing.getObjectSummaries().iterator();
				while (objIter.hasNext()) {
					s3client.deleteObject(bucketName, objIter.next().getKey());
				}
				if (objectListing.isTruncated()) {
					objectListing = s3client.listNextBatchOfObjects(objectListing);
				} else {
					break;
				}
			}

			VersionListing versionList = s3client.listVersions(new ListVersionsRequest().withBucketName(bucketName));
			while (true) {
				Iterator<S3VersionSummary> versionIter = versionList.getVersionSummaries().iterator();
				while (versionIter.hasNext()) {
					S3VersionSummary vs = versionIter.next();
					s3client.deleteVersion(bucketName, vs.getKey(), vs.getVersionId());
				}

				if (versionList.isTruncated()) {
					versionList = s3client.listNextBatchOfVersions(versionList);
				} else {
					break;
				}
			}
			s3client.deleteBucket(bucketName);
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		//1.직접 지정해서
		//s3client = createConnectionWithCredentials(credentials);

		//2.환경 설정에서
		//		s3client = createConnectionWithCredentials();
	}

	@Test
	public void test_버킷_생성하기() {
		String[] bucketList = new String[] { BUCKET_NAME, BUCKET_NAME2 };

		for (String bucketName : bucketList) {
			if (s3client.doesBucketExist(bucketName)) {
				s3client.deleteBucket(bucketName);
			}
			s3client.createBucket(bucketName); //#1 - 버킷 생성
			assertTrue(s3client.doesBucketExist(bucketName));
		}
	}

	@Test
	public void test_buckets_목록_프린트하기() {
		List<Bucket> buckets = s3client.listBuckets();
		for (Bucket b : buckets) {
			System.out.println("* " + b.getName());
		}
	}

	@Test
	public void test_bucket에_파일_올리기() throws IOException {
		String webImageUrl = "https://images-na.ssl-images-amazon.com/images/I/51ADJwz5bwL._SY355_.png";
		String filename = "/Users/ykoh/Desktop/test.png";
		downloadFileFromURL(webImageUrl, filename);

		String bucketKey = "image/test.png";
		s3client.putObject(BUCKET_NAME, bucketKey, new File(filename));
	}

	@Test
	public void test_bucket에서_파일_다운로드하기() throws IOException {
		String destFilename = "/Users/ykoh/Desktop/test.png";

		S3Object s3object = s3client.getObject(BUCKET_NAME, "image/test.png");
		S3ObjectInputStream inputStream = s3object.getObjectContent();

		FileUtils.copyInputStreamToFile(inputStream, new File(destFilename));
	}

	@Test
	public void test_bucket에서_파일_삭제하기() throws IOException {
		String webImageUrl = "https://images-na.ssl-images-amazon.com/images/I/51ADJwz5bwL._SY355_.png";
		downloadFileFromURL(webImageUrl, "image/test1.png");
		downloadFileFromURL(webImageUrl, "image/test2.png");
		downloadFileFromURL(webImageUrl, "image/test3.png");

		//delete single file
		s3client.putObject(BUCKET_NAME, "image/test1.png", new File("/Users/ykoh/Desktop/test.png"));
		s3client.deleteObject(BUCKET_NAME, "image/test1.png");

		//delete multiple files
		String objkeyArr[] = { "image/test2.png", "image/test3.png" };
		DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(BUCKET_NAME).withKeys(objkeyArr);
		s3client.deleteObjects(delObjReq);
	}

	@Test
	public void test_bucket_삭제하기() {
		deleteAllObjectsAndBucket(s3client, BUCKET_NAME);
	}

	private AmazonS3 createConnectionWithCredentials(AWSCredentials credentials) {
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}

	private AmazonS3 createConnectionWithCredentials() {
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new DefaultAWSCredentialsProviderChain())
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}

	private void downloadFileFromURL(String sourceUrl, String destPath) throws IOException {
		FileUtils.copyURLToFile(new URL(sourceUrl), new File(destPath));
	}
}
