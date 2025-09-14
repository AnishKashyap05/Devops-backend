package com.camerarent.backend.repositoty;

import com.camerarent.backend.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
