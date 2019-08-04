package cup.com.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import cup.com.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:11:27
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private FastFileStorageClient storageClient;

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    /**
     * 支持上传的文件类型
     */
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg", "image/jpg");


    @Override
    public String upload(MultipartFile file) {
        /**
         * 1.图片信息校验
         *      1)校验文件类型
         *      2)校验图片内容
         * 2.保存图片
         *      1)生成保存目录
         *      2)保存图片
         *      3)拼接图片地址
         */
        try {
            String type = file.getContentType();
            if (!suffixes.contains(type)) {
                logger.info("上传文件失败，文件类型不匹配：{}", type);
                return null;
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                logger.info("上传失败，文件内容不符合要求");
                return null;
            }

            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), getExtension(file.getOriginalFilename()), null);

            String url = "192.168.0.111/" + storePath.getFullPath();
            return url;
        } catch (Exception e) {
            return null;
        }
    }

    public String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, ".");
    }
}
