package assesment.apiportal.publisher.demo.exception;

public class FileStorageException extends Exception{
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public FileStorageException(){ super();}

    public FileStorageException(String errorMessage){
        super(errorMessage);
        this.errorMessage=errorMessage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}