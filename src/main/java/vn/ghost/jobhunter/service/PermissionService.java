package vn.ghost.jobhunter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.ghost.jobhunter.domain.Permission;
import vn.ghost.jobhunter.domain.response.ResultPaginationDTO;
import vn.ghost.jobhunter.repository.PermissionRepository;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    // get all permission
    public ResultPaginationDTO fetchAllPermission(Specification<Permission> spec, Pageable pageable) {
        Page<Permission> pagePermission = this.permissionRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pagePermission.getTotalPages());
        mt.setTotal(pagePermission.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pagePermission.getContent());
        return rs;
    }

    // get permission by id
    public Permission fetchPermissionById(long id) {
        Optional<Permission> optionalP = this.permissionRepository.findById(id);
        if (optionalP.isPresent()) {
            return optionalP.get();

        }
        return null;
    }

    // post permission or update permission
    public Permission handleCreatePermission(Permission permission) {
        return this.permissionRepository.save(permission);
    }

    // put all permission
    public Permission handleUpdatePermission(Permission reqPermission) {
        Permission currentPermission = this.fetchPermissionById(reqPermission.getId());
        if (currentPermission != null) {
            currentPermission.setName(reqPermission.getName());
            currentPermission.setApiPath(reqPermission.getApiPath());
            currentPermission.setMethod(reqPermission.getMethod());
            currentPermission.setModule(reqPermission.getModule());
            currentPermission = this.permissionRepository.save(currentPermission);
        }
        return currentPermission;
    }

    // delete permission
    public void handleDeletePermission(long id) {
        // delete permission_role
        Optional<Permission> permissionOptional = this.permissionRepository.findById(id);
        Permission currentPermission = permissionOptional.get();
        currentPermission.getRoles().forEach(role -> role.getPermissions().remove(currentPermission));

        // delete permission
        this.permissionRepository.delete(currentPermission);
    }

    //
    public boolean isPermissionExist(Permission p) {
        return permissionRepository.existsByModuleAndApiPathAndMethod(
                p.getModule(),
                p.getApiPath(),
                p.getMethod());
    }
}
