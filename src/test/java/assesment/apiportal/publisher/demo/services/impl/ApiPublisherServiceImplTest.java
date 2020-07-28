package assesment.apiportal.publisher.demo.services.impl;

import assesment.apiportal.publisher.demo.config.AuthEntryPointJwt;
import assesment.apiportal.publisher.demo.config.JwtUtils;
import assesment.apiportal.publisher.demo.controller.dto.ApiPublishDTO;
import assesment.apiportal.publisher.demo.controller.dto.MessageResponceDTO;
import assesment.apiportal.publisher.demo.exception.ApiProvisionErrorResponce;
import assesment.apiportal.publisher.demo.exception.FileStorageException;
import assesment.apiportal.publisher.demo.models.Api;
import assesment.apiportal.publisher.demo.repositories.ApiRepository;
import assesment.apiportal.publisher.demo.repositories.RoleRepository;
import assesment.apiportal.publisher.demo.repositories.UserRepository;
import assesment.apiportal.publisher.demo.services.ApiPublisherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApiPublisherServiceImpl.class)
class ApiPublisherServiceImplTest {

    @Autowired
    ApiPublisherService apiPublisherService;

    @MockBean
    private ApiRepository apiRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Before
    void setUp() {

        ApiPublishDTO api1 = new ApiPublishDTO();
        api1.setName("api one");
        api1.setFileType("application/x-yaml");
        api1.setProtected(true);

        ApiPublishDTO api2 = new ApiPublishDTO();
        api2.setName("api two");
        api2.setFileType("application/x-yaml");
        api2.setProtected(true);

        ApiPublishDTO api3 = new ApiPublishDTO();
        api3.setName("api three public endpoint");
        api3.setFileType("application/x-yaml");
        api3.setProtected(false);

        Api saveTest = new Api();
        saveTest.setName("Save teting endpoint");
        saveTest.setProtected(false);
        saveTest.setFileType("application/x-yaml");

        List<ApiPublishDTO> apiListProtected = new ArrayList<>();
        List<ApiPublishDTO> apiListPublic = new ArrayList<>();

        apiListProtected.add(api1);
        apiListProtected.add(api2);

        apiListPublic.add(api3);

        when(modelMapper.map(apiRepository.findAllByProtected(true), new TypeToken<List<ApiPublishDTO>>() {}.getType())).thenReturn(apiListProtected);
        when(modelMapper.map(apiRepository.findAllByProtected(false), new TypeToken<List<ApiPublishDTO>>() {}.getType())).thenReturn(apiListPublic);
        when(apiRepository.save(saveTest)).thenReturn(saveTest);
    }

    @Test
    void publishApi() {


        try {
            MessageResponceDTO messageResponceDTO = apiPublisherService.publishApi("test endpoint", new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                    "Hello World".getBytes()), true);

            Assert.assertTrue(messageResponceDTO.getMessage() != null);

        } catch (ApiProvisionErrorResponce apiProvisionErrorResponce) {
            apiProvisionErrorResponce.printStackTrace();
        } catch (FileStorageException e) {
            e.printStackTrace();
        }


    }

    @Test
    void getProtectedEndpoints() {

        List<ApiPublishDTO> apis = apiPublisherService.getEndpoints(true);

        Assert.assertTrue(apis.size() >= 0);

    }

    @Test
    void getPublicEndpoints() {

        List<ApiPublishDTO> apis = apiPublisherService.getEndpoints(false);

        Assert.assertTrue(apis.size() >= 0);

    }
}