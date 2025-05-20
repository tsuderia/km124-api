package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.service.RoleService;
import io.tsuderia.km124_api.data.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findRoleById(id));
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getRoleNameById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findRoleNameById(id));
    }

//    TODO: update, delete
}
