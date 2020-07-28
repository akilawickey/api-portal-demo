package assesment.apiportal.publisher.demo.controller.dto;

public class ApiPublishDTO {
    private Long id;
    private String name;
    private byte[] swaggerFile;
    private String fileType;
    private boolean isProtected;

    public ApiPublishDTO(Long id, String name, byte[] swaggerFile, String fileType, boolean isProtected) {
        this.id = id;
        this.name = name;
        this.swaggerFile = swaggerFile;
        this.fileType = fileType;
        this.isProtected = isProtected;
    }

    public ApiPublishDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getSwaggerFile() {
        return swaggerFile;
    }

    public void setSwaggerFile(byte[] swaggerFile) {
        this.swaggerFile = swaggerFile;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }
}
