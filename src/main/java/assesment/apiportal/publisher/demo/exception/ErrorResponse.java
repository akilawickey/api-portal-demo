package assesment.apiportal.publisher.demo.exception;

public class ErrorResponse {

    private int errorCode;
    private String desc;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
