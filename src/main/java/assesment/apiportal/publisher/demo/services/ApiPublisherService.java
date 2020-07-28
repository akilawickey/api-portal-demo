package assesment.apiportal.publisher.demo.services;

import assesment.apiportal.publisher.demo.controller.dto.ApiPublishDTO;
import assesment.apiportal.publisher.demo.controller.dto.MessageResponceDTO;
import assesment.apiportal.publisher.demo.exception.ApiProvisionErrorResponce;
import assesment.apiportal.publisher.demo.exception.FileStorageException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApiPublisherService {
    MessageResponceDTO publishApi(String name, MultipartFile swaggerFile, boolean isProtected) throws ApiProvisionErrorResponce, FileStorageException;

    List<ApiPublishDTO> getEndpoints(boolean isProtected);
}
