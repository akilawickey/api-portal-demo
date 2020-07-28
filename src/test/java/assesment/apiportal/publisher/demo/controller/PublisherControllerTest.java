package assesment.apiportal.publisher.demo.controller;

import assesment.apiportal.publisher.demo.controller.dto.ApiPublishDTO;
import assesment.apiportal.publisher.demo.services.ApiPublisherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
@ContextConfiguration(classes = ApiPublisherService.class)
class PublisherControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mvc;
    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mvc = builder.build();
    }
    @Test
    void getPublicEndpoints() {
        boolean assertionStatus = false;

        List<ApiPublishDTO> apiPublishDTOList = new ArrayList<>();
        ApiPublishDTO apiPublishDTO = new ApiPublishDTO();
        apiPublishDTO.setName("newApi");
        apiPublishDTO.setFileType("/newApi");
        apiPublishDTO.setSwaggerFile("Hello World".getBytes());
        apiPublishDTOList.add(apiPublishDTO);

        try{
            ApiPublisherService apiPublisherService = mock(ApiPublisherService.class);
            Mockito.when(apiPublisherService.getEndpoints(isA(Boolean.class))).thenReturn(apiPublishDTOList);
            apiPublisherService.getEndpoints(false);

            verify(apiPublisherService, times(1)).getEndpoints(false);

            try {
                ResultActions resultActions= mvc.perform(MockMvcRequestBuilders.get("/api/publisher/private").contentType(MediaType.APPLICATION_JSON));

                if (resultActions != null) {

                    int httpStatus = resultActions.andReturn().getResponse().getStatus();
                    if (httpStatus == 401) {
                        assertionStatus = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.assertEquals(true, assertionStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getProtectedEndpoints() {
    }

    @Test
    void apiPublishCall() {
    }
}