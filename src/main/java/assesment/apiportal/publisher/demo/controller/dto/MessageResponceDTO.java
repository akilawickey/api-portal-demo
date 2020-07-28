package assesment.apiportal.publisher.demo.controller.dto;

public class MessageResponceDTO {
    private String message;

    public MessageResponceDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
