package com.mc.mvc.Infra.util.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mc.mvc.Infra.util.file.FilePath;

@Repository
public interface FileRepository extends JpaRepository<FilePath, Long>{

}