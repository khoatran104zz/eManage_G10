package com.company.sales_management.service.impl;

import com.company.sales_management.dto.request.UserRequest;
import com.company.sales_management.dto.response.UserResponse;
import com.company.sales_management.entity.Branch;
import com.company.sales_management.entity.Role;
import com.company.sales_management.entity.User;
import com.company.sales_management.exception.BadRequestException;
import com.company.sales_management.exception.ResourceNotFoundException;
import com.company.sales_management.repository.BranchRepository;
import com.company.sales_management.repository.RoleRepository;
import com.company.sales_management.repository.UserRepository;
import com.company.sales_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));
        java.util.List<org.springframework.security.core.GrantedAuthority> authorities = new java.util.ArrayList<>();
        if (user.getRole() != null) {
            authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public Page<UserResponse> findAll(String search, Pageable pageable) {
        if (search != null && !search.isBlank()) {
            return userRepository.findBySearchTerm(search, pageable).map(this::toResponse);
        }
        return userRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    public UserResponse findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Người dùng", "id", id));
        return toResponse(user);
    }

    @Override
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Tên đăng nhập đã tồn tại: " + request.getUsername());
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setActive(request.getActive() != null ? request.getActive() : true);
        setRelations(request, user);
        return toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(Integer id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Người dùng", "id", id));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        if (request.getActive() != null) user.setActive(request.getActive());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        setRelations(request, user);
        return toResponse(userRepository.save(user));
    }

    @Override
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Người dùng", "id", id);
        }
        userRepository.deleteById(id);
    }

    private void setRelations(UserRequest request, User user) {
        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vai trò", "id", request.getRoleId()));
            user.setRole(role);
        }
        if (request.getBranchId() != null) {
            Branch branch = branchRepository.findById(request.getBranchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Chi nhánh", "id", request.getBranchId()));
            user.setBranch(branch);
        }
    }

    private UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setActive(user.getActive());
        if (user.getRole() != null) {
            response.setRoleId(user.getRole().getId());
            response.setRoleName(user.getRole().getName());
        }
        if (user.getBranch() != null) {
            response.setBranchId(user.getBranch().getId());
            response.setBranchName(user.getBranch().getName());
        }
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
