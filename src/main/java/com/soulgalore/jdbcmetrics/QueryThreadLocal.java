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

/**
 * Holds the number of queries per request.
 *
 */
public abstract class QueryThreadLocal {

	private static final ThreadLocal<ReadAndWrites> nrOfQueries = new ThreadLocal<ReadAndWrites>();
	
	public static ReadAndWrites getNrOfQueries(){
        return nrOfQueries.get();
    }
	
	// TODO depending on this is executed first, change this! :)
	public static void init() {
		nrOfQueries.set(new ReadAndWrites());
	}
	
	 public static void addRead() {
		 if (nrOfQueries.get()!=null) {
			 nrOfQueries.get().incReads();
		 }
	 }
	 
	 public static void addWrite() {
		 if (nrOfQueries.get()!=null) {
			 nrOfQueries.get().incWrites();
		 }
	 }

    public static void removeNrOfQueries(){
    	 nrOfQueries.remove();
    }

	
}
