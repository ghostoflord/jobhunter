package vn.ghost.jobhunter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.ghost.jobhunter.domain.Role;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.domain.response.ResultPaginationDTO;
import vn.ghost.jobhunter.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // get role by id
    public Role fetchRoleById(long id) {
        Optional<Role> Orole = this.roleRepository.findById(id);
        if (Orole.isPresent()) {
            return Orole.get();
        }
        return null;
    }

    // get all role
    public ResultPaginationDTO fetchAllRole(Specification<Role> spec, Pageable pageable) {
        Page<Role> pageRole = this.roleRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageRole.getTotalPages());
        mt.setTotal(pageRole.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageRole.getContent());
        return rs;
    }

    // delete role
    public void handleDeleteRole(long id) {
        this.roleRepository.deleteById(id);
    }

    // post role
    public Role handleCreateRole(Role role) {
        return this.roleRepository.save(role);
    }

    // put role
    public Role handleUpdateRole(Role reqRole) {
        Role currentRole = this.fetchRoleById(reqRole.getId());
        if (currentRole != null) {
            currentRole.setName(reqRole.getName());
            currentRole = this.roleRepository.save(currentRole);
        }
        return currentRole;
    }

}
