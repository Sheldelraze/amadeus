package com.minh.nguyen.form.material;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
public class MaterialUpdateForm extends BaseForm {
    @Required(displayFieldName = "name")
    @MaxLength(maxlength = 300, displayFieldName = "name")
    private String name;

    @MaxLength(maxlength = 1000, displayFieldName = "description")
    private String description;

    private Integer isPublic;

    private Integer sutId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getSutId() {
        return sutId;
    }

    public void setSutId(Integer sutId) {
        this.sutId = sutId;
    }
}
