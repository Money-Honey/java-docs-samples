/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.monitoring;

import com.google.api.Metric;
import com.google.api.MonitoredResource;

// Imports the Google Cloud client library
import com.google.cloud.monitoring.spi.v3.MetricServiceClient;

import com.google.monitoring.v3.CreateTimeSeriesRequest;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.ProjectName;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;
import com.google.monitoring.v3.TypedValue;
import com.google.protobuf.util.Timestamps;


public class Snippets {

  /**
   * Exercises the methods defined in this class.
   *
   * <p>Assumes that you are authenticated using the Google Cloud SDK (using
   * {@code gcloud auth application-default-login}).
   */
  public static void main(String[] args) throws Exception {
    Snippets snippets = new Snippets();
    System.out.println("Stackdriver snippets");
    System.out.println();
    printUsage();
    while (true) {
      String commandLine = System.console().readLine("> ");
      if (commandLine.trim().isEmpty()) {
        break;
      }
      try {
        snippets.handleCommandLine(commandLine);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        printUsage();
      }
    }
    System.out.println("exiting");
    System.exit(0);
  }

  void createMetricDescriptor() {

  }


  /**
   * Handles a single command.
   *
   * @param commandLine A line of input provided by the user
   */
  void handleCommandLine(String commandLine) {
    String[] args = commandLine.split("\\s+");

    if (args.length < 1) {
      throw new IllegalArgumentException("not enough args");
    }

    String command = args[0];
    switch (command) {
      case "new-metric-descriptor":
        // Everything after the first whitespace token is interpreted to be the description.
        args = commandLine.split("\\s+", 2);
        if (args.length != 1) {
          throw new IllegalArgumentException("missing description");
        }
        // Set created to now() and done to false.
        createMetricDescriptor();
        System.out.println("Metric descriptor created");
        break;
    }
  }

  private void assertArgsLength(String[] args, int expectedLength) {
    if (args.length != expectedLength) {
      throw new IllegalArgumentException(
          String.format("expected exactly %d arg(s), found %d", expectedLength, args.length));
    }
  }

  private static void printUsage() {
    System.out.println("Usage:");
    System.out.println();
    System.out.println("  new Creates a metric descriptorl");
    System.out.println("  done <task-id>     Marks a task as done");
    System.out.println("  list               Lists all tasks by creation time");
    System.out.println("  delete <task-id>   Deletes a task");
    System.out.println();
  }

}
