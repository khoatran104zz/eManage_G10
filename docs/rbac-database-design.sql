-- =========================================================================
-- DATABASE SCHEMA DESIGN FOR ROLE BASED ACCESS CONTROL (RBAC) - eManage
-- =========================================================================

-- 1. Create ROLES Table
CREATE TABLE IF NOT EXISTS roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Create PERMISSIONS Table
CREATE TABLE IF NOT EXISTS permissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Create ROLE_PERMISSIONS Join Table (Many-to-Many Relationship)
CREATE TABLE IF NOT EXISTS role_permissions (
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

-- 4. Create USERS Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    branch_id INT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- =========================================================================
-- MOCK DATA SEEDING (Dữ liệu mẫu)
-- =========================================================================

-- Seed Roles
INSERT INTO roles (id, name, description) VALUES
(1, 'ADMIN', 'Chủ doanh nghiệp, có toàn quyền quản lý hệ thống.'),
(2, 'BRANCH_MANAGER', 'Quản lý một chi nhánh.'),
(3, 'CASHIER', 'Nhân viên bán hàng / Thu ngân.'),
(4, 'INVENTORY_STAFF', 'Nhân viên quản lý kho.'),
(5, 'ACCOUNTANT', 'Kế toán quản lý thu chi, công nợ.'),
(6, 'CUSTOMER', 'Khách hàng thân thiết.');

-- Seed Permissions
INSERT INTO permissions (id, name, description) VALUES
(1, 'VIEW_DASHBOARD_GLOBAL', 'Xem dashboard tổng quan toàn hệ thống'),
(2, 'VIEW_DASHBOARD_BRANCH', 'Xem dashboard chi nhánh'),
(3, 'MANAGE_BRANCHES', 'Quản lý chi nhánh'),
(4, 'MANAGE_EMPLOYEES', 'Quản lý nhân viên'),
(5, 'MANAGE_PRODUCTS', 'Quản lý sản phẩm (thêm/sửa/xóa)'),
(6, 'VIEW_PRODUCTS', 'Xem danh sách sản phẩm'),
(7, 'MANAGE_INVENTORY', 'Quản lý xuất/nhập/kiểm kho'),
(8, 'MANAGE_ORDERS', 'Quản lý đơn hàng'),
(9, 'MANAGE_CUSTOMERS', 'Quản lý danh sách khách hàng'),
(10, 'MANAGE_PROMOTIONS', 'Quản lý khuyến mãi'),
(11, 'MANAGE_FINANCE', 'Quản lý tài chính (thu chi, công nợ)'),
(12, 'VIEW_REPORTS_GLOBAL', 'Xem toàn bộ báo cáo'),
(13, 'VIEW_REPORTS_BRANCH', 'Xem báo cáo chi nhánh'),
(14, 'CONFIGURE_SYSTEM', 'Cấu hình hệ thống'),
(15, 'MANAGE_RBAC', 'Phân quyền người dùng'),
(16, 'ACCESS_POS', 'Truy cập màn hình POS bán hàng'),
(17, 'VIEW_PERSONAL_INFO', 'Xem thông tin cá nhân và lịch sử cá nhân');

-- Seed Role Permissions mapping
-- ADMIN (All permissions)
INSERT INTO role_permissions (role_id, permission_id) VALUES
(1, 1), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 14), (1, 15), (1, 16);

-- BRANCH_MANAGER
INSERT INTO role_permissions (role_id, permission_id) VALUES
(2, 2), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 10), (2, 13);

-- CASHIER
INSERT INTO role_permissions (role_id, permission_id) VALUES
(3, 16), (3, 8), (3, 9);

-- INVENTORY_STAFF
INSERT INTO role_permissions (role_id, permission_id) VALUES
(4, 6), (4, 7);

-- ACCOUNTANT
INSERT INTO role_permissions (role_id, permission_id) VALUES
(5, 11), (5, 13);

-- CUSTOMER
INSERT INTO role_permissions (role_id, permission_id) VALUES
(6, 17);

-- Seed Sample Users (passwords are hashed placeholders)
INSERT INTO users (name, email, password, role_id, branch_id) VALUES
('Lê Văn Admin', 'admin@emanage.vn', '$2a$10$abcdefghijklmnopqrstuv', 1, NULL),
('Nguyễn Văn Manager', 'manager@emanage.vn', '$2a$10$abcdefghijklmnopqrstuv', 2, 1),
('Phạm Thị Cashier', 'cashier@emanage.vn', '$2a$10$abcdefghijklmnopqrstuv', 3, 1),
('Trần Văn Kho', 'inventory@emanage.vn', '$2a$10$abcdefghijklmnopqrstuv', 4, 1),
('Hoàng Thị Kế Toán', 'accountant@emanage.vn', '$2a$10$abcdefghijklmnopqrstuv', 5, NULL),
('Khách Hàng A', 'customer@gmail.com', '$2a$10$abcdefghijklmnopqrstuv', 6, NULL);
