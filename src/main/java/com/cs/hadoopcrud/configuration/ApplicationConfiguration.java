package com.cs.hadoopcrud.configuration;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by dev on 06-04-2015.
 */

@Configuration
public class ApplicationConfiguration {

  @Autowired
  String hostName;

  @Autowired
  Integer port;

  @Autowired
  org.apache.hadoop.conf.Configuration configuration;

  @Bean (name = "configuration")
  public org.apache.hadoop.conf.Configuration getConfiguration(){
    org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
    configuration.set("fs.default.name", "hdfs://" + hostName + ":" + port);

    return configuration;
  }

  @Bean (name = "fileSystem")
  public FileSystem getFileSystem(){
    FileSystem fileSystem = null;
    try {
      fileSystem = FileSystem.get(configuration);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return fileSystem;
  }
}
