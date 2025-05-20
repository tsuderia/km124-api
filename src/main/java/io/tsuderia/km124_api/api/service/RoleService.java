package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.data.entity.RoleEntity;
import io.tsuderia.km124_api.data.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity createRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    public RoleEntity findRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public String findRoleNameById(Long id) {
        RoleEntity role = findRoleById(id);
        return role.getName();
    }

    public List<RoleEntity> findAllRoles() {
        return roleRepository.findAll().stream().collect(Collectors.toList());
    }
}
