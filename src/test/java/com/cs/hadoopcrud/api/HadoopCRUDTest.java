package com.cs.hadoopcrud.api;

import junit.framework.TestCase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class HadoopCRUDTest extends TestCase {

  @Autowired
  HadoopCRUD hadoopCrud;

  @Autowired
  FileSystem fileSystem;

  @Autowired
  Configuration configuration;

  @Test
  public void testCreate() throws Exception {
//    FileSystem fileSystem = hadoopCrud.connect("localhost", 19000, "");
    Boolean created = hadoopCrud.create(fileSystem, configuration, "D:/test.txt", "/testFolder/test.txt");
    Assert.assertTrue(created);
//    fileSystem.close();
  }

  @Test
  public void testRead() throws Exception {
//    FileSystem fileSystem = hadoopCrud.connect("localhost", 19000, "");
    Boolean isRead = hadoopCrud.read(fileSystem, configuration, "/testFolder/test.txt", "D:/test.txt");
    Assert.assertTrue(isRead);
//    fileSystem.close();
  }

  @Test
  public void testDelete() throws Exception {
//    FileSystem fileSystem = hadoopCrud.connect("localhost", 19000, "");
    Boolean deleted = hadoopCrud.delete(fileSystem, "/testFolder/test.txt");
    Assert.assertTrue(deleted);
//    fileSystem.close();
  }
}