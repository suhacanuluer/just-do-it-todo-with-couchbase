package org.suhacan.justdoit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

	@Override
	public String getConnectionString() {
		return "couchbase";
	}

	@Override
	public String getUserName() {
		return "user";
	}

	@Override
	public String getPassword() {
		return "asdf1234";
	}

	@Override
	public String getBucketName() {
		return "justdoit";
	}

}
