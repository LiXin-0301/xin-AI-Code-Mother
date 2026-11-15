package com.lixin.xinaicodemother.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
public interface ProjectDownloadService {
    /**
     * 下载项目为压缩包
     * @param projectPath
     * @param downloadFileName
     * @param response
     */
    void downloadProjectAsZip(String projectPath, String downloadFileName, HttpServletResponse response);
}
