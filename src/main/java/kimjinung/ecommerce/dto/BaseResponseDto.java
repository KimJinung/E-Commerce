package kimjinung.ecommerce.dto;


import lombok.Data;


@Data
public class BaseResponseDto<T> {
    private int statusCode;
    private Long timestamp;
    private T response;

    public BaseResponseDto(int statusCode, T response) {
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis();
        this.response = response;
    }
}
