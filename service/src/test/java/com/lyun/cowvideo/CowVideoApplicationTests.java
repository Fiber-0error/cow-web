package com.lyun.cowvideo;

import com.lyun.cowvideo.util.CowObjectStorageUtil;
import com.lyun.cowvideo.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class CowVideoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(CowObjectStorageUtil.getUploadToken());
    }

    @Test
    void testCow(){
        System.out.println(TimeUtil.second2Time(3600));
    }

    @Test
    void testCowUpload(){
        File file = new File("C:\\Users\\LENOVO\\Pictures\\Saved Pictures\\发现.gif");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            CowObjectStorageUtil.upload(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
