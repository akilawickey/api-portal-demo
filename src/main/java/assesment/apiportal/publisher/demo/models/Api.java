package assesment.apiportal.publisher.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "apis",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] swaggerFile;
    private String fileType;
    private boolean isProtected;

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
