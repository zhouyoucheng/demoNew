package io;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: kira
 * @time: 2020/08/04 11:37
 */
public class ApkGLDownload {
    private static final Logger logger = LoggerFactory.getLogger(ApkGLDownload.class);
    public static void main(String[] args){
        Request request = new Request.Builder()
                .url("http://app.mi.com/download/14083?id=cn.com.spdb.mobilebank.per&ref=appstore.mobile_download&nonce=8610208373076431209%3A26607947&appClientId=2882303761517485445&appSignature=7rYXM-ZGjxJS-L7IqWIr2A3bwtc8Z8uwSsphhfkmg6A")
                .build();
        OkHttpClient client = new OkHttpClient();
        //异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                long total = response.body().contentLength();
                File filePath = new File("F:\\testDownload\\glapk");
                if (!filePath.exists()) {
                    filePath.mkdir();
                }
                File downloadFile = new File(filePath.getAbsolutePath() + File.separator + "fileName.apk");
                if (!downloadFile.exists()) {
                    try {
                        downloadFile.createNewFile();
                    } catch (IOException e) {
                        logger.info("请求异常：" + e.getMessage());
                    }
                }
                byte[] bytes = new byte[1024];
                int length = -1;
                InputStream is = null;
                FileOutputStream fileOutputStream = null;
                try {
                    is = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(downloadFile);
                    while (((length = is.read(bytes, 0, 1024)) != -1)) {
                        fileOutputStream.write(bytes, 0, length);
                    }
                    fileOutputStream.flush();
                    logger.info("文件下载成功 ：" );
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //关闭流
                    if (is != null) {
                        is.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
            }
        });
        System.out.println(111);
    }
}

