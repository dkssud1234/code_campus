package hanium.cocam.dto;


import lombok.*;

@Getter
@Builder
public class ResponseDTO<T> {
    private boolean result;
    private int status;
    private String message;
    private T data;
}

