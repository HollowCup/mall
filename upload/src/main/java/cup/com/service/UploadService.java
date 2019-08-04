package cup.com.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:11:27
 */
public interface UploadService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
