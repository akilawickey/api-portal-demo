package assesment.apiportal.publisher.demo.services.impl;

import assesment.apiportal.publisher.demo.controller.dto.ApiPublishDTO;
import assesment.apiportal.publisher.demo.controller.dto.MessageResponceDTO;
import assesment.apiportal.publisher.demo.exception.ApiProvisionErrorResponce;
import assesment.apiportal.publisher.demo.exception.CustomException;
import assesment.apiportal.publisher.demo.exception.FileStorageException;
import assesment.apiportal.publisher.demo.models.Api;
import assesment.apiportal.publisher.demo.repositories.ApiRepository;
import assesment.apiportal.publisher.demo.services.ApiPublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiPublisherServiceImpl implements ApiPublisherService {

    @Autowired
    ApiRepository apiRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MessageResponceDTO publishApi(String name, MultipartFile swaggerFile, boolean isProtected) throws ApiProvisionErrorResponce, FileStorageException {

        if (name.isEmpty() && name == null) {
            throw new ApiProvisionErrorResponce("Please provide a valid endpoint name");
        }

        if (apiRepository.findAllByEndpointName(name)) {
            throw new ApiProvisionErrorResponce("Endpoint name already exists");
        }

        // Normalize file name
        String fileName = StringUtils.cleanPath(swaggerFile.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        try {
            Api api = new Api();
            api.setName(name);
            api.setFileType(swaggerFile.getContentType());
            api.setSwaggerFile(swaggerFile.getBytes());

            if (isProtected == false) {
                api.setProtected(false);
            } else {
                api.setProtected(true);
            }
            apiRepository.save(api);
            return new MessageResponceDTO("API creation successful");

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    @Override
    public List<ApiPublishDTO> getEndpoints(boolean isProtected) {
        try {
            return (apiRepository
                    .findAllByProtected(isProtected))
                    .stream()
                    .map(this::convertToUserLocationDTO)
                    .collect(Collectors.toList());

        }catch (NullPointerException e){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Empty results");
        }
    }

    private ApiPublishDTO convertToUserLocationDTO(Api api) {
        return modelMapper.map(api, ApiPublishDTO.class);
    }
}
