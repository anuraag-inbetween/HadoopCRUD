package com.cs.hadoopcrud.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

/**
 * Created by dev on 02-04-2015.
 */
public class HadoopCRUD {

  public FileSystem connect(String hostName, Integer port, String filePath){
    FileSystem fileSystem = null;
    try {
      Configuration conf = new Configuration();
      /*URI uri = URI.create("hdfs://" + hostName + ":" + port + "/" + filePath);
      fileSystem = FileSystem.get (uri, conf);*/
      conf.set("fs.default.name", "hdfs://" + hostName + ":" + port);
      fileSystem = FileSystem.get(conf);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return fileSystem;
  }

  public boolean create(FileSystem fileSystem, Configuration conf, String localInputPath, String outputPath){

    try {
      Path output = new Path(outputPath);
      OutputStream outputStream = fileSystem.create(output);
      InputStream inputStream = new BufferedInputStream(new FileInputStream(localInputPath));
      IOUtils.copyBytes(inputStream, outputStream, conf);

      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean read(FileSystem fileSystem, Configuration conf, String inputPath, String localOutputPath){

    try{
      Path input = new Path(inputPath);
      InputStream inputStream = fileSystem.open(input);
      OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localOutputPath));
      IOUtils.copyBytes(inputStream, outputStream, conf);

      return true;
    }catch (IOException e){
      e.printStackTrace();
      return false;
    }
  }

  public Boolean delete(FileSystem fileSystem, String filePath){
    Boolean deleted = false;

    try {
      Path path = new Path(filePath);
      deleted = fileSystem.delete(path);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return deleted;
  }
}
