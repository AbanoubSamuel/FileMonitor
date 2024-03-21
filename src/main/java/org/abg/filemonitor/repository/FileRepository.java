package org.abg.filemonitor.repository;

import org.abg.filemonitor.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}