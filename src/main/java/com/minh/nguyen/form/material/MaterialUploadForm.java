package com.minh.nguyen.form.material;

import com.minh.nguyen.form.BaseForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
public class MaterialUploadForm extends BaseForm {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
