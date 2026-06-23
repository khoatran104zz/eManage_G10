Sales Management Platform  |  02\_Modules\_and\_Features.docx  |  v1.0	**INTERNAL  |  CONFIDENTIAL**

**SALES MANAGEMENT PLATFORM**

**1. Purpose and Scope of this Document**

Tài liệu Modules and Features Specification (MFS) này là tài liệu tham chiếu kỹ thuật cấp 2 trong bộ hồ sơ phân tích thiết kế dự án Sales Management Platform. Tài liệu định nghĩa chi tiết 25 module nghiệp vụ cốt lõi của nền tảng, bao gồm mô tả module, mục tiêu kinh doanh, toàn bộ feature với mức độ ưu tiên và liên kết nhất quán với:

• Các Functional Requirements (FR-001 đến FR-114) trong 01\_Software\_Requirement\_Specification.docx

• Non-Functional Requirements (NFR-001 đến NFR-054) trong SRS

• Business Rules (BR-01 đến BR-17) trong SRS

• Business Objectives (BO-01 đến BO-08) trong SRS

Tài liệu này là nền tảng trực tiếp để xây dựng: 03\_Use\_Case\_Document, 04\_ERD\_Database\_Design, 05\_Product\_Backlog và 06\_System\_Architecture.

# **2. Module Summary Matrix**
Bảng dưới đây liệt kê tổng quan 25 module của hệ thống với tổng số features, mục tiêu kinh doanh và các FR được liên kết.

|**ID**|**Module Name**|**# Features**|**Business Goal**|**FR References**|**User Roles**|**Priority**|
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
|**MOD-01**|**Authentication**|**10**|Đảm bảo chỉ người dùng hợp lệ, được cấp phép mới có thể truy cập hệ thống; bảo vệ dữ liệu kinh doanh nhạy cảm trước truy...|FR-001, FR-002, FR-003, FR-004, FR-005|Store Owner, Branch Manager, Cashier|**High**|
|**MOD-02**|**User Management**|**10**|Đảm bảo mỗi nhân viên có một tài khoản duy nhất, được phân công đúng chi nhánh và vai trò; hỗ trợ quản lý nhân sự hệ thố...|FR-006, FR-007, FR-008, FR-009, FR-010|Store Owner, Branch Manager|**High**|
|**MOD-03**|**Role & Permission Management**|**11**|Kiểm soát truy cập chính xác đến từng màn hình và tính năng; đảm bảo mỗi vai trò chỉ làm được việc trong phạm vi trách n...|FR-011, FR-012, FR-013, FR-014, FR-015|Store Owner|**High**|
|**MOD-04**|**Product Management**|**12**|Xây dựng nền tảng dữ liệu sản phẩm chuẩn xác, nhất quán phục vụ POS, quản lý kho và báo cáo. Gắn với BO-01, BO-02.|FR-016, FR-017, FR-018, FR-019, FR-020, FR-021, FR-022|Store Owner, Branch Manager, Cashier (read)|**High**|
|**MOD-05**|**Category Management**|**10**|Chuẩn hóa phân loại sản phẩm, giúp nhân viên tìm sản phẩm nhanh tại POS và cải thiện độ chính xác báo cáo theo nhóm hàng...|FR-023, FR-024, FR-025, FR-026|Store Owner, Branch Manager|**Medium**|
|**MOD-06**|**Brand Management**|**8**|Phân loại sản phẩm theo thương hiệu, hỗ trợ phân tích doanh thu theo đối tác/nhà sản xuất và tra cứu sản phẩm nhanh.|FR-027, FR-028, FR-029|Store Owner, Branch Manager|**Low**|
|**MOD-07**|**Supplier Management**|**10**|Tập trung hóa thông tin nhà cung cấp, kiểm soát giá nhập và công nợ mua hàng hiệu quả. Gắn với BO-05.|FR-030, FR-031, FR-032, FR-033, FR-034|Store Owner, Branch Manager, Accountant|**High**|
|**MOD-08**|**Customer Management**|**11**|Xây dựng cơ sở dữ liệu khách hàng chất lượng, hỗ trợ chính sách giá nhóm, quản lý công nợ và chương trình khách hàng thâ...|FR-035, FR-036, FR-037, FR-038, FR-039, FR-040|Store Owner, Branch Manager, Cashier|**High**|
|**MOD-09**|**Inventory Management**|**11**|Kiểm soát tồn kho chính xác, giảm thất thoát hàng hóa xuống dưới 1% theo giá trị. Gắn với BO-02.|FR-041, FR-042, FR-043, FR-044, FR-045, FR-046, FR-047|Store Owner, Branch Manager, Inventory Staff|**High**|
|**MOD-10**|**Warehouse Management**|**8**|Cung cấp khả năng quản lý kho vật lý linh hoạt, hỗ trợ mô hình doanh nghiệp có nhiều điểm lưu trữ hàng hóa trong một chi...|FR-048, FR-049, FR-050, FR-051|Store Owner, Branch Manager, Inventory Staff|**Medium**|
|**MOD-11**|**Purchase Order (Đơn đặt hàng nhập)**|**10**|Kiểm soát quy trình nhập hàng chặt chẽ, đảm bảo tồn kho được bổ sung đúng thời điểm và giá mua được theo dõi minh bạch.|FR-052, FR-053, FR-054, FR-055, FR-056, FR-057|Store Owner, Branch Manager, Inventory Staff|**High**|
|**MOD-12**|**Sales Order (Đơn hàng bán)**|**10**|Chuẩn hóa quy trình bán hàng, đảm bảo ghi nhận doanh thu chính xác và quản lý trạng thái đơn hàng nhất quán. Gắn với BO-...|FR-058, FR-059, FR-060, FR-061, FR-062, FR-063|Store Owner, Branch Manager, Cashier|**High**|
|**MOD-13**|**POS (Point of Sale - Bán hàng tại quầy)**|**12**|Đảm bảo mỗi giao dịch POS hoàn tất trong dưới 2 giây, trải nghiệm người dùng mượt mà, không gây gián đoạn kinh doanh. Gắ...|FR-064, FR-065, FR-066, FR-067, FR-068, FR-069, FR-070|Cashier, Branch Manager|**High**|
|**MOD-14**|**Payment Management**|**8**|Đảm bảo mọi dòng tiền được ghi nhận chính xác, hỗ trợ theo dõi công nợ và đối soát tài chính cuối kỳ. Gắn với BO-05.|FR-071, FR-072, FR-073, FR-074, FR-075|Cashier, Accountant, Branch Manager|**High**|
|**MOD-15**|**Promotion Management**|**11**|Tăng doanh thu qua các chương trình khuyến mãi linh hoạt, thu hút khách hàng mới và tăng giá trị đơn hàng trung bình. Gắ...|FR-076, FR-077, FR-078, FR-079, FR-080|Store Owner, Branch Manager|**High**|
|**MOD-16**|**Loyalty Program**|**8**|Tăng tỷ lệ khách hàng quay lại (repeat purchase rate) tối thiểu 15% sau 6 tháng triển khai. Gắn với BO-03.|FR-081, FR-082, FR-083, FR-084|Store Owner, Cashier, Customer|**Medium**|
|**MOD-17**|**Employee Management**|**8**|Tập trung hóa thông tin nhân sự, theo dõi hiệu suất bán hàng và tính hoa hồng tự động, giảm tải công việc hành chính.|FR-085, FR-086, FR-087, FR-088|Store Owner, Branch Manager|**Medium**|
|**MOD-18**|**Attendance Management**|**7**|Tự động hóa chấm công, giảm gian lận giờ công và cung cấp dữ liệu chính xác cho tính lương hàng tháng.|FR-089, FR-090, FR-091|Store Owner, Branch Manager, Cashier|**Medium**|
|**MOD-19**|**Debt Management (Quản lý công nợ)**|**9**|Đảm bảo 100% giao dịch có công nợ được theo dõi tự động, giảm nợ khó đòi và tối ưu dòng tiền. Gắn với BO-05.|FR-092, FR-093, FR-094, FR-095|Store Owner, Accountant, Branch Manager|**High**|
|**MOD-20**|**Reporting & Analytics**|**12**|Cung cấp dữ liệu chính xác, kịp thời để ban điều hành ra quyết định kinh doanh. Giảm 30% thời gian lập báo cáo. Gắn với ...|FR-096, FR-097, FR-098, FR-099, FR-100|Store Owner, Branch Manager, Accountant|**High**|
|**MOD-21**|**Dashboard (Điều hành)**|**9**|Cung cấp cái nhìn tổng quan tức thì về tình hình kinh doanh trong vòng 3 giây, hỗ trợ ra quyết định nhanh. Gắn với BO-04...|FR-101, FR-102, FR-103|Store Owner, Branch Manager, Accountant|**High**|
|**MOD-22**|**Notification Management**|**10**|Đảm bảo thông tin quan trọng đến đúng người đúng lúc, giảm thời gian phản ứng với sự cố và tăng hiệu quả vận hành.|FR-104, FR-105, FR-106|Store Owner, Branch Manager, Cashier|**Medium**|
|**MOD-23**|**Multi-Branch Management**|**10**|Hỗ trợ mở rộng kinh doanh từ 1 lên 100 chi nhánh không cần thay đổi kiến trúc. Gắn với BO-06.|FR-107, FR-108, FR-109|Store Owner|**High**|
|**MOD-24**|**Audit Log**|**8**|Đảm bảo tính minh bạch, truy vết và không thể phủ nhận (non-repudiation) của mọi thao tác hệ thống. Gắn với BO-07.|FR-110, FR-111|Store Owner|**High**|
|**MOD-25**|**System Settings**|**11**|Cho phép doanh nghiệp tùy chỉnh hệ thống theo đặc thù của mình mà không cần sự can thiệp của đội phát triển.|FR-112, FR-113, FR-114|Store Owner, Branch Manager (branch settings only)|**Medium**|

**Tổng cộng: 25 Modules | 244 Features (bao gồm 114 FR, 54 NFR liên kết)**
# **3. Module Detailed Specification**
Mỗi module dưới đây được trình bày đầy đủ với: Module Overview và bảng Feature Detail bao gồm Feature ID, Feature Name, mô tả nghiệp vụ chi tiết, quy tắc nghiệp vụ/yêu cầu liên quan, mức độ ưu tiên và mã FR tham chiếu.
# **MOD-01 — Module: Authentication**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-01|
|**Module Name**|**Authentication**|
|**Description**|Cung cấp cơ chế xác thực người dùng an toàn, bao gồm đăng nhập, đăng xuất, quản lý phiên làm việc (session), đặt lại mật khẩu và hỗ trợ xác thực hai yếu tố (2FA) cho các tài khoản đặc quyền.|
|**Business Goal**|Đảm bảo chỉ người dùng hợp lệ, được cấp phép mới có thể truy cập hệ thống; bảo vệ dữ liệu kinh doanh nhạy cảm trước truy cập trái phép. Gắn với BO-07.|
|**FR References**|FR-001, FR-002, FR-003, FR-004, FR-005|
|**NFR References**|NFR-001, NFR-002, NFR-004, NFR-005, NFR-008, NFR-009, NFR-010|
|**User Roles**|Store Owner, Branch Manager, Cashier, Inventory Staff, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-01-01**|**Đăng nhập bằng Username / Email + Password**|Người dùng nhập email/username và mật khẩu; hệ thống xác thực và phát hành JWT access token + refresh token.|FR-001|**High**|FR-001|
|**F-01-02**|**Đăng xuất an toàn (Logout)**|Thu hồi token hiện tại và hủy toàn bộ session trên thiết bị đang dùng. Hỗ trợ 'Đăng xuất tất cả thiết bị'.|FR-002|**High**|FR-002|
|**F-01-03**|**Enforce Password Complexity**|Kiểm tra độ phức tạp khi tạo/đổi mật khẩu: tối thiểu 8 ký tự, có chữ hoa, số, ký tự đặc biệt.|FR-003|**High**|FR-003|
|**F-01-04**|**Đặt lại mật khẩu qua Email / OTP**|Gửi link hoặc mã OTP 6 chữ số qua email/SMS để đặt lại mật khẩu, OTP có thời hạn 15 phút.|FR-004|**High**|FR-004|
|**F-01-05**|**Khóa tài khoản sau đăng nhập thất bại**|Khóa tài khoản N phút sau K lần đăng nhập sai liên tiếp (N, K cấu hình được). Ghi audit log mỗi lần thất bại.|FR-005|Medium|FR-005|
|**F-01-06**|**Xác thực hai yếu tố (2FA/TOTP)**|Hỗ trợ 2FA qua ứng dụng authenticator (TOTP) cho Store Owner và Branch Manager. Người dùng bật/tắt từ trang cài đặt hồ sơ.|FR-001, NFR-010|Medium|FR-001|
|**F-01-07**|**Refresh Token & Session Management**|Access token hết hạn sau 15 phút; refresh token hợp lệ 7 ngày với cơ chế rotation. Hệ thống tự động gia hạn session minh bạch với người dùng.|NFR-004|**High**|NFR-004|
|**F-01-08**|**Tự động đăng xuất khi không hoạt động**|Hệ thống tự kết thúc session sau khoảng thời gian không hoạt động cấu hình được (mặc định 30 phút).|NFR-008|Medium|NFR-008|
|**F-01-09**|**Ghi log mọi lần xác thực**|Ghi lại mọi sự kiện đăng nhập/đăng xuất thành công và thất bại kèm IP, user-agent, timestamp vào Audit Log.|NFR-005|Medium|NFR-005|
|**F-01-10**|**Đăng nhập SSO (Single Sign-On) - tương lai**|Thiết kế sẵn sàng tích hợp SSO (OAuth2/OIDC) với hệ thống directory của doanh nghiệp trong giai đoạn sau V1.0.|NFR-047|Low|NFR-047|


# **MOD-02 — Module: User Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-02|
|**Module Name**|**User Management**|
|**Description**|Quản lý toàn bộ vòng đời tài khoản người dùng trong hệ thống: tạo, cập nhật, vô hiệu hóa, khôi phục tài khoản, phân công chi nhánh và lưu trữ lịch sử thay đổi.|
|**Business Goal**|Đảm bảo mỗi nhân viên có một tài khoản duy nhất, được phân công đúng chi nhánh và vai trò; hỗ trợ quản lý nhân sự hệ thống minh bạch. Gắn với BO-07.|
|**FR References**|FR-006, FR-007, FR-008, FR-009, FR-010|
|**NFR References**|NFR-003, NFR-006, NFR-007|
|**User Roles**|Store Owner, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-02-01**|**Tạo tài khoản người dùng mới**|Tạo tài khoản với thông tin: họ tên, email, số điện thoại, chi nhánh được gán, vai trò, trạng thái. Gửi email chào mừng kèm hướng dẫn đặt mật khẩu lần đầu.|FR-006|**High**|FR-006|
|**F-02-02**|**Chỉnh sửa thông tin tài khoản**|Cập nhật họ tên, email, số điện thoại, chi nhánh, vai trò, trạng thái. Ghi nhận ai/khi nào/thay đổi gì vào lịch sử.|FR-007|**High**|FR-007|
|**F-02-03**|**Vô hiệu hóa / Kích hoạt lại tài khoản**|Soft-disable tài khoản (không xóa): người dùng không thể đăng nhập nhưng dữ liệu lịch sử vẫn toàn vẹn. Kích hoạt lại khi cần.|FR-008|**High**|FR-008|
|**F-02-04**|**Cập nhật hồ sơ cá nhân (self-service)**|Người dùng tự cập nhật tên hiển thị, avatar, số điện thoại cá nhân và ngôn ngữ giao diện. Email dùng để đăng nhập chỉ admin mới thay đổi được.|FR-009|Medium|FR-009|
|**F-02-05**|**Xem lịch sử thay đổi tài khoản**|Hiển thị danh sách các thay đổi đã thực hiện trên tài khoản: trường thay đổi, giá trị cũ/mới, người thực hiện, thời gian.|FR-010|Medium|FR-010|
|**F-02-06**|**Tìm kiếm & lọc danh sách người dùng**|Tìm theo tên, email, chi nhánh, vai trò, trạng thái (Active/Inactive). Hỗ trợ phân trang và sắp xếp.|FR-006|Medium|FR-006|
|**F-02-07**|**Phân công người dùng vào chi nhánh**|<a name="_heading=h.92r6omk84b63"></a>Gán người dùng vào một hoặc nhiều chi nhánh. Cashier/Inventory Staff chỉ thấy dữ liệu chi nhánh được gán (theo BR-07).|FR-006, FR-107|**High**|FR-006|
|**F-02-08**|**Đặt lại mật khẩu cho người dùng (Admin reset)**|Admin có thể kích hoạt luồng đặt lại mật khẩu thay cho người dùng, gửi link reset qua email.|FR-004|Medium|FR-004|
|**F-02-09**|**Xem tổng quan hoạt động của người dùng**|Hiển thị các phiên đăng nhập gần đây (thiết bị, IP, thời gian), tổng số đơn hàng tạo, giá trị bán hàng trong kỳ.|FR-010|Low|FR-010|
|**F-02-10**|**Export danh sách người dùng**|Xuất danh sách người dùng ra file Excel với các trường được chọn (họ tên, email, chi nhánh, vai trò, trạng thái, ngày tạo).|NFR-047|Low|NFR-047|


# **MOD-03 — Module: Role & Permission Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-03|
|**Module Name**|**Role & Permission Management**|
|**Description**|Quản lý hệ thống phân quyền theo mô hình RBAC (Role-Based Access Control). Cho phép tạo vai trò tùy chỉnh, gán quyền theo module/feature với các mức: View, Create, Edit, Delete, Approve, Export.|
|**Business Goal**|Kiểm soát truy cập chính xác đến từng màn hình và tính năng; đảm bảo mỗi vai trò chỉ làm được việc trong phạm vi trách nhiệm. Gắn với BO-07, AC-08.|
|**FR References**|FR-011, FR-012, FR-013, FR-014, FR-015|
|**NFR References**|NFR-003|
|**User Roles**|Store Owner|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-03-01**|**Tạo vai trò (Role) tùy chỉnh**|Tạo vai trò mới với tên và mô tả. Hệ thống tích hợp sẵn 5 vai trò mặc định: Store Owner, Branch Manager, Cashier, Inventory Staff, Accountant.|FR-011|**High**|FR-011|
|**F-03-02**|**Chỉnh sửa và xóa vai trò**|Sửa tên, mô tả và bộ quyền của vai trò tùy chỉnh. Xóa chỉ khi không có người dùng nào đang được gán vai trò đó.|FR-011|**High**|FR-011|
|**F-03-03**|**Gán quyền theo module và feature**|Ma trận quyền: mỗi dòng là một module/feature, mỗi cột là một loại quyền (View / Create / Edit / Delete / Approve / Export). Admin tick/bỏ tick từng ô.|FR-012|**High**|FR-012|
|**F-03-04**|**Gán nhiều vai trò cho một người dùng**|Một người dùng có thể có nhiều vai trò; quyền được merge theo union (quyền cao nhất được áp dụng).|FR-013|**High**|FR-013|
|**F-03-05**|**Thực thi phân quyền trên giao diện**|Menu, nút bấm, tab không được phép sẽ bị ẩn hoặc disabled trên giao diện frontend.|FR-014|**High**|FR-014|
|**F-03-06**|**Thực thi phân quyền trên API**|Mọi API endpoint kiểm tra quyền của JWT token trước khi xử lý; trả về HTTP 403 nếu không đủ quyền.|FR-014, NFR-003|**High**|FR-014|
|**F-03-07**|**Màn hình Role-Permission Matrix**|Giao diện dạng bảng cho phép xem và chỉnh sửa toàn bộ bộ quyền của một vai trò một cách trực quan.|FR-015|Medium|FR-015|
|**F-03-08**|**Phân quyền theo chi nhánh (Branch Scope)**|Quyền có thể được scoped theo chi nhánh: Cashier chỉ xem/tạo đơn tại chi nhánh được gán (BR-07).|FR-014, BR-07|**High**|FR-014|
|**F-03-09**|**Audit log thay đổi quyền**|Mọi thay đổi về quyền và vai trò đều được ghi vào Audit Log (người thay đổi, thời gian, vai trò/quyền bị thay đổi).|FR-110|Medium|FR-110|
|**F-03-10**|**Quyền Dashboard tối thiểu bắt buộc**|Mọi vai trò bắt buộc phải có quyền View trên module Dashboard. Hệ thống tự thêm quyền này nếu bị bỏ quên (BR-13).|BR-13|Medium|BR-13|
|**F-03-11**|**Clone vai trò**|Sao chép cấu hình quyền của một vai trò hiện có để tạo nhanh vai trò mới tương tự.|FR-011|Low|FR-011|


# **MOD-04 — Module: Product Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-04|
|**Module Name**|**Product Management**|
|**Description**|Quản lý danh mục sản phẩm đầy đủ: thông tin sản phẩm, biến thể (variants), mã vạch, ảnh sản phẩm, giá vốn, giá bán, trạng thái và lịch sử thay đổi giá.|
|**Business Goal**|Xây dựng nền tảng dữ liệu sản phẩm chuẩn xác, nhất quán phục vụ POS, quản lý kho và báo cáo. Gắn với BO-01, BO-02.|
|**FR References**|FR-016, FR-017, FR-018, FR-019, FR-020, FR-021, FR-022|
|**NFR References**|NFR-014, NFR-016, NFR-019|
|**User Roles**|Store Owner, Branch Manager, Cashier (read), Inventory Staff (read)|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-04-01**|**Tạo sản phẩm mới**|Nhập đầy đủ: SKU, tên, mô tả, đơn vị tính, danh mục, thương hiệu, nhà cung cấp chính, giá vốn, giá bán, hình ảnh, trọng lượng.|FR-016|**High**|FR-016|
|**F-04-02**|**Chỉnh sửa thông tin sản phẩm**|Cập nhật mọi trường thông tin. Lịch sử thay đổi giá được lưu riêng với timestamp và người thay đổi.|FR-017|**High**|FR-017|
|**F-04-03**|**Vô hiệu hóa sản phẩm (Soft Delete)**|Deactivate sản phẩm: không xuất hiện trong POS/đặt hàng mới nhưng vẫn hiển thị đầy đủ trong lịch sử và báo cáo (BR-10).|FR-018, BR-10|**High**|FR-018|
|**F-04-04**|**Quản lý biến thể sản phẩm (Variants)**|Tạo biến thể theo các thuộc tính (size, color, material). Mỗi biến thể có SKU riêng, giá riêng, tồn kho riêng.|FR-019|**High**|FR-019|
|**F-04-05**|**Import / Export danh mục sản phẩm**|Import hàng loạt từ file Excel/CSV theo template mẫu. Export danh mục hiện tại với bộ lọc tùy chọn. Async với progress bar cho file lớn.|FR-020|Medium|FR-020|
|**F-04-06**|**Tìm kiếm & lọc sản phẩm**|Tìm theo tên, SKU, mã vạch, danh mục, thương hiệu, nhà cung cấp, trạng thái, khoảng giá. Hỗ trợ full-text search và gợi ý.|FR-021|**High**|FR-021|
|**F-04-07**|**Tạo & in mã vạch / QR code**|Tự động tạo barcode (Code128/EAN13) hoặc QR code cho sản phẩm và biến thể. In hàng loạt theo template.|FR-022|Medium|FR-022|
|**F-04-08**|**Upload & quản lý hình ảnh sản phẩm**|Upload nhiều ảnh, chỉ định ảnh đại diện. Ảnh lưu trên MinIO. Resize tự động về các kích cỡ thumbnail phục vụ POS.|FR-016|Medium|FR-016|
|**F-04-09**|**Lịch sử thay đổi giá**|Xem toàn bộ lịch sử thay đổi giá vốn/giá bán của sản phẩm: giá cũ, giá mới, người thay đổi, thời điểm hiệu lực.|FR-017|Medium|FR-017|
|**F-04-10**|**Giá bán theo chi nhánh**|Cho phép thiết lập giá bán riêng cho từng chi nhánh (override giá chung), hỗ trợ mô hình đa chi nhánh có chiến lược giá khác nhau.|FR-108|Medium|FR-108|
|**F-04-11**|**Kiểm tra trùng SKU / mã vạch**|Hệ thống tự động kiểm tra và cảnh báo khi SKU hoặc mã vạch bị trùng khi tạo mới hoặc import.|FR-016, BR-01|**High**|FR-016|
|**F-04-12**|**Danh sách sản phẩm liên quan**|Liên kết sản phẩm với các sản phẩm thay thế hoặc bán kèm, hỗ trợ gợi ý upsell/cross-sell tại POS.|FR-064|Low|FR-064|


# **MOD-05 — Module: Category Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-05|
|**Module Name**|**Category Management**|
|**Description**|Quản lý cây danh mục sản phẩm theo cấu trúc phân cấp đa tầng (parent-child). Hỗ trợ gán sản phẩm vào nhiều danh mục và lọc/duyệt sản phẩm theo danh mục trên toàn hệ thống.|
|**Business Goal**|Chuẩn hóa phân loại sản phẩm, giúp nhân viên tìm sản phẩm nhanh tại POS và cải thiện độ chính xác báo cáo theo nhóm hàng.|
|**FR References**|FR-023, FR-024, FR-025, FR-026|
|**NFR References**|NFR-016|
|**User Roles**|Store Owner, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-05-01**|**Tạo danh mục sản phẩm**|Tạo danh mục với tên, mô tả, hình ảnh đại diện và danh mục cha (nếu có). Danh mục gốc (root) không có cha.|FR-023|**High**|FR-023|
|**F-05-02**|**Chỉnh sửa danh mục**|Cập nhật tên, mô tả, ảnh và vị trí trong cây danh mục (thay đổi danh mục cha).|FR-023|**High**|FR-023|
|**F-05-03**|**Xóa danh mục**|Xóa danh mục chỉ khi không có sản phẩm hoặc danh mục con nào được gán. Hiển thị cảnh báo nếu danh mục đang được sử dụng.|FR-023|**High**|FR-023|
|**F-05-04**|**Cấu trúc phân cấp đa tầng**|Hỗ trợ tối thiểu 5 cấp danh mục lồng nhau. Hiển thị dạng cây (tree view) trên giao diện quản lý.|FR-024|Medium|FR-024|
|**F-05-05**|**Gán sản phẩm vào danh mục**|Một sản phẩm có thể thuộc nhiều danh mục. Bắt buộc gán ít nhất một danh mục khi tạo sản phẩm (BR-01).|FR-025, BR-01|**High**|FR-025|
|**F-05-06**|**Lọc sản phẩm theo danh mục**|Bộ lọc danh mục trên màn hình danh mục sản phẩm, POS và màn hình báo cáo. Lọc bao gồm tất cả danh mục con.|FR-026|Medium|FR-026|
|**F-05-07**|**Sắp xếp thứ tự hiển thị danh mục**|Kéo-thả để sắp xếp thứ tự hiển thị của các danh mục trong cùng cấp trên menu/giao diện POS.|FR-023|Low|FR-023|
|**F-05-08**|**Thống kê sản phẩm theo danh mục**|Hiển thị số lượng sản phẩm, tổng doanh thu và tỷ trọng doanh thu của từng danh mục.|FR-096|Low|FR-096|
|**F-05-09**|**Import danh mục từ file Excel**|Upload file Excel để tạo cây danh mục hàng loạt, hỗ trợ khi mới triển khai hệ thống.|FR-023|Low|FR-023|
|**F-05-10**|**Cache danh mục cho hiệu năng**|Cây danh mục được cache trong Redis và invalidate khi có thay đổi, đảm bảo POS tải danh mục nhanh.|NFR-016|Medium|NFR-016|


# **MOD-06 — Module: Brand Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-06|
|**Module Name**|**Brand Management**|
|**Description**|Quản lý danh sách thương hiệu (brand/nhà sản xuất) của sản phẩm. Hỗ trợ lọc sản phẩm và báo cáo theo thương hiệu.|
|**Business Goal**|Phân loại sản phẩm theo thương hiệu, hỗ trợ phân tích doanh thu theo đối tác/nhà sản xuất và tra cứu sản phẩm nhanh.|
|**FR References**|FR-027, FR-028, FR-029|
|**NFR References**|NFR-016|
|**User Roles**|Store Owner, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-06-01**|**Tạo thương hiệu mới**|Thêm thương hiệu với tên, logo, mô tả, website và quốc gia xuất xứ.|FR-027|Medium|FR-027|
|**F-06-02**|**Chỉnh sửa thông tin thương hiệu**|Cập nhật tên, logo, mô tả thương hiệu. Thay đổi được phản ánh ngay trên tất cả sản phẩm liên quan.|FR-027|Medium|FR-027|
|**F-06-03**|**Xóa thương hiệu**|Chỉ xóa được khi không có sản phẩm nào gán thương hiệu đó. Hiển thị danh sách sản phẩm liên quan trước khi xóa.|FR-027|Medium|FR-027|
|**F-06-04**|**Gán thương hiệu cho sản phẩm**|Trường Brand trên form sản phẩm, chọn từ danh sách có tìm kiếm (typeahead).|FR-028|Medium|FR-028|
|**F-06-05**|**Lọc sản phẩm theo thương hiệu**|Bộ lọc Brand trong danh mục sản phẩm, POS và báo cáo tồn kho.|FR-029|Low|FR-029|
|**F-06-06**|**Báo cáo doanh thu theo thương hiệu**|Widget báo cáo thương hiệu bán chạy: top 10 brands theo doanh thu, số lượng trong kỳ.|FR-029, FR-096|Low|FR-029|
|**F-06-07**|**Import thương hiệu hàng loạt**|Upload danh sách thương hiệu từ file Excel để nhập liệu nhanh khi khởi tạo hệ thống.|FR-027|Low|FR-027|
|**F-06-08**|**Liên kết thương hiệu với nhà cung cấp**|Gợi ý nhà cung cấp dựa trên thương hiệu khi tạo Purchase Order, tăng tốc độ lập đơn mua.|FR-033|Low|FR-033|


# **MOD-07 — Module: Supplier Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-07|
|**Module Name**|**Supplier Management**|
|**Description**|Quản lý thông tin đầy đủ của nhà cung cấp: thông tin liên hệ, điều khoản thanh toán, liên kết sản phẩm-nhà cung cấp với giá mua riêng, lịch sử đặt hàng và công nợ phải trả.|
|**Business Goal**|Tập trung hóa thông tin nhà cung cấp, kiểm soát giá nhập và công nợ mua hàng hiệu quả. Gắn với BO-05.|
|**FR References**|FR-030, FR-031, FR-032, FR-033, FR-034|
|**NFR References**|NFR-020|
|**User Roles**|Store Owner, Branch Manager, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-07-01**|**Tạo nhà cung cấp mới**|Tạo hồ sơ: tên công ty, mã số thuế, địa chỉ, người liên hệ, email, điện thoại, tài khoản ngân hàng, điều khoản thanh toán (NET 15/30/60).|FR-030, FR-031|**High**|FR-030|
|**F-07-02**|**Chỉnh sửa và vô hiệu hóa nhà cung cấp**|Cập nhật thông tin, vô hiệu hóa nhà cung cấp không còn hợp tác (soft delete). Lịch sử PO vẫn lưu.|FR-030|**High**|FR-030|
|**F-07-03**|**Lịch sử đặt hàng từ nhà cung cấp**|Xem toàn bộ Purchase Orders và Goods Receipts của từng nhà cung cấp theo thời gian.|FR-032|Medium|FR-032|
|**F-07-04**|**Liên kết sản phẩm - nhà cung cấp**|Gán sản phẩm cho nhà cung cấp với giá mua riêng (supplier cost price), thời gian giao hàng (lead time) và MOQ.|FR-033|Medium|FR-033|
|**F-07-05**|**Theo dõi công nợ phải trả (AP)**|Hiển thị tổng dư nợ phải trả của từng nhà cung cấp, chi tiết theo từng hóa đơn mua, tình trạng thanh toán.|FR-034|**High**|FR-034|
|**F-07-06**|**Ghi nhận thanh toán cho nhà cung cấp**|Ghi nhận payment cho nhà cung cấp, cập nhật số dư công nợ phải trả theo thời gian thực.|FR-034, FR-071|**High**|FR-034|
|**F-07-07**|**Báo cáo công nợ phải trả theo tuổi nợ**|Báo cáo aging AP: nhóm dư nợ theo 0-30, 31-60, 61-90, >90 ngày quá hạn.|FR-093, FR-094|Medium|FR-093|
|**F-07-08**|**Import danh sách nhà cung cấp**|Upload file Excel để import hàng loạt nhà cung cấp khi khởi tạo hệ thống.|FR-030|Low|FR-030|
|**F-07-09**|**Đánh giá nhà cung cấp**|Ghi nhận đánh giá chất lượng, đúng hạn và giá cả theo từng đơn nhận hàng để hỗ trợ lựa chọn nhà cung cấp.|FR-032|Low|FR-032|
|**F-07-10**|**Cảnh báo sắp đến hạn thanh toán**|Thông báo tự động khi đến hạn thanh toán cho nhà cung cấp theo điều khoản đã thỏa thuận.|FR-095, FR-104|Medium|FR-095|


# **MOD-08 — Module: Customer Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-08|
|**Module Name**|**Customer Management**|
|**Description**|Quản lý toàn bộ thông tin khách hàng: hồ sơ cá nhân, nhóm khách hàng, lịch sử mua hàng, công nợ phải thu và hạn mức tín dụng. Hỗ trợ tìm kiếm nhanh trong giao dịch POS.|
|**Business Goal**|Xây dựng cơ sở dữ liệu khách hàng chất lượng, hỗ trợ chính sách giá nhóm, quản lý công nợ và chương trình khách hàng thân thiết. Gắn với BO-03, BO-05.|
|**FR References**|FR-035, FR-036, FR-037, FR-038, FR-039, FR-040|
|**NFR References**|NFR-006, NFR-007|
|**User Roles**|Store Owner, Branch Manager, Cashier, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-08-01**|**Tạo hồ sơ khách hàng mới**|Tạo với: họ tên, số điện thoại, email, địa chỉ, ngày sinh, giới tính, nhóm khách hàng, hạn mức tín dụng. Số điện thoại là mã định danh chính.|FR-035, FR-036|**High**|FR-035|
|**F-08-02**|**Chỉnh sửa và vô hiệu hóa khách hàng**|Cập nhật thông tin, vô hiệu hóa (soft delete). Lịch sử mua hàng, điểm thưởng, công nợ vẫn lưu.|FR-035|**High**|FR-035|
|**F-08-03**|**Lịch sử mua hàng của khách hàng**|Xem toàn bộ đơn hàng, hóa đơn, thanh toán của khách hàng theo thời gian. Lọc theo chi nhánh, kỳ thời gian.|FR-037|**High**|FR-037|
|**F-08-04**|**Quản lý nhóm khách hàng**|Tạo, sửa nhóm: Lẻ, Sỉ, VIP... Mỗi nhóm có chính sách giá riêng (% chiết khấu) hoặc bảng giá riêng.|FR-038|Medium|FR-038|
|**F-08-05**|**Import / Export danh sách khách hàng**|Import từ Excel/CSV, export ra Excel với bộ lọc tùy chọn. Template chuẩn được cung cấp.|FR-039|Medium|FR-039|
|**F-08-06**|**Tìm kiếm khách hàng nhanh tại POS**|Tìm theo số điện thoại, tên, mã KH trong giao diện POS. Kết quả hiển thị trong 300ms. Cho phép tạo KH mới nhanh.|FR-040|**High**|FR-040|
|**F-08-07**|**Theo dõi công nợ phải thu (AR)**|Hiển thị tổng AR, chi tiết hóa đơn chưa thanh toán, ngày đến hạn theo từng khách hàng.|FR-092|**High**|FR-092|
|**F-08-08**|**Quản lý hạn mức tín dụng**|Thiết lập credit limit theo khách hàng. Hệ thống chặn đơn hàng mới khi vượt hạn mức (BR-09), cần BM phê duyệt ngoại lệ.|BR-09|**High**|BR-09|
|**F-08-09**|**Điểm thưởng của khách hàng**|Xem số dư điểm hiện tại, lịch sử cộng/trừ điểm với lý do chi tiết.|FR-081, FR-084|Medium|FR-081|
|**F-08-10**|**Nhãn và ghi chú nội bộ**|Gắn nhãn (tag) và ghi chú nội bộ cho khách hàng (không hiển thị cho KH), hỗ trợ phân loại và chăm sóc.|FR-035|Low|FR-035|
|**F-08-11**|**Phân tích hành vi mua hàng (RFM cơ bản)**|Hiển thị chỉ số: lần mua cuối (Recency), tần suất (Frequency), giá trị (Monetary) để đánh giá chất lượng khách hàng.|FR-096|Low|FR-096|


# **MOD-09 — Module: Inventory Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-09|
|**Module Name**|**Inventory Management**|
|**Description**|Quản lý tồn kho theo thời gian thực tại từng chi nhánh và kho. Ghi nhận mọi biến động tồn kho (nhập/xuất/kiểm kê/chuyển kho), cảnh báo tồn kho thấp và duy trì audit trail đầy đủ.|
|**Business Goal**|Kiểm soát tồn kho chính xác, giảm thất thoát hàng hóa xuống dưới 1% theo giá trị. Gắn với BO-02.|
|**FR References**|FR-041, FR-042, FR-043, FR-044, FR-045, FR-046, FR-047|
|**NFR References**|NFR-020, NFR-024, NFR-025|
|**User Roles**|Store Owner, Branch Manager, Inventory Staff, Cashier (read)|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-09-01**|**Xem tồn kho theo thời gian thực**|Hiển thị tồn kho hiện tại của từng sản phẩm/biến thể theo chi nhánh và kho. Dữ liệu cập nhật ngay sau mỗi giao dịch.|FR-041|**High**|FR-041|
|**F-09-02**|**Ghi nhận nhập kho**|Nhập kho khi nhận hàng theo PO hoặc nhập điều chỉnh có lý do. Tự động tăng số lượng tồn kho.|FR-042|**High**|FR-042|
|**F-09-03**|**Ghi nhận xuất kho**|Xuất kho khi bán hàng, chuyển kho hoặc xuất điều chỉnh (hư hỏng, mất mát). Tự động giảm tồn kho.|FR-043|**High**|FR-043|
|**F-09-04**|**Kiểm kê kho (Stock Take)**|Tạo phiên kiểm kê: nhập số lượng đếm thực tế theo từng sản phẩm, so sánh với số liệu hệ thống, phê duyệt và đăng điều chỉnh chênh lệch.|FR-044|**High**|FR-044|
|**F-09-05**|**Cảnh báo tồn kho thấp (Low-Stock Alert)**|Tự động cảnh báo khi tồn kho dưới ngưỡng reorder point cấu hình theo sản phẩm/chi nhánh. Cảnh báo qua in-app notification.|FR-045|**High**|FR-045|
|**F-09-06**|**Ngưỡng đặt hàng lại (Reorder Point)**|Cấu hình reorder point và reorder quantity cho mỗi sản phẩm tại từng chi nhánh, hỗ trợ tự động đề xuất PO.|FR-045|Medium|FR-045|
|**F-09-07**|**Yêu cầu chuyển kho (Stock Transfer)**|Tạo, phê duyệt và thực hiện chuyển kho giữa chi nhánh/kho. Tồn kho nguồn giảm khi xuất; tồn kho đích tăng khi bên nhận xác nhận (BR-11).|FR-046, BR-11|**High**|FR-046|
|**F-09-08**|**Lịch sử giao dịch tồn kho**|Audit trail đầy đủ, bất biến: loại GD, số lượng, tồn trước/sau, người thực hiện, timestamp, tài liệu liên quan.|FR-047|**High**|FR-047|
|**F-09-09**|**Điều chỉnh tồn kho thủ công**|Nhập lý do bắt buộc và cần phê duyệt của Branch Manager (BR-08). Ghi audit log với đầy đủ thông tin.|BR-08|**High**|BR-08|
|**F-09-10**|**Báo cáo tồn kho & định giá tồn kho**|Báo cáo tồn kho hiện tại theo sản phẩm/chi nhánh/kho, kèm giá trị tồn (theo giá vốn bình quân hoặc FIFO).|FR-097|**High**|FR-097|
|**F-09-11**|**Đối soát tồn kho tự động (Reconciliation)**|Job định kỳ so sánh tổng các giao dịch tồn kho với số dư hiện tại; cảnh báo admin nếu có sai lệch.|NFR-024|Medium|NFR-024|


# **MOD-10 — Module: Warehouse Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-10|
|**Module Name**|**Warehouse Management**|
|**Description**|Quản lý thông tin kho vật lý gắn với chi nhánh. Hỗ trợ nhiều kho trong một chi nhánh, theo dõi tồn kho theo từng kho và quy trình chuyển kho nội bộ.|
|**Business Goal**|Cung cấp khả năng quản lý kho vật lý linh hoạt, hỗ trợ mô hình doanh nghiệp có nhiều điểm lưu trữ hàng hóa trong một chi nhánh.|
|**FR References**|FR-048, FR-049, FR-050, FR-051|
|**NFR References**|NFR-020|
|**User Roles**|Store Owner, Branch Manager, Inventory Staff|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-10-01**|**Tạo và quản lý kho hàng**|Tạo kho với: mã kho, tên, địa chỉ, chi nhánh quản lý, loại kho (main/secondary). Sửa và soft-delete.|FR-048|Medium|FR-048|
|**F-10-02**|**Kho mặc định theo chi nhánh**|Khi tạo chi nhánh mới, hệ thống tự tạo một kho mặc định gắn với chi nhánh đó (BR-17).|BR-17|**High**|BR-17|
|**F-10-03**|**Nhiều kho trong một chi nhánh**|Hỗ trợ mô hình 1 chi nhánh - nhiều kho. Tồn kho của chi nhánh = tổng tồn kho của tất cả kho.|FR-049|Medium|FR-049|
|**F-10-04**|**Xem tồn kho theo từng kho**|Dashboard tồn kho chi tiết đến từng kho trong chi nhánh: sản phẩm, số lượng, vị trí lưu.|FR-050|Medium|FR-050|
|**F-10-05**|**Quy trình chuyển kho nội bộ (trong chi nhánh)**|Chuyển hàng giữa các kho cùng chi nhánh với workflow: Pending → Approved → In Transit → Received.|FR-051|Medium|FR-051|
|**F-10-06**|**Phân công kho mặc định cho sản phẩm**|Chỉ định kho lưu trữ ưu tiên cho từng sản phẩm, giúp nhân viên nhập/xuất kho nhanh.|FR-048|Low|FR-048|
|**F-10-07**|**Lịch sử nhập/xuất theo kho**|Xem lịch sử toàn bộ giao dịch nhập/xuất theo từng kho với đầy đủ thông tin.|FR-047|Medium|FR-047|
|**F-10-08**|**Báo cáo tồn kho theo kho**|So sánh tồn kho giữa các kho trong chi nhánh; hỗ trợ cân bằng tồn kho giữa các kho.|FR-097|Low|FR-097|


# **MOD-11 — Module: Purchase Order (Đơn đặt hàng nhập)**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-11|
|**Module Name**|**Purchase Order (Đơn đặt hàng nhập)**|
|**Description**|Quản lý toàn bộ quy trình mua hàng từ nhà cung cấp: tạo đơn đặt hàng, phê duyệt, nhận hàng (full/partial), cập nhật tồn kho và theo dõi trạng thái.|
|**Business Goal**|Kiểm soát quy trình nhập hàng chặt chẽ, đảm bảo tồn kho được bổ sung đúng thời điểm và giá mua được theo dõi minh bạch.|
|**FR References**|FR-052, FR-053, FR-054, FR-055, FR-056, FR-057|
|**NFR References**|NFR-020, NFR-027|
|**User Roles**|Store Owner, Branch Manager, Inventory Staff, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-11-01**|**Tạo đơn đặt hàng (Purchase Order)**|Tạo PO: chọn nhà cung cấp, chi nhánh/kho nhận, ngày đặt, ngày giao dự kiến. Thêm dòng hàng với sản phẩm/variant, SL, đơn giá, chiết khấu dòng.|FR-052, FR-053|**High**|FR-052|
|**F-11-02**|**Workflow phê duyệt PO**|Luồng phê duyệt: Draft → Pending Approval → Approved → Sent. Branch Manager hoặc Store Owner phê duyệt. Email thông báo khi cần duyệt.|FR-054|Medium|FR-054|
|**F-11-03**|**Nhận hàng theo PO (Goods Receipt)**|Ghi nhận hàng nhận được: có thể nhận đủ hoặc nhận một phần. Nhập số lượng thực nhận, ghi chú sai lệch. Trạng thái PO chuyển sang Partially Received hoặc Fully Received.|FR-055|**High**|FR-055|
|**F-11-04**|**Tự động cập nhật tồn kho khi nhận hàng**|Sau khi xác nhận Goods Receipt, hệ thống tự động tăng số lượng tồn kho tại kho đích trong 1 transaction DB (NFR-020).|FR-056, NFR-020|**High**|FR-056|
|**F-11-05**|**Chỉnh sửa và hủy PO**|Chỉnh sửa PO khi còn ở trạng thái Draft. Hủy PO khi chưa có Goods Receipt nào. PO đã có GR không được xóa (BR-16).|FR-057, BR-16|Medium|FR-057|
|**F-11-06**|**In và gửi PO cho nhà cung cấp**|Xuất PO ra PDF theo template chuẩn. Gửi email trực tiếp cho nhà cung cấp từ hệ thống.|FR-052|Medium|FR-052|
|**F-11-07**|**Theo dõi trạng thái PO**|Dashboard PO với các trạng thái: Draft / Pending Approval / Approved / Sent / Partially Received / Fully Received / Cancelled.|FR-054, FR-055|**High**|FR-054|
|**F-11-08**|**Tự động đề xuất PO từ cảnh báo tồn kho thấp**|Khi sản phẩm dưới reorder point, hệ thống gợi ý tạo PO với nhà cung cấp mặc định và số lượng đề xuất.|FR-045, FR-052|Low|FR-045|
|**F-11-09**|**Lịch sử nhận hàng theo PO**|Xem danh sách tất cả Goods Receipts liên quan đến một PO, số lượng đã nhận, còn lại, sai lệch.|FR-055|Medium|FR-055|
|**F-11-10**|**Báo cáo nhập hàng**|Báo cáo giá trị nhập hàng theo nhà cung cấp, sản phẩm, chi nhánh, kỳ thời gian.|FR-097|Medium|FR-097|


# **MOD-12 — Module: Sales Order (Đơn hàng bán)**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-12|
|**Module Name**|**Sales Order (Đơn hàng bán)**|
|**Description**|Quản lý đơn hàng bán cho khách hàng: tạo đơn, tính toán giá/thuế/phí, theo dõi trạng thái từ Pending đến Completed, xuất hóa đơn và ghi nhận doanh thu.|
|**Business Goal**|Chuẩn hóa quy trình bán hàng, đảm bảo ghi nhận doanh thu chính xác và quản lý trạng thái đơn hàng nhất quán. Gắn với BO-01.|
|**FR References**|FR-058, FR-059, FR-060, FR-061, FR-062, FR-063|
|**NFR References**|NFR-020, NFR-027|
|**User Roles**|Store Owner, Branch Manager, Cashier, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-12-01**|**Tạo đơn hàng bán (Sales Order)**|Tạo SO: chọn khách hàng (hoặc walk-in), chi nhánh, kênh bán. Thêm sản phẩm/variant, số lượng, đơn giá, chiết khấu dòng, thuế.|FR-058, FR-059|**High**|FR-058|
|**F-12-02**|**Tính toán tự động tổng đơn hàng**|Tính theo thời gian thực: subtotal, tổng chiết khấu, tổng thuế, phí vận chuyển, grand total. Hiển thị chi tiết mỗi dòng.|FR-060|**High**|FR-060|
|**F-12-03**|**Theo dõi trạng thái đơn hàng**|Vòng đời đơn: Pending → Confirmed → Packed → Delivered → Completed / Cancelled. Timestamp cho từng bước.|FR-061|**High**|FR-061|
|**F-12-04**|**Tự động trừ tồn kho**|Trừ tồn kho khi Confirmed (hoặc Delivered tùy cấu hình). Kiểm tra tồn kho khả dụng trước khi confirm (BR-03).|FR-062, BR-03|**High**|FR-062|
|**F-12-05**|**Tạo hóa đơn từ đơn hàng**|Xuất Invoice từ SO ở trạng thái Confirmed trở lên. Hóa đơn có số tự tăng, ký hiệu cấu hình theo chi nhánh.|FR-063|**High**|FR-063|
|**F-12-06**|**Áp dụng khuyến mãi trên đơn hàng**|Tự động kiểm tra và áp dụng promotion phù hợp khi tạo SO. Hiển thị chi tiết từng ưu đãi được áp.|FR-079|**High**|FR-079|
|**F-12-07**|**Ghi chú nội bộ và ghi chú giao hàng**|Trường ghi chú nội bộ (không in hóa đơn) và ghi chú giao hàng (in trên phiếu giao).|FR-058|Low|FR-058|
|**F-12-08**|**Lịch sử trạng thái đơn hàng**|Timeline đầy đủ các thay đổi trạng thái: trạng thái cũ/mới, người thực hiện, thời gian, ghi chú.|FR-061|Medium|FR-061|
|**F-12-09**|**Hủy đơn hàng**|Hủy SO: lý do bắt buộc, phê duyệt nếu đã Confirmed. Tự động hoàn trả tồn kho đã trừ.|FR-061|**High**|FR-061|
|**F-12-10**|**Tìm kiếm và lọc đơn hàng**|Tìm theo mã SO, tên KH, trạng thái, chi nhánh, khoảng thời gian, nhân viên bán. Phân trang.|FR-096|**High**|FR-096|


# **MOD-13 — Module: POS (Point of Sale - Bán hàng tại quầy)**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-13|
|**Module Name**|**POS (Point of Sale - Bán hàng tại quầy)**|
|**Description**|Giao diện bán hàng tối ưu tốc độ cho Cashier: tìm sản phẩm nhanh, quét mã vạch, đa phương thức thanh toán, in hóa đơn, hỗ trợ offline và các tính năng nâng cao như hold bill, split bill.|
|**Business Goal**|Đảm bảo mỗi giao dịch POS hoàn tất trong dưới 2 giây, trải nghiệm người dùng mượt mà, không gây gián đoạn kinh doanh. Gắn với BO-01.|
|**FR References**|FR-064, FR-065, FR-066, FR-067, FR-068, FR-069, FR-070|
|**NFR References**|NFR-012, NFR-018, NFR-025, NFR-031|
|**User Roles**|Cashier, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-13-01**|**Thêm sản phẩm vào giỏ hàng POS**|Tìm nhanh bằng tên/SKU/barcode với typeahead <300ms. Click để thêm, chỉnh số lượng trực tiếp trên cart.|FR-064|**High**|FR-064|
|**F-13-02**|**Quét mã vạch (Barcode Scanner)**|Hỗ trợ quét qua camera thiết bị hoặc đầu đọc barcode USB/Bluetooth. Sản phẩm được thêm vào cart trong 300ms (NFR-018).|FR-065, NFR-018|**High**|FR-065|
|**F-13-03**|**Đa phương thức thanh toán trong một giao dịch**|Chấp nhận tiền mặt, thẻ, ví điện tử (VNPay, Momo, ZaloPay), chuyển khoản trong cùng một hóa đơn.|FR-066|**High**|FR-066|
|**F-13-04**|**Tính tiền thừa tự động**|Nhập tiền khách đưa, hệ thống tính và hiển thị số tiền thừa trả lại cho giao dịch tiền mặt.|FR-067|**High**|FR-067|
|**F-13-05**|**Áp dụng khuyến mãi và voucher tại POS**|Tự động hiển thị và áp dụng promotion hợp lệ. Nhập mã voucher thủ công. Xử lý conflict rules (FR-080).|FR-068, FR-080|**High**|FR-068|
|**F-13-06**|**In hóa đơn nhiệt / Gửi hóa đơn điện tử**|In hóa đơn ra máy in nhiệt kết nối qua USB/Bluetooth. Gửi email hoặc SMS hóa đơn cho khách.|FR-069|**High**|FR-069|
|**F-13-07**|**Tạm giữ đơn hàng (Hold / Park Bill)**|Lưu tạm giỏ hàng đang xử lý để phục vụ khách khác, gọi lại sau. Đặt tên/ghi chú cho từng hold.|FR-070|Medium|FR-070|
|**F-13-08**|**Tách hóa đơn (Split Bill)**|Chia một giỏ hàng thành nhiều hóa đơn độc lập hoặc chia đều giá trị giữa nhiều người.|FR-070|Medium|FR-070|
|**F-13-09**|**Chế độ offline POS**|Lưu giao dịch cục bộ khi mất mạng. Đồng bộ tự động, đúng 1 lần khi có mạng trở lại (NFR-025, NFR-031).|NFR-025, NFR-031|**High**|NFR-025|
|**F-13-10**|**Mở/Đóng ca làm việc (Shift)**|Cashier mở ca với số tiền quỹ đầu ca. Kết ca: đối soát tiền thực tế vs hệ thống, in báo cáo ca.|FR-089|**High**|FR-089|
|**F-13-11**|**Tra cứu thông tin khách hàng tại POS**|Tìm KH, hiển thị điểm thưởng, công nợ, lịch sử mua gần nhất. Chọn KH gắn với đơn hàng.|FR-040|**High**|FR-040|
|**F-13-12**|**Đổi trả hàng (Return) tại POS**|Tra cứu hóa đơn gốc, chọn sản phẩm trả, xử lý hoàn tiền qua phương thức gốc hoặc credit.|FR-073|Medium|FR-073|


# **MOD-14 — Module: Payment Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-14|
|**Module Name**|**Payment Management**|
|**Description**|Quản lý toàn bộ giao dịch thu chi: ghi nhận thanh toán nhiều đợt (partial payment), hoàn tiền, tích hợp cổng thanh toán điện tử và phát hành phiếu thu/chi.|
|**Business Goal**|Đảm bảo mọi dòng tiền được ghi nhận chính xác, hỗ trợ theo dõi công nợ và đối soát tài chính cuối kỳ. Gắn với BO-05.|
|**FR References**|FR-071, FR-072, FR-073, FR-074, FR-075|
|**NFR References**|NFR-020, NFR-027|
|**User Roles**|Cashier, Accountant, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-14-01**|**Ghi nhận thanh toán cho hóa đơn**|Ghi nhận một hoặc nhiều payment cho Sales Invoice hoặc Purchase Invoice. Chọn phương thức: tiền mặt, thẻ, ví, chuyển khoản.|FR-071|**High**|FR-071|
|**F-14-02**|**Thanh toán một phần (Partial Payment)**|Ghi nhận thanh toán từng phần, hệ thống theo dõi số tiền còn nợ theo thời gian thực trên từng hóa đơn.|FR-072|**High**|FR-072|
|**F-14-03**|**Hoàn tiền (Refund)**|Tạo refund liên kết với payment gốc. Cập nhật AR/AP và tồn kho nếu có đổi trả hàng. Ghi audit log.|FR-073|**High**|FR-073|
|**F-14-04**|**Tích hợp cổng thanh toán (Payment Gateway)**|Tích hợp VNPay, Momo, ZaloPay qua API. Trạng thái thanh toán được đồng bộ tự động (webhook).|FR-074|Medium|FR-074|
|**F-14-05**|**Phiếu thu / phiếu chi (Receipt/Voucher)**|Tự động phát hành phiếu thu/chi cho mỗi giao dịch. In theo template chuẩn hoặc gửi email.|FR-075|Medium|FR-075|
|**F-14-06**|**Đối soát thanh toán (Payment Reconciliation)**|Đối soát thanh toán ngân hàng/ví điện tử với giao dịch hệ thống theo kỳ (ngày/tuần).|FR-075, FR-098|Low|FR-075|
|**F-14-07**|**Báo cáo thu chi theo phương thức thanh toán**|Tổng hợp thu/chi theo tiền mặt, thẻ, ví, chuyển khoản theo kỳ và chi nhánh.|FR-098|Medium|FR-098|
|**F-14-08**|**Xử lý tiền mặt đầu/cuối ngày**|Ghi nhận số dư quỹ tiền mặt đầu ngày, kiểm kê cuối ngày, nộp quỹ. Báo cáo chênh lệch.|FR-066|Medium|FR-066|


# **MOD-15 — Module: Promotion Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-15|
|**Module Name**|**Promotion Management**|
|**Description**|Tạo và quản lý các chương trình khuyến mãi đa dạng: giảm giá theo %, giảm giá cố định, mua N tặng M, gói bundle. Hệ thống tự động áp dụng tại POS và Sales Order.|
|**Business Goal**|Tăng doanh thu qua các chương trình khuyến mãi linh hoạt, thu hút khách hàng mới và tăng giá trị đơn hàng trung bình. Gắn với BO-03.|
|**FR References**|FR-076, FR-077, FR-078, FR-079, FR-080|
|**NFR References**|NFR-016|
|**User Roles**|Store Owner, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-15-01**|**Tạo chương trình khuyến mãi**|Tạo campaign: tên, mô tả, ngày bắt đầu/kết thúc, trạng thái Active/Inactive, ưu tiên áp dụng (priority order).|FR-076|**High**|FR-076|
|**F-15-02**|**Giảm giá theo phần trăm (% Discount)**|Giảm X% trên tổng đơn hoặc trên sản phẩm/danh mục được chọn.|FR-077|**High**|FR-077|
|**F-15-03**|**Giảm giá cố định (Fixed Amount Discount)**|Giảm N đồng trên tổng đơn khi đạt điều kiện tối thiểu (minimum order value).|FR-077|**High**|FR-077|
|**F-15-04**|**Mua N tặng M (Buy X Get Y)**|Mua đủ X sản phẩm/SKU được tặng thêm Y sản phẩm. Cấu hình sản phẩm mua và sản phẩm tặng.|FR-077|**High**|FR-077|
|**F-15-05**|**Giá gói (Bundle Pricing)**|Mua combo các sản phẩm được giá đặc biệt. Ví dụ: mua A+B+C giá 200k thay vì 250k.|FR-077|Medium|FR-077|
|**F-15-06**|**Phạm vi áp dụng khuyến mãi**|Scope theo: sản phẩm cụ thể, danh mục, thương hiệu, chi nhánh, nhóm khách hàng, ngày trong tuần/giờ trong ngày.|FR-078|Medium|FR-078|
|**F-15-07**|**Tự động áp khuyến mãi tại checkout**|Engine kiểm tra tất cả promotions hợp lệ khi checkout, tự động áp dụng ưu đãi tốt nhất hoặc tất cả ưu đãi có thể cộng gộp.|FR-079, BR-05|**High**|FR-079|
|**F-15-08**|**Quản lý xung đột khuyến mãi**|Cấu hình rule: exclusive (một ưu đãi), combinable (cộng gộp được). Hệ thống tự resolve conflict (FR-080).|FR-080|Medium|FR-080|
|**F-15-09**|**Voucher Code**|Tạo và phát hành mã voucher đơn/hàng loạt. Giới hạn số lần dùng, số lần/KH, thời hạn.|FR-076, FR-068|Medium|FR-076|
|**F-15-10**|**Báo cáo hiệu quả khuyến mãi**|Doanh thu, số đơn, discount value của từng campaign. So sánh hiệu quả giữa các kỳ.|FR-096|Medium|FR-096|
|**F-15-11**|**Lịch sử áp dụng khuyến mãi**|Tra cứu promotion nào đã được áp dụng vào đơn hàng cụ thể, giá trị tiết kiệm.|FR-079|Low|FR-079|


# **MOD-16 — Module: Loyalty Program**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-16|
|**Module Name**|**Loyalty Program**|
|**Description**|Chương trình khách hàng thân thiết: tích điểm tự động theo giá trị mua hàng, đổi điểm lấy ưu đãi, cấu hình tỷ lệ và quản lý vòng đời điểm thưởng.|
|**Business Goal**|Tăng tỷ lệ khách hàng quay lại (repeat purchase rate) tối thiểu 15% sau 6 tháng triển khai. Gắn với BO-03.|
|**FR References**|FR-081, FR-082, FR-083, FR-084|
|**NFR References**|NFR-020|
|**User Roles**|Store Owner, Cashier, Customer|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-16-01**|**Tự động tích điểm khi mua hàng**|Cộng điểm tự động khi đơn hàng chuyển sang Completed. Điểm hoàn trả nếu đơn bị hủy sau đó (BR-06).|FR-081, BR-06|Medium|FR-081|
|**F-16-02**|**Đổi điểm lấy ưu đãi**|Khách hàng hoặc Cashier nhập số điểm muốn đổi; hệ thống tính giá trị ưu đãi tương ứng và áp vào đơn hàng.|FR-082|Medium|FR-082|
|**F-16-03**|**Cấu hình tỷ lệ tích/đổi điểm**|Store Owner cấu hình: X VND = 1 điểm; 1 điểm = Y VND ưu đãi. Thay đổi chỉ áp dụng cho giao dịch sau ngày hiệu lực (BR-14).|FR-083, BR-14|Medium|FR-083|
|**F-16-04**|**Xem số dư và lịch sử điểm thưởng**|Hiển thị số điểm hiện tại, lịch sử cộng/trừ với lý do chi tiết (đơn hàng nào, campaign nào) trên hồ sơ khách hàng.|FR-084|Medium|FR-084|
|**F-16-05**|**Điểm thưởng theo sự kiện đặc biệt**|Cộng điểm bonus vào ngày sinh nhật, mua lần đầu, giới thiệu bạn bè hoặc theo campaign đặc biệt.|FR-083|Low|FR-083|
|**F-16-06**|**Hạng thành viên (Membership Tier)**|Phân hạng KH theo tổng điểm tích lũy: Bronze/Silver/Gold/Platinum với quyền lợi khác nhau.|FR-038, FR-083|Low|FR-038|
|**F-16-07**|**Thông báo điểm thưởng cho khách hàng**|Gửi SMS/email thông báo sau mỗi lần cộng/trừ điểm, nhắc nhở KH khi điểm sắp hết hạn.|FR-105|Low|FR-105|
|**F-16-08**|**Báo cáo chương trình loyalty**|Tổng điểm đã phát, điểm đã đổi, điểm còn tồn, chi phí ưu đãi loyalty theo kỳ.|FR-096|Low|FR-096|


# **MOD-17 — Module: Employee Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-17|
|**Module Name**|**Employee Management**|
|**Description**|Quản lý thông tin nhân viên, vị trí, chi nhánh làm việc, thông tin lương cơ bản và cấu hình hoa hồng bán hàng. Liên kết với tài khoản người dùng hệ thống.|
|**Business Goal**|Tập trung hóa thông tin nhân sự, theo dõi hiệu suất bán hàng và tính hoa hồng tự động, giảm tải công việc hành chính.|
|**FR References**|FR-085, FR-086, FR-087, FR-088|
|**NFR References**|NFR-006|
|**User Roles**|Store Owner, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-17-01**|**Tạo hồ sơ nhân viên**|Tạo với: họ tên, mã NV, CCCD/CMND, ngày sinh, giới tính, địa chỉ, email, SĐT, chức danh, ngày vào làm.|FR-085|Medium|FR-085|
|**F-17-02**|**Chỉnh sửa và vô hiệu hóa nhân viên**|Cập nhật thông tin. Soft-delete khi nhân viên nghỉ việc, giữ lại toàn bộ lịch sử bán hàng.|FR-085|Medium|FR-085|
|**F-17-03**|**Lưu trữ thông tin việc làm**|Chức danh/vị trí, chi nhánh làm việc, loại hợp đồng, mức lương cơ bản, ngày bắt đầu/kết thúc.|FR-086|Medium|FR-086|
|**F-17-04**|**Phân công chi nhánh cho nhân viên**|Gán nhân viên vào một hoặc nhiều chi nhánh. Liên kết với User Account hệ thống (1-1 mapping).|FR-087|Medium|FR-087|
|**F-17-05**|**Cấu hình và tính hoa hồng bán hàng**|Thiết lập rule hoa hồng: % trên doanh thu cá nhân, theo sản phẩm/danh mục, theo hạn mức đạt được. Tính hoa hồng tự động theo kỳ.|FR-088|Medium|FR-088|
|**F-17-06**|**Báo cáo hiệu suất bán hàng nhân viên**|Doanh thu, số đơn, giá trị trung bình đơn, hoa hồng ước tính theo nhân viên/chi nhánh/kỳ.|FR-088, FR-096|Medium|FR-088|
|**F-17-07**|**Hồ sơ nhân viên chi tiết (Profile)**|Xem toàn bộ thông tin cá nhân, lịch sử công việc, hiệu suất và chấm công tổng hợp.|FR-085|Low|FR-085|
|**F-17-08**|**Quản lý tài liệu nhân viên**|Upload và lưu trữ hợp đồng lao động, CCCD, bằng cấp... trên MinIO với phân quyền truy cập.|NFR-043|Low|NFR-043|


# **MOD-18 — Module: Attendance Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-18|
|**Module Name**|**Attendance Management**|
|**Description**|Quản lý chấm công nhân viên: ghi nhận giờ vào/ra, quản lý ca làm việc, phê duyệt công, xuất báo cáo chấm công phục vụ tính lương.|
|**Business Goal**|Tự động hóa chấm công, giảm gian lận giờ công và cung cấp dữ liệu chính xác cho tính lương hàng tháng.|
|**FR References**|FR-089, FR-090, FR-091|
|**NFR References**|NFR-003|
|**User Roles**|Store Owner, Branch Manager, Cashier, Inventory Staff|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-18-01**|**Check-in / Check-out ca làm việc**|Nhân viên bấm vào/ra ca trên hệ thống. Ghi nhận timestamp, thiết bị, IP. Hỗ trợ geolocation nếu cần.|FR-089|Medium|FR-089|
|**F-18-02**|**Cấu hình ca làm việc**|Định nghĩa các ca: tên ca, giờ bắt đầu/kết thúc, ngày áp dụng trong tuần, chi nhánh.|FR-089|Medium|FR-089|
|**F-18-03**|**Xem và chỉnh sửa bảng chấm công**|Manager xem bảng công của từng nhân viên theo tuần/tháng. Chỉnh sửa khi có sai sót kèm lý do.|FR-090|Medium|FR-090|
|**F-18-04**|**Phê duyệt công và nghỉ phép**|Nhân viên xin nghỉ/điều chỉnh công qua hệ thống; Manager phê duyệt hoặc từ chối. Thông báo kết quả.|FR-090|Medium|FR-090|
|**F-18-05**|**Báo cáo chấm công tổng hợp**|Báo cáo ngày công, giờ OT, giờ vắng có phép/không phép, muộn/về sớm theo nhân viên và chi nhánh.|FR-091|Medium|FR-091|
|**F-18-06**|**Xuất báo cáo chấm công cho tính lương**|Xuất file Excel chuẩn với ngày công thực tế phục vụ bộ phận lương hàng tháng.|FR-091|Medium|FR-091|
|**F-18-07**|**Cảnh báo bất thường chấm công**|Cảnh báo khi nhân viên không check-in trong ca đã đăng ký, check-in muộn hoặc quên check-out.|FR-104|Low|FR-104|


# **MOD-19 — Module: Debt Management (Quản lý công nợ)**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-19|
|**Module Name**|**Debt Management (Quản lý công nợ)**|
|**Description**|Theo dõi và quản lý công nợ phải thu (AR - Accounts Receivable từ khách hàng) và công nợ phải trả (AP - Accounts Payable đến nhà cung cấp). Báo cáo aging và cảnh báo đến hạn.|
|**Business Goal**|Đảm bảo 100% giao dịch có công nợ được theo dõi tự động, giảm nợ khó đòi và tối ưu dòng tiền. Gắn với BO-05.|
|**FR References**|FR-092, FR-093, FR-094, FR-095|
|**NFR References**|NFR-020, NFR-027|
|**User Roles**|Store Owner, Accountant, Branch Manager|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-19-01**|**Theo dõi công nợ phải thu (AR)**|AR tự động tăng khi tạo sales invoice; giảm khi ghi nhận payment. Hiển thị theo từng khách hàng và hóa đơn.|FR-092|**High**|FR-092|
|**F-19-02**|**Theo dõi công nợ phải trả (AP)**|AP tự động tăng khi tạo purchase invoice; giảm khi thanh toán nhà cung cấp. Hiển thị theo từng NCC.|FR-093|**High**|FR-093|
|**F-19-03**|**Báo cáo AR theo tuổi nợ (Aging)**|Phân loại AR theo: Current (chưa đến hạn), 1-30, 31-60, 61-90, >90 ngày quá hạn. Xuất Excel/PDF.|FR-094|Medium|FR-094|
|**F-19-04**|**Báo cáo AP theo tuổi nợ (Aging)**|Phân loại AP theo: Current, 1-30, 31-60, 61-90, >90 ngày quá hạn. Hỗ trợ lên kế hoạch chi tiền.|FR-094|Medium|FR-094|
|**F-19-05**|**Cảnh báo tự động nợ đến/quá hạn**|Gửi in-app notification và email cho Accountant/Branch Manager khi nợ sắp đến hạn (N ngày trước) và quá hạn.|FR-095, FR-104|Medium|FR-095|
|**F-19-06**|**Hạn mức tín dụng và kiểm soát bán hàng**|Hệ thống chặn tạo SO mới khi AR của KH vượt credit limit (BR-09). Alert đến BM để phê duyệt ngoại lệ.|BR-09|**High**|BR-09|
|**F-19-07**|**Ghi nhận và đối soát thanh toán công nợ**|Ghi payment cho từng hóa đơn công nợ. Hỗ trợ partial payment, advance payment (trả trước).|FR-071, FR-072|**High**|FR-071|
|**F-19-08**|**Báo cáo tổng hợp công nợ**|Dashboard công nợ: tổng AR, tổng AP, net position, top 10 khách nợ nhiều nhất, top 10 NCC cần trả.|FR-094, FR-101|Medium|FR-094|
|**F-19-09**|**Ghi chú và lịch sử theo dõi công nợ**|Ghi nhận kết quả liên hệ đòi nợ, hẹn ngày thanh toán, lý do chậm. Tạo timeline follow-up.|FR-092|Low|FR-092|


# **MOD-20 — Module: Reporting & Analytics**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-20|
|**Module Name**|**Reporting & Analytics**|
|**Description**|Hệ thống báo cáo đa chiều tổng hợp: doanh thu, tồn kho, lợi nhuận gộp, công nợ, nhân viên, khuyến mãi. Hỗ trợ lọc linh hoạt theo chi nhánh, thời gian, sản phẩm và xuất Excel/PDF.|
|**Business Goal**|Cung cấp dữ liệu chính xác, kịp thời để ban điều hành ra quyết định kinh doanh. Giảm 30% thời gian lập báo cáo. Gắn với BO-04, BO-08.|
|**FR References**|FR-096, FR-097, FR-098, FR-099, FR-100|
|**NFR References**|NFR-015, NFR-039, NFR-040|
|**User Roles**|Store Owner, Branch Manager, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-20-01**|**Báo cáo doanh thu bán hàng**|Tổng hợp doanh thu theo ngày/tuần/tháng/quý/năm. Lọc theo chi nhánh, nhân viên, sản phẩm, danh mục. So sánh kỳ trước.|FR-096|**High**|FR-096|
|**F-20-02**|**Báo cáo sản phẩm bán chạy / chậm**|Top sản phẩm theo số lượng và doanh thu. Sản phẩm tồn lâu, ít bán. Theo chi nhánh và kỳ.|FR-096|**High**|FR-096|
|**F-20-03**|**Báo cáo tồn kho**|Số lượng tồn kho hiện tại, biến động tồn kho trong kỳ (nhập/xuất/điều chỉnh), định giá tồn kho.|FR-097|**High**|FR-097|
|**F-20-04**|**Báo cáo lãi gộp (Gross Profit)**|Doanh thu - Giá vốn hàng bán (COGS) = Lãi gộp. Chi tiết theo sản phẩm, danh mục, chi nhánh.|FR-098|**High**|FR-098|
|**F-20-05**|**Báo cáo mua hàng**|Tổng giá trị nhập hàng theo nhà cung cấp, sản phẩm, chi nhánh. So sánh giá mua qua các kỳ.|FR-097|Medium|FR-097|
|**F-20-06**|**Báo cáo nhân viên bán hàng**|Doanh thu, số đơn, hoa hồng theo từng nhân viên. Xếp hạng theo hiệu suất.|FR-096|Medium|FR-096|
|**F-20-07**|**Báo cáo công nợ**|Tổng AR/AP, aging report, danh sách nợ quá hạn. Hỗ trợ đối soát công nợ cuối kỳ.|FR-094|Medium|FR-094|
|**F-20-08**|**Xuất báo cáo Excel / PDF**|Tất cả báo cáo đều có nút Export ra Excel (.xlsx) và PDF. Định dạng chuyên nghiệp theo template.|FR-099|Medium|FR-099|
|**F-20-09**|**Lập lịch gửi báo cáo tự động**|Cấu hình gửi báo cáo định kỳ qua email (hàng ngày, hàng tuần, hàng tháng) theo danh sách người nhận.|FR-100|Low|FR-100|
|**F-20-10**|**Báo cáo đa chi nhánh hợp nhất**|Tổng hợp và so sánh hiệu suất giữa các chi nhánh trên cùng một báo cáo. Gắn với MOD-23.|FR-109|**High**|FR-109|
|**F-20-11**|**Báo cáo khuyến mãi**|Hiệu quả từng campaign: số đơn áp dụng, giá trị discount, doanh thu từ khuyến mãi.|FR-096|Medium|FR-096|
|**F-20-12**|**Báo cáo khách hàng**|Phân tích KH mới, KH quay lại, giá trị vòng đời (LTV), phân nhóm RFM cơ bản.|FR-096|Low|FR-096|


# **MOD-21 — Module: Dashboard (Điều hành)**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-21|
|**Module Name**|**Dashboard (Điều hành)**|
|**Description**|Giao diện dashboard tổng quan theo thời gian thực với các KPI điều hành quan trọng, biểu đồ xu hướng, bộ lọc linh hoạt theo chi nhánh và thời gian.|
|**Business Goal**|Cung cấp cái nhìn tổng quan tức thì về tình hình kinh doanh trong vòng 3 giây, hỗ trợ ra quyết định nhanh. Gắn với BO-04.|
|**FR References**|FR-101, FR-102, FR-103|
|**NFR References**|NFR-015, NFR-016|
|**User Roles**|Store Owner, Branch Manager, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-21-01**|**Widget KPI chính (Key Metrics)**|Hiển thị thẻ KPI: Tổng doanh thu, Số đơn hàng, Lãi gộp, Khách hàng mới, So sánh cùng kỳ (%tăng/giảm).|FR-101|**High**|FR-101|
|**F-21-02**|**Biểu đồ doanh thu theo thời gian**|Line/Bar chart doanh thu theo ngày/tuần/tháng trong kỳ đã chọn. Có thể so sánh với kỳ trước.|FR-103|**High**|FR-103|
|**F-21-03**|**Bộ lọc chi nhánh và khoảng thời gian**|Dropdown chọn chi nhánh (tất cả hoặc một chi nhánh) và date range picker. Dashboard cập nhật real-time khi thay bộ lọc.|FR-102|**High**|FR-102|
|**F-21-04**|**Top sản phẩm bán chạy**|Bảng top 10 sản phẩm theo doanh thu và số lượng trong kỳ. Click để drill-down vào báo cáo chi tiết.|FR-101|**High**|FR-101|
|**F-21-05**|**Tồn kho cần chú ý**|Widget danh sách sản phẩm low-stock và sắp hết hàng. Link trực tiếp đến màn hình tạo PO.|FR-045, FR-101|Medium|FR-045|
|**F-21-06**|**Công nợ cần xử lý**|Widget tổng AR quá hạn, top 5 KH nợ nhiều nhất, số hóa đơn AP đến hạn tuần này.|FR-101, FR-094|Medium|FR-101|
|**F-21-07**|**Hoạt động gần đây (Activity Feed)**|Feed real-time các sự kiện quan trọng: đơn hàng mới, nhận hàng, cảnh báo tồn kho, thanh toán lớn.|FR-104|Medium|FR-104|
|**F-21-08**|**Dashboard tùy chỉnh (Customizable)**|Người dùng kéo-thả, ẩn/hiện, thay đổi kích cỡ widget theo sở thích. Lưu layout riêng cho từng user.|FR-101|Low|FR-101|
|**F-21-09**|**Biểu đồ phân tích chi nhánh (Multi-branch Overview)**|Bar chart so sánh doanh thu, số đơn, tăng trưởng của tất cả chi nhánh trên một biểu đồ.|FR-102, FR-109|**High**|FR-102|


# **MOD-22 — Module: Notification Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-22|
|**Module Name**|**Notification Management**|
|**Description**|Hệ thống thông báo đa kênh (in-app, email, SMS) cho các sự kiện quan trọng: cảnh báo tồn kho, trạng thái đơn hàng, đến hạn công nợ, phê duyệt chờ xử lý. Người dùng tự cấu hình kênh nhận.|
|**Business Goal**|Đảm bảo thông tin quan trọng đến đúng người đúng lúc, giảm thời gian phản ứng với sự cố và tăng hiệu quả vận hành.|
|**FR References**|FR-104, FR-105, FR-106|
|**NFR References**|NFR-021, NFR-040|
|**User Roles**|Store Owner, Branch Manager, Cashier, Inventory Staff, Accountant|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-22-01**|**Thông báo trong ứng dụng (In-App Notification)**|Bell icon với badge số lượng chưa đọc. Danh sách thông báo với trạng thái đọc/chưa đọc, nhấn để đến màn hình liên quan.|FR-104|Medium|FR-104|
|**F-22-02**|**Cảnh báo tồn kho thấp**|Thông báo tự động khi sản phẩm dưới reorder point, gửi đến Inventory Staff và Branch Manager.|FR-045, FR-104|**High**|FR-045|
|**F-22-03**|**Thông báo trạng thái đơn hàng**|Thông báo khi Sales Order thay đổi trạng thái quan trọng (Confirmed, Delivered, Cancelled).|FR-104, FR-061|Medium|FR-104|
|**F-22-04**|**Thông báo công nợ đến hạn**|Nhắc nhở tự động N ngày trước hạn thanh toán cho Accountant và Branch Manager theo cấu hình.|FR-095, FR-104|Medium|FR-095|
|**F-22-05**|**Thông báo phê duyệt chờ xử lý**|Thông báo cho người có quyền phê duyệt khi có PO, chuyển kho, điều chỉnh tồn kho cần duyệt.|FR-104, FR-054|Medium|FR-104|
|**F-22-06**|**Gửi email thông báo**|Gửi email HTML cho các cảnh báo quan trọng: low stock report, debt due, daily summary theo lịch.|FR-105|Medium|FR-105|
|**F-22-07**|**Gửi SMS thông báo**|Gửi SMS cho các alert khẩn cấp (tích hợp với nhà mạng/SMSGW). Template SMS cấu hình được.|FR-105|Low|FR-105|
|**F-22-08**|**Cấu hình thông báo cá nhân**|Mỗi người dùng chọn loại sự kiện muốn nhận và kênh nhận (in-app / email / SMS) trong phần cài đặt.|FR-106|Low|FR-106|
|**F-22-09**|**Xử lý thông báo bất đồng bộ qua Queue**|Thông báo được đẩy qua RabbitMQ để xử lý async, không ảnh hưởng đến luồng nghiệp vụ chính. Retry 3 lần (NFR-021).|NFR-021, NFR-040|Medium|NFR-021|
|**F-22-10**|**Lịch sử thông báo**|Lưu trữ 90 ngày lịch sử thông báo đã gửi, có thể tìm kiếm và lọc theo loại và khoảng thời gian.|FR-104|Low|FR-104|


# **MOD-23 — Module: Multi-Branch Management**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-23|
|**Module Name**|**Multi-Branch Management**|
|**Description**|Quản lý mạng lưới chi nhánh: tạo và cấu hình chi nhánh, chia sẻ hoặc tùy biến master data (sản phẩm, giá, khuyến mãi) theo chi nhánh, báo cáo hợp nhất toàn hệ thống.|
|**Business Goal**|Hỗ trợ mở rộng kinh doanh từ 1 lên 100 chi nhánh không cần thay đổi kiến trúc. Gắn với BO-06.|
|**FR References**|FR-107, FR-108, FR-109|
|**NFR References**|NFR-037, NFR-038, NFR-041, NFR-042|
|**User Roles**|Store Owner|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-23-01**|**Tạo và cấu hình chi nhánh**|Thêm chi nhánh: tên, mã chi nhánh, địa chỉ, SĐT, email, múi giờ, tiền tệ, múi giờ, ảnh. Tự động tạo kho mặc định (BR-17).|FR-107, BR-17|**High**|FR-107|
|**F-23-02**|**Vô hiệu hóa / tạm ngừng chi nhánh**|Vô hiệu hóa chi nhánh: người dùng chi nhánh đó không thể đăng nhập. Dữ liệu vẫn truy cập được.|FR-107|Medium|FR-107|
|**F-23-03**|**Chia sẻ master data toàn hệ thống**|Sản phẩm, danh mục, thương hiệu chia sẻ cho tất cả chi nhánh. Store Owner quyết định có cho phép chi nhánh tùy chỉnh không.|FR-108|**High**|FR-108|
|**F-23-04**|**Tùy chỉnh giá theo chi nhánh**|Mỗi chi nhánh có thể có bảng giá bán riêng (override giá toàn hệ thống) nếu được cấp quyền.|FR-108|Medium|FR-108|
|**F-23-05**|**Tùy chỉnh khuyến mãi theo chi nhánh**|Campaign khuyến mãi có thể áp dụng toàn hệ thống hoặc chỉ cho chi nhánh được chọn.|FR-108, FR-078|Medium|FR-108|
|**F-23-06**|**Dashboard hợp nhất toàn hệ thống**|Store Owner xem dashboard với dữ liệu hợp nhất từ tất cả chi nhánh, cập nhật trong vòng 3 giây.|FR-109, FR-101|**High**|FR-109|
|**F-23-07**|**Báo cáo hợp nhất đa chi nhánh**|Các báo cáo tài chính/tồn kho/nhân viên có thể chọn xem cho 1 chi nhánh hoặc tổng hợp tất cả.|FR-109|**High**|FR-109|
|**F-23-08**|**Phân quyền theo chi nhánh**|Branch Manager chỉ thấy và quản lý dữ liệu chi nhánh của mình. Store Owner thấy tất cả.|FR-014, BR-07|**High**|FR-014|
|**F-23-09**|**Cấu hình riêng theo chi nhánh**|Mỗi chi nhánh có thể cấu hình: template hóa đơn, thiết bị in, ngưỡng cảnh báo tồn kho riêng.|FR-113|Medium|FR-113|
|**F-23-10**|**Đồng bộ hóa dữ liệu real-time**|Mọi thay đổi master data từ Store Owner được đồng bộ ngay đến tất cả chi nhánh qua RabbitMQ.|NFR-040, NFR-037|**High**|NFR-040|


# **MOD-24 — Module: Audit Log**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-24|
|**Module Name**|**Audit Log**|
|**Description**|Ghi nhận bất biến mọi thao tác tạo/sửa/xóa trên dữ liệu nghiệp vụ quan trọng: người thực hiện, thời gian, giá trị trước/sau thay đổi. Hỗ trợ tra cứu, lọc và xuất log.|
|**Business Goal**|Đảm bảo tính minh bạch, truy vết và không thể phủ nhận (non-repudiation) của mọi thao tác hệ thống. Gắn với BO-07.|
|**FR References**|FR-110, FR-111|
|**NFR References**|NFR-003, NFR-050|
|**User Roles**|Store Owner|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-24-01**|**Tự động ghi log thao tác**|Intercept mọi CRUD operation qua AOP/filter. Ghi: user\_id, timestamp, module, action, entity\_id, old\_value (JSON), new\_value (JSON), IP.|FR-110|**High**|FR-110|
|**F-24-02**|**Audit Log bất biến**|Không user nào (kể cả Store Owner) có thể sửa hoặc xóa audit log (BR-15). Stored separately with write-only access.|FR-110, BR-15|**High**|FR-110|
|**F-24-03**|**Tìm kiếm và lọc audit log**|Lọc theo: người dùng, module, loại action (CREATE/UPDATE/DELETE), khoảng thời gian, entity ID. Kết quả phân trang.|FR-111|Medium|FR-111|
|**F-24-04**|**Xem chi tiết thay đổi (Diff View)**|So sánh giá trị trước và sau thay đổi theo dạng diff trực quan, highlight các trường bị thay đổi.|FR-111|Medium|FR-111|
|**F-24-05**|**Export audit log**|Xuất audit log đã lọc ra Excel hoặc CSV cho mục đích kiểm toán nội bộ hoặc pháp lý.|FR-111|Medium|FR-111|
|**F-24-06**|**Retention Policy**|Cấu hình thời hạn lưu trữ audit log (mặc định 2 năm). Tự động archive log cũ sang cold storage.|FR-114|Low|FR-114|
|**F-24-07**|**Cảnh báo hoạt động bất thường**|Alert admin khi phát hiện pattern bất thường: nhiều lần đăng nhập thất bại, thay đổi quyền hàng loạt.|NFR-005, FR-110|Medium|NFR-005|
|**F-24-08**|**Log truy cập API (Access Log)**|Ghi log mọi API request: endpoint, method, user, IP, response code, latency. Hỗ trợ debug và security audit.|NFR-050|Medium|NFR-050|


# **MOD-25 — Module: System Settings**

## **1. Module Overview**

|**Field**|**Detail**|
| :-: | :-: |
|**Module ID**|MOD-25|
|**Module Name**|**System Settings**|
|**Description**|Cấu hình toàn bộ tham số hệ thống và từng chi nhánh: tiền tệ, thuế, múi giờ, mẫu hóa đơn, thiết bị kết nối, chính sách backup dữ liệu và feature toggles.|
|**Business Goal**|Cho phép doanh nghiệp tùy chỉnh hệ thống theo đặc thù của mình mà không cần sự can thiệp của đội phát triển.|
|**FR References**|FR-112, FR-113, FR-114|
|**NFR References**|NFR-044, NFR-046, NFR-052, NFR-054|
|**User Roles**|Store Owner, Branch Manager (branch settings only)|

## **2. Features Detail**

|**Feature ID**|**Feature Name**|**Description**|**Business Behaviour / Rule**|**Priority**|**FR Ref**|
| :-: | :-: | :-: | :-: | :-: | :-: |
|**F-25-01**|**Cấu hình hệ thống toàn cục**|Tiền tệ mặc định, múi giờ, ngôn ngữ, định dạng ngày/số, mã quốc gia. Áp dụng cho toàn bộ hệ thống.|FR-112|Medium|FR-112|
|**F-25-02**|**Cấu hình thuế (Tax)**|Tạo các mức thuế suất (VAT 0%, 5%, 8%, 10%). Gán mặc định theo sản phẩm hoặc danh mục.|FR-112|Medium|FR-112|
|**F-25-03**|**Cấu hình thông tin doanh nghiệp**|Tên công ty, địa chỉ, MST, logo, thông tin liên hệ. Hiển thị trên hóa đơn và báo cáo.|FR-112|Medium|FR-112|
|**F-25-04**|**Cấu hình mẫu hóa đơn / phiếu thu**|Tùy chỉnh template hóa đơn nhiệt và hóa đơn PDF: logo, thông tin, màu sắc, font. Riêng cho từng chi nhánh.|FR-113|Medium|FR-113|
|**F-25-05**|**Cấu hình thiết bị kết nối (POS Devices)**|Quản lý danh sách máy in nhiệt, đầu đọc barcode kết nối theo chi nhánh. Test kết nối thiết bị.|FR-113|Medium|FR-113|
|**F-25-06**|**Cấu hình backup tự động**|Cấu hình lịch backup: tần suất, thời điểm, số bản lưu tối đa, vị trí lưu (S3/MinIO). Hiển thị lịch sử backup.|FR-114, NFR-022|Medium|FR-114|
|**F-25-07**|**Feature Toggles**|Bật/tắt tính năng thực nghiệm hoặc tùy chọn mà không cần redeploy: loyalty program, 2FA bắt buộc, offline POS...|NFR-054|Low|NFR-054|
|**F-25-08**|**Cấu hình chính sách bảo mật**|Thời gian timeout session, số lần đăng nhập sai cho phép, độ phức tạp mật khẩu, yêu cầu 2FA theo vai trò.|FR-003, FR-005, NFR-008|**High**|FR-003|
|**F-25-09**|**Cài đặt thông báo hệ thống**|Cấu hình email server (SMTP), SMS gateway, template nội dung notification. Test gửi thử.|FR-105, FR-106|Medium|FR-105|
|**F-25-10**|**Nhật ký hệ thống (System Log Viewer)**|Xem log ứng dụng theo mức độ (ERROR/WARN/INFO) trong giao diện admin. Lọc theo thời gian và module.|NFR-050|Low|NFR-050|
|**F-25-11**|**Quản lý số hóa đơn tự động**|Cấu hình prefix, định dạng, sequence cho số hóa đơn theo chi nhánh và năm tài chính.|FR-063, FR-113|Medium|FR-063|


# **4. Feature Priority Summary**
Bảng tổng hợp số lượng features theo mức độ ưu tiên giúp Product Owner lập kế hoạch Sprint và phân bổ nguồn lực.

|**HIGH Priority**|**MEDIUM Priority**|**LOW Priority**|
| :-: | :-: | :-: |
|**99**|**106**|**39**|
|Must-have for Sprint 1-3|Sprint 3-5 implementation|Post-MVP / Enhancement|


# **5. Module ↔ FR Traceability Matrix**
Bảng truy vết xác nhận tính nhất quán giữa tài liệu này và 01\_Software\_Requirement\_Specification.docx.

|**Module ID**|**Module Name**|**FR References**|**NFR References**|**Business Rules**|
| :-: | :-: | :-: | :-: | :-: |
|**MOD-01**|Authentication|FR-001, FR-002, FR-003, FR-004, FR-005|NFR-001, NFR-002, NFR-004|—|
|**MOD-02**|User Management|FR-006, FR-007, FR-008, FR-009, FR-010|NFR-003, NFR-006, NFR-007|—|
|**MOD-03**|Role & Permission Management|FR-011, FR-012, FR-013, FR-014, FR-015|NFR-003|BR-13|
|**MOD-04**|Product Management|FR-016, FR-017, FR-018, FR-019, FR-020, FR-021, FR-022|NFR-014, NFR-016, NFR-019|BR-01, BR-02, BR-10|
|**MOD-05**|Category Management|FR-023, FR-024, FR-025, FR-026|NFR-016|BR-01|
|**MOD-06**|Brand Management|FR-027, FR-028, FR-029|NFR-016|—|
|**MOD-07**|Supplier Management|FR-030, FR-031, FR-032, FR-033, FR-034|NFR-020|BR-07|
|**MOD-08**|Customer Management|FR-035, FR-036, FR-037, FR-038, FR-039, FR-040|NFR-006, NFR-007|BR-07, BR-09|
|**MOD-09**|Inventory Management|FR-041, FR-042, FR-043, FR-044, FR-045, FR-046, FR-047|NFR-020, NFR-024, NFR-025|BR-08, BR-11|
|**MOD-10**|Warehouse Management|FR-048, FR-049, FR-050, FR-051|NFR-020|BR-17|
|**MOD-11**|Purchase Order (Đơn đặt hàng nhập)|FR-052, FR-053, FR-054, FR-055, FR-056, FR-057|NFR-020, NFR-027|BR-16|
|**MOD-12**|Sales Order (Đơn hàng bán)|FR-058, FR-059, FR-060, FR-061, FR-062, FR-063|NFR-020, NFR-027|BR-03|
|**MOD-13**|POS (Point of Sale - Bán hàng tại quầy)|FR-064, FR-065, FR-066, FR-067, FR-068, FR-069, FR-070|NFR-012, NFR-018, NFR-025|BR-05|
|**MOD-14**|Payment Management|FR-071, FR-072, FR-073, FR-074, FR-075|NFR-020, NFR-027|BR-04|
|**MOD-15**|Promotion Management|FR-076, FR-077, FR-078, FR-079, FR-080|NFR-016|BR-05|
|**MOD-16**|Loyalty Program|FR-081, FR-082, FR-083, FR-084|NFR-020|BR-06, BR-14|
|**MOD-17**|Employee Management|FR-085, FR-086, FR-087, FR-088|NFR-006|—|
|**MOD-18**|Attendance Management|FR-089, FR-090, FR-091|NFR-003|—|
|**MOD-19**|Debt Management (Quản lý công nợ)|FR-092, FR-093, FR-094, FR-095|NFR-020, NFR-027|BR-09|
|**MOD-20**|Reporting & Analytics|FR-096, FR-097, FR-098, FR-099, FR-100|NFR-015, NFR-039, NFR-040|—|
|**MOD-21**|Dashboard (Điều hành)|FR-101, FR-102, FR-103|NFR-015, NFR-016|—|
|**MOD-22**|Notification Management|FR-104, FR-105, FR-106|NFR-021, NFR-040|—|
|**MOD-23**|Multi-Branch Management|FR-107, FR-108, FR-109|NFR-037, NFR-038, NFR-041|BR-07, BR-17|
|**MOD-24**|Audit Log|FR-110, FR-111|NFR-003, NFR-050|BR-15|
|**MOD-25**|System Settings|FR-112, FR-113, FR-114|NFR-044, NFR-046, NFR-052|—|

**— END OF DOCUMENT —**

Total Modules: 25   |   Total Features: 244   |   High: 99   |   Medium: 106   |   Low: 39
Page  of    |   © 2025 Sales Management Platform Project
