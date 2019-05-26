package ru.vsu.aviaticketsback.api.dto.response;

public class UserResponseDTO {
    private Long id;

    private String code;

    public UserResponseDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
