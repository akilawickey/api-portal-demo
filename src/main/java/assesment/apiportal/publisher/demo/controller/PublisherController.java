package assesment.apiportal.publisher.demo.controller;

import assesment.apiportal.publisher.demo.controller.dto.MessageResponceDTO;
import assesment.apiportal.publisher.demo.services.ApiPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/publisher")
public class PublisherController extends BaseController{

    @Autowired
    ApiPublisherService apiPublisherService;

    @GetMapping("/public")
    public ResponseEntity getPublicEndpoints() {

        boolean isProtected = false;
        return new ResponseEntity( apiPublisherService.getEndpoints(isProtected), HttpStatus.OK);
    }

    @GetMapping("/protected")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity getProtectedEndpoints() {

        boolean isProtected = true;
        return new ResponseEntity( apiPublisherService.getEndpoints(isProtected), HttpStatus.OK);
    }


    /**
     * @api {post} /api/publisher/publish
     * @apiParam {ApiPublishDTO} apiPublishDTO
     * Mandatory ApiPublishDTO object.
     * @apiParamExample {json} Request-Example:
     * {
     * apiPublishDTO
     * }
     */
    @PostMapping("/publish")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<MessageResponceDTO> apiPublishCall(@RequestParam("name") String name, @RequestParam("file") MultipartFile swaggerfile, @RequestHeader(value = "isProtected", defaultValue = "true", required = false) boolean isProtected,
                                                             HttpServletRequest request) throws Exception {

        return new ResponseEntity<>(apiPublisherService.publishApi(name, swaggerfile, isProtected), HttpStatus.OK);
    }
}
