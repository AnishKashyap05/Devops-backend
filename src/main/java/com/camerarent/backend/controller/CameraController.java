package com.camerarent.backend.controller;

import com.camerarent.backend.entity.Camera;
import com.camerarent.backend.repositoty.CameraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cameras")
@CrossOrigin
public class CameraController {

    private final CameraRepository cameraRepo;

    public CameraController(CameraRepository cameraRepo) {
        this.cameraRepo = cameraRepo;
    }

    // 1. Add a new camera
    @PostMapping
    public Camera addCamera(@RequestBody Camera camera) {
        return cameraRepo.save(camera);
    }

    // 2. Update camera details (full update)
    @PutMapping("/{id}")
    public ResponseEntity<Camera> updateCamera(@PathVariable Long id, @RequestBody Camera updatedCamera) {
        Optional<Camera> camOpt = cameraRepo.findById(id);
        if (camOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Camera camera = camOpt.get();
        camera.setName(updatedCamera.getName());
        camera.setModel(updatedCamera.getModel());
        camera.setDescription(updatedCamera.getDescription());
        camera.setPrice(updatedCamera.getPrice());
        camera.setAvailable(updatedCamera.isAvailable());
        cameraRepo.save(camera);
        return ResponseEntity.ok(camera);
    }

    @GetMapping
    public List<Camera> getAllCameras() {
        return cameraRepo.findAll();
    }

}
