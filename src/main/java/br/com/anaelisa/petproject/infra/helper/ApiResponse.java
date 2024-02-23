package br.com.anaelisa.petproject.infra.helper;

public class ApiResponse<T> {

        private String message;
        private T content;
        private Long status;
        private String error;

        public ApiResponse(String message, T content, Long status, String error) {
            this.message = message;
            this.content = content;
            this.status = status;
            this.error = error;
        }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
