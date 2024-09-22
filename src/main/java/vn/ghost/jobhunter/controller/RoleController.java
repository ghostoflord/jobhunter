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
import vn.ghost.jobhunter.domain.Role;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.domain.response.ResCreateUserDTO;
import vn.ghost.jobhunter.domain.response.ResUpdateUserDTO;
import vn.ghost.jobhunter.domain.response.ResultPaginationDTO;
import vn.ghost.jobhunter.service.RoleService;
import vn.ghost.jobhunter.util.annotation.ApiMessage;
import vn.ghost.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    @ApiMessage("fetch all role")
    public ResponseEntity<ResultPaginationDTO> getAllRole(
            @Filter Specification<Role> spec,
            Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(
                this.roleService.fetchAllRole(spec, pageable));
    }

    @PostMapping("/roles")
    @ApiMessage("Create a new role")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role postManRole)
            throws IdInvalidException {
        // check name
        if (this.roleService.existByName(postManRole.getName())) {
            throw new IdInvalidException("Role với name = " + postManRole.getName() + " đã tồn tại");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.handleCreateRole(postManRole));
    }

    @DeleteMapping("/roles/{id}")
    @ApiMessage("Delete a role")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") long id)
            throws IdInvalidException {
        // check id
        if (this.roleService.fetchRoleById(id) == null) {
            throw new IdInvalidException("Role với id = " + id + " không tồn tại");
        }
        this.roleService.handleDeleteRole(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/roles")
    public ResponseEntity<Role> updateRole(@Valid @RequestBody Role role) throws IdInvalidException {
        // check id
        if (this.roleService.fetchRoleById(role.getId()) == null) {
            throw new IdInvalidException("Role với id = " + role.getId() + " không tồn tại");
        }

        // check name
        if (this.roleService.existByName(role.getName())) {
            throw new IdInvalidException("Role với name = " + role.getName() + " đã tồn tại");
        }
        return ResponseEntity.ok(this.roleService.handleUpdateRole(role));
    }
}
