//package com.oracle.retry;
//
//public class RetryTest {
//
//	/**
//	 * getWithRetries
//	 * Issues a REST GET with retries. Throws Exception if the
//	 * GET could not succeed after retries attempts.
//	 * If the GET was successful, but returned anything other than an HTTP 200
//	 * status, return null;
//	 * If successful, returns the value.
//	 *
//	 * @param target     WebTarget for the REST call
//	 * @param key        The cache key to fetch
//	 * @param retries    Number of retries to attempt
//	 * @param retrySleep Sleep time in milliseconds between each attempt
//	 * @return String value, or null if the GET did not return an HTTP 200 code
//	 * @throws Exception
//	 */
//	public String getWithRetries(String  target,
//			String key,
//			int retries,
//			long retrySleep) throws Exception {
//		Response getResponse = null;
//		boolean success = false;
//		int getStatus;
//
//		// For handling retries
//		RetryOnException retryHandler = new RetryOnException(retries, retrySleep);
//
//		while (true) {
//			try {
//				getResponse = target
//						.path(CACHE_NAME + "/" + key)
//						.request(MediaType.APPLICATION_OCTET_STREAM)
//						.get();
//			}
//			// Catch exception and retry.
//			// If beyond retry limit, this will throw an exception.
//			catch (Exception ex) {
//				retryHandler.exceptionOccurred();
//				continue;
//			}
//
//			// If the status is not a 200, return a NULL.
//			// Otherwise, exit the loop to return the value.
//			getStatus = getResponse.getStatus();
//			if (getStatus != 200) {
//				return null;
//			} else {
//				break;
//			}
//		}
//
//		// Return the result
//		return getResponse.readEntity(String.class);
//	}
//}
