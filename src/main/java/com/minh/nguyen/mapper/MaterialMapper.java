package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.MaterialDTO;
import com.minh.nguyen.entity.MaterialEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("MaterialMapper")
public interface MaterialMapper extends BaseMapper<MaterialEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertMaterial(MaterialEntity record);

    List<MaterialDTO> getMaterial(@Param("handle") String handle, @Param("getAllMaterial") Boolean getAllMaterial);

    List<MaterialDTO> getMaterialInCourse(@Param("ceId") Integer ceId, @Param("getAllMaterial") Boolean getAllMaterial);

    List<MaterialDTO> getMaterialToAdd(@Param("ceId") Integer ceId, @Param("handle") String handle);
}
