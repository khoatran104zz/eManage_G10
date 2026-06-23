# Sprint Plan cho Sales Management Platform

> Mục tiêu: chia dự án thành 4 giai đoạn để AI Agent có thể follow, làm từng phần Backend và Frontend, tránh làm lan man hoặc rewrite toàn bộ project.

## Nguyên tắc chung cho AI Agent

- Không tạo project mới.
- Không rewrite toàn bộ code.
- Làm theo từng sprint.
- Mỗi sprint phải hoàn thành cả Backend và Frontend tương ứng.
- Backend phải kết nối PostgreSQL thật.
- Frontend không dùng mock data nếu API đã có.
- Mỗi API cần có test bằng Postman/curl hoặc hướng dẫn test.
- Sau mỗi sprint phải cập nhật `task.md` và `implementation_plan.md`.

---

# Phase 1 — Core Foundation: Auth, RBAC, Shop Scope

## Mục tiêu

Xây nền tảng bắt buộc trước khi làm nghiệp vụ: đăng nhập thật, phân quyền, shop/branch scope, kết nối frontend-backend-database.

## Sprint 1.1 — Backend: Authentication thật

### Backend tasks

- Kiểm tra `AuthController`, `AuthService`, `UserRepository`, `User`, `Role`.
- Login phải kiểm tra user trong PostgreSQL.
- Không cho đăng nhập bằng tài khoản không tồn tại.
- Không dùng mock login/fake token.
- Dùng BCrypt để kiểm tra password.
- JWT phải chứa tối thiểu:
  - `userId`
  - `username/email`
  - `role`
  - `shopId`
  - `branchId` nếu có

### Frontend tasks

- Kiểm tra form login Next.js.
- Gọi đúng endpoint backend.
- Cấu hình `NEXT_PUBLIC_API_URL`.
- Login thành công mới lưu token.
- Login thất bại phải hiển thị lỗi.
- Không tự redirect nếu chưa có token hợp lệ.

### Done khi

- Đúng tài khoản/mật khẩu thì vào dashboard.
- Sai tài khoản/mật khẩu thì bị chặn.
- Tắt backend thì frontend báo lỗi kết nối.

---

## Sprint 1.2 — Backend: Role & Permission Foundation

### Backend tasks

- Chuẩn hóa role:
  - `SYSTEM_ADMIN`
  - `SHOP_OWNER`
  - `BRANCH_MANAGER`
  - `CASHIER`
  - `INVENTORY_STAFF`
  - `ACCOUNTANT`
- Tạo/kiểm tra bảng:
  - `users`
  - `roles`
  - `permissions`
  - `user_roles`
- Seed dữ liệu role mặc định.
- Thêm permission theo module/feature nếu đã có entity.
- Thêm kiểm tra role ở API bằng Spring Security.

### Frontend tasks

- Lưu role từ login response.
- Điều hướng dashboard theo role.
- Ẩn menu không thuộc quyền.
- Không chỉ ẩn frontend, backend vẫn phải chặn API.

### Done khi

- Staff không vào được màn hình admin.
- Shop Owner vào được màn hình quản lý shop.
- API trả 403 nếu role không đủ quyền.

---

## Sprint 1.3 — Backend: Shop, Branch, Tenant Scope

### Backend tasks

- Kiểm tra/tạo entity:
  - `Shop`
  - `Branch`
  - `User`
- User phải thuộc shop/branch.
- Dữ liệu nghiệp vụ phải gắn `shop_id`.
- Branch Manager/Staff chỉ xem dữ liệu thuộc shop/branch được gán.
- SYSTEM_ADMIN có thể xem danh sách shop.

### Frontend tasks

- Sau login lưu `shopId`, `branchId`.
- Dashboard chỉ hiển thị dữ liệu shop hiện tại.
- Không hardcode shop/branch.

### Done khi

- Shop A không xem được dữ liệu Shop B.
- Staff không lấy được dữ liệu ngoài branch nếu có branch scope.

---

# Phase 2 — Master Data: Product, Customer, Supplier, Inventory Base

## Mục tiêu

Làm dữ liệu nền cho bán hàng: sản phẩm, danh mục, thương hiệu, nhà cung cấp, khách hàng, tồn kho.

---

## Sprint 2.1 — Product, Category, Brand

### Backend tasks

- Hoàn thiện CRUD:
  - Product
  - Category
  - Brand
- Mỗi bản ghi phải có `shop_id`.
- Kiểm tra SKU/barcode trùng trong cùng shop.
- Soft delete sản phẩm.
- API filter/search/pagination.

### Frontend tasks

- Trang danh sách sản phẩm.
- Trang thêm/sửa sản phẩm.
- Dropdown category/brand lấy từ API thật.
- Không dùng mock product.

### Done khi

- Tạo/sửa/xóa mềm sản phẩm được.
- Reload lại vẫn còn dữ liệu từ PostgreSQL.
- Shop khác không thấy sản phẩm này.

---

## Sprint 2.2 — Supplier & Customer

### Backend tasks

- Hoàn thiện CRUD:
  - Supplier
  - Customer
- Gắn `shop_id`.
- Tìm kiếm khách hàng theo tên/số điện thoại.
- Tìm kiếm nhà cung cấp theo tên/số điện thoại/email.
- Soft delete.

### Frontend tasks

- Màn hình Customer Management.
- Màn hình Supplier Management.
- Form thêm/sửa/xóa mềm.
- Search/filter/pagination.

### Done khi

- Dữ liệu khách hàng/nhà cung cấp lưu PostgreSQL thật.
- Frontend gọi API thật.

---

## Sprint 2.3 — Inventory Base

### Backend tasks

- Hoàn thiện tồn kho cơ bản:
  - Stock/Inventory
  - StockTransaction
  - Nhập kho
  - Xuất kho
  - Lịch sử giao dịch tồn kho
- Mỗi giao dịch phải gắn:
  - productId
  - shopId
  - branchId/warehouseId nếu có
  - quantity
  - transactionType
  - createdBy

### Frontend tasks

- Màn hình tồn kho.
- Màn hình lịch sử nhập/xuất kho.
- Form nhập kho/xuất kho.
- Cảnh báo tồn kho thấp nếu đã có field reorder point.

### Done khi

- Nhập kho làm tăng tồn.
- Xuất kho làm giảm tồn.
- Không cho xuất quá tồn.

---

# Phase 3 — Sales Operation: Order, POS, Payment

## Mục tiêu

Làm luồng vận hành bán hàng thực tế: tạo đơn, POS, thanh toán, trừ tồn kho.

---

## Sprint 3.1 — Sales Order

### Backend tasks

- Hoàn thiện:
  - Order
  - OrderItem
  - OrderService
- Tạo đơn hàng với nhiều sản phẩm.
- Tính tổng tiền:
  - subtotal
  - discount
  - tax nếu có
  - total
- Kiểm tra tồn kho trước khi confirm đơn.
- Trừ tồn khi đơn được xác nhận/thanh toán.

### Frontend tasks

- Màn hình tạo đơn hàng.
- Chọn khách hàng.
- Chọn sản phẩm từ API.
- Tính tổng tiền trên UI và xác nhận bằng backend.
- Danh sách đơn hàng.

### Done khi

- Tạo đơn thành công.
- Tồn kho giảm đúng.
- Không tạo được đơn nếu thiếu tồn.

---

## Sprint 3.2 — POS Basic

### Backend tasks

- API tìm sản phẩm nhanh cho POS.
- API tạo hóa đơn nhanh.
- API thanh toán đơn POS.
- Ghi nhận cashier/user tạo đơn.

### Frontend tasks

- Giao diện POS cơ bản:
  - search product
  - cart
  - quantity
  - total
  - payment
- Không dùng mock product.
- Sau thanh toán clear cart.

### Done khi

- Cashier bán hàng được từ dữ liệu thật.
- Đơn POS lưu vào database.
- Tồn kho giảm sau bán.

---

## Sprint 3.3 — Payment Basic

### Backend tasks

- Tạo entity/payment nếu chưa có.
- Ghi nhận phương thức thanh toán:
  - Cash
  - Bank Transfer
  - Card
  - E-wallet nếu cần
- Hỗ trợ thanh toán đủ tiền trước.
- Cập nhật trạng thái order.

### Frontend tasks

- UI chọn phương thức thanh toán.
- Hiển thị số tiền cần trả.
- Báo thanh toán thành công/thất bại.

### Done khi

- Order có payment record.
- Order chuyển trạng thái đúng.
- Báo cáo doanh thu có dữ liệu đầu vào.

---

# Phase 4 — Management, Reporting, Audit, Stabilization

## Mục tiêu

Hoàn thiện quản trị, báo cáo, audit log, settings và kiểm thử toàn hệ thống.

---

## Sprint 4.1 — Dashboard & Reporting

### Backend tasks

- API dashboard theo shop:
  - doanh thu hôm nay
  - số đơn
  - sản phẩm bán chạy
  - tồn kho thấp
- API report cơ bản:
  - doanh thu theo ngày
  - doanh thu theo sản phẩm
  - doanh thu theo nhân viên nếu có

### Frontend tasks

- Dashboard theo role.
- Biểu đồ doanh thu.
- Bảng top sản phẩm.
- Card tồn kho thấp.

### Done khi

- Dashboard lấy dữ liệu thật từ database.
- Staff chỉ thấy dữ liệu liên quan.
- Shop Owner thấy tổng quan shop.

---

## Sprint 4.2 — User Management & Staff Management

### Backend tasks

- Shop Owner tạo/sửa/vô hiệu hóa staff.
- Gán role cho user.
- Reset password cơ bản.
- Không cho staff tự nâng quyền.

### Frontend tasks

- Màn hình quản lý nhân viên.
- Form tạo tài khoản staff.
- Chọn role và branch.
- Active/inactive user.

### Done khi

- Shop Owner tạo được staff.
- Staff login được.
- Staff chỉ thấy menu đúng quyền.

---

## Sprint 4.3 — System Settings, Audit Log, Final QA

### Backend tasks

- Settings theo shop.
- Audit log các hành động quan trọng:
  - login
  - tạo/sửa/xóa sản phẩm
  - tạo đơn hàng
  - chỉnh tồn kho
  - thay đổi quyền
- Chuẩn hóa response lỗi.
- Kiểm tra CORS, security, validation.

### Frontend tasks

- Màn hình settings cơ bản.
- Hiển thị lỗi API rõ ràng.
- Loading/empty/error state.
- Kiểm tra route guard.

### Done khi

- Không còn đăng nhập giả.
- Không còn mock data ở các module chính.
- API lỗi trả response rõ ràng.
- Frontend không crash khi backend lỗi.

---

# Cấu trúc backend đề xuất

## Nguyên tắc

Không chia code theo role kiểu:

```text
admin/
shopOwner/
staff/
```

Không nên mỗi role là một file hoặc một folder controller riêng cho toàn bộ hệ thống.

Nên chia theo module nghiệp vụ:

```text
auth/
user/
role/
shop/
product/
order/
inventory/
payment/
report/
```

Role và permission nên nằm trong database và được kiểm tra bằng Spring Security.

---

## Cấu trúc package gợi ý

```text
src/main/java/com/company/sales_management/
├── ManagementApplication.java
│
├── common/
│   ├── response/
│   ├── exception/
│   ├── security/
│   └── util/
│
├── config/
│   ├── SecurityConfig.java
│   ├── CorsConfig.java
│   └── DataInitializer.java
│
├── auth/
│   ├── controller/
│   ├── service/
│   ├── dto/
│   └── jwt/
│
├── user/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── rbac/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── shop/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── catalog/
│   ├── product/
│   ├── category/
│   └── brand/
│
├── partner/
│   ├── customer/
│   └── supplier/
│
├── inventory/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
│
├── sales/
│   ├── order/
│   ├── pos/
│   └── payment/
│
├── reporting/
│   ├── controller/
│   ├── service/
│   └── dto/
│
└── audit/
    ├── controller/
    ├── service/
    ├── repository/
    ├── entity/
    └── dto/
```

---

# Có nên mỗi role là một file không?

Không.

Role không nên được hardcode thành từng file xử lý riêng. Ví dụ không nên làm:

```text
AdminController.java
ShopOwnerController.java
StaffController.java
```

cho mọi nghiệp vụ.

Thay vào đó:

- Controller chia theo nghiệp vụ.
- Role nằm trong database.
- Permission nằm trong database.
- Backend kiểm tra quyền ở từng API.
- Frontend chỉ ẩn/hiện menu theo quyền.

Ví dụ:

```java
@PreAuthorize("hasAuthority('PRODUCT_CREATE')")
@PostMapping("/api/products")
public ProductResponse createProduct(@RequestBody ProductRequest request) {
    return productService.createProduct(request);
}
```

Hoặc:

```java
@PreAuthorize("hasAnyRole('SHOP_OWNER', 'BRANCH_MANAGER')")
@PutMapping("/api/products/{id}")
public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
    return productService.updateProduct(id, request);
}
```

---

# Database phân quyền nên có

```text
users
roles
permissions
user_roles
role_permissions
shops
branches
user_branches
```

Các bảng nghiệp vụ nên có:

```text
shop_id
branch_id
created_by
updated_by
is_deleted
created_at
updated_at
```

Ví dụ:

```text
products.shop_id
orders.shop_id
customers.shop_id
suppliers.shop_id
stock_transactions.shop_id
```

---

# Prompt ngắn cho AI Agent

```text
Hãy làm sprint hiện tại theo file Sprint Plan.

Quy tắc:
- Không tạo project mới.
- Không rewrite toàn bộ code.
- Chỉ làm đúng sprint được chỉ định.
- Backend và frontend phải kết nối thật.
- Không dùng mock data nếu API đã có.
- Role/Permission phải dựa trên database và Spring Security, không hardcode role thành từng folder riêng.
- Dữ liệu nghiệp vụ phải có shop_id để tách dữ liệu giữa các shop.
- Sau khi làm xong, cập nhật task.md và implementation_plan.md.

Sprint cần làm: <ghi sprint ở đây>
```
