package com.company.sales_management.config;

import com.company.sales_management.entity.Branch;
import com.company.sales_management.entity.Role;
import com.company.sales_management.entity.User;
import com.company.sales_management.repository.BranchRepository;
import com.company.sales_management.repository.RoleRepository;
import com.company.sales_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Initial Roles
        initRole("ADMIN", "Quản trị viên hệ thống");
        initRole("BRANCH_MANAGER", "Quản lý chi nhánh");
        initRole("CASHIER", "Nhân viên thu ngân");
        initRole("INVENTORY_STAFF", "Nhân viên kho");
        initRole("ACCOUNTANT", "Nhân viên kế toán");
        initRole("CUSTOMER", "Khách hàng");

        // 2. Initial Branch
        Branch branch = initBranch("Trung tâm", "Hà Nội");

        // 3. Initial Admin User
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        if (adminRole != null && !userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Lê Văn Admin");
            admin.setEmail("admin@emanage.vn");
            admin.setPhone("0123456789");
            admin.setActive(true);
            admin.setRole(adminRole);
            admin.setBranch(branch);
            userRepository.save(admin);
        }
    }

    private void initRole(String name, String description) {
        Optional<Role> existing = roleRepository.findByName(name);
        if (existing.isEmpty()) {
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }

    private Branch initBranch(String name, String address) {
        return branchRepository.findAll().stream()
                .filter(b -> b.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(() -> {
                    Branch b = new Branch();
                    b.setName(name);
                    b.setAddress(address);
                    return branchRepository.save(b);
                });
    }
}
