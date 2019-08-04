package cup.com.controller;

import cup.com.service.impl.UploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:11:31
 */
@RestController
@RequestMapping("upload")
@Api(tags = "UploadController", description = "图片上传相关接口")
public class UploadController {
    @Autowired
    private UploadServiceImpl uploadServiceImpl;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("/image")
    @ApiOperation(value = "图片上传", notes = "图片上传")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
        String url= uploadServiceImpl.upload(file);
        if(StringUtils.isBlank(url)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(url);
    }
}
