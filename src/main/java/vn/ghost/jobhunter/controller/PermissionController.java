package vn.ghost.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.ghost.jobhunter.domain.Permission;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.domain.response.ResCreateUserDTO;
import vn.ghost.jobhunter.domain.response.ResUpdateUserDTO;
import vn.ghost.jobhunter.domain.response.ResultPaginationDTO;
import vn.ghost.jobhunter.service.PermissionService;
import vn.ghost.jobhunter.util.annotation.ApiMessage;
import vn.ghost.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    // get all permission
    @GetMapping("/permission")
    @ApiMessage("fetch all user")
    public ResponseEntity<ResultPaginationDTO> getAllPermission(
            @Filter Specification<Permission> spec,
            Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.permissionService.fetchAllPermission(spec, pageable));
    }

    // post permission
    @PostMapping("/permission")
    @ApiMessage("Create a new user")
    public ResponseEntity<Permission> createPermission(@Valid @RequestBody Permission postManPermission)
            throws IdInvalidException {
        // check exist
        if (this.permissionService.isPermissionExist(postManPermission)) {
            throw new IdInvalidException("Permission đã tồn tại.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.permissionService.handleCreatePermission(postManPermission));
    }

    // put permission
    @PutMapping("/permission")
    public ResponseEntity<Permission> updatePermission(@Valid @RequestBody Permission permission)
            throws IdInvalidException {
        // check exist by id
        if (this.permissionService.fetchPermissionById(permission.getId()) == null) {
            throw new IdInvalidException("Permission với id = " + permission.getId() + " không tồn tại.");
        }

        // check exist by module, apiPath and method
        if (this.permissionService.isPermissionExist(permission)) {
            throw new IdInvalidException("Permission đã tồn tại.");
        }
        return ResponseEntity.ok(this.permissionService.handleUpdatePermission(permission));
    }

    // delete
    @DeleteMapping("/permission/{id}")
    @ApiMessage("Delete a user")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id)
            throws IdInvalidException {
        // check exist by id
        if (this.permissionService.fetchPermissionById(id) == null) {
            throw new IdInvalidException("Permission với id = " + id + " không tồn tại.");
        }
        this.permissionService.handleDeletePermission(id);
        return ResponseEntity.ok(null);
    }

}
