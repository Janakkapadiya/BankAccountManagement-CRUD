package com.accountmanagement.practice.dto.FileResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class FileResponse {
    private String fileName;
    private String url;
    private String message;
}
