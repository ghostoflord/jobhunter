package vn.ghost.jobhunter.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.ghost.jobhunter.domain.Permission;
import vn.ghost.jobhunter.domain.Role;
import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.service.UserService;
import vn.ghost.jobhunter.util.SecurityUtil;
import vn.ghost.jobhunter.util.error.IdInvalidException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws Exception {

        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();
        System.out.println(">>> RUN preHandle");
        // System.out.println(">>> path= " + path);
        System.out.println(">>> httpMethod= " + httpMethod);
        System.out.println(">>> requestURI= " + requestURI);
        log.debug("Request to save path : {}", path);

        // check permission
        String email = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";
        if (email != null && !email.isEmpty()) {
            User user = this.userService.handleGetUserByUsername(email);
            if (user != null) {
                Role role = user.getRole();
                if (role != null) {
                    List<Permission> permissions = role.getPermissions();
                    boolean isAllow = permissions.stream().anyMatch(item -> item.getApiPath().equals(path)
                            && item.getMethod().equals(httpMethod));

                    if (isAllow == false) {
                        throw new IdInvalidException("Bạn không có quyền truy cập endpoint này.");
                    }
                } else {
                    throw new IdInvalidException("Bạn không có quyền truy cập endpoint này.");
                }
            }
        }

        return true;
    }
}
