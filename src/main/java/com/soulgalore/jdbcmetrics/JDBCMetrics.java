/******************************************************
 * JDBCMetrics
 * 
 *
 * Copyright (C) 2013 by Magnus Lundberg (http://magnuslundberg.com) & Peter Hedenskog (http://peterhedenskog.com)
 *
 ******************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License at
 * 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is 
 * distributed  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   
 * See the License for the specific language governing permissions and limitations under the License.
 *
 *******************************************************
 */
package com.soulgalore.jdbcmetrics;

import java.util.concurrent.TimeUnit;

import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.Histogram;
import com.yammer.metrics.core.Meter;
import com.yammer.metrics.core.Timer;
import com.yammer.metrics.core.MetricName;
import com.yammer.metrics.core.MetricsRegistry;

/**
 * Class responsible for holding all the Yammer Metrics.
 *
 */
public class JDBCMetrics {

	private static final String GROUP = "jdbc";
	private static final String TYPE_READ = "read";
	private static final String TYPE_WRITE = "write";
	
	private final MetricsRegistry registry = new MetricsRegistry();
	
	private final Counter totalNumberOfReads = registry.newCounter(new MetricName(
			GROUP, TYPE_READ, "total-of-reads"));

	private final Counter totalNumberOfWrites = registry.newCounter(new MetricName(
			GROUP, TYPE_WRITE, "total-of-writes"));

	private final Histogram readCountsPerRequest = registry.newHistogram(new MetricName(
			GROUP, TYPE_READ, "read-counts-per-request"), true);

	private final Histogram writeCountsPerRequest = registry.newHistogram(new MetricName(
			GROUP, TYPE_WRITE, "write-counts-per-request"), true);

	private final Meter readMeter = registry.newMeter(new MetricName(GROUP, TYPE_READ,
			"reads"), "jdbcread", TimeUnit.SECONDS);

	private final Meter writeMeter = registry.newMeter(new MetricName(GROUP,
			TYPE_WRITE, "writes"), "jdbcwrite", TimeUnit.SECONDS);
	
	private final Timer writeTimer = registry.newTimer(new MetricName(GROUP,
			TYPE_WRITE, "write-time"), TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
	
	private final Timer readTimer = registry.newTimer(new MetricName(GROUP,
			TYPE_WRITE, "read-time"), TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
	
	
	private static final JDBCMetrics INSTANCE = new JDBCMetrics();

	
	private JDBCMetrics() {
	}

	/**
	 * Get the instance.
	 * 
	 * @return the singleton instance.
	 */
	public static JDBCMetrics getInstance() {
		return INSTANCE;
	}

	public Counter getTotalNumberOfReads() {
		return totalNumberOfReads;
	}

	public Counter getTotalNumberOfWrites() {
		return totalNumberOfWrites;
	}

	public Histogram getReadCountsPerRequest() {
		return readCountsPerRequest;
	}

	public Histogram getWriteCountsPerRequest() {
		return writeCountsPerRequest;
	}

	public Meter getReadMeter() {
		return readMeter;
	}

	public Meter getWriteMeter() {
		return writeMeter;
	}
	
	public Timer getWriteTimer() {
		return writeTimer;
	}
	
	public Timer getReadTimer() {
		return readTimer;
	}
	
	public MetricsRegistry getRegistry() {
		return registry;
	}
	
}
